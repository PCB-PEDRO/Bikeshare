package br.ufal.ic.p2.bikeshare.models.pay;

public class PayBalance extends Payment{

    private Double saldo;

    public PayBalance(String tipo, double saldo) {
        super(tipo);
        this.saldo = saldo;
    }

    @Override
    public Double obterSaldo(){
        return saldo;
    }

    @Override
    public void attSaldo(Double saldo){
        this.saldo = saldo;
    }

    //Xml


    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public PayBalance() {
    }
}
