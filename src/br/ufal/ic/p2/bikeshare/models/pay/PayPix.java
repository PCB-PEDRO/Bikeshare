package br.ufal.ic.p2.bikeshare.models.pay;

import br.ufal.ic.p2.bikeshare.models.Users.User;

public class PayPix extends Payment {

    private String chave;

    public PayPix(String chave) {
        super();
        this.chave = chave;
    }

    @Override
    public String getTipo() {
        return "pix";
    }

    @Override
    public String obterChave() {
        return chave;
    }

    @Override
    public void validarParaAluguel(User usuario) {
    }

    @Override
    public void cobrar(User usuario, double valor) {
    }

    //xml

    public PayPix() {
        super();
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }
}
