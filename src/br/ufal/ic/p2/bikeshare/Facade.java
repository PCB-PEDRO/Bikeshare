package br.ufal.ic.p2.bikeshare;

import br.ufal.ic.p2.bikeshare.controller.DataSystem;
import br.ufal.ic.p2.bikeshare.controller.UserController;
import br.ufal.ic.p2.bikeshare.exeption.negocio.*;
import br.ufal.ic.p2.bikeshare.exeption.sistema.PersistenciaException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.nulos.*;
import br.ufal.ic.p2.bikeshare.exeption.validacao.numericos.MensalidadeNaoNegativaException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.numericos.MensalidadeNaoNumeroException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.tipos.AtributoNaoExisteException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.tipos.EmailIvalidoException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.tipos.PlanoInvalidoException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.tipos.TipoInvalidoException;

/**
 Facade
 */
public class Facade {
    private DataSystem data;
    private UserController userController;

    public Facade() throws PersistenciaException {
        this.data = new DataSystem();
        this.userController = new UserController(data);
    }

    private static final String AINDA_NAO_IMPLEMENTADO = "Ainda não implementado.";

    // =====================================================================
    // US1 - Cadastro de usuario
    // =====================================================================

    public String criarUsuario(String nome, String email, String senha, String tipo, String cpfOuCnpj) throws NomeNaoNuloException, CnpjNaoNuloException, EmailNaoNuloException, SenhaNaoNuloException, CpfNaoNuloException, TipoInvalidoException, EmailIvalidoException, EmailCadastradoException {
        return userController.criarUsuario(nome, email, senha, tipo, cpfOuCnpj);
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cnpj, String razaoSocial) throws NomeNaoNuloException, RazaoNaoNuloException, CnpjNaoNuloException, EmailNaoNuloException, SenhaNaoNuloException, CpfNaoNuloException, TipoInvalidoException, EmailIvalidoException, EmailCadastradoException {
        return userController.criarUsuario(nome, email, senha, tipo, cnpj, razaoSocial);
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cpf, String plano, String mensalidade) throws NomeNaoNuloException, CnpjNaoNuloException, PlanoNaoNuloException, EmailNaoNuloException, SenhaNaoNuloException, MensalidadeNaoNuloException, CpfNaoNuloException, TipoInvalidoException, PlanoInvalidoException, MensalidadeNaoNegativaException, MensalidadeNaoNumeroException, EmailIvalidoException, EmailCadastradoException, PlanoSemMensalidadeException {
        return userController.criarUsuario(nome, email, senha, tipo, cpf, plano, mensalidade);
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cnpj, String razaoSocial, String plano, String mensalidade) throws NomeNaoNuloException, RazaoNaoNuloException, CnpjNaoNuloException, PlanoNaoNuloException, EmailNaoNuloException, SenhaNaoNuloException, MensalidadeNaoNuloException, CpfNaoNuloException, TipoInvalidoException, PlanoInvalidoException, MensalidadeNaoNegativaException, MensalidadeNaoNumeroException, EmailIvalidoException, EmailCadastradoException, PlanoSemMensalidadeException {
        return userController.criarUsuario(nome, email, senha, tipo, cnpj, razaoSocial, plano, mensalidade);
    }

    // =====================================================================
    // US2 - Consulta de usuario
    // =====================================================================

    public String getAtributoUsuario(String usuario, String atributo) throws UsuarioNaoExisteException, IdNaoNuloException, NaoPfException, AtributoNaoExisteException, NaoPjException, PlanoSemMensalidadeException {
        return userController.getAtributoUsuario(usuario,atributo,null,null, null);
    }

    public String getUsuarioPorEmail(String email) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // US3 - Alteracao de usuario (inclui metodo de pagamento)
    // =====================================================================

    public void alterarUsuario(String usuario, String atributo, String valor) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    public void alterarUsuario(String usuario, String atributo, String valor1, String chave) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    public void alterarUsuario(String usuario, String atributo, String valor1, String numero, String validade) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // US4 - Remocao de usuario
    // =====================================================================

    public void removerUsuario(String usuario) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // US5 - Cadastro de estacao
    // =====================================================================

    public String criarEstacao(String nome, String endereco, String capacidade) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // US6 - Gestao de bicicletas na estacao
    // =====================================================================

    public void adicionarBicicleta(String estacao, String bicicleta, String tipo) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    public void adicionarBicicleta(String estacao, String bicicleta, String tipo, String nivelBateria) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    public void removerBicicleta(String estacao, String bicicleta) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    public String getAtributoBicicleta(String bicicleta, String atributo) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // US7 - Consulta de disponibilidade
    // =====================================================================

    public String getBicicletasDisponiveis(String estacao) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    public String getVagasDisponiveis(String estacao) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    public String getEstadoBicicleta(String bicicleta) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // US8 - Retirada de bicicleta
    // =====================================================================

    public String retirarBicicleta(String usuario, String estacao, String bicicleta, String data) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // US9 - Devolucao de bicicleta
    // =====================================================================

    public void devolverBicicleta(String aluguel, String estacao, String data) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // US10 - Consulta de historico de alugueis
    // =====================================================================

    public String getHistoricoAlugueis(String usuario) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    public String getHistoricoAlugueis(String usuario, String atributo) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    public String getAtributoAluguel(String aluguel, String atributo) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // US11/US12 - Planos e tarifacao / criacao de novos planos
    // =====================================================================

    public String criarPlano(String nome, String franquiaMinutos, String valorMinutoExcedente, String mensalidade) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // US13 - Regras de bloqueio / lancamento manual de penalidade
    // =====================================================================

    public void lancaPenalidade(String usuario, String tipo, String valor, String motivo) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // US14 - Lancamento e conclusao de manutencao
    // =====================================================================

    public void lancaDefeito(String bicicleta, String descricao) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    public void concluiManutencao(String bicicleta) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // US15 - Relatorios
    // =====================================================================

    public String getTotalFaturado(String dataInicial, String dataFinal) {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // US16 - Undo/redo
    // =====================================================================

    public void undo() {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    public void redo() {
        throw new UnsupportedOperationException(AINDA_NAO_IMPLEMENTADO);
    }

    // =====================================================================
    // Ciclo de vida do sistema
    // =====================================================================

    public void zerarSistema() throws PersistenciaException {
        data.zerarSistema();
    }

    public void encerrarSistema() throws PersistenciaException {
        data.salvarDados();
    }
}
