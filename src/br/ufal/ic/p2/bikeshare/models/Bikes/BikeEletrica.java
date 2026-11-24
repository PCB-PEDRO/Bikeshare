package br.ufal.ic.p2.bikeshare.models.Bikes;

import br.ufal.ic.p2.bikeshare.exception.negocio.BateriaInsuficienteException;
import br.ufal.ic.p2.bikeshare.exception.negocio.BicicletaIndisponivelException;

public class BikeEletrica extends Bike {
    private static final int NIVEL_MINIMO_BATERIA = 15;
    private static final double TAXA_ADICIONAL_POR_MINUTO = 0.20;

    private int bateria;

    public BikeEletrica(String estacao, int bateria) {
        super(estacao);
        this.bateria = bateria;
    }

    @Override
    public String getTipo() {
        return "eletrica";
    }

    @Override
    public int obterNivelBateria() {
        return bateria;
    }

    @Override
    public boolean estaDisponivel() {
        return super.estaDisponivel() && this.bateria >= NIVEL_MINIMO_BATERIA;
    }

    @Override
    public void validarRetirada() throws BicicletaIndisponivelException, BateriaInsuficienteException {
        super.validarRetirada();
        if (bateria < NIVEL_MINIMO_BATERIA) {
            throw new BateriaInsuficienteException();
        }
    }

    @Override
    public double calcularAdicional(long minutos) {
        return minutos * TAXA_ADICIONAL_POR_MINUTO;
    }

    //xml
    public BikeEletrica() {
        super();
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }
}

