package br.ufal.ic.p2.bikeshare.models.pay;

import br.ufal.ic.p2.bikeshare.models.Users.User;

public class PayCard extends Payment {

    private String numero;
    private String validade;

    public PayCard(String numero, String validade) {
        super();
        this.numero = numero;
        this.validade = validade;
    }

    @Override
    public String getTipo() {
        return "cartao";
    }

    @Override
    public String obterNumero() {
        return numero;
    }

    @Override
    public String obterValidade() {
        return validade;
    }

    @Override
    public void validarParaAluguel(User usuario) {
    }

    @Override
    public void cobrar(User usuario, double valor) {
    }

    //Xml

    public PayCard() {
        super();
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }
}
