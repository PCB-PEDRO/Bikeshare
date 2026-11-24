package br.ufal.ic.p2.bikeshare.exception.validacao.tipos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class PlanoInvalidoException extends ValidacaoException {
    public PlanoInvalidoException() {
        super("Plano invalido.");
    }
}
