package br.ufal.ic.p2.bikeshare.models.Bikes;

import br.ufal.ic.p2.bikeshare.exeption.negocio.BateriaInsuficienteException;

public class BIkeEletric extends Bike{
    private int bateria;

    public BIkeEletric(String tipo, String estacao, int bateria) {
        super(tipo, estacao);
        this.bateria = bateria;
    }

    @Override
    public int obterNivelBateria(){
        return bateria;
    }

    @Override
    public boolean estaDisponivel() {
        return super.estaDisponivel() && this.bateria >= 15;
    }

    @Override
    public void validarRetirada() throws BateriaInsuficienteException {
        if(bateria < 15){
            throw new BateriaInsuficienteException();
        }
    }

    @Override
    public double calcularAdicional(long minutos){
        return minutos * 0.20;
    }

    //xml
    public BIkeEletric(){super();}

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }
}
