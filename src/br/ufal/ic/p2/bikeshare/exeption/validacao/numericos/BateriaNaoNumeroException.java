package br.ufal.ic.p2.bikeshare.exeption.validacao.numericos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class BateriaNaoNumeroException extends ValidacaoException {
    public BateriaNaoNumeroException() {
        super("Nivel de bateria deve ser numerico.");
    }
}
