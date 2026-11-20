package br.ufal.ic.p2.bikeshare.models.pay;

import br.ufal.ic.p2.bikeshare.exeption.negocio.NaoCartaoException;
import br.ufal.ic.p2.bikeshare.exeption.negocio.NaoPixException;
import br.ufal.ic.p2.bikeshare.exeption.negocio.NaoSaldoException;

public abstract class Payment {

    private String tipo;

    public Payment(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String obterChave() throws NaoPixException {
        throw new NaoPixException();
    }

    public void attChave(String chave) throws NaoPixException {
        throw new NaoPixException();
    }

    public String obterNumero() throws NaoCartaoException {
        throw new NaoCartaoException();
    }

    public void attNumero(String numero) throws NaoCartaoException {
        throw new NaoCartaoException();
    }

    public String obterValidade() throws NaoCartaoException {
        throw new NaoCartaoException();
    }

    public void attValidade(String validade) throws NaoCartaoException {
        throw new NaoCartaoException();
    }

    public Double obterSaldo() throws NaoSaldoException {
        throw new NaoSaldoException();
    }

    public void attSaldo(Double saldo) throws NaoSaldoException {
        throw new NaoSaldoException();
    }

    //XML

    public Payment(){ }
}
