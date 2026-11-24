package br.ufal.ic.p2.bikeshare.exception.negocio;

public class BicicletaIndisponivelException extends NegocioException {
    public BicicletaIndisponivelException() {
        super("Bicicleta nao esta disponivel.");
    }
}
