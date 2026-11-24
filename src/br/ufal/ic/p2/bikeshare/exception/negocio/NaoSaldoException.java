package br.ufal.ic.p2.bikeshare.exception.negocio;

public class NaoSaldoException extends NegocioException{
    public NaoSaldoException() {
        super("Usuario nao usa saldo pre-carregado.");
    }
}
