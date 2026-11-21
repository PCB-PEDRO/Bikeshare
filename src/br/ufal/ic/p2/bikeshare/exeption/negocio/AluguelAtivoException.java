package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class AluguelAtivoException extends NegocioException {
    public AluguelAtivoException() {
        super("não remover usuário com aluguel em andamento");
    }
}
