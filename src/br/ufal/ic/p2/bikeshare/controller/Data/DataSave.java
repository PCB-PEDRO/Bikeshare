package br.ufal.ic.p2.bikeshare.controller.Data;

import br.ufal.ic.p2.bikeshare.models.Bikes.Bike;
import br.ufal.ic.p2.bikeshare.models.Users.User;
import br.ufal.ic.p2.bikeshare.models.stations.Station;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataSave {

    private Map<String, User> usuarios = new LinkedHashMap<>();
    private int proximoid;
    private Map<String, Station> estacoes;
    private int proximoIdEstacao = 1;
    private Map<String, Bike> bicicletas;

    public DataSave(Map<String, User> usuarios, int proximoid, Map<String, Station> estacoes, int proximoIdEstacao, Map<String, Bike> bicicletas) {
        this.usuarios = new LinkedHashMap<>(usuarios);
        this.proximoid = proximoid;
        this.estacoes = new LinkedHashMap<>(estacoes);
        this.proximoIdEstacao = proximoIdEstacao;
        this.bicicletas = new LinkedHashMap<>(bicicletas);
    }

    public DataSave() {
    }

    public Map<String, User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Map<String, User> usuarios) {
        this.usuarios = usuarios;
    }

    public int getProximoid() {
        return proximoid;
    }

    public void setProximoid(int proximoid) {
        this.proximoid = proximoid;
    }

    public Map<String, Station> getEstacoes() {
        return estacoes;
    }

    public void setEstacoes(Map<String, Station> estacoes) {
        this.estacoes = estacoes;
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
        this.bicicletas = bicicletas;
    }
}
