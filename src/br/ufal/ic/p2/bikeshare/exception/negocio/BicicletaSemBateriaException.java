package br.ufal.ic.p2.bikeshare.exception.negocio;

public class BicicletaSemBateriaException extends NegocioException {
    public BicicletaSemBateriaException() {
        super("Bicicleta nao possui bateria.");
    }
}
