package br.ufal.ic.p2.bikeshare.models.stations;

import java.util.ArrayList;
import java.util.List;

public class Station {
    private String nome;
    private String endereco;
    private int capacidade;
    List<String> bicicletas = new ArrayList<>();

    public Station(String nome, String endereco, int capacidade) {
        this.nome = nome;
        this.endereco = endereco;
        this.capacidade = capacidade;
        this.bicicletas = new ArrayList<>(capacidade);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public List<String> getBicicletas() {
        return bicicletas;
    }

    public void setBicicletas(List<String> bicicletas) {
        this.bicicletas = bicicletas;
    }

    public int calcularVagasDisponiveis() {
        return capacidade - bicicletas.size();
    }
    public int calcularBicicletasDisponiveis() {
        return bicicletas.size();
    }

    //Xml

    public Station(){ }
}
