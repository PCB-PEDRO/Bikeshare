package br.ufal.ic.p2.bikeshare.exeption.validacao.tipos;


import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class AtributoNaoExisteException extends ValidacaoException {
    public AtributoNaoExisteException() {
        super("Atributo nao existe.");
    }
}
