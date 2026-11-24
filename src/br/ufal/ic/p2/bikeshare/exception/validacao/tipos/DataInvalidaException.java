package br.ufal.ic.p2.bikeshare.exception.validacao.tipos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class DataInvalidaException extends ValidacaoException {
    public DataInvalidaException() {
        super("Data invalida.");
    }
}
