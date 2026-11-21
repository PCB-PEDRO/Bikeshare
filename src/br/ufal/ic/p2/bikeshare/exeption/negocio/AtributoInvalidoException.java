package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class AtributoInvalidoException extends NegocioException {
    public AtributoInvalidoException() {
        super("Atributo invalido.");
    }
}
