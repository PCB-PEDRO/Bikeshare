package br.ufal.ic.p2.bikeshare.models.planos;

import br.ufal.ic.p2.bikeshare.exception.negocio.PlanoSemMensalidadeException;

public class PlanoAvulso extends Plano {
    private static final double VALOR_POR_MINUTO = 0.50;

    public PlanoAvulso() {
        super();
    }

    @Override
    public String getNome() {
        return "avulso";
    }

    @Override
    public double calcularTarifa(long minutos) {
        return minutos * VALOR_POR_MINUTO;
    }

    @Override
    public double obterMensalidade() throws PlanoSemMensalidadeException {
        throw new PlanoSemMensalidadeException();
    }
}