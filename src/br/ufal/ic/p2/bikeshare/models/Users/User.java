package br.ufal.ic.p2.bikeshare.models.Users;

import br.ufal.ic.p2.bikeshare.exeption.negocio.NaoPfException;
import br.ufal.ic.p2.bikeshare.exeption.negocio.NaoPjException;
import br.ufal.ic.p2.bikeshare.models.pay.NoPay;
import br.ufal.ic.p2.bikeshare.models.pay.Payment;

public abstract class User{

    private String nome;
    private String email;
    private String senha;
    private String tipo;
    private String plano;
    private double mensalidade;
    private String estado;
    private Payment metodoPagamento;
    private String aluguelAtivo;
    private double totalPendencias;

    public User(String nome, String email, String senha, String tipo, String plano, double mensalidade) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.plano = plano;
        this.mensalidade = mensalidade;
        this.estado ="ativo";
        this.metodoPagamento = new NoPay("nenhum");
        this.aluguelAtivo = null;
        this.totalPendencias = 0.0;
    }

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public double getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(double mensalidade) {
        this.mensalidade = mensalidade;
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

    //xml

    public User(){ }

}
