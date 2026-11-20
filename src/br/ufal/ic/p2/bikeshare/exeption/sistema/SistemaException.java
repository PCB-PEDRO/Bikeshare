package br.ufal.ic.p2.bikeshare.exeption.sistema;

public abstract class SistemaException extends Exception {
    public SistemaException(String message) {
        super(message);
    }
}
