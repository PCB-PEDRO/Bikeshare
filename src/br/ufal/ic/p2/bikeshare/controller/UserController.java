package br.ufal.ic.p2.bikeshare.controller;

import br.ufal.ic.p2.bikeshare.controller.Data.DataSystem;
import br.ufal.ic.p2.bikeshare.exception.BikeshareException;
import br.ufal.ic.p2.bikeshare.exception.negocio.*;
import br.ufal.ic.p2.bikeshare.exception.validacao.nulos.*;
import br.ufal.ic.p2.bikeshare.exception.validacao.numericos.*;
import br.ufal.ic.p2.bikeshare.exception.validacao.tipos.*;
import br.ufal.ic.p2.bikeshare.models.Users.User;
import br.ufal.ic.p2.bikeshare.models.Users.UserPf;
import br.ufal.ic.p2.bikeshare.models.Users.UserPj;
import br.ufal.ic.p2.bikeshare.models.pay.PayBalance;
import br.ufal.ic.p2.bikeshare.models.pay.PayCard;
import br.ufal.ic.p2.bikeshare.models.pay.PayPix;
import br.ufal.ic.p2.bikeshare.models.pay.Payment;
import br.ufal.ic.p2.bikeshare.models.planos.Plano;
import br.ufal.ic.p2.bikeshare.models.planos.PlanoAssinante;
import br.ufal.ic.p2.bikeshare.models.planos.PlanoAvulso;
import br.ufal.ic.p2.bikeshare.utils.ValidadorUtils;

public class UserController {
    private DataSystem data;

    public UserController(DataSystem data) {
        this.data = data;
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cpf)
            throws BikeshareException {
        email = validarDados(nome, email, senha, tipo, cpf, "pessoaFisica");
        User novo = new UserPf(nome, email, senha, cpf, new PlanoAvulso());
        return data.addUser(novo);
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cnpj, String razaoSocial)
            throws BikeshareException {
        email = validarDados(nome, email, senha, tipo, cnpj, "pessoaJuridica");
        ValidadorUtils.validaString(razaoSocial, RazaoNaoNuloException::new);

        User novo = new UserPj(nome, email, senha, cnpj, razaoSocial, new PlanoAvulso());
        return data.addUser(novo);
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cpf, String plano,
            String smensalidade) throws BikeshareException {
        email = validarDados(nome, email, senha, tipo, cpf, "pessoaFisica");
        Plano planoObj = criarPlano(plano, smensalidade);

        User novo = new UserPf(nome, email, senha, cpf, planoObj);
        return data.addUser(novo);
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cnpj, String razaoSocial,
            String plano, String smensalidade) throws BikeshareException {
        email = validarDados(nome, email, senha, tipo, cnpj, "pessoaJuridica");
        ValidadorUtils.validaString(razaoSocial, RazaoNaoNuloException::new);
        Plano planoObj = criarPlano(plano, smensalidade);

        User novo = new UserPj(nome, email, senha, cnpj, razaoSocial, planoObj);
        return data.addUser(novo);
    }

