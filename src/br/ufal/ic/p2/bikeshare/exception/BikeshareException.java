package br.ufal.ic.p2.bikeshare.exception;

public abstract class BikeshareException extends Exception {
    public BikeshareException(String message) {
        super(message);
    }

    public BikeshareException(String message, Throwable cause) {
        super(message, cause);
    }
}
