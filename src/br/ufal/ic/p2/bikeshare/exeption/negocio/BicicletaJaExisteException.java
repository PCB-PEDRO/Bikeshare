package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class BicicletaJaExisteException extends NegocioException{
    public BicicletaJaExisteException(){super("Bicicleta ja existe.");}
}
