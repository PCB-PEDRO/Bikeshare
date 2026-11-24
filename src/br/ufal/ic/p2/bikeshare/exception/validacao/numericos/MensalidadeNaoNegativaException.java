package br.ufal.ic.p2.bikeshare.exception.validacao.numericos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class MensalidadeNaoNegativaException extends ValidacaoException {
    public MensalidadeNaoNegativaException() {
        super("Mensalidade deve ser nao-negativa.");
    }
}
