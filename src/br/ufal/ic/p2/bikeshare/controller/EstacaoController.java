package br.ufal.ic.p2.bikeshare.controller;

import br.ufal.ic.p2.bikeshare.controller.Data.DataSystem;
import br.ufal.ic.p2.bikeshare.exeption.negocio.*;
import br.ufal.ic.p2.bikeshare.exeption.validacao.nulos.*;
import br.ufal.ic.p2.bikeshare.exeption.validacao.numericos.Bateria0a100Exception;
import br.ufal.ic.p2.bikeshare.exeption.validacao.numericos.BateriaNaoNumeroException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.numericos.CapacidadeNaoNegativaException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.numericos.CapacidadeNaoNumeroException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.tipos.AtributoNaoExisteException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.tipos.TipoInvalidoException;
import br.ufal.ic.p2.bikeshare.models.Bikes.BIkeEletric;
import br.ufal.ic.p2.bikeshare.models.Bikes.Bike;
import br.ufal.ic.p2.bikeshare.models.Bikes.BikeNorm;
import br.ufal.ic.p2.bikeshare.models.stations.Station;
import br.ufal.ic.p2.bikeshare.utils.ValidadorUtils;

public class EstacaoController {
    private DataSystem data;

    public EstacaoController (DataSystem data){
        this.data = data;
    }

    public String criarEstacao(String nome, String endereco, String scapacidade) throws NomeNaoNuloException, CapacidadeNaoNumeroException, CapacidadeNaoNegativaException, CapacidadeNaoNulaException, EnderecoNaoNuloException {
        int capacidade = validarDadosEstacao( nome, endereco, scapacidade);
        Station nova = new Station(nome, endereco, capacidade);
        String id = data.addStation(nova);

        return id;
    }

    public String obterVagasDisponiveis(String idE) throws EstacaoNaoExisteException, IdEstacaoNulaException {
        Station consulta = data.obterEstacao(idE);

        int vagas = consulta.calcularVagasDisponiveis();

        return String.valueOf(vagas);
    }

    public String getBicicletasDisponiveis(String idE) throws EstacaoNaoExisteException, IdEstacaoNulaException {
        Station consulta = data.obterEstacao(idE);

        int bikes = consulta.calcularBicicletasDisponiveis();

        return String.valueOf(bikes);
    }

    public void adicionarBicicleta(String sestacao, String bicicleta, String tipo, String sbateria) throws EstacaoNaoExisteException, IdEstacaoNulaException, IdBicicletaNulaException, BicicletaJaExisteException, BicicletaSemBateriaException, BateriaNulaException, Bateria0a100Exception, BateriaNaoNumeroException, TipoInvalidoException, EstacaoSemVagaException {
        Station estacao = data.obterEstacao(sestacao);

        ValidadorUtils.validaString(bicicleta, IdBicicletaNulaException::new);
        if (data.obterBike(bicicleta) != null) {
            throw new BicicletaJaExisteException();
        }

        Bike nova;
        if (tipo.equals("convencional")) {
            if (sbateria != null) {
                throw new BicicletaSemBateriaException();
            }
            nova = new BikeNorm(tipo, sestacao);
        } else if (tipo.equals("eletrica")) {
            int bateria = validarBateria(sbateria);
            nova = new BIkeEletric(tipo, sestacao, bateria);
        } else {
            throw new TipoInvalidoException();
        }

        estacao.addBike(nova);
        data.addBike(nova, bicicleta);
    }

    public void removerBicicleta(String sestacao, String sbicicleta) throws EstacaoNaoExisteException, IdEstacaoNulaException, IdBicicletaNulaException, BicicletaNaoExisteException, BicicletaNaoEstacaoException {
        Station estacao = data.obterEstacao(sestacao);
        ValidadorUtils.validaString(sbicicleta, IdBicicletaNulaException::new);

        Bike bicicleta = data.obterBike(sbicicleta);

        if(bicicleta == null){
            throw new BicicletaNaoExisteException();
        }else {
            estacao.removerBike(bicicleta);
            data.removerBike(sbicicleta);
        }
    }

    public String getEstadoBicicleta(String bicicleta) throws IdBicicletaNulaException, BicicletaNaoExisteException {
        ValidadorUtils.validaString(bicicleta, IdBicicletaNulaException::new);
        Bike consulta = data.obterBike(bicicleta);
        if(consulta == null){
            throw new BicicletaNaoExisteException();
        }else {
            return consulta.getEstado();
        }
    }

    public String getAtributoBicicleta(String bicicleta, String atributo) throws IdBicicletaNulaException, AtributoInvalidoException, BicicletaSemBateriaException, AtributoNaoAlteraException, BicicletaNaoExisteException, AtributoNaoExisteException {
        ValidadorUtils.validaString(bicicleta, IdBicicletaNulaException::new);
        ValidadorUtils.validaString(atributo, AtributoNaoExisteException::new);

        Bike consulta = data.obterBike(bicicleta);
        if(consulta == null){
            throw new BicicletaNaoExisteException();
        }

        switch(atributo){
            case "tipo":
                return consulta.getTipo();
            case "estado":
                return consulta.getEstado();
            case"estacao":
                return consulta.getEstacao();
            case "nivelBateria":
                return String.valueOf(consulta.obterNivelBateria());
            default:
                throw new AtributoNaoExisteException();
        }
    }


    private int validarDadosEstacao(String nome, String endereco, String capacidade) throws EnderecoNaoNuloException, NomeNaoNuloException, CapacidadeNaoNumeroException, CapacidadeNaoNegativaException, CapacidadeNaoNulaException {
        ValidadorUtils.validaString(nome, NomeNaoNuloException::new);
        ValidadorUtils.validaString(endereco, EnderecoNaoNuloException::new);
        int num = ValidadorUtils.conversaoIntPositivo(capacidade, CapacidadeNaoNumeroException::new, CapacidadeNaoNegativaException::new, CapacidadeNaoNulaException::new);

        return num;
    }

    private int validarBateria(String bateria) throws BateriaNulaException, Bateria0a100Exception, BateriaNaoNumeroException {
        int num = ValidadorUtils.conversaoInt(bateria, BateriaNaoNumeroException::new,BateriaNulaException::new);
        if (num < 0 || num > 100) {
            throw new Bateria0a100Exception();
        }

        return num;

    }
}
