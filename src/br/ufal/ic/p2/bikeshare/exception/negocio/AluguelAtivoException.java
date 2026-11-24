package br.ufal.ic.p2.bikeshare.exception.negocio;

public class AluguelAtivoException extends NegocioException {
    public AluguelAtivoException() {
        super("Usuario ja possui um aluguel em andamento.");
    }
}
