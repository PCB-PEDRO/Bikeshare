package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class NaoPfException extends NegocioException {
    public NaoPfException() {
        super("Usuario nao eh pessoa fisica.");
    }
}
