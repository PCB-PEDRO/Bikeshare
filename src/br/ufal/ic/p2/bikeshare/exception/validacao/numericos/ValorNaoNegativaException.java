package br.ufal.ic.p2.bikeshare.exception.validacao.numericos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class ValorNaoNegativaException extends ValidacaoException {
    public ValorNaoNegativaException() {
        super("Valor deve ser positivo.");
    }
}
