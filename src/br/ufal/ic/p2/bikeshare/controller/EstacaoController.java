package br.ufal.ic.p2.bikeshare.controller;

import br.ufal.ic.p2.bikeshare.controller.Data.DataSystem;
import br.ufal.ic.p2.bikeshare.exception.BikeshareException;
import br.ufal.ic.p2.bikeshare.exception.negocio.*;
import br.ufal.ic.p2.bikeshare.exception.validacao.nulos.*;
import br.ufal.ic.p2.bikeshare.exception.validacao.numericos.Bateria0a100Exception;
import br.ufal.ic.p2.bikeshare.exception.validacao.numericos.BateriaNaoNumeroException;
import br.ufal.ic.p2.bikeshare.exception.validacao.numericos.CapacidadeNaoNegativaException;
import br.ufal.ic.p2.bikeshare.exception.validacao.numericos.CapacidadeNaoNumeroException;
import br.ufal.ic.p2.bikeshare.exception.validacao.tipos.AtributoNaoExisteException;
import br.ufal.ic.p2.bikeshare.exception.validacao.tipos.TipoInvalidoException;
import br.ufal.ic.p2.bikeshare.models.Bikes.Bike;
import br.ufal.ic.p2.bikeshare.models.Bikes.BikeEletrica;
import br.ufal.ic.p2.bikeshare.models.Bikes.BikeNorm;
import br.ufal.ic.p2.bikeshare.models.stations.Station;
import br.ufal.ic.p2.bikeshare.utils.ValidadorUtils;

public class EstacaoController {
    private static final int BATERIA_MINIMA_VALIDA = 0;
    private static final int BATERIA_MAXIMA_VALIDA = 100;

    private DataSystem data;

    public EstacaoController(DataSystem data) {
        this.data = data;
    }

    public String criarEstacao(String nome, String endereco, String scapacidade) throws BikeshareException {
        int capacidade = validarDadosEstacao(nome, endereco, scapacidade);
        Station nova = new Station(nome, endereco, capacidade);
        return data.addStation(nova);
    }

    public String obterVagasDisponiveis(String idE) throws BikeshareException {
        Station consulta = data.obterEstacao(idE);
        return String.valueOf(consulta.calcularVagasDisponiveis());
    }

    public String getBicicletasDisponiveis(String idE) throws BikeshareException {
        Station consulta = data.obterEstacao(idE);
        return String.valueOf(consulta.calcularBicicletasDisponiveis());
    }

    public void adicionarBicicleta(String sestacao, String bicicleta, String tipo, String sbateria) throws BikeshareException {
        Station estacao = data.obterEstacao(sestacao);

        ValidadorUtils.validaString(bicicleta, IdBicicletaNulaException::new);
        if (data.contemBicicleta(bicicleta)) {
            throw new BicicletaJaExisteException();
        }

        Bike nova;
        if ("convencional".equals(tipo)) {
            if (sbateria != null) {
                throw new BicicletaSemBateriaException();
            }
            nova = new BikeNorm(sestacao);
        } else if ("eletrica".equals(tipo)) {
            int bateria = validarBateria(sbateria);
            nova = new BikeEletrica(sestacao, bateria);
        } else {
            throw new TipoInvalidoException();
        }

        estacao.addBike(nova);
        data.addBike(nova, bicicleta);
    }

    public void removerBicicleta(String sestacao, String sbicicleta) throws BikeshareException {
        Station estacao = data.obterEstacao(sestacao);
        Bike bicicleta = data.obterBike(sbicicleta);

        estacao.removerBike(bicicleta);
        data.removerBike(sbicicleta);
    }

    public String getEstadoBicicleta(String bicicleta) throws BikeshareException {
        Bike consulta = data.obterBike(bicicleta);
        return consulta.getEstado();
    }

    public String getAtributoBicicleta(String bicicleta, String atributo) throws BikeshareException {
        Bike consulta = data.obterBike(bicicleta);
        ValidadorUtils.validaString(atributo, AtributoNaoExisteException::new);

        switch (atributo) {
            case "tipo":
                return consulta.getTipo();
            case "estado":
                return consulta.getEstado();
            case "estacao":
                return consulta.getEstacao();
            case "nivelBateria":
                return String.valueOf(consulta.obterNivelBateria());
            default:
                throw new AtributoNaoExisteException();
        }
    }

    private int validarDadosEstacao(String nome, String endereco, String capacidade) throws BikeshareException {
        ValidadorUtils.validaString(nome, NomeNaoNuloException::new);
        ValidadorUtils.validaString(endereco, EnderecoNaoNuloException::new);
        return ValidadorUtils.conversaoIntPositivo(capacidade, CapacidadeNaoNumeroException::new, CapacidadeNaoNegativaException::new, CapacidadeNaoNulaException::new);
    }

    private int validarBateria(String bateria) throws BikeshareException {
        int num = ValidadorUtils.conversaoInt(bateria, BateriaNaoNumeroException::new, BateriaNulaException::new);
        if (num < BATERIA_MINIMA_VALIDA || num > BATERIA_MAXIMA_VALIDA) {
            throw new Bateria0a100Exception();
        }
        return num;
    }
}
