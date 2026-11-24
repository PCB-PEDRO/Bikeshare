package br.ufal.ic.p2.bikeshare.exception.negocio;

public class NaoRemoverException extends NegocioException {
    public NaoRemoverException() {
        super("Nao e possivel remover usuario com aluguel em andamento.");
    }
}
