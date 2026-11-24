package br.ufal.ic.p2.bikeshare.models.pay;

import br.ufal.ic.p2.bikeshare.exception.negocio.SaldoInsuficienteException;
import br.ufal.ic.p2.bikeshare.models.Users.User;

public class PayBalance extends Payment {

    public PayBalance() {
        super();
    }

    @Override
    public String getTipo() {
        return "saldoPreCarregado";
    }

    @Override
    public double obterSaldo(User usuario) {
        return usuario.getSaldo();
    }

    @Override
    public void adicionarSaldo(User usuario, double valorAdicional) {
        usuario.setSaldo(usuario.getSaldo() + valorAdicional);
    }

    @Override
    public void validarParaAluguel(User usuario) throws SaldoInsuficienteException {
        if (usuario.getSaldo() <= 0) {
            throw new SaldoInsuficienteException();
        }
    }

    @Override
    public void cobrar(User usuario, double valor) throws SaldoInsuficienteException {
        if (usuario.getSaldo() < valor) {
            throw new SaldoInsuficienteException();
        }
        usuario.setSaldo(usuario.getSaldo() - valor);
    }
}
