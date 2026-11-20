package br.ufal.ic.p2.bikeshare.exeption.negocio;

public abstract class NegocioException extends Exception {
    public NegocioException(String message) {
        super(message);
    }
}
