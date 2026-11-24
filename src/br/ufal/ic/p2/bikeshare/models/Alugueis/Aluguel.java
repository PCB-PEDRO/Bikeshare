package br.ufal.ic.p2.bikeshare.models.Alugueis;

public class Aluguel {
    private static final String ESTADO_EM_ANDAMENTO = "emAndamento";

    private String usuario;
    private String bicicleta;
    private String estacaoOrigem;
    private String estacaoDestino;
    private String inicio;
    private String fim;
    private String estado;
    private double valorCobrado;

    public Aluguel(String usuario, String bicicleta, String estacaoOrigem, String inicio) {
        this.usuario = usuario;
        this.bicicleta = bicicleta;
        this.estacaoOrigem = estacaoOrigem;
        this.inicio = inicio;
        this.estacaoDestino = null;
        this.fim = null;
        this.estado = ESTADO_EM_ANDAMENTO;
        this.valorCobrado = 0.0;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(String bicicleta) {
        this.bicicleta = bicicleta;
    }

    public String getEstacaoOrigem() {
        return estacaoOrigem;
    }

    public void setEstacaoOrigem(String estacaoOrigem) {
        this.estacaoOrigem = estacaoOrigem;
    }

    public String getEstacaoDestino() {
        return estacaoDestino;
    }

    public void setEstacaoDestino(String estacaoDestino) {
        this.estacaoDestino = estacaoDestino;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(double valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    //Xml

    public Aluguel() {
    }
}
