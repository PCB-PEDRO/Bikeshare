package br.ufal.ic.p2.bikeshare.exception.validacao.numericos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class ValorNaoNumeroException extends ValidacaoException {
    public ValorNaoNumeroException() {
        super("Valor deve ser numerico.");
    }
}
