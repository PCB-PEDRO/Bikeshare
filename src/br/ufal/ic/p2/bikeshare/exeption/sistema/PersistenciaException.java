package br.ufal.ic.p2.bikeshare.exeption.sistema;

public class PersistenciaException extends SistemaException {
    public PersistenciaException() {
        super("Erro ao restaurar sistema");
    }
}