    public String getAtributoUsuario(String usuario, String atributo) throws BikeshareException {
        User consulta = data.obterUsuario(usuario);
        ValidadorUtils.validaString(atributo, AtributoNaoExisteException::new);

        switch (atributo) {
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
                return consulta.getMetodoPagamento().obterChave();
            case "numeroCartao":
                return consulta.getMetodoPagamento().obterNumero();
            case "validadeCartao":
                return consulta.getMetodoPagamento().obterValidade();
            case "saldo":
                return ValidadorUtils.formatarMoeda(consulta.obterSaldo());
            case "plano":
                return consulta.obterNomePlano();
            case "mensalidade":
                return ValidadorUtils.formatarMoeda(consulta.obterMensalidade());
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

    public String getUserEmail(String semail) throws BikeshareException {
        String email = validarEmail(semail);

        String id = data.getUserEmail(email);

        if (id == null) {
            throw new UsuarioNaoExisteException();
        }
        return id;
    }

    public void alterarAtributos(String usuario, String atributo, String valor) throws BikeshareException {
        User consulta = data.obterUsuario(usuario);
        ValidadorUtils.validaString(atributo, AtributoNaoAlteraException::new);

        switch (atributo) {
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
            case "senha" -> {
                ValidadorUtils.validaString(valor, SenhaNaoNuloException::new);
                consulta.setSenha(valor);
            }
            case "plano" -> consulta.setPlano(selecionarPlanoAlteracao(valor, consulta.getPlano()));
            case "mensalidade" -> {
                double mensalidade = ValidadorUtils.conversaoDouble(valor, MensalidadeNaoNumeroException::new,
                        MensalidadeNaoNegativaException::new, MensalidadeNaoNuloException::new);
                consulta.getPlano().alterarMensalidade(mensalidade);
            }
            case "cpf" -> consulta.attCpf(valor);
            case "cnpj" -> consulta.attCnpj(valor);
            case "razaoSocial" -> consulta.attRazao(valor);
            case "metodoPagamento" -> {
                if ("saldoPreCarregado".equals(valor)) {
                    PayBalance novo = new PayBalance();
                    consulta.setMetodoPagamento(novo);
                } else {
                    throw new MetodoPagamentoInvalidoException();
                }
            }
            case "saldo" -> {
                consulta.obterSaldo();
                double valorAdicional = ValidadorUtils.conversaoDoublePositivo(valor, ValorNaoNumeroException::new,
                        ValorNaoNegativaException::new, ValorNuloException::new);
                consulta.recarregarSaldo(valorAdicional);
            }
            default -> throw new AtributoNaoAlteraException();
        }
    }

    public void alterarMetodoPix(String usuario, String atributo, String valor1, String chave)
            throws BikeshareException {
        User consulta = data.obterUsuario(usuario);

        if (!"metodoPagamento".equals(atributo)) {
            throw new AtributoNaoAlteraException();
        }

        if (!"pix".equals(valor1)) {
            throw new MetodoPagamentoInvalidoException();
        }

        ValidadorUtils.validaString(chave, ChaveNaoNuloException::new);

        PayPix novo = new PayPix(chave);
        consulta.setMetodoPagamento(novo);
    }

    public void alterarMetodoCard(String usuario, String atributo, String valor1, String numero, String validade)
            throws BikeshareException {
        User consulta = data.obterUsuario(usuario);

        if (!"metodoPagamento".equals(atributo)) {
            throw new AtributoNaoAlteraException();
        }

        if (!"cartao".equals(valor1)) {
            throw new MetodoPagamentoInvalidoException();
        }

        ValidadorUtils.validaString(numero, CartaoNaoNuloException::new);
        ValidadorUtils.validaString(validade, ValidadeNaoNulaException::new);
        ValidadorUtils.validaValidadeCartao(validade);

        PayCard novo = new PayCard(numero, validade);
        consulta.setMetodoPagamento(novo);
    }

    public void removerUsuario(String id) throws BikeshareException {
        User usuario = data.obterUsuario(id);

        if (usuario.getAluguelAtivo() != null) {
            throw new NaoRemoverException();
        } else {
            data.removerUser(id);
        }
    }

    private String validarDados(String nome, String email, String senha, String tipo, String cpfOuCnpj,
            String tipoEsperado) throws BikeshareException {
        ValidadorUtils.validaString(nome, NomeNaoNuloException::new);
        email = validarEmail(email);

        if (data.getUserEmail(email) != null) {
            throw new EmailCadastradoException();
        }

        ValidadorUtils.validaString(senha, SenhaNaoNuloException::new);
        if ("pessoaFisica".equals(tipo)) {
            ValidadorUtils.validaString(cpfOuCnpj, CpfNaoNuloException::new);
        } else if ("pessoaJuridica".equals(tipo)) {
            ValidadorUtils.validaString(cpfOuCnpj, CnpjNaoNuloException::new);
        }

        if (!tipoEsperado.equals(tipo)) {
            throw new TipoInvalidoException();
        }

        return email;
    }

    private String validarEmail(String email) throws BikeshareException {
        ValidadorUtils.validaString(email, EmailNaoNuloException::new);
        return ValidadorUtils.validaEmail(email);
    }

    private Plano selecionarPlanoAlteracao(String nomePlano, Plano planoAtual) throws PlanoInvalidoException {
        ValidadorUtils.validaString(nomePlano, PlanoInvalidoException::new);
        return switch (nomePlano) {
            case "assinante" -> planoAtual.migrarParaAssinante();
            case "avulso" -> new PlanoAvulso();
            default -> throw new PlanoInvalidoException();
        };
    }

    private Plano criarPlano(String nomePlano, String smensalidade) throws BikeshareException {
        ValidadorUtils.validaString(nomePlano, PlanoInvalidoException::new);
        return switch (nomePlano) {
            case "avulso" -> {
                if (smensalidade != null && !smensalidade.isBlank() && !"0.0".equals(smensalidade)
                        && !"0".equals(smensalidade)) {
                    throw new PlanoSemMensalidadeException();
                }
                yield new PlanoAvulso();
            }
            case "assinante" -> {
                double mensalidade = ValidadorUtils.conversaoDouble(smensalidade, MensalidadeNaoNumeroException::new,
                        MensalidadeNaoNegativaException::new, MensalidadeNaoNuloException::new);
                yield new PlanoAssinante(mensalidade);
            }
            default -> throw new PlanoInvalidoException();
        };
    }
}
