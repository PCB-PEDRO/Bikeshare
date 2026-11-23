package br.ufal.ic.p2.bikeshare.models.pay;

import br.ufal.ic.p2.bikeshare.exeption.negocio.SemMetodoPagamentoException;

public class NoPay extends Payment{
    public NoPay(String tipo) {
        super(tipo);
    }

    @Override
    public boolean validarParaAluguel() throws SemMetodoPagamentoException {
        throw new SemMetodoPagamentoException();
    }

    //Xml

    public NoPay() {}
}
