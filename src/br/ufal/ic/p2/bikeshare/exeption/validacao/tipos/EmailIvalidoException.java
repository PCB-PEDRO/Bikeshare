package br.ufal.ic.p2.bikeshare.exeption.validacao.tipos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class EmailIvalidoException extends ValidacaoException {
    public EmailIvalidoException() {
        super("Email invalido.");
    }
}
