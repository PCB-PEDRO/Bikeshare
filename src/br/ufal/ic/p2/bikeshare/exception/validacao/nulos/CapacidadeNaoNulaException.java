package br.ufal.ic.p2.bikeshare.exception.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class CapacidadeNaoNulaException extends ValidacaoException {
    public CapacidadeNaoNulaException() {
        super("Capacidade nao pode ser nula.");
    }
}
