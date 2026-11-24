package br.ufal.ic.p2.bikeshare.controller;

import br.ufal.ic.p2.bikeshare.controller.Data.DataSystem;
import br.ufal.ic.p2.bikeshare.exception.BikeshareException;
import br.ufal.ic.p2.bikeshare.exception.negocio.*;
import br.ufal.ic.p2.bikeshare.exception.validacao.nulos.*;
import br.ufal.ic.p2.bikeshare.exception.validacao.tipos.AtributoNaoExisteException;
import br.ufal.ic.p2.bikeshare.exception.validacao.tipos.DataInvalidaException;
import br.ufal.ic.p2.bikeshare.models.Alugueis.Aluguel;
import br.ufal.ic.p2.bikeshare.models.Bikes.Bike;
import br.ufal.ic.p2.bikeshare.models.Users.User;
import br.ufal.ic.p2.bikeshare.models.stations.Station;
import br.ufal.ic.p2.bikeshare.utils.ValidadorUtils;

public class AluguelController {
    private static final String ESTADO_EM_ANDAMENTO = "emAndamento";
    private static final String ESTADO_FINALIZADO = "finalizado";
    private static final String ESTADO_CANCELADO = "cancelado";
    private static final String ESTADO_EXPIRADO = "expirado";
    private static final String BIKE_DISPONIVEL = "disponivel";
    private static final String BIKE_EM_USO = "emUso";
    private static final String BIKE_EM_MANUTENCAO = "emManutencao";
    private static final String ESTACAO_NENHUMA = "nenhuma";
    private static final String USUARIO_INADIMPLENTE = "inadimplente";
    private static final String ALUGUEL_NENHUM = "nenhum";

    private static final long MINUTOS_LIMITE_EXPIRACAO = 1440L;
    private static final long MINUTOS_CARENCIA_CANCELAMENTO = 2L;
    private static final double MULTA_ATRASO_EXPIRACAO = 10.0;

    private DataSystem data;

    public AluguelController(DataSystem data) {
        this.data = data;
    }

    public String retirarBicicleta(String susuario, String sestacao, String sbicicleta, String sdataAluguel) throws BikeshareException {
        User usuario = data.obterUsuario(susuario);
        Station estacao = data.obterEstacao(sestacao);
        Bike bicicleta = data.obterBike(sbicicleta);
        String dataAluguel = ValidadorUtils.validarData(sdataAluguel, DataNaoNulaException::new, DataInvalidaException::new);

        if (USUARIO_INADIMPLENTE.equals(usuario.getEstado())) {
            throw new UsuarioInadimplenteException();
        }

        if (usuario.getAluguelAtivo() != null && !ALUGUEL_NENHUM.equals(usuario.getAluguelAtivo())) {
            throw new AluguelAtivoException();
        }

        if (!sestacao.equals(bicicleta.getEstacao()) || !estacao.contemBicicleta(bicicleta)) {
            throw new BicicletaNaoEstacaoException();
        }

        bicicleta.validarRetirada();
        usuario.validarPagamentoParaAluguel();

        estacao.removerBike(bicicleta);
        Aluguel novo = new Aluguel(susuario, sbicicleta, sestacao, dataAluguel);
        String id = data.addAluguel(novo);

        bicicleta.setEstado(BIKE_EM_USO);
        bicicleta.setEstacao(ESTACAO_NENHUMA);
        usuario.setAluguelAtivo(id);

        return id;
    }

    public void devolverBicicleta(String saluguel, String sestacao, String sdataDevolucao) throws BikeshareException {
        Aluguel aluguel = data.obterAluguel(saluguel);
        Station estacao = data.obterEstacao(sestacao);
        String dataFim = ValidadorUtils.validarData(sdataDevolucao, DataNaoNulaException::new, DataInvalidaException::new);

        if (!ESTADO_EM_ANDAMENTO.equals(aluguel.getEstado())) {
            throw new AluguelNaoAndamentoException();
        }

        if (estacao.calcularVagasDisponiveis() <= 0) {
            throw new EstacaoCheiaException();
        }

        Bike bicicleta = data.obterBike(aluguel.getBicicleta());
        User usuario = data.obterUsuario(aluguel.getUsuario());

        long minutos = ValidadorUtils.calcularMinutos(aluguel.getInicio(), dataFim, DataAnteriorARetiradaException::new);

        if (minutos > MINUTOS_LIMITE_EXPIRACAO) {
            concluirDevolucao(aluguel, estacao, sestacao, bicicleta, usuario, dataFim, ESTADO_EXPIRADO, BIKE_EM_MANUTENCAO, 0.0);
            usuario.setEstado(USUARIO_INADIMPLENTE);
            usuario.setTotalPendencias(usuario.getTotalPendencias() + MULTA_ATRASO_EXPIRACAO);
        } else if (sestacao.equals(aluguel.getEstacaoOrigem()) && minutos <= MINUTOS_CARENCIA_CANCELAMENTO) {
            concluirDevolucao(aluguel, estacao, sestacao, bicicleta, usuario, dataFim, ESTADO_CANCELADO, BIKE_DISPONIVEL, 0.0);
        } else {
            double valor = usuario.calcularTarifaPlano(minutos) + bicicleta.calcularAdicional(minutos);
            usuario.cobrarAluguel(valor);
            concluirDevolucao(aluguel, estacao, sestacao, bicicleta, usuario, dataFim, ESTADO_FINALIZADO, BIKE_DISPONIVEL, valor);
        }
    }

    public String getAtributoAluguel(String aluguel, String atributo) throws BikeshareException {
        Aluguel consulta = data.obterAluguel(aluguel);
        ValidadorUtils.validaString(atributo, AtributoNaoExisteException::new);

        switch (atributo) {
            case "estado":
                return consulta.getEstado();
            case "usuario":
                return consulta.getUsuario();
            case "bicicleta":
                return consulta.getBicicleta();
            case "estacaoOrigem":
                return consulta.getEstacaoOrigem();
            case "inicio":
                return consulta.getInicio();
            case "fim":
                exigirAluguelEncerrado(consulta);
                return consulta.getFim();
            case "estacaoDestino":
                exigirAluguelEncerrado(consulta);
                return consulta.getEstacaoDestino();
            case "valorCobrado":
                exigirAluguelEncerrado(consulta);
                return ValidadorUtils.formatarMoeda(consulta.getValorCobrado());
            default:
                throw new AtributoNaoExisteException();
        }
    }

    private void concluirDevolucao(Aluguel aluguel, Station estacao, String idEstacaoDestino, Bike bicicleta,
                                   User usuario, String dataFim, String estadoAluguel, String estadoBicicleta,
                                   double valorCobrado) throws EstacaoSemVagaException {
        estacao.addBike(bicicleta);
        bicicleta.setEstado(estadoBicicleta);
        bicicleta.setEstacao(idEstacaoDestino);

        aluguel.setEstado(estadoAluguel);
        aluguel.setFim(dataFim);
        aluguel.setValorCobrado(valorCobrado);
        aluguel.setEstacaoDestino(idEstacaoDestino);

        usuario.setAluguelAtivo(null);
    }

    private void exigirAluguelEncerrado(Aluguel aluguel) throws AluguelEmAndamentoException {
        if (ESTADO_EM_ANDAMENTO.equals(aluguel.getEstado())) {
            throw new AluguelEmAndamentoException();
        }
    }
}
