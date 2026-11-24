package br.ufal.ic.p2.bikeshare.exception.negocio;

public class SaldoInsuficienteException extends NegocioException {
    public SaldoInsuficienteException() {
        super("Saldo insuficiente.");
    }
}
