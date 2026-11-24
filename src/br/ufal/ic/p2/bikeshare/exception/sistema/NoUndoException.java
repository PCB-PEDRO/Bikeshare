package br.ufal.ic.p2.bikeshare.exception.sistema;

public class NoUndoException extends SistemaException {
    public NoUndoException() {
        super("Nao ha comando a desfazer.");
    }
}
