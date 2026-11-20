package br.ufal.ic.p2.bikeshare.exeption.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class PlanoNaoNuloException extends ValidacaoException {
    public PlanoNaoNuloException() {
        super("Plano nao pode ser nulo.");
    }
}
