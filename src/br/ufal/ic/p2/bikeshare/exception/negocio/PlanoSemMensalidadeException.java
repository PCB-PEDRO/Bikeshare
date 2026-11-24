package br.ufal.ic.p2.bikeshare.exception.negocio;

public class PlanoSemMensalidadeException extends NegocioException {
    public PlanoSemMensalidadeException() {
        super("Plano nao possui mensalidade.");
    }
}
