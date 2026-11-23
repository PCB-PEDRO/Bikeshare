package br.ufal.ic.p2.bikeshare.models.pay;

import br.ufal.ic.p2.bikeshare.exeption.negocio.SaldoInsuficienteException;
import br.ufal.ic.p2.bikeshare.exeption.negocio.SemMetodoPagamentoException;

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

    @Override
    public boolean validarParaAluguel() throws SaldoInsuficienteException {
        if(this.saldo > 0){
            return true;
        }else {
            throw new SaldoInsuficienteException();
        }
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
