package br.ufal.ic.p2.bikeshare.controller;

import br.ufal.ic.p2.bikeshare.exeption.negocio.NaoPfException;
import br.ufal.ic.p2.bikeshare.exeption.negocio.NaoPjException;
import br.ufal.ic.p2.bikeshare.exeption.negocio.PlanoSemMensalidadeException;
import br.ufal.ic.p2.bikeshare.exeption.negocio.UsuarioNaoExisteException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.nulos.*;
import br.ufal.ic.p2.bikeshare.exeption.validacao.numericos.MensalidadeNaoNegativaException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.numericos.MensalidadeNaoNumeroException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.tipos.AtributoNaoExisteException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.tipos.EmailIvalidoException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.tipos.PlanoInvalidoException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.tipos.TipoInvalidoException;
import br.ufal.ic.p2.bikeshare.models.User;
import br.ufal.ic.p2.bikeshare.models.UserPf;
import br.ufal.ic.p2.bikeshare.models.UserPj;
import br.ufal.ic.p2.bikeshare.utils.ValidadorUtils;

public class UserController {
    private DataSystem data;

    public UserController (DataSystem data){
        this.data = data;
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cpf) throws NomeNaoNuloException, CnpjNaoNuloException, EmailNaoNuloException, SenhaNaoNuloException, CpfNaoNuloException, TipoInvalidoException, EmailIvalidoException {
        validarDados( nome, email, senha, tipo, cpf,"pessoaFisica");
        User novo = new UserPf( nome, email, senha, tipo, cpf, "avulso", 0.0);
        String id = data.addUser(novo);

        return id;
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cnpj, String razaoSocial) throws NomeNaoNuloException, CnpjNaoNuloException, EmailNaoNuloException, SenhaNaoNuloException, CpfNaoNuloException, TipoInvalidoException, RazaoNaoNuloException, EmailIvalidoException {
        validarDados( nome, email, senha, tipo, cnpj, "pessoaJuridica");
        ValidadorUtils.validaString(razaoSocial, RazaoNaoNuloException::new);

        User novo = new UserPj( nome, email, senha, tipo, cnpj, razaoSocial, "avulso", 0.0);
        String id = data.addUser(novo);

        return id;
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cpf, String plano, String smensalidade) throws NomeNaoNuloException, CnpjNaoNuloException, EmailNaoNuloException, SenhaNaoNuloException, CpfNaoNuloException, TipoInvalidoException, PlanoNaoNuloException, PlanoInvalidoException, MensalidadeNaoNuloException, MensalidadeNaoNegativaException, MensalidadeNaoNumeroException, EmailIvalidoException {
        validarDados(nome, email, senha, tipo, cpf, "pessoaFisica");
        validarPlano(plano);
        double mensalidade = ValidadorUtils.conversaoDouble(smensalidade, MensalidadeNaoNumeroException::new, MensalidadeNaoNegativaException::new, MensalidadeNaoNuloException::new);

        User novo = new UserPf( nome, email, senha, tipo, cpf, plano, mensalidade);
        String id = data.addUser(novo);

        return id;
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cnpj, String razaoSocial, String plano, String smensalidade) throws NomeNaoNuloException, CnpjNaoNuloException, EmailNaoNuloException, SenhaNaoNuloException, CpfNaoNuloException, TipoInvalidoException, RazaoNaoNuloException, PlanoNaoNuloException, PlanoInvalidoException, MensalidadeNaoNuloException, MensalidadeNaoNegativaException, MensalidadeNaoNumeroException, EmailIvalidoException {
        validarDados(nome,email,senha,tipo,cnpj, "pessoaJuridica");
        ValidadorUtils.validaString(razaoSocial, RazaoNaoNuloException::new);
        validarPlano(plano);
        double mensalidade = ValidadorUtils.conversaoDouble(smensalidade, MensalidadeNaoNumeroException::new, MensalidadeNaoNegativaException::new, MensalidadeNaoNuloException::new);

        User novo = new UserPj(nome,email,senha,tipo,cnpj, razaoSocial, plano, mensalidade);
        String id = data.addUser(novo);

        return id;
    }

    public String getAtributoUsuario(String usuario, String atributo, String valor1, String numero, String validade) throws UsuarioNaoExisteException, IdNaoNuloException, NaoPfException, NaoPjException, AtributoNaoExisteException, PlanoSemMensalidadeException {
        User consulta = data.queryUser(usuario);

        switch (atributo){
            case "nome":
                return consulta.getNome();
            case "tipo":
                return consulta.getTipo();
            case "email":
                return consulta.getEmail();
            case "senha":
                return consulta.getSenha();
            case "estado":
                return consulta.getEstado();
            case "metodoPagamento":
                return consulta.getMetodoPagamento();
            case "plano":
                return consulta.getPlano();
            case "mensalidade":
                if(consulta.getMensalidade() == 0.0){
                    throw new PlanoSemMensalidadeException();
                }
                return ValidadorUtils.formatarMoeda(consulta.getMensalidade());
            case "cpf":
                return consulta.getCpf();
            case "cnpj":
                return consulta.getCnpj();
            case "razaoSocial":
                return consulta.getRazao();
            default:
                throw new AtributoNaoExisteException();
        }
    }

    private void validarDados(String nome, String email, String senha, String tipo, String cpfOuCnpj, String tipoEsperado) throws NomeNaoNuloException, EmailNaoNuloException, SenhaNaoNuloException, CpfNaoNuloException, CnpjNaoNuloException, TipoInvalidoException, EmailIvalidoException {

        ValidadorUtils.validaString(nome,NomeNaoNuloException::new);
        ValidadorUtils.validaString(email, EmailNaoNuloException::new);
        ValidadorUtils.validaEmail(email);
        ValidadorUtils.validaString(senha, SenhaNaoNuloException::new);
        if(tipo.equals("pessoaFisica")){
            ValidadorUtils.validaString(cpfOuCnpj, CpfNaoNuloException::new);
        }
        else if(tipo.equals("pessoaJuridica")){
            ValidadorUtils.validaString(cpfOuCnpj, CnpjNaoNuloException::new );
        }

        if(!tipo.equals(tipoEsperado)){
            throw new TipoInvalidoException();
        }
    }

    private void validarPlano(String plano) throws PlanoNaoNuloException, PlanoInvalidoException {
        ValidadorUtils.validaString(plano,PlanoNaoNuloException::new);
        if(!plano.equals("avulso") && !plano.equals("assinante")){
            throw new PlanoInvalidoException();
        }
    }


}
