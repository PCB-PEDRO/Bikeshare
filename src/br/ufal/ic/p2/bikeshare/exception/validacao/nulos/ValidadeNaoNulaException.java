package br.ufal.ic.p2.bikeshare.exception.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class ValidadeNaoNulaException extends ValidacaoException {
    public ValidadeNaoNulaException() {
        super("Validade do cartao nao pode ser nula.");
    }
}
