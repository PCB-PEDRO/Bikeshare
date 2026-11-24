package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class AluguelNaoAndamentoException extends NegocioException {
    public AluguelNaoAndamentoException() {
        super("Aluguel nao esta em andamento.");
    }
}
