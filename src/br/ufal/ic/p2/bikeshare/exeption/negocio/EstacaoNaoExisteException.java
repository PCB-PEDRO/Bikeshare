package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class EstacaoNaoExisteException extends NegocioException {
    public EstacaoNaoExisteException() {
        super("Estacao nao existe.");
    }
}
