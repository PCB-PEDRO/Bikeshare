package br.ufal.ic.p2.bikeshare.exeption.validacao.numericos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class MensalidadeNaoNumeroException extends ValidacaoException {
    public MensalidadeNaoNumeroException() {
        super("Mensalidade deve ser numerica.");
    }
}
