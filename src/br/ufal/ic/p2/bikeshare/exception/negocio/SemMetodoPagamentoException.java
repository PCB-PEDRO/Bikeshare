package br.ufal.ic.p2.bikeshare.exception.negocio;

public class SemMetodoPagamentoException extends NegocioException {
    public SemMetodoPagamentoException() {
        super("Metodo de pagamento nao cadastrado.");
    }
}
