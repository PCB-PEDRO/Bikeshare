package br.ufal.ic.p2.bikeshare.controller;

import br.ufal.ic.p2.bikeshare.exeption.negocio.UsuarioNaoExisteException;
import br.ufal.ic.p2.bikeshare.exeption.validacao.nulos.IdNaoNuloException;
import br.ufal.ic.p2.bikeshare.models.User;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

public class DataSystem {
    private Map<String, User> usuarios = new LinkedHashMap<>();
    private int proximoid = 1;
    private Stack<String> undoStack = new Stack<>();
    private Stack<String> redoStack = new Stack<>();

    public User queryUser(String id) throws IdNaoNuloException, UsuarioNaoExisteException {
        if (id == null || id.isBlank()) {
            throw new IdNaoNuloException();
        }
        User usuario = this.usuarios.get(id);
        if (usuario == null) {
            throw new UsuarioNaoExisteException();
        }
        return usuario;
    }

    public String addUser(User novo){
        String key = "Uid" + proximoid;
        usuarios.put(key, novo);
        proximoid += 1;

        return key;
    }

    public void zerarSistema(){
        usuarios.clear();
        proximoid = 1;
    }

}
