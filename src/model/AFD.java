package model;

import java.util.List;

public class AFD {

    private Estado estados;
    private Alfabeto alfabeto;
    private List<Transicion> transiciones;

    public AFD() {
        this.alfabeto = new Alfabeto();
        this.estados = new Estado();
    }

    public Estado getEstados() {
        return estados;
    }

    public void setEstados(Estado estado) {
        this.estados = estado;
    }

    public Alfabeto getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(Alfabeto alfabeto) {
        this.alfabeto = alfabeto;
    }

    public List<Transicion> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(List<Transicion> transiciones) {
        this.transiciones = transiciones;
    }

}
