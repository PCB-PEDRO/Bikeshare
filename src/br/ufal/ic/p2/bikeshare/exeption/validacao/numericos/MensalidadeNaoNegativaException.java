package br.ufal.ic.p2.bikeshare.exeption.validacao.numericos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class MensalidadeNaoNegativaException extends ValidacaoException {
    public MensalidadeNaoNegativaException() {
        super("Mensalidade deve ser nao-negativa.");
    }
}
