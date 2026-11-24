package br.ufal.ic.p2.bikeshare.models.Bikes;

import br.ufal.ic.p2.bikeshare.exception.negocio.BateriaInsuficienteException;
import br.ufal.ic.p2.bikeshare.exception.negocio.BicicletaIndisponivelException;
import br.ufal.ic.p2.bikeshare.exception.negocio.BicicletaSemBateriaException;

public abstract class Bike {
    private static final String ESTADO_DISPONIVEL = "disponivel";

    private String estado;
    private String estacao;

    public Bike(String estacao) {
        this.estado = ESTADO_DISPONIVEL;
        this.estacao = estacao;
    }

    public abstract String getTipo();

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstacao() {
        return estacao;
    }

    public void setEstacao(String estacao) {
        this.estacao = estacao;
    }

    public int obterNivelBateria() throws BicicletaSemBateriaException {
        throw new BicicletaSemBateriaException();
    }

    public boolean estaDisponivel() {
        return ESTADO_DISPONIVEL.equals(this.estado);
    }

    public void validarRetirada() throws BicicletaIndisponivelException, BateriaInsuficienteException {
        if (!ESTADO_DISPONIVEL.equals(this.estado)) {
            throw new BicicletaIndisponivelException();
        }
    }

    public double calcularAdicional(long minutos) {
        return 0.0;
    }

    //Xml

    public Bike() {
    }
}
