package br.ufal.ic.p2.bikeshare.exeption.validacao.numericos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class ValidadeInvalidaException extends ValidacaoException {
    public ValidadeInvalidaException() {
        super("Validade do cartao invalida.");
    }
}
