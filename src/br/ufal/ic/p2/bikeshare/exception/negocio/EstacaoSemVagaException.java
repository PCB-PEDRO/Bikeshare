package br.ufal.ic.p2.bikeshare.exception.negocio;

public class EstacaoSemVagaException extends NegocioException {
    public EstacaoSemVagaException() {
        super("Estacao sem vagas disponiveis.");
    }
}
