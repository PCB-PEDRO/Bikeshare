package br.ufal.ic.p2.bikeshare.exception.validacao;

import br.ufal.ic.p2.bikeshare.exception.BikeshareException;

public abstract class ValidacaoException extends BikeshareException {
    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
