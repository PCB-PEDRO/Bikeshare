package br.ufal.ic.p2.bikeshare.exeption.validacao.numericos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class Bateria0a100Exception extends ValidacaoException {
    public Bateria0a100Exception() {
        super("Nivel de bateria deve estar entre 0 e 100.");
    }
}
