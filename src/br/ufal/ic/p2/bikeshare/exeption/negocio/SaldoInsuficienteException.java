package br.ufal.ic.p2.bikeshare.exeption.negocio;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class SaldoInsuficienteException extends ValidacaoException {
    public SaldoInsuficienteException() {
        super("Saldo insuficiente.");
    }
}
