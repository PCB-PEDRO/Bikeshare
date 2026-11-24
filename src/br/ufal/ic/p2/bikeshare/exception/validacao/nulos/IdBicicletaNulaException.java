package br.ufal.ic.p2.bikeshare.exception.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class IdBicicletaNulaException extends ValidacaoException {
    public IdBicicletaNulaException() {
        super("Identificacao da bicicleta nao pode ser nula.");
    }
}
