package br.ufal.ic.p2.bikeshare.exception.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class SenhaNaoNuloException extends ValidacaoException {
    public SenhaNaoNuloException() {
        super("Senha nao pode ser nula.");
    }
}
