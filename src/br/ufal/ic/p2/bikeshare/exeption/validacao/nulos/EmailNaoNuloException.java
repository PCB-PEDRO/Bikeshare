package br.ufal.ic.p2.bikeshare.exeption.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class EmailNaoNuloException extends ValidacaoException {
    public EmailNaoNuloException() {
        super("Email nao pode ser nulo.");
    }
}
