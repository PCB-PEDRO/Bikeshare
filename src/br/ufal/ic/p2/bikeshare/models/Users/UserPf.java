package br.ufal.ic.p2.bikeshare.models.Users;

import br.ufal.ic.p2.bikeshare.models.planos.Plano;

public class UserPf extends User {

    private String cpf;

    public UserPf(String nome, String email, String senha, String cpf, Plano plano) {
        super(nome, email, senha, plano);
        this.cpf = cpf;
    }

    @Override
    public String getTipo() {
        return "pessoaFisica";
    }

    @Override
    public String obterCpf() {
        return cpf;
    }

    @Override
    public void attCpf(String cpf) {
        this.cpf = cpf;
    }

    //XML

    public UserPf() {
        super();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
