package br.ufal.ic.p2.bikeshare.exeption.negocio;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class AluguelNaoExisteException extends ValidacaoException {
    public AluguelNaoExisteException() {
        super("Aluguel nao existe.");
    }
}
