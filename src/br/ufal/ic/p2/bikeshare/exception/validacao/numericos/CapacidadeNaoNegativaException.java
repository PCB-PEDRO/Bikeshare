package br.ufal.ic.p2.bikeshare.exception.validacao.numericos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class CapacidadeNaoNegativaException extends ValidacaoException {
    public CapacidadeNaoNegativaException() {
        super("Capacidade deve ser positiva.");
    }
}
