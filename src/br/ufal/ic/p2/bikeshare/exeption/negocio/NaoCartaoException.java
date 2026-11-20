package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class NaoCartaoException extends NegocioException {
    public NaoCartaoException() {
        super("Usuario nao usa cartao.");
    }
}
