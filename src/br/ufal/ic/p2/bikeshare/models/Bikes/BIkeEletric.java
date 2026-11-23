package br.ufal.ic.p2.bikeshare.models.Bikes;

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

    //xml
    public BIkeEletric(){super();}

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }
}
