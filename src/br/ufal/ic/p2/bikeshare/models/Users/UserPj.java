package br.ufal.ic.p2.bikeshare.models.Users;

import br.ufal.ic.p2.bikeshare.models.planos.Plano;

public class UserPj extends User {
    private String cnpj;
    private String razao;

    public UserPj(String nome, String email, String senha, String cnpj, String razao, Plano plano) {
        super(nome, email, senha, plano);
        this.cnpj = cnpj;
        this.razao = razao;
    }

    @Override
    public String getTipo() {
        return "pessoaJuridica";
    }

    @Override
    public String obterCnpj() {
        return cnpj;
    }

    @Override
    public void attCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String obterRazao() {
        return razao;
    }

    @Override
    public void attRazao(String razao) {
        this.razao = razao;
    }

    //xml

    public UserPj() {
        super();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }
}
