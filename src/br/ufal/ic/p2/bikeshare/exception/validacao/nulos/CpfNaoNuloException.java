package br.ufal.ic.p2.bikeshare.exception.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class CpfNaoNuloException extends ValidacaoException {
    public CpfNaoNuloException() {
        super("cpf nao pode ser nulo.");
    }
}
