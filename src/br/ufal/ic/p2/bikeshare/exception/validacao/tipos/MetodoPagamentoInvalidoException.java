package br.ufal.ic.p2.bikeshare.exception.validacao.tipos;

import br.ufal.ic.p2.bikeshare.exception.validacao.ValidacaoException;

public class MetodoPagamentoInvalidoException extends ValidacaoException {
    public MetodoPagamentoInvalidoException() {
        super("Valor de metodo de pagamento invalido.");
    }
}
