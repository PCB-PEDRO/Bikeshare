package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class EstacaoSemVagaException extends NegocioException {
    public EstacaoSemVagaException() {
        super("Estacao sem vagas disponiveis.");
    }
}
