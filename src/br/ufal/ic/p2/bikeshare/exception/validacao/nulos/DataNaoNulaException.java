package br.ufal.ic.p2.bikeshare.exception.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class DataNaoNulaException extends ValidacaoException {
    public DataNaoNulaException() {
        super("Data nao pode ser nula.");
    }
}
