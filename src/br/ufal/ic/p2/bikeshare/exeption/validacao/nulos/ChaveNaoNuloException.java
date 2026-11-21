package br.ufal.ic.p2.bikeshare.exeption.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class ChaveNaoNuloException extends ValidacaoException {
    public ChaveNaoNuloException() {
        super("Chave pix nao pode ser nula.");
    }
}
