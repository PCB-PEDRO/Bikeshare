package br.ufal.ic.p2.bikeshare.exception.negocio;

public class BicicletaNaoEstacaoException extends NegocioException {
    public BicicletaNaoEstacaoException() {
        super("Bicicleta nao esta nesta estacao.");
    }
}
