package br.ufal.ic.p2.bikeshare.exeption.validacao.tipos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class PlanoInvalidoException extends ValidacaoException {
    public PlanoInvalidoException() {
        super("Plano invalido.");
    }
}
