package br.ufal.ic.p2.bikeshare.exception.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class RazaoNaoNuloException extends ValidacaoException {
    public RazaoNaoNuloException() {
        super("Razao social nao pode ser nula.");
    }
}
