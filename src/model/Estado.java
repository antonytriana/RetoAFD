package model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Antony
 */
public class Estado {

    private Set<String> estados;
    private Set<String> estadosFinales;
    private String estadoInicial;

    public Estado() {
        this.estados = new HashSet<String>();
        this.estadosFinales = new HashSet<String>();
    }

    public void agregarEstado(String[] estados) {
        for (String estado : estados) {
            this.estados.add(estado);
        }
    }

    public void agregarEstadoInicial(String estadoInicial) throws Exception {
        System.out.println(this.estados.contains(estadoInicial));
        if (!this.estados.contains(estadoInicial)) {
            System.out.println("Errrrrr");
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
