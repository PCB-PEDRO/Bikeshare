package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class EstacaoCheiaException extends NegocioException {
    public EstacaoCheiaException() {
        super("Estacao sem vagas disponiveis.");
    }
}
