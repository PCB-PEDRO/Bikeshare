package br.ufal.ic.p2.bikeshare.exeption.validacao.tipos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class DataInvalidaException extends ValidacaoException {
    public DataInvalidaException() {
        super("Data invalida.");
    }
}
