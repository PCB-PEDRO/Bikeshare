package br.ufal.ic.p2.bikeshare.exception.negocio;

public class AtributoNaoAlteraException extends NegocioException {
    public AtributoNaoAlteraException() {
        super("Atributo nao pode ser alterado.");
    }
}
