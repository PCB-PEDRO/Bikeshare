package br.ufal.ic.p2.bikeshare.exeption.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class CartaoNaoNuloException extends ValidacaoException {
    public CartaoNaoNuloException() {
        super("Numero do cartao nao pode ser nulo.");
    }
}
