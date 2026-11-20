package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class NaoPjException extends NegocioException {
    public NaoPjException() {
        super("Usuario nao eh pessoa juridica.");
    }
}
