package br.ufal.ic.p2.bikeshare.models;

public class UserPf extends User{

    private String cpf;

    public UserPf(String nome, String email, String senha, String tipo, String cpf, String plano, Double mensalidade){
        super(nome,email,senha,tipo,plano,mensalidade);
        this.cpf = cpf;
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

    public UserPf(){super();}

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }



}
