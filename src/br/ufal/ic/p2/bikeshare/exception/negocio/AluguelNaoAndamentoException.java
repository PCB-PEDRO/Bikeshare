package br.ufal.ic.p2.bikeshare.exception.negocio;

public class AluguelNaoAndamentoException extends NegocioException {
    public AluguelNaoAndamentoException() {
        super("Aluguel nao esta em andamento.");
    }
}
