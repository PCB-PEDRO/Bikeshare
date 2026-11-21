package br.ufal.ic.p2.bikeshare.exeption.validacao.numericos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class ValorNaoNumeroException extends ValidacaoException {
    public ValorNaoNumeroException() {
        super("Valor deve ser numerico.");
    }
}
