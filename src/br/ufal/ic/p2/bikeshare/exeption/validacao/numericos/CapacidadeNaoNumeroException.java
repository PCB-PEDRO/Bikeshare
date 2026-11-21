package br.ufal.ic.p2.bikeshare.exeption.validacao.numericos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class CapacidadeNaoNumeroException extends ValidacaoException {
    public CapacidadeNaoNumeroException() {
        super("Capacidade deve ser numerica.");
    }
}
