package br.ufal.ic.p2.bikeshare.models.Users;

import br.ufal.ic.p2.bikeshare.exception.negocio.NaoPfException;
import br.ufal.ic.p2.bikeshare.exception.negocio.NaoPjException;
import br.ufal.ic.p2.bikeshare.exception.negocio.NaoSaldoException;
import br.ufal.ic.p2.bikeshare.exception.negocio.PlanoSemMensalidadeException;
import br.ufal.ic.p2.bikeshare.exception.negocio.SaldoInsuficienteException;
import br.ufal.ic.p2.bikeshare.exception.negocio.SemMetodoPagamentoException;
import br.ufal.ic.p2.bikeshare.models.pay.NoPay;
import br.ufal.ic.p2.bikeshare.models.pay.Payment;
import br.ufal.ic.p2.bikeshare.models.planos.Plano;

public abstract class User {
    private static final String ESTADO_ATIVO = "ativo";

    private String nome;
    private String email;
    private String senha;
    private Plano plano;
    private String estado;
    private Payment metodoPagamento;
    private String aluguelAtivo;
    private double totalPendencias;
    private double saldo;

    public User(String nome, String email, String senha, Plano plano) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.plano = plano;
        this.estado = ESTADO_ATIVO;
        this.metodoPagamento = new NoPay();
        this.aluguelAtivo = null;
        this.totalPendencias = 0.0;
        this.saldo = 0.0;
    }

    public abstract String getTipo();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public String obterNomePlano() {
        return plano.getNome();
    }

    public double obterMensalidade() throws PlanoSemMensalidadeException {
        return plano.obterMensalidade();
    }

    public double calcularTarifaPlano(long minutos) {
        return plano.calcularTarifa(minutos);
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Payment getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(Payment metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getAluguelAtivo() {
        return aluguelAtivo;
    }

    public void setAluguelAtivo(String aluguelAtivo) {
        this.aluguelAtivo = aluguelAtivo;
    }

    public double getTotalPendencias() {
        return totalPendencias;
    }

    public void setTotalPendencias(double totalPendencias) {
        this.totalPendencias = totalPendencias;
    }

    public String obterCpf() throws NaoPfException {
        throw new NaoPfException();
    }

    public void attCpf(String cpf) throws NaoPfException {
        throw new NaoPfException();
    }

    public String obterCnpj() throws NaoPjException {
        throw new NaoPjException();
    }

    public void attCnpj(String cnpj) throws NaoPjException {
        throw new NaoPjException();
    }

    public String obterRazao() throws NaoPjException {
        throw new NaoPjException();
    }

    public void attRazao(String razao) throws NaoPjException {
        throw new NaoPjException();
    }

    public double obterSaldo() throws NaoSaldoException {
        return metodoPagamento.obterSaldo(this);
    }

    public void recarregarSaldo(double valorAdicional) throws NaoSaldoException {
        metodoPagamento.adicionarSaldo(this, valorAdicional);
    }

    public void validarPagamentoParaAluguel() throws SemMetodoPagamentoException, SaldoInsuficienteException {
        metodoPagamento.validarParaAluguel(this);
    }

    public void cobrarAluguel(double valor) throws SemMetodoPagamentoException, SaldoInsuficienteException {
        metodoPagamento.cobrar(this, valor);
    }

    //xml

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public User() {
    }
}
