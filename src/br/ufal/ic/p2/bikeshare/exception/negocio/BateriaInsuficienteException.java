package br.ufal.ic.p2.bikeshare.exception.negocio;

public class BateriaInsuficienteException extends NegocioException {
    public BateriaInsuficienteException() {
        super("Nivel de bateria insuficiente.");
    }
}
