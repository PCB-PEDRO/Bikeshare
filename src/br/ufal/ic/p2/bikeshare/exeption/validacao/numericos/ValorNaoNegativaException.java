package br.ufal.ic.p2.bikeshare.exeption.validacao.numericos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class ValorNaoNegativaException extends ValidacaoException {
    public ValorNaoNegativaException() {
        super("Valor deve ser positivo.");
    }
}
