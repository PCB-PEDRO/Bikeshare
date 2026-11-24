package br.ufal.ic.p2.bikeshare.models.stations;

import br.ufal.ic.p2.bikeshare.exception.negocio.BicicletaNaoEstacaoException;
import br.ufal.ic.p2.bikeshare.exception.negocio.EstacaoSemVagaException;
import br.ufal.ic.p2.bikeshare.models.Bikes.Bike;

import java.util.ArrayList;
import java.util.List;

public class Station {
    private String nome;
    private String endereco;
    private int capacidade;
    private List<Bike> bicicletas = new ArrayList<>();

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

    public void addBike(Bike nova) throws EstacaoSemVagaException {
        if (calcularVagasDisponiveis() > 0) {
            bicicletas.add(nova);
        } else {
            throw new EstacaoSemVagaException();
        }
    }

    public boolean contemBicicleta(Bike bike) {
        return bicicletas.contains(bike);
    }

    public void removerBike(Bike bike) throws BicicletaNaoEstacaoException {
        boolean removida = bicicletas.remove(bike);
        if (!removida) {
            throw new BicicletaNaoEstacaoException();
        }
    }

    public int calcularVagasDisponiveis() {
        return capacidade - bicicletas.size();
    }

    public int calcularBicicletasDisponiveis() {
        int disponiveis = 0;
        for (Bike bike : bicicletas) {
            if (bike.estaDisponivel()) {
                disponiveis++;
            }
        }
        return disponiveis;
    }

    //Xml

    public Station() {
    }

    public List<Bike> getBicicletas() {
        return bicicletas;
    }

    public void setBicicletas(List<Bike> bicicletas) {
        this.bicicletas = (bicicletas == null) ? new ArrayList<>() : new ArrayList<>(bicicletas);
    }
}
