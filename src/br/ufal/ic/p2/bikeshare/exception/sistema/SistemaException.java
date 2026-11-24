package br.ufal.ic.p2.bikeshare.exception.sistema;

import br.ufal.ic.p2.bikeshare.exception.BikeshareException;

public abstract class SistemaException extends BikeshareException {
    public SistemaException(String message) {
        super(message);
    }

    public SistemaException(String message, Throwable cause) {
        super(message, cause);
    }
}
