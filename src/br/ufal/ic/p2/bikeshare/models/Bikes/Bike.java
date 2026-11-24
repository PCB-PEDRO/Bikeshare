package br.ufal.ic.p2.bikeshare.models.Bikes;

import br.ufal.ic.p2.bikeshare.exeption.negocio.BateriaInsuficienteException;
import br.ufal.ic.p2.bikeshare.exeption.negocio.BicicletaSemBateriaException;

public abstract class Bike {
    private String tipo;
    private String estado;
    private String estacao;

    public Bike(String tipo, String estacao) {
        this.tipo = tipo;
        this.estado = "disponivel";
        this.estacao = estacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstacao() {
        return estacao;
    }

    public void setEstacao(String estacao) {
        this.estacao = estacao;
    }

    public int obterNivelBateria() throws BicicletaSemBateriaException{
        throw new BicicletaSemBateriaException();
    }

    public boolean estaDisponivel() {
        return this.estado.equals("disponivel");
    }

    public void validarRetirada() throws BateriaInsuficienteException {

    }

    public double calcularAdicional(long minutos){
        return 0.0;
    }

    //Xml

    public Bike(){}
}
