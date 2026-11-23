package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class BicicletaSemBateriaException extends NegocioException {
    public BicicletaSemBateriaException() {
        super("Bicicleta nao possui bateria.");
    }
}
