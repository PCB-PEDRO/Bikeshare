package br.ufal.ic.p2.bikeshare.exception.negocio;

public class BicicletaJaExisteException extends NegocioException{
    public BicicletaJaExisteException(){super("Bicicleta ja existe.");}
}
