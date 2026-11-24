package br.ufal.ic.p2.bikeshare.models.planos;

import br.ufal.ic.p2.bikeshare.exception.negocio.AtributoNaoAlteraException;
import br.ufal.ic.p2.bikeshare.exception.negocio.PlanoSemMensalidadeException;

public abstract class Plano {
    private static final double MENSALIDADE_PADRAO_ASSINANTE = 30.0;

    public Plano() {
    }

    public abstract String getNome();

    public abstract double calcularTarifa(long minutos);

    public abstract double obterMensalidade() throws PlanoSemMensalidadeException;

    public Plano migrarParaAssinante() {
        return new PlanoAssinante(MENSALIDADE_PADRAO_ASSINANTE);
    }

    public void alterarMensalidade(double novaMensalidade) throws AtributoNaoAlteraException {
        throw new AtributoNaoAlteraException();
    }
}