package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class BicicletaNaoExisteException extends NegocioException {
    public BicicletaNaoExisteException() {
        super("Bicicleta nao existe.");
    }
}
