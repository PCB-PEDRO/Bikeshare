package br.ufal.ic.p2.bikeshare.controller.Data;

import br.ufal.ic.p2.bikeshare.exeption.negocio.EstacaoNaoExisteException;
import br.ufal.ic.p2.bikeshare.exeption.negocio.UsuarioNaoExisteException;
import br.ufal.ic.p2.bikeshare.exeption.sistema.PersistenciaException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.nulos.IdEstacaoNulaException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.nulos.IdNaoNuloException;
import br.ufal.ic.p2.bikeshare.models.Users.User;
import br.ufal.ic.p2.bikeshare.models.stations.Station;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

public class DataSystem {
    private Map<String, User> usuarios = new LinkedHashMap<>();
    private int proximoid = 1;
    private Map<String, Station> estacoes = new LinkedHashMap<>();
    private int proximoIdEstacao = 1;
    private Stack<String> undoStack = new Stack<>();
    private Stack<String> redoStack = new Stack<>();

    public DataSystem () throws PersistenciaException {
        carregarDados();
    }

    public void zerarSistema() throws PersistenciaException {
        usuarios.clear();
        proximoid = 1;
        estacoes.clear();
        proximoIdEstacao = 1;
        salvarDados();
    }

    // User

    public String addUser(User novo){
        String key = "U" + proximoid;
        usuarios.put(key, novo);
        proximoid += 1;

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

    public String getUserEmail(String email){

        for (String id : usuarios.keySet()) {
            User usuario = usuarios.get(id);

            if (usuario.getEmail().equals(email)) {
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

    public String addStation(Station novo){
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

    // persistencia xml

    public void salvarDados() throws PersistenciaException {
        try (FileOutputStream fos = new FileOutputStream("ShareBankData.xml");
             XMLEncoder encoder = new XMLEncoder(fos)) {

            DataSave dados = new DataSave(this.usuarios, this.proximoid, this.estacoes, this.proximoIdEstacao);
            encoder.writeObject(dados);

        } catch (Exception e) {
            throw new PersistenciaException();
        }
    }

    public void carregarDados() throws PersistenciaException {
        File arquivo = new File("ShareBankData.xml");

        if (!arquivo.exists()) {
            return;
        }

        try (FileInputStream fis = new FileInputStream(arquivo); XMLDecoder decoder = new XMLDecoder(fis)) {

            DataSave dadosLidos = (DataSave) decoder.readObject();

            this.usuarios = new LinkedHashMap<>(dadosLidos.getUsuarios());
            this.proximoid = dadosLidos.getProximoid();
            this.estacoes = new LinkedHashMap<>(dadosLidos.getEstacoes());
            this.proximoIdEstacao = dadosLidos.getProximoIdEstacao();

        } catch (Exception e) {
            throw new PersistenciaException();
        }
    }
}
