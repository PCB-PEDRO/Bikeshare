package br.ufal.ic.p2.bikeshare.controller.Data;

import br.ufal.ic.p2.bikeshare.models.Alugueis.Aluguel;
import br.ufal.ic.p2.bikeshare.models.Bikes.Bike;
import br.ufal.ic.p2.bikeshare.models.Users.User;
import br.ufal.ic.p2.bikeshare.models.stations.Station;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataSave {

    private Map<String, User> usuarios = new LinkedHashMap<>();
    private int proximoIdUsuario = 1;
    private Map<String, Station> estacoes = new LinkedHashMap<>();
    private int proximoIdEstacao = 1;
    private Map<String, Bike> bicicletas = new LinkedHashMap<>();
    private Map<String, Aluguel> alugueis = new LinkedHashMap<>();
    private int proximoIdAluguel = 1;

    public DataSave(Map<String, User> usuarios, int proximoIdUsuario, Map<String, Station> estacoes, int proximoIdEstacao, Map<String, Bike> bicicletas, Map<String, Aluguel> alugueis, int proximoIdAluguel) {
        this.usuarios = new LinkedHashMap<>(usuarios);
        this.proximoIdUsuario = proximoIdUsuario;
        this.estacoes = new LinkedHashMap<>(estacoes);
        this.proximoIdEstacao = proximoIdEstacao;
        this.bicicletas = new LinkedHashMap<>(bicicletas);
        this.alugueis = new LinkedHashMap<>(alugueis);
        this.proximoIdAluguel = proximoIdAluguel;
    }

    public DataSave() {
    }

    public Map<String, User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Map<String, User> usuarios) {
        this.usuarios = (usuarios == null) ? new LinkedHashMap<>() : usuarios;
    }

    public int getProximoIdUsuario() {
        return proximoIdUsuario;
    }

    public void setProximoIdUsuario(int proximoIdUsuario) {
        this.proximoIdUsuario = proximoIdUsuario;
    }

    public Map<String, Station> getEstacoes() {
        return estacoes;
    }

    public void setEstacoes(Map<String, Station> estacoes) {
        this.estacoes = (estacoes == null) ? new LinkedHashMap<>() : estacoes;
    }

    public int getProximoIdEstacao() {
        return proximoIdEstacao;
    }

    public void setProximoIdEstacao(int proximoIdEstacao) {
        this.proximoIdEstacao = proximoIdEstacao;
    }

    public Map<String, Bike> getBicicletas() {
        return bicicletas;
    }

    public void setBicicletas(Map<String, Bike> bicicletas) {
        this.bicicletas = (bicicletas == null) ? new LinkedHashMap<>() : bicicletas;
    }

    public Map<String, Aluguel> getAlugueis() {
        return alugueis;
    }

    public void setAlugueis(Map<String, Aluguel> alugueis) {
        this.alugueis = (alugueis == null) ? new LinkedHashMap<>() : alugueis;
    }

    public int getProximoIdAluguel() {
        return proximoIdAluguel;
    }

    public void setProximoIdAluguel(int proximoIdAluguel) {
        this.proximoIdAluguel = proximoIdAluguel;
    }
}
