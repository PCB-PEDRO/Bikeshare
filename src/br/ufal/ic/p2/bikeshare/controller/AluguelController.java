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

public class AluguelController {
    private DataSystem data;

    public AluguelController(DataSystem data){
        this.data = data;
    }

    public String retirarBicicleta(String susuario, String sestacao, String sbicicleta, String sdataAluguel) throws UsuarioNaoExisteException, IdNaoNuloException, EstacaoNaoExisteException, IdEstacaoNulaException, BicicletaNaoExisteException, IdBicicletaNulaException, DataNaoNulaException, DataInvalidaException, AluguelAtivoException, BicicletaNaoEstacaoException, BateriaInsuficienteException, SaldoInsuficienteException, SemMetodoPagamentoException {
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
                return String.valueOf(consulta.getValorCobrado());
            default:
                throw new AtributoNaoExisteException();
        }
    }

}
