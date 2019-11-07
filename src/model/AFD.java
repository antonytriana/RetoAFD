package model;

import java.util.ArrayList;
import java.util.List;

public class AFD {

    private Estado estados;
    private Alfabeto alfabeto;
    private List<Transicion> transiciones;

    public AFD() {
        this.alfabeto = new Alfabeto();
        this.estados = new Estado();
        this.transiciones = new ArrayList<Transicion>();
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

    public boolean noPerteneceACadena(String cadena) {
        for (int i = 0; i < cadena.length(); i++) {
            if (!this.alfabeto.getSimbolo().contains(String.valueOf(cadena.charAt(i)))) {
                return true;
            }
        }
        return false;
    }

    public String funcionTransicion(String estadoActual, String simbolo) {
        for (Transicion transicion : transiciones) {
            if (transicion.getEstadoActual().equals(estadoActual)
                    && transicion.getSimbolo().equals(simbolo)) {
                return transicion.getEstadoSiguiente();
            }
        }
        return null;
    }

}
