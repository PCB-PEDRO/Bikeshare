package br.ufal.ic.p2.bikeshare.exeption.validacao;

public abstract class ValidacaoException extends Exception {
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
