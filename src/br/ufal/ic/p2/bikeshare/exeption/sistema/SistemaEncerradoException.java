package br.ufal.ic.p2.bikeshare.exeption.sistema;

public class SistemaEncerradoException extends SistemaException {
    public SistemaEncerradoException() {
        super("Nao pode dar comandos depois de encerrarSistema.");
    }
}
