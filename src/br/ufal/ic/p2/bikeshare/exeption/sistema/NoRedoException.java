package br.ufal.ic.p2.bikeshare.exeption.sistema;

public class NoRedoException extends SistemaException {
    public NoRedoException() {
        super("Nao ha comando a refazer.");
    }
}
