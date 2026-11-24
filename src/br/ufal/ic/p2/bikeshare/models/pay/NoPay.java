package br.ufal.ic.p2.bikeshare.models.pay;

import br.ufal.ic.p2.bikeshare.exception.negocio.SemMetodoPagamentoException;
import br.ufal.ic.p2.bikeshare.models.Users.User;

public class NoPay extends Payment {

    public NoPay() {
        super();
    }

    @Override
    public String getTipo() {
        return "nenhum";
    }

    @Override
    public void validarParaAluguel(User usuario) throws SemMetodoPagamentoException {
        throw new SemMetodoPagamentoException();
    }

    @Override
    public void cobrar(User usuario, double valor) throws SemMetodoPagamentoException {
        throw new SemMetodoPagamentoException();
    }
}
