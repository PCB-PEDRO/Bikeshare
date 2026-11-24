package br.ufal.ic.p2.bikeshare.exception.negocio;

public class AluguelNaoExisteException extends NegocioException {
    public AluguelNaoExisteException() {
        super("Aluguel nao existe.");
    }
}
