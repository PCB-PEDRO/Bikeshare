package br.ufal.ic.p2.bikeshare.exception.sistema;

public class PersistenciaException extends SistemaException {
    public PersistenciaException() {
        super("Erro ao restaurar sistema");
    }

    public PersistenciaException(Throwable cause) {
        super("Erro ao restaurar sistema", cause);
    }

    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}
