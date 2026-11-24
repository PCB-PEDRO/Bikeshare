package br.ufal.ic.p2.bikeshare.controller.Data;

import br.ufal.ic.p2.bikeshare.exception.negocio.AluguelNaoExisteException;
import br.ufal.ic.p2.bikeshare.exception.negocio.BicicletaNaoExisteException;
import br.ufal.ic.p2.bikeshare.exception.negocio.EstacaoNaoExisteException;
import br.ufal.ic.p2.bikeshare.exception.negocio.UsuarioNaoExisteException;
import br.ufal.ic.p2.bikeshare.exception.sistema.PersistenciaException;
import br.ufal.ic.p2.bikeshare.exception.validacao.nulos.IdAluguelNaoNulaException;
import br.ufal.ic.p2.bikeshare.exception.validacao.nulos.IdBicicletaNulaException;
import br.ufal.ic.p2.bikeshare.exception.validacao.nulos.IdEstacaoNulaException;
import br.ufal.ic.p2.bikeshare.exception.validacao.nulos.IdNaoNuloException;
import br.ufal.ic.p2.bikeshare.models.Alugueis.Aluguel;
import br.ufal.ic.p2.bikeshare.models.Bikes.Bike;
import br.ufal.ic.p2.bikeshare.models.Users.User;
import br.ufal.ic.p2.bikeshare.models.stations.Station;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

public class DataSystem {
    private Map<String, User> usuarios = new LinkedHashMap<>();
    private int proximoIdUsuario = 1;
    private Map<String, Station> estacoes = new LinkedHashMap<>();
    private int proximoIdEstacao = 1;
    private Map<String, Bike> bicicletas = new LinkedHashMap<>();
    private Map<String, Aluguel> alugueis = new LinkedHashMap<>();
    private int proximoIdAluguel = 1;

    private final PersistenciaXML persistencia = new PersistenciaXML();

    private Stack<String> undoStack = new Stack<>();
    private Stack<String> redoStack = new Stack<>();

    public DataSystem() throws PersistenciaException {
        carregarDados();
    }

    public void zerarSistema() throws PersistenciaException {
        usuarios.clear();
        proximoIdUsuario = 1;
        estacoes.clear();
        proximoIdEstacao = 1;
        bicicletas.clear();
        alugueis.clear();
        proximoIdAluguel = 1;
        salvarDados();
    }

    // User

    public String addUser(User novo) {
        String key = "U" + proximoIdUsuario;
        usuarios.put(key, novo);
        proximoIdUsuario += 1;

        return key;
    }

    public User obterUsuario(String id) throws IdNaoNuloException, UsuarioNaoExisteException {
        if (id == null || id.isBlank()) {
            throw new IdNaoNuloException();
        }
        User usuario = this.usuarios.get(id);
        if (usuario == null) {
            throw new UsuarioNaoExisteException();
        }
        return usuario;
    }

    public String getUserEmail(String email) {
        for (String id : usuarios.keySet()) {
            User usuario = usuarios.get(id);

            if (email != null && email.equals(usuario.getEmail())) {
                return id;
            }
        }
        return null;
    }

    public void removerUser(String id) throws UsuarioNaoExisteException {
        User remove = usuarios.remove(id);

        if (remove == null) {
            throw new UsuarioNaoExisteException();
        }
    }

    //station

    public String addStation(Station novo) {
        String key = "E" + proximoIdEstacao;
        estacoes.put(key, novo);
        proximoIdEstacao += 1;

        return key;
    }

    public Station obterEstacao(String id) throws EstacaoNaoExisteException, IdEstacaoNulaException {
        if (id == null || id.isBlank()) {
            throw new IdEstacaoNulaException();
        }
        Station estacao = this.estacoes.get(id);
        if (estacao == null) {
            throw new EstacaoNaoExisteException();
        }
        return estacao;
    }

    public void addBike(Bike nova, String key) {
        bicicletas.put(key, nova);
    }

    public boolean contemBicicleta(String id) {
        return id != null && this.bicicletas.containsKey(id);
    }

    public Bike obterBike(String id) throws IdBicicletaNulaException, BicicletaNaoExisteException {
        if (id == null || id.isBlank()) {
            throw new IdBicicletaNulaException();
        }
        Bike bike = this.bicicletas.get(id);
        if (bike == null) {
            throw new BicicletaNaoExisteException();
        }
        return bike;
    }

    public void removerBike(String remove) throws BicicletaNaoExisteException {
        Bike aux = bicicletas.remove(remove);

        if (aux == null) {
            throw new BicicletaNaoExisteException();
        }
    }

    //aluguel

    public String addAluguel(Aluguel novo) {
        String key = "A" + proximoIdAluguel;
        alugueis.put(key, novo);
        proximoIdAluguel += 1;

        return key;
    }

    public Aluguel obterAluguel(String id) throws IdAluguelNaoNulaException, AluguelNaoExisteException {
        if (id == null || id.isBlank()) {
            throw new IdAluguelNaoNulaException();
        }
        Aluguel aluguel = this.alugueis.get(id);
        if (aluguel == null) {
            throw new AluguelNaoExisteException();
        }
        return aluguel;
    }

    // persistencia xml

    public void salvarDados() throws PersistenciaException {
        DataSave dados = new DataSave(this.usuarios, this.proximoIdUsuario, this.estacoes, this.proximoIdEstacao, this.bicicletas, this.alugueis, this.proximoIdAluguel);
        persistencia.salvarDados(dados);
    }

    public void carregarDados() throws PersistenciaException {
        DataSave dadosLidos = persistencia.carregarDados();
        if (dadosLidos == null) {
            return;
        }

        this.usuarios = new LinkedHashMap<>(dadosLidos.getUsuarios());
        this.proximoIdUsuario = dadosLidos.getProximoIdUsuario();
        this.estacoes = new LinkedHashMap<>(dadosLidos.getEstacoes());
        this.proximoIdEstacao = dadosLidos.getProximoIdEstacao();
        this.bicicletas = new LinkedHashMap<>(dadosLidos.getBicicletas());
        this.alugueis = new LinkedHashMap<>(dadosLidos.getAlugueis());
        this.proximoIdAluguel = dadosLidos.getProximoIdAluguel();
    }
}
