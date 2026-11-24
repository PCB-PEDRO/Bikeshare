package br.ufal.ic.p2.bikeshare.exception.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class CnpjNaoNuloException extends ValidacaoException {
    public CnpjNaoNuloException() {
        super("CNPJ nao pode ser nulo.");
    }
}
