package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class NaoRemoverException extends NegocioException {
    public NaoRemoverException() {
        super("Nao e possivel remover usuario com aluguel em andamento.");
    }
}
