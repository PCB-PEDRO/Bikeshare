package br.ufal.ic.p2.bikeshare.exception.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class IdEstacaoNulaException extends ValidacaoException {
    public IdEstacaoNulaException() {
        super("Identificacao da estacao nao pode ser nula.");
    }
}
