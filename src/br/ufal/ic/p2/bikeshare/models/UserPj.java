package br.ufal.ic.p2.bikeshare.models;

public class UserPj extends User{
    private String cnpj;
    private String razao;

    public UserPj(String nome, String email, String senha, String tipo, String CNPJ, String razao, String plano, Double mensalidade) {
        super(nome, email, senha, tipo, plano, mensalidade);
        this.cnpj = CNPJ;
        this.razao = razao;
    }

    @Override
    public String getCnpj() {
        return cnpj;
    }

    @Override
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String getRazao() {
        return razao;
    }

    @Override
    public void setRazao(String razao) {
        this.razao = razao;
    }
}
