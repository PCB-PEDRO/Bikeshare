package br.ufal.ic.p2.bikeshare.exception.validacao.numericos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class BateriaNaoNumeroException extends ValidacaoException {
    public BateriaNaoNumeroException() {
        super("Nivel de bateria deve ser numerico.");
    }
}
