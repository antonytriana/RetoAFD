package model;

/**
 *
 * @author Antony
 */
public class Transicion {

    private String estadoActual;
    private String simbolo;
    private String estadoSiguiente;

    public Transicion(String estadoActual, String simbolo,
            String estadoSiguiente) {
        this.estadoActual = estadoActual;
        this.simbolo = simbolo;
        this.estadoSiguiente = estadoSiguiente;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getEstadoSiguiente() {
        return estadoSiguiente;
    }

    public void setEstadoSiguiente(String estadoSiguiente) {
        this.estadoSiguiente = estadoSiguiente;
    }

    @Override
    public String toString() {
        return "Transicion{" + "estadoActual=" + estadoActual + ","
                + " simbolo=" + simbolo + ","
                + " siguienteEstado=" + estadoSiguiente + '}';
    }

}
