package br.ufal.ic.p2.bikeshare;

import br.ufal.ic.p2.bikeshare.controller.AluguelController;
import br.ufal.ic.p2.bikeshare.controller.Data.DataSystem;
import br.ufal.ic.p2.bikeshare.controller.EstacaoController;
import br.ufal.ic.p2.bikeshare.controller.UserController;
import br.ufal.ic.p2.bikeshare.exception.BikeshareException;

/**
 Facade
 */
public class Facade {
    private DataSystem data;
    private UserController userController;
    private EstacaoController estacaoController;
    private AluguelController aluguelController;

    public Facade() throws BikeshareException {
        this.data = new DataSystem();
        this.userController = new UserController(data);
        this.estacaoController = new EstacaoController(data);
        this.aluguelController = new AluguelController(data);
    }

    private static final String AINDA_NAO_IMPLEMENTADO = "Ainda não implementado.";

    // =====================================================================
    // US1 - Cadastro de usuario
    // =====================================================================

    public String criarUsuario(String nome, String email, String senha, String tipo, String cpfOuCnpj) throws BikeshareException {
        return userController.criarUsuario(nome, email, senha, tipo, cpfOuCnpj);
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cnpj, String razaoSocial) throws BikeshareException {
        return userController.criarUsuario(nome, email, senha, tipo, cnpj, razaoSocial);
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cpf, String plano, String mensalidade) throws BikeshareException {
        return userController.criarUsuario(nome, email, senha, tipo, cpf, plano, mensalidade);
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cnpj, String razaoSocial, String plano, String mensalidade) throws BikeshareException {
        return userController.criarUsuario(nome, email, senha, tipo, cnpj, razaoSocial, plano, mensalidade);
    }

    // =====================================================================
    // US2 - Consulta de usuario
    // =====================================================================

    public String getAtributoUsuario(String usuario, String atributo) throws BikeshareException {
        return userController.getAtributoUsuario(usuario, atributo);
    }

    public String getUsuarioPorEmail(String email) throws BikeshareException {
        return userController.getUserEmail(email);
    }

    // =====================================================================
    // US3 - Alteracao de usuario (inclui metodo de pagamento)
    // =====================================================================

    public void alterarUsuario(String usuario, String atributo, String valor) throws BikeshareException {
        userController.alterarAtributos(usuario, atributo, valor);
    }

    public void alterarUsuario(String usuario, String atributo, String valor1, String chave) throws BikeshareException {
        userController.alterarMetodoPix(usuario, atributo, valor1, chave);
    }

    public void alterarUsuario(String usuario, String atributo, String valor1, String numero, String validade) throws BikeshareException {
        userController.alterarMetodoCard(usuario, atributo, valor1, numero, validade);
    }

    // =====================================================================
    // US4 - Remocao de usuario
    // =====================================================================

    public void removerUsuario(String usuario) throws BikeshareException {
        userController.removerUsuario(usuario);
    }

    // =====================================================================
    // US5 - Cadastro de estacao
    // =====================================================================

    public String criarEstacao(String nome, String endereco, String capacidade) throws BikeshareException {
        return estacaoController.criarEstacao(nome, endereco, capacidade);
    }

    // =====================================================================
    // US6 - Gestao de bicicletas na estacao
    // =====================================================================

    public void adicionarBicicleta(String estacao, String bicicleta, String tipo) throws BikeshareException {
        estacaoController.adicionarBicicleta(estacao, bicicleta, tipo, null);
    }

    public void adicionarBicicleta(String estacao, String bicicleta, String tipo, String nivelBateria) throws BikeshareException {
        estacaoController.adicionarBicicleta(estacao, bicicleta, tipo, nivelBateria);
    }

    public void removerBicicleta(String estacao, String bicicleta) throws BikeshareException {
        estacaoController.removerBicicleta(estacao, bicicleta);
    }

    public String getAtributoBicicleta(String bicicleta, String atributo) throws BikeshareException {
        return estacaoController.getAtributoBicicleta(bicicleta, atributo);
    }

    // =====================================================================
    // US7 - Consulta de disponibilidade
    // =====================================================================

    public String getBicicletasDisponiveis(String estacao) throws BikeshareException {
        return estacaoController.getBicicletasDisponiveis(estacao);
    }

    public String getVagasDisponiveis(String estacao) throws BikeshareException {
        return estacaoController.obterVagasDisponiveis(estacao);
    }

    public String getEstadoBicicleta(String bicicleta) throws BikeshareException {
        return estacaoController.getEstadoBicicleta(bicicleta);
    }

    // =====================================================================
    // US8 - Retirada de bicicleta
    // =====================================================================

    public String retirarBicicleta(String usuario, String estacao, String bicicleta, String data) throws BikeshareException {
        return aluguelController.retirarBicicleta(usuario, estacao, bicicleta, data);
    }

    // =====================================================================
    // US9 - Devolucao de bicicleta
    // =====================================================================

    public void devolverBicicleta(String aluguel, String estacao, String data) throws BikeshareException {
        aluguelController.devolverBicicleta(aluguel, estacao, data);
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

    public String getAtributoAluguel(String aluguel, String atributo) throws BikeshareException {
        return aluguelController.getAtributoAluguel(aluguel, atributo);
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

    public void zerarSistema() throws BikeshareException {
        data.zerarSistema();
    }

    public void encerrarSistema() throws BikeshareException {
        data.salvarDados();
    }
}
