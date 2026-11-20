package br.ufal.ic.p2.bikeshare.exeption.sistema;

public class NoUndoException extends SistemaException {
    public NoUndoException() {
        super("Nao ha comando a desfazer.");
    }
}
