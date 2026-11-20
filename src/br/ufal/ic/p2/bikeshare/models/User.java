package br.ufal.ic.p2.bikeshare.models;

import br.ufal.ic.p2.bikeshare.exeption.negocio.NaoPfException;
import br.ufal.ic.p2.bikeshare.exeption.negocio.NaoPjException;

public abstract class User{

    private String nome;
    private String email;
    private String senha;
    private String tipo;
    private String plano;
    private double mensalidade;
    private String estado;
    private String metodoPagamento;

    public User(String nome, String email, String senha, String tipo, String plano, double mensalidade) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.plano = plano;
        this.mensalidade = mensalidade;
        this.estado ="ativo";
        this.metodoPagamento = "nenhum";
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

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getCpf() throws NaoPfException {
        throw new NaoPfException();
    }

    public void setCpf(String cpf) throws NaoPfException {
        throw new NaoPfException();
    }

    public String getCnpj() throws NaoPjException {
        throw new NaoPjException();
    }

    public void setCnpj(String cnpj) throws NaoPjException {
        throw new NaoPjException();
    }

    public String getRazao() throws NaoPjException {
        throw new NaoPjException();
    }

    public void setRazao(String razao) throws NaoPjException {
        throw new NaoPjException();
    }

}
