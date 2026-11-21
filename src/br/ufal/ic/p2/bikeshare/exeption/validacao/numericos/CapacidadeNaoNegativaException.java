package br.ufal.ic.p2.bikeshare.exeption.validacao.numericos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class CapacidadeNaoNegativaException extends ValidacaoException {
    public CapacidadeNaoNegativaException() {
        super("Capacidade deve ser positiva.");
    }
}
