package model;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Antony
 */
public class Alfabeto {

    private Set<String> simbolo;

    public Alfabeto() {
        this.simbolo = new TreeSet<String>();
    }

    public Set<String> getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(Set<String> simbolo) {
        this.simbolo = simbolo;
    }

    public void agregarSimbolo(String[] simbolos) throws Exception {
        for (String simbolo : simbolos) {
            if (simbolo.length() != 1) {
                throw new Exception("El simbolo no puede ser de longitud superior a 1");
            }
            this.simbolo.add(simbolo);
        }
    }

    @Override
    public String toString() {
        return "{" + this.simbolo.toString().substring(1,
                this.simbolo.toString().length() - 1) + "}";
    }

}
