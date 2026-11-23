package br.ufal.ic.p2.bikeshare.exeption.negocio;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class AluguelAtivoException extends ValidacaoException {
    public AluguelAtivoException() {
        super("Usuario ja possui um aluguel em andamento.");
    }
}
