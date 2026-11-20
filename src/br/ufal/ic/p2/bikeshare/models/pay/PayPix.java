package br.ufal.ic.p2.bikeshare.models.pay;

public class PayPix extends Payment {

    private String chave;

    public PayPix(String tipo, String chave) {
        super(tipo);
        this.chave = chave;
    }

    @Override
    public String obterChave(){
        return chave;
    }

    @Override
    public void attChave(String chave){
        this.chave = chave;
    }

    //xml

    public PayPix(){super();}

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }
}
