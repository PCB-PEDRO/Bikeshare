package br.ufal.ic.p2.bikeshare.exception.validacao.tipos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class EmailInvalidoException extends ValidacaoException {
    public EmailInvalidoException() {
        super("Email invalido.");
    }
}
