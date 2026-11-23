package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class SemMetodoPagamentoException extends NegocioException {
    public SemMetodoPagamentoException() {
        super("Metodo de pagamento nao cadastrado.");
    }
}
