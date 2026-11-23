package br.ufal.ic.p2.bikeshare.controller;

import br.ufal.ic.p2.bikeshare.controller.Data.DataSystem;
import br.ufal.ic.p2.bikeshare.exeption.negocio.*;
import br.ufal.ic.p2.bikeshare.exeption.validacao.nulos.*;
import br.ufal.ic.p2.bikeshare.exeption.validacao.numericos.*;
import br.ufal.ic.p2.bikeshare.exeption.validacao.tipos.*;
import br.ufal.ic.p2.bikeshare.models.Users.User;
import br.ufal.ic.p2.bikeshare.models.Users.UserPf;
import br.ufal.ic.p2.bikeshare.models.Users.UserPj;
import br.ufal.ic.p2.bikeshare.models.pay.PayBalance;
import br.ufal.ic.p2.bikeshare.models.pay.PayCard;
import br.ufal.ic.p2.bikeshare.models.pay.PayPix;
import br.ufal.ic.p2.bikeshare.models.pay.Payment;
import br.ufal.ic.p2.bikeshare.utils.ValidadorUtils;

public class UserController {
    private DataSystem data;

    public UserController (DataSystem data){
        this.data = data;
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cpf) throws NomeNaoNuloException, CnpjNaoNuloException, EmailNaoNuloException, SenhaNaoNuloException, CpfNaoNuloException, TipoInvalidoException, EmailIvalidoException, EmailCadastradoException {
        email = validarDados( nome, email, senha, tipo, cpf,"pessoaFisica");
        User novo = new UserPf( nome, email, senha, tipo, cpf, "avulso", 0.0);
        String id = data.addUser(novo);

        return id;
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cnpj, String razaoSocial) throws NomeNaoNuloException, CnpjNaoNuloException, EmailNaoNuloException, SenhaNaoNuloException, CpfNaoNuloException, TipoInvalidoException, RazaoNaoNuloException, EmailIvalidoException, EmailCadastradoException {
        email = validarDados( nome, email, senha, tipo, cnpj, "pessoaJuridica");
        ValidadorUtils.validaString(razaoSocial, RazaoNaoNuloException::new);

        User novo = new UserPj( nome, email, senha, tipo, cnpj, razaoSocial, "avulso", 0.0);
        String id = data.addUser(novo);

        return id;
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cpf, String plano, String smensalidade) throws NomeNaoNuloException, CnpjNaoNuloException, EmailNaoNuloException, SenhaNaoNuloException, CpfNaoNuloException, TipoInvalidoException, PlanoNaoNuloException, PlanoInvalidoException, MensalidadeNaoNuloException, MensalidadeNaoNegativaException, MensalidadeNaoNumeroException, EmailIvalidoException, EmailCadastradoException, PlanoSemMensalidadeException {
        email = validarDados(nome, email, senha, tipo, cpf, "pessoaFisica");
        double mensalidade = validarPlano(plano, smensalidade);

        User novo = new UserPf( nome, email, senha, tipo, cpf, plano, mensalidade);
        String id = data.addUser(novo);

        return id;
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cnpj, String razaoSocial, String plano, String smensalidade) throws NomeNaoNuloException, CnpjNaoNuloException, EmailNaoNuloException, SenhaNaoNuloException, CpfNaoNuloException, TipoInvalidoException, RazaoNaoNuloException, PlanoNaoNuloException, PlanoInvalidoException, MensalidadeNaoNuloException, MensalidadeNaoNegativaException, MensalidadeNaoNumeroException, EmailIvalidoException, EmailCadastradoException, PlanoSemMensalidadeException {
        email = validarDados(nome,email,senha,tipo,cnpj, "pessoaJuridica");
        ValidadorUtils.validaString(razaoSocial, RazaoNaoNuloException::new);
        double mensalidade = validarPlano(plano, smensalidade);


        User novo = new UserPj(nome,email,senha,tipo,cnpj, razaoSocial, plano, mensalidade);
        String id = data.addUser(novo);

        return id;
    }

