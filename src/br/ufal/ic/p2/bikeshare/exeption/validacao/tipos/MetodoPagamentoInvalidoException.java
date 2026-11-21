package br.ufal.ic.p2.bikeshare.exeption.validacao.tipos;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class MetodoPagamentoInvalidoException extends ValidacaoException {
    public MetodoPagamentoInvalidoException() {
        super("Valor de metodo de pagamento invalido.");
    }
}
