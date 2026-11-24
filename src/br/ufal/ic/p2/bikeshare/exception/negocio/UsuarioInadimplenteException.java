package br.ufal.ic.p2.bikeshare.exception.negocio;

public class UsuarioInadimplenteException extends NegocioException {
    public UsuarioInadimplenteException() {
        super("Usuario inadimplente.");
    }
}
