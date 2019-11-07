package model;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Antony
 */
public class Estado {

    private Set<String> estados;
    private Set<String> estadosFinales;
    private String estadoInicial;

    public Estado() {
        this.estados = new TreeSet<String>();
        this.estadosFinales = new TreeSet<String>();
    }

    public void agregarEstado(String[] estados) {
        for (String estado : estados) {
            this.estados.add(estado);
        }
    }

    public void agregarEstadoInicial(String estadoInicial) throws Exception {
        if (!this.estados.contains(estadoInicial)) {
            throw new Exception("El estado inicial debe pertenecer"
                    + " al conjunto de estado");
        }
        this.estadoInicial = estadoInicial;
    }

    public void agregarEstadosFinales(String[] estadosFinales) throws Exception {
        for (String eFinales : estadosFinales) {
            if (!this.estados.contains(eFinales)) {
                throw new Exception("Los estados finales deben"
                        + " pertenecer al conjunto de estado");
            }
            this.estadosFinales.add(eFinales);
        }
    }

    @Override
    public String toString() {
        return "{" + this.estados.toString().substring(1,
                this.estados.toString().length() - 1) + "}";
    }

    public String estadosFinales() {
        return "{" + this.estadosFinales.toString().substring(1,
                this.estadosFinales.toString().length() - 1) + "}";
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

}
