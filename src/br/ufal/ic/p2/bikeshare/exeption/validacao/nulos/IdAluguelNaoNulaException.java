package br.ufal.ic.p2.bikeshare.exeption.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class IdAluguelNaoNulaException extends ValidacaoException {
    public IdAluguelNaoNulaException() {
        super("Identificacao do aluguel nao pode ser nula.");
    }
}
