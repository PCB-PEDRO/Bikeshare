package br.ufal.ic.p2.bikeshare.models.planos;

public class PlanoAssinante extends Plano {
    private static final long FRANQUIA_MINUTOS = 60L;
    private static final double VALOR_MINUTO_EXCEDENTE = 0.30;

    private double mensalidade;

    public PlanoAssinante() {
        super();
    }

    public PlanoAssinante(double mensalidade) {
        super();
        this.mensalidade = mensalidade;
    }

    @Override
    public String getNome() {
        return "assinante";
    }

    @Override
    public double calcularTarifa(long minutos) {
        long excedente = Math.max(0L, minutos - FRANQUIA_MINUTOS);
        return excedente * VALOR_MINUTO_EXCEDENTE;
    }

    @Override
    public double obterMensalidade() {
        return mensalidade;
    }

    @Override
    public Plano migrarParaAssinante() {
        return this;
    }

    @Override
    public void alterarMensalidade(double novaMensalidade) {
        this.mensalidade = novaMensalidade;
    }

    public double getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(double mensalidade) {
        this.mensalidade = mensalidade;
    }
}