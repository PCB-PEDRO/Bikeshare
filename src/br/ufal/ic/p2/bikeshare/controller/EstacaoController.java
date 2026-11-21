package br.ufal.ic.p2.bikeshare.controller;

import br.ufal.ic.p2.bikeshare.controller.Data.DataSystem;
import br.ufal.ic.p2.bikeshare.exeption.negocio.EmailCadastradoException;
import br.ufal.ic.p2.bikeshare.exeption.negocio.EstacaoNaoExisteException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.nulos.*;
import br.ufal.ic.p2.bikeshare.exeption.validacao.numericos.CapacidadeNaoNegativaException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.numericos.CapacidadeNaoNumeroException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.tipos.EmailIvalidoException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.tipos.TipoInvalidoException;
import br.ufal.ic.p2.bikeshare.models.Users.User;
import br.ufal.ic.p2.bikeshare.models.Users.UserPf;
import br.ufal.ic.p2.bikeshare.models.stations.Station;
import br.ufal.ic.p2.bikeshare.utils.ValidadorUtils;

public class EstacaoController {
    private DataSystem data;

    public EstacaoController (DataSystem data){
        this.data = data;
    }

    public String criarEstacao(String nome, String endereco, String scapacidade) throws NomeNaoNuloException, CapacidadeNaoNumeroException, CapacidadeNaoNegativaException, CapacidadeNaoNulaException, EnderecoNaoNuloException {
        int capacidade = validarDados( nome, endereco, scapacidade);
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


    private int validarDados(String nome, String endereco, String capacidade) throws EnderecoNaoNuloException, NomeNaoNuloException, CapacidadeNaoNumeroException, CapacidadeNaoNegativaException, CapacidadeNaoNulaException {
        ValidadorUtils.validaString(nome, NomeNaoNuloException::new);
        ValidadorUtils.validaString(endereco, EnderecoNaoNuloException::new);
        int num = ValidadorUtils.conversaoIntPositivo(capacidade);

        return num;
    }


}