    public String getAtributoUsuario(String usuario, String atributo, String valor1, String numero, String validade) throws UsuarioNaoExisteException, IdNaoNuloException, NaoPfException, NaoPjException, AtributoNaoExisteException, PlanoSemMensalidadeException, NaoPixException, NaoCartaoException, NaoSaldoException {
        User consulta = data.obterUsuario(usuario);

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
            case "aluguelAtivo":
                if (consulta.getAluguelAtivo() == null) {
                    return "nenhum";
                }
                return consulta.getAluguelAtivo();
            case "totalPendencias":
                return ValidadorUtils.formatarMoeda(consulta.getTotalPendencias());
            case "metodoPagamento":
                Payment metodo = consulta.getMetodoPagamento();
                return metodo.getTipo();
            case "chavePix":
                Payment metodo2 = consulta.getMetodoPagamento();
                return metodo2.obterChave();
            case "numeroCartao":
                Payment metodo3 = consulta.getMetodoPagamento();
                return metodo3.obterNumero();
            case "validadeCartao":
                Payment metodo31 = consulta.getMetodoPagamento();
                return metodo31.obterValidade();
            case "saldo":
                Payment metodo4 = consulta.getMetodoPagamento();
                return ValidadorUtils.formatarMoeda(metodo4.obterSaldo());
            case "plano":
                return consulta.getPlano();
            case "mensalidade":
                if(consulta.getPlano().equals("avulso")){
                    throw new PlanoSemMensalidadeException();
                }
                return ValidadorUtils.formatarMoeda(consulta.getMensalidade());
            case "cpf":
                return consulta.obterCpf();
            case "cnpj":
                return consulta.obterCnpj();
            case "razaoSocial":
                return consulta.obterRazao();
            default:
                throw new AtributoNaoExisteException();
        }
    }

    public String getUserEmail(String semail) throws UsuarioNaoExisteException, EmailCadastradoException, EmailNaoNuloException, EmailIvalidoException {
        String email = validarEmail(semail);

        String id = data.getUserEmail(email);

        if(id == null){
            throw new UsuarioNaoExisteException();
        }
        return id;
    }

    public void alterarAtributos(String usuario, String atributo, String valor) throws UsuarioNaoExisteException, AtributoNaoExisteException, ValorNuloException, IdNaoNuloException, ValorNaoNegativaException, ValorNaoNumeroException, MensalidadeNaoNuloException, MensalidadeNaoNegativaException, MensalidadeNaoNumeroException, NaoPfException, NaoPjException, AtributoInvalidoException, NomeNaoNuloException, EmailNaoNuloException, EmailCadastradoException, EmailIvalidoException, SenhaNaoNuloException, PlanoInvalidoException, AtributoNaoAlteraException, NaoSaldoException, MetodoPagamentoInvalidoException {

        User consulta = data.obterUsuario(usuario);
        ValidadorUtils.validaString(atributo, AtributoNaoAlteraException::new);

        switch (atributo){
            case "nome" -> {
                ValidadorUtils.validaString(valor, NomeNaoNuloException::new);
                consulta.setNome(valor);
            }
            case "email" -> {
                String email = validarEmail(valor);
                String iddono = data.getUserEmail(email);
                if (iddono != null && !iddono.equals(usuario)) {
                    throw new EmailCadastradoException();
                }
                consulta.setEmail(email);
            }
            case "senha"-> {
                    ValidadorUtils.validaString(valor, SenhaNaoNuloException::new)
                ;
                consulta.setSenha(valor);
                }
            case "plano" -> {
                if (valor.equals("assinante")) {
                    if (!consulta.getPlano().equals("assinante")) {
                        consulta.setMensalidade(30.0);
                    }
                    consulta.setPlano(valor);
                } else if (valor.equals("avulso")) {
                    consulta.setPlano(valor);
                    consulta.setMensalidade(0.0);
                } else {
                    throw new PlanoInvalidoException();
                }
            }
            case "mensalidade" -> {
                double mensalidade = ValidadorUtils.conversaoDouble(valor, MensalidadeNaoNumeroException::new, MensalidadeNaoNegativaException::new, MensalidadeNaoNuloException::new);
                consulta.setMensalidade(mensalidade);
            }
            case "cpf" -> {
                consulta.attCpf(valor);
            }
            case "cnpj" -> {
                consulta.attCnpj(valor);
            }
            case "razaoSocial" -> {
                consulta.attRazao(valor);
            }
            case "metodoPagamento" -> {
                if (valor.equals("saldoPreCarregado")) {
                    PayBalance novo = new PayBalance("saldoPreCarregado", consulta.getSaldo());
                    consulta.setMetodoPagamento(novo);
                } else {
                    throw new MetodoPagamentoInvalidoException();
                }
            }
            case "saldo" -> {
                double sal = consulta.obterSaldo();
                sal += ValidadorUtils.conversaoDoublePositivo(valor, ValorNaoNumeroException::new, ValorNaoNegativaException::new, ValorNuloException::new);
                consulta.attSaldo(sal);
            }
            default -> {
                throw new AtributoNaoAlteraException();
            }
        }
    }

    public void alterarMetodoPix(String usuario, String atributo, String valor1, String chave) throws AtributoNaoExisteException, ValorNuloException, ChaveNaoNuloException, UsuarioNaoExisteException, IdNaoNuloException, AtributoInvalidoException, AtributoNaoAlteraException, MetodoPagamentoInvalidoException {

        User consulta = data.obterUsuario(usuario);

        if (atributo == null || !atributo.equals("metodoPagamento")) {
            throw new AtributoNaoAlteraException();
        }

        if (valor1 == null || !valor1.equals("pix")) {
            throw new MetodoPagamentoInvalidoException();
        }

        ValidadorUtils.validaString(chave, ChaveNaoNuloException::new);

        PayPix novo = new PayPix("pix", chave);
        consulta.setMetodoPagamento(novo);
    }

    public void alterarMetodoCard(String usuario, String atributo, String valor1, String numero, String validade) throws UsuarioNaoExisteException, IdNaoNuloException, AtributoNaoAlteraException, MetodoPagamentoInvalidoException, CartaoNaoNuloException, ValidadeNaoNulaException, ValidadeInvalidaException {

        User consulta = data.obterUsuario(usuario);

        if (atributo == null || !atributo.equals("metodoPagamento")) {
            throw new AtributoNaoAlteraException();
        }

        if (valor1 == null || !valor1.equals("cartao")) {
            throw new MetodoPagamentoInvalidoException();
        }

        ValidadorUtils.validaString(numero, CartaoNaoNuloException::new);
        ValidadorUtils.validaString(validade, ValidadeNaoNulaException::new);
        ValidadorUtils.validaValidadeCartao(validade);

        PayCard novo = new PayCard("cartao", numero, validade);
        consulta.setMetodoPagamento(novo);
    }

    public void removerUsuario(String id) throws UsuarioNaoExisteException, IdNaoNuloException, NaoRemoverException {
        User usuario = data.obterUsuario(id);

        if(usuario.getAluguelAtivo() != null){
            throw new NaoRemoverException();
        } else{
            data.removerUser(id);
        }
    }

    private String validarDados(String nome, String email, String senha, String tipo, String cpfOuCnpj, String tipoEsperado) throws NomeNaoNuloException, EmailNaoNuloException, SenhaNaoNuloException, CpfNaoNuloException, CnpjNaoNuloException, TipoInvalidoException, EmailIvalidoException, EmailCadastradoException {

        ValidadorUtils.validaString(nome,NomeNaoNuloException::new);
        email = validarEmail(email);

        if(data.getUserEmail(email) != null){
            throw new EmailCadastradoException();
        }

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

        return email;
    }
    private String validarEmail(String email) throws EmailNaoNuloException, EmailIvalidoException, EmailCadastradoException {
        ValidadorUtils.validaString(email, EmailNaoNuloException::new);

        email = ValidadorUtils.validaEmail(email);

        return email;
    }
    private double validarPlano(String plano, String smensalidade) throws PlanoNaoNuloException, PlanoInvalidoException, MensalidadeNaoNuloException, MensalidadeNaoNegativaException, MensalidadeNaoNumeroException, PlanoSemMensalidadeException {
        ValidadorUtils.validaString(plano, PlanoInvalidoException::new);

        if(plano.equals("avulso")){

            if (smensalidade != null && !smensalidade.isBlank() && !smensalidade.equals("0.0") && !smensalidade.equals("0")){

                throw new PlanoSemMensalidadeException();
            }

            return 0.0;

        } else if(plano.equals("assinante")){

            return ValidadorUtils.conversaoDouble(smensalidade, MensalidadeNaoNumeroException::new, MensalidadeNaoNegativaException::new, MensalidadeNaoNuloException::new);

        } else {
            throw new PlanoInvalidoException();
        }
    }
}
