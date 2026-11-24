package br.ufal.ic.p2.bikeshare.exception.negocio;

import br.ufal.ic.p2.bikeshare.exception.BikeshareException;

public abstract class NegocioException extends BikeshareException {
    public NegocioException(String message) {
        super(message);
    }
}
