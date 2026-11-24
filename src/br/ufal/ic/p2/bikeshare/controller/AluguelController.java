package br.ufal.ic.p2.bikeshare.controller;

import br.ufal.ic.p2.bikeshare.controller.Data.DataSystem;
import br.ufal.ic.p2.bikeshare.exeption.negocio.*;
import br.ufal.ic.p2.bikeshare.exeption.validacao.nulos.*;
import br.ufal.ic.p2.bikeshare.exeption.validacao.tipos.AtributoNaoExisteException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.tipos.DataInvalidaException;
import br.ufal.ic.p2.bikeshare.models.Alugueis.Aluguel;
import br.ufal.ic.p2.bikeshare.models.Bikes.Bike;
import br.ufal.ic.p2.bikeshare.models.Users.User;
import br.ufal.ic.p2.bikeshare.models.stations.Station;
import br.ufal.ic.p2.bikeshare.utils.ValidadorUtils;

import java.util.Locale;

public class AluguelController {
    private DataSystem data;

    public AluguelController(DataSystem data){
        this.data = data;
    }

    public String retirarBicicleta(String susuario, String sestacao, String sbicicleta, String sdataAluguel) throws UsuarioNaoExisteException, IdNaoNuloException, EstacaoNaoExisteException, IdEstacaoNulaException, BicicletaNaoExisteException, IdBicicletaNulaException, DataNaoNulaException, DataInvalidaException, AluguelAtivoException, BicicletaNaoEstacaoException, BateriaInsuficienteException, SaldoInsuficienteException, SemMetodoPagamentoException, UsuarioInadimplenteException {
        User usuario = data.obterUsuario(susuario);
        Station estacao = data.obterEstacao(sestacao);
        Bike bicicleta = data.obterBike(sbicicleta);

        if (bicicleta == null) {
            throw new BicicletaNaoExisteException();
        }

        String dataAluguel = ValidadorUtils.validarData(sdataAluguel, DataNaoNulaException::new, DataInvalidaException::new);

        if (usuario.getAluguelAtivo() != null && !usuario.getAluguelAtivo().equals("nenhum")) {
            throw new AluguelAtivoException();
        }

        if(usuario.getEstado().equals("inadimplente")){
            throw new UsuarioInadimplenteException();
        }

        if (!sestacao.equals(bicicleta.getEstacao())) {
            throw new BicicletaNaoEstacaoException();
        }

        bicicleta.validarRetirada();
        usuario.getMetodoPagamento().validarParaAluguel();

        Aluguel novo = new Aluguel(susuario, sbicicleta, sestacao, dataAluguel);
        String id = data.addAluguel(novo);

        estacao.removerBike(bicicleta);
        bicicleta.setEstado("emUso");
        bicicleta.setEstacao("nenhuma");
        usuario.setAluguelAtivo(id);

        return id;
    }

    public void devolverBicicleta(String saluguel, String sestacao, String sdataDevolucao) throws IdAluguelNaoNulaException, AluguelNaoExisteException, EstacaoNaoExisteException, IdEstacaoNulaException, DataNaoNulaException, DataInvalidaException, AluguelNaoAndamentoException, EstacaoSemVagaException, EstacaoCheiaException, DataAnteriorARetiradaException, IdBicicletaNulaException, UsuarioNaoExisteException, IdNaoNuloException, SaldoInsuficienteException, NaoSaldoException {
        Aluguel aluguel = data.obterAluguel(saluguel);
        Station estacao = data.obterEstacao(sestacao);
        String dataFim = ValidadorUtils.validarData(sdataDevolucao,DataNaoNulaException::new, DataInvalidaException::new);

        if(!aluguel.getEstado().equals("emAndamento")){
            throw new AluguelNaoAndamentoException();
        }

        if(estacao.calcularVagasDisponiveis() <= 0){
            throw new EstacaoCheiaException();
        }

        Bike bicicleta = data.obterBike(aluguel.getBicicleta());
        User usuario = data.obterUsuario(aluguel.getUsuario());


        long minutos = ValidadorUtils.calcularMinutos(aluguel.getInicio(), dataFim, DataAnteriorARetiradaException::new);

        if(minutos >1440){
            aluguel.setEstado("expirado");
            aluguel.setValorCobrado(0.0);
            aluguel.setFim(dataFim);
            aluguel.setEstacaoDestino(sestacao);

            bicicleta.setEstado("emManutencao");
            bicicleta.setEstacao(sestacao);
            estacao.addBike(bicicleta);

            usuario.setEstado("inadimplente");
            usuario.setTotalPendencias(usuario.getTotalPendencias() + 10);
            usuario.setAluguelAtivo(null);
        } else if (aluguel.getEstacaoOrigem().equals(sestacao) && minutos <= 2) {
            aluguel.setEstado("cancelado");
            aluguel.setValorCobrado(0.0);
            aluguel.setFim(dataFim);
            aluguel.setEstacaoDestino(sestacao);

            bicicleta.setEstado("disponivel");
            bicicleta.setEstacao(sestacao);
            estacao.addBike(bicicleta);

            usuario.setAluguelAtivo(null);
        }else {
            double valor = 0.0;

            if (usuario.getPlano().equals("avulso")) {
                valor = minutos * 0.50;
            } else if (usuario.getPlano().equals("assinante")) {
                long excedente = Math.max(0, minutos - 60);
                valor = excedente * 0.30;
            }

            valor += bicicleta.calcularAdicional(minutos);

            if(usuario.getMetodoPagamento().getTipo().equals("saldoPreCarregado")){
                double saldo = usuario.getSaldo();
                if(saldo < valor){
                    throw new SaldoInsuficienteException();
                } else {
                    saldo -= valor;
                    usuario.setSaldo(saldo);
                    usuario.getMetodoPagamento().attSaldo(saldo);
                }
            }

            aluguel.setEstado("finalizado");
            aluguel.setFim(dataFim);
            aluguel.setValorCobrado(valor);
            aluguel.setEstacaoDestino(sestacao);

            bicicleta.setEstado("disponivel");
            estacao.addBike(bicicleta);

            bicicleta.setEstacao(sestacao);

            usuario.setAluguelAtivo(null);
        }


    }

    public String getAtributoAluguel(String aluguel, String atributo) throws AtributoInvalidoException, IdAluguelNaoNulaException, AluguelNaoExisteException, AtributoNaoExisteException, AluguelAtivoException, AluguelEmAndamentoException {
        ValidadorUtils.validaString(aluguel, IdAluguelNaoNulaException::new);
        ValidadorUtils.validaString(atributo, AtributoNaoExisteException::new);

        Aluguel consulta = data.obterAluguel(aluguel);

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
                if (consulta.getEstado().equals("emAndamento")) {
                    throw new AluguelEmAndamentoException();
                }
                return consulta.getFim();
            case "estacaoDestino":
                if (consulta.getEstado().equals("emAndamento")) {
                    throw new AluguelEmAndamentoException();
                }
                return consulta.getEstacaoDestino();
            case "valorCobrado":
                if (consulta.getEstado().equals("emAndamento")) {
                    throw new AluguelEmAndamentoException();
                }
                return String.format(new Locale("pt", "BR"), "%.2f", consulta.getValorCobrado());
            default:
                throw new AtributoNaoExisteException();
        }
    }

}
