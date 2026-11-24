package br.ufal.ic.p2.bikeshare.exception.negocio;

public class NaoPixException extends NegocioException {
    public NaoPixException() {
        super("Usuario nao usa pix.");
    }
}
