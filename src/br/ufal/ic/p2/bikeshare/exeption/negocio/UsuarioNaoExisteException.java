package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class UsuarioNaoExisteException extends NegocioException {
    public UsuarioNaoExisteException() {
        super("Usuario nao existe.");
    }
}
