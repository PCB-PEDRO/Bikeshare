package br.ufal.ic.p2.bikeshare.exception.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class BateriaNulaException extends ValidacaoException {
    public BateriaNulaException() {
        super("Nivel de bateria nao pode ser nulo.");
    }
}
