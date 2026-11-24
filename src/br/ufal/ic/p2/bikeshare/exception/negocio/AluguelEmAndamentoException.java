package br.ufal.ic.p2.bikeshare.exception.negocio;

public class AluguelEmAndamentoException extends NegocioException {
    public AluguelEmAndamentoException() {
        super("Aluguel ainda esta em andamento.");
    }
}
