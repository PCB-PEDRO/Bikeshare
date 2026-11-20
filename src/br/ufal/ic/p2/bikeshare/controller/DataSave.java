package br.ufal.ic.p2.bikeshare.controller;

import br.ufal.ic.p2.bikeshare.models.Users.User;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataSave {

    private Map<String, User> usuarios = new LinkedHashMap<>();
    private int proximoid;

    public DataSave(Map<String, User> usuarios, int proximoid) {
        this.usuarios = new LinkedHashMap<>(usuarios);
        this.proximoid = proximoid;
    }

    public DataSave(Map<String, User> usuarios, int proximoid, List<String> agendasCustomizadas) {
        this.usuarios = new LinkedHashMap<>(usuarios);
        this.proximoid = proximoid;
    }

    public DataSave() {
    }

    public Map<String, User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Map<String, User> usuarios) {
        this.usuarios = usuarios;
    }

    public int getProximoid() {
        return proximoid;
    }

    public void setProximoid(int proximoid) {
        this.proximoid = proximoid;
    }
}
