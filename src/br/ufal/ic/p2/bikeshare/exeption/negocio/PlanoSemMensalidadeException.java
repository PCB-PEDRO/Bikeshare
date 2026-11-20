package br.ufal.ic.p2.bikeshare.exeption.negocio;

public class PlanoSemMensalidadeException extends NegocioException {
    public PlanoSemMensalidadeException() {
        super("Plano nao possui mensalidade.");
    }
}
