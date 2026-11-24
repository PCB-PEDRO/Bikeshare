package br.ufal.ic.p2.bikeshare.exception.validacao.tipos;


import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class AtributoNaoExisteException extends ValidacaoException {
    public AtributoNaoExisteException() {
        super("Atributo nao existe.");
    }
}
