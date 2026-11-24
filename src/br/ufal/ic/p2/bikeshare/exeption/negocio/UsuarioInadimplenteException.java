package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class UsuarioInadimplenteException extends NegocioException {
    public UsuarioInadimplenteException() {
        super("Usuario inadimplente.");
    }
}
