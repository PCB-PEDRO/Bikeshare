package br.ufal.ic.p2.bikeshare.models.pay;

import br.ufal.ic.p2.bikeshare.exception.negocio.*;
import br.ufal.ic.p2.bikeshare.models.Users.User;

public abstract class Payment {

    public Payment() {
    }

    public abstract String getTipo();

    public String obterChave() throws NaoPixException {
        throw new NaoPixException();
    }

    public String obterNumero() throws NaoCartaoException {
        throw new NaoCartaoException();
    }

    public String obterValidade() throws NaoCartaoException {
        throw new NaoCartaoException();
    }

    public double obterSaldo(User usuario) throws NaoSaldoException {
        throw new NaoSaldoException();
    }

    public void adicionarSaldo(User usuario, double valorAdicional) throws NaoSaldoException {
        throw new NaoSaldoException();
    }

    public abstract void validarParaAluguel(User usuario) throws SemMetodoPagamentoException, SaldoInsuficienteException;

    public abstract void cobrar(User usuario, double valor) throws SemMetodoPagamentoException, SaldoInsuficienteException;
}
