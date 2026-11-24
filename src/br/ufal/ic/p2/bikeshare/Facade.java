package br.ufal.ic.p2.bikeshare;

import br.ufal.ic.p2.bikeshare.controller.AluguelController;
import br.ufal.ic.p2.bikeshare.controller.Data.DataSystem;
import br.ufal.ic.p2.bikeshare.controller.EstacaoController;
import br.ufal.ic.p2.bikeshare.controller.UserController;
import br.ufal.ic.p2.bikeshare.exeption.negocio.*;
import br.ufal.ic.p2.bikeshare.exeption.sistema.PersistenciaException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.nulos.*;
import br.ufal.ic.p2.bikeshare.exeption.validacao.numericos.*;
import br.ufal.ic.p2.bikeshare.exeption.validacao.tipos.*;

/**
 Facade
 */
public class Facade {
    private DataSystem data;
    private UserController userController;
    private EstacaoController estacaoController;
    private AluguelController aluguelController;

    public Facade() throws PersistenciaException {
        this.data = new DataSystem();
        this.userController = new UserController(data);
        this.estacaoController = new EstacaoController(data);
        this.aluguelController = new AluguelController(data);
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

    public String getAtributoUsuario(String usuario, String atributo) throws UsuarioNaoExisteException, IdNaoNuloException, NaoPfException, AtributoNaoExisteException, NaoPjException, PlanoSemMensalidadeException, NaoCartaoException, NaoSaldoException, NaoPixException {
        return userController.getAtributoUsuario(usuario,atributo,null,null, null);
    }

    public String getUsuarioPorEmail(String email) throws UsuarioNaoExisteException, EmailCadastradoException, EmailNaoNuloException, EmailIvalidoException {
        return userController.getUserEmail(email);
    }

    // =====================================================================
    // US3 - Alteracao de usuario (inclui metodo de pagamento)
    // =====================================================================

    public void alterarUsuario(String usuario, String atributo, String valor) throws UsuarioNaoExisteException, ValorNuloException, AtributoInvalidoException, IdNaoNuloException, NaoPfException, AtributoNaoExisteException, ValorNaoNegativaException, MensalidadeNaoNuloException, MensalidadeNaoNegativaException, ValorNaoNumeroException, MensalidadeNaoNumeroException, NaoPjException, NomeNaoNuloException, EmailCadastradoException, EmailNaoNuloException, EmailIvalidoException, SenhaNaoNuloException, PlanoInvalidoException, AtributoNaoAlteraException, NaoSaldoException, MetodoPagamentoInvalidoException {
        userController.alterarAtributos(usuario, atributo, valor);
    }

    public void alterarUsuario(String usuario, String atributo, String valor1, String chave) throws ValorNuloException, UsuarioNaoExisteException, AtributoInvalidoException, IdNaoNuloException, AtributoNaoExisteException, ChaveNaoNuloException, AtributoNaoAlteraException, MetodoPagamentoInvalidoException {
        userController.alterarMetodoPix(usuario, atributo, valor1, chave);
    }

    public void alterarUsuario(String usuario, String atributo, String valor1, String numero, String validade) throws ValorNuloException, ValidadeNaoNulaException, UsuarioNaoExisteException, AtributoInvalidoException, IdNaoNuloException, AtributoNaoExisteException, CartaoNaoNuloException, AtributoNaoAlteraException, ValidadeInvalidaException, MetodoPagamentoInvalidoException {
        userController.alterarMetodoCard(usuario, atributo, valor1, numero, validade);
    }

    // =====================================================================
    // US4 - Remocao de usuario
    // =====================================================================

    public void removerUsuario(String usuario) throws UsuarioNaoExisteException, IdNaoNuloException, NaoRemoverException {
        userController.removerUsuario( usuario);
    }

    // =====================================================================
    // US5 - Cadastro de estacao
    // =====================================================================

    public String criarEstacao(String nome, String endereco, String capacidade) throws NomeNaoNuloException, CapacidadeNaoNumeroException, CapacidadeNaoNegativaException, CapacidadeNaoNulaException, EnderecoNaoNuloException {
        return estacaoController.criarEstacao(nome, endereco, capacidade);
    }

    // =====================================================================
    // US6 - Gestao de bicicletas na estacao
    // =====================================================================

    public void adicionarBicicleta(String estacao, String bicicleta, String tipo) throws BicicletaJaExisteException, BicicletaSemBateriaException, IdBicicletaNulaException, BateriaNulaException, TipoInvalidoException, EstacaoNaoExisteException, EstacaoSemVagaException, Bateria0a100Exception, BateriaNaoNumeroException, IdEstacaoNulaException {
        estacaoController.adicionarBicicleta(estacao, bicicleta, tipo, null);
    }

    public void adicionarBicicleta(String estacao, String bicicleta, String tipo, String nivelBateria) throws BicicletaJaExisteException, BicicletaSemBateriaException, IdBicicletaNulaException, BateriaNulaException, TipoInvalidoException, EstacaoNaoExisteException, EstacaoSemVagaException, Bateria0a100Exception, BateriaNaoNumeroException, IdEstacaoNulaException {
        estacaoController.adicionarBicicleta(estacao, bicicleta, tipo, nivelBateria);
    }

    public void removerBicicleta(String estacao, String bicicleta) throws BicicletaNaoExisteException, BicicletaNaoEstacaoException, IdBicicletaNulaException, EstacaoNaoExisteException, IdEstacaoNulaException {
        estacaoController.removerBicicleta(estacao, bicicleta);
    }

    public String getAtributoBicicleta(String bicicleta, String atributo) throws AtributoInvalidoException, AtributoNaoAlteraException, BicicletaSemBateriaException, IdBicicletaNulaException, BicicletaNaoExisteException, AtributoNaoExisteException {
        return estacaoController.getAtributoBicicleta(bicicleta, atributo);
    }

    // =====================================================================
    // US7 - Consulta de disponibilidade
    // =====================================================================

    public String getBicicletasDisponiveis(String estacao) throws EstacaoNaoExisteException, IdEstacaoNulaException {
        return estacaoController.getBicicletasDisponiveis(estacao);
    }

    public String getVagasDisponiveis(String estacao) throws EstacaoNaoExisteException, IdEstacaoNulaException {
        return estacaoController.obterVagasDisponiveis(estacao);
    }

    public String getEstadoBicicleta(String bicicleta) throws BicicletaNaoExisteException, IdBicicletaNulaException {
        return estacaoController.getEstadoBicicleta(bicicleta);
    }

    // =====================================================================
    // US8 - Retirada de bicicleta
    // =====================================================================

    public String retirarBicicleta(String usuario, String estacao, String bicicleta, String data) throws BicicletaNaoExisteException, SaldoInsuficienteException, IdNaoNuloException, AluguelAtivoException, BicicletaNaoEstacaoException, BateriaInsuficienteException, UsuarioNaoExisteException, DataNaoNulaException, IdBicicletaNulaException, SemMetodoPagamentoException, EstacaoNaoExisteException, DataInvalidaException, IdEstacaoNulaException, UsuarioInadimplenteException {
        return aluguelController.retirarBicicleta(usuario, estacao, bicicleta, data);
    }

    // =====================================================================
    // US9 - Devolucao de bicicleta
    // =====================================================================

    public void devolverBicicleta(String aluguel, String estacao, String data) throws SaldoInsuficienteException, IdNaoNuloException, DataAnteriorARetiradaException, EstacaoCheiaException, UsuarioNaoExisteException, DataNaoNulaException, IdAluguelNaoNulaException, IdBicicletaNulaException, EstacaoSemVagaException, AluguelNaoAndamentoException, NaoSaldoException, AluguelNaoExisteException, EstacaoNaoExisteException, DataInvalidaException, IdEstacaoNulaException {
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

    public String getAtributoAluguel(String aluguel, String atributo) throws AtributoInvalidoException, AluguelAtivoException, IdAluguelNaoNulaException, AtributoNaoExisteException, AluguelNaoExisteException, AluguelEmAndamentoException {
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

    public void zerarSistema() throws PersistenciaException {
        data.zerarSistema();
    }

    public void encerrarSistema() throws PersistenciaException {
        data.salvarDados();
    }
}
