package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class NaoPixException extends NegocioException {
    public NaoPixException() {
        super("Usuario nao usa pix.");
    }
}
