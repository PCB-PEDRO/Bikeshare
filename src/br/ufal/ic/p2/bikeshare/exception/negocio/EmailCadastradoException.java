package br.ufal.ic.p2.bikeshare.exception.negocio;

public class EmailCadastradoException extends NegocioException {
    public EmailCadastradoException() {
        super("Email ja cadastrado.");
    }
}
