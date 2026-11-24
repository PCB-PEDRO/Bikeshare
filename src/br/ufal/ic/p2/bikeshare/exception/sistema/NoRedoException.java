package br.ufal.ic.p2.bikeshare.exception.sistema;

public class NoRedoException extends SistemaException {
    public NoRedoException() {
        super("Nao ha comando a refazer.");
    }
}
