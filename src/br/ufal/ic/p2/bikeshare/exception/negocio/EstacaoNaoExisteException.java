package br.ufal.ic.p2.bikeshare.exception.negocio;

public class EstacaoNaoExisteException extends NegocioException {
    public EstacaoNaoExisteException() {
        super("Estacao nao existe.");
    }
}
