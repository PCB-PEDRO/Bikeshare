package br.ufal.ic.p2.bikeshare.exeption.validacao.nulos;


import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class IdNaoNuloException extends ValidacaoException {
    public IdNaoNuloException() {
        super("Identificacao do usuario nao pode ser nula.");
    }
}
