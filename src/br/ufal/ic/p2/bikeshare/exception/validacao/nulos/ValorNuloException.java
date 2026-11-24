package br.ufal.ic.p2.bikeshare.exception.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class ValorNuloException extends ValidacaoException {
    public ValorNuloException() {
        super("Valor nao pode ser nulo.");
    }
}
