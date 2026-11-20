package br.ufal.ic.p2.bikeshare.exeption.validacao.tipos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class TipoInvalidoException extends ValidacaoException {
    public TipoInvalidoException() {
        super("Tipo invalido.");
    }
}
