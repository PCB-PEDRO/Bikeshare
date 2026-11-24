package br.ufal.ic.p2.bikeshare.models.Bikes;

public class BikeNorm extends Bike {

    public BikeNorm(String estacao) {
        super(estacao);
    }

    @Override
    public String getTipo() {
        return "convencional";
    }

    public BikeNorm() {
        super();
    }
}
