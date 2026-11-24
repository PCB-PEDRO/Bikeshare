package br.ufal.ic.p2.bikeshare.exception.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class MensalidadeNaoNuloException extends ValidacaoException {
    public MensalidadeNaoNuloException() {
        super("Mensalidade nao pode ser nula.");
    }
}
