package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class DataAnteriorARetiradaException extends NegocioException {
    public DataAnteriorARetiradaException() {
        super("Data de devolucao anterior a data de retirada.");
    }
}
