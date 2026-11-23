package br.ufal.ic.p2.bikeshare.models.pay;

import br.ufal.ic.p2.bikeshare.exeption.negocio.SemMetodoPagamentoException;

public class PayCard extends Payment{

    private String numero;
    private String validade;

    public PayCard(String tipo, String numero, String validade) {
        super(tipo);
        this.numero = numero;
        this.validade = validade;
    }



    @Override
    public String obterNumero(){
        return numero;
    }

    @Override
    public void attNumero(String numero){
        this.numero = numero;
    }

    @Override
    public String obterValidade(){
        return validade;
    }

    @Override
    public void attValidade(String validade){
        this.validade = validade;
    }

    @Override
    public boolean validarParaAluguel() throws SemMetodoPagamentoException {
        return true;
    }


    //Xml
    public PayCard() {
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
