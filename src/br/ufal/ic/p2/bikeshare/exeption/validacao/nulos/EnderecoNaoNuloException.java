package br.ufal.ic.p2.bikeshare.exeption.validacao.nulos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class EnderecoNaoNuloException extends ValidacaoException {
    public EnderecoNaoNuloException() {
        super("Endereco nao pode ser nulo.");
    }
}
