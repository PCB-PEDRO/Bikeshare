package br.ufal.ic.p2.bikeshare.exeption.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class NomeNaoNuloException extends ValidacaoException {
    public NomeNaoNuloException() {
        super("Nome nao pode ser nulo.");
    }
}
