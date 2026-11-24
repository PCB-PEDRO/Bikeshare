package br.ufal.ic.p2.bikeshare.exception.validacao.numericos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class CapacidadeNaoNumeroException extends ValidacaoException {
    public CapacidadeNaoNumeroException() {
        super("Capacidade deve ser numerica.");
    }
}
