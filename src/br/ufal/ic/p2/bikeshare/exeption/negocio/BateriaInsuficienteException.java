package br.ufal.ic.p2.bikeshare.exeption.negocio;

import br.ufal.ic.p2.bikeshare.exeption.validacao.ValidacaoException;

public class BateriaInsuficienteException extends ValidacaoException {
    public BateriaInsuficienteException() {
        super("Nivel de bateria insuficiente.");
    }
}
