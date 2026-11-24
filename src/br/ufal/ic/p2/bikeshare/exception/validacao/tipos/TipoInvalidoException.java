package br.ufal.ic.p2.bikeshare.exception.validacao.tipos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class TipoInvalidoException extends ValidacaoException {
    public TipoInvalidoException() {
        super("Tipo invalido.");
    }
}
