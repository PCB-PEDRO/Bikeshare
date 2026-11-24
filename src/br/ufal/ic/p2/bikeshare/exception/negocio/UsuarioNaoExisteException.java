package br.ufal.ic.p2.bikeshare.exception.negocio;

public class UsuarioNaoExisteException extends NegocioException {
    public UsuarioNaoExisteException() {
        super("Usuario nao existe.");
    }
}
