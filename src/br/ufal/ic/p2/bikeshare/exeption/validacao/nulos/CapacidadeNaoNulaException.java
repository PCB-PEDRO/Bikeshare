package br.ufal.ic.p2.bikeshare.exeption.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class CapacidadeNaoNulaException extends ValidacaoException {
    public CapacidadeNaoNulaException() {
        super("Capacidade nao pode ser nula.");
    }
}
