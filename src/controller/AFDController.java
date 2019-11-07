package controller;

import model.Mensaje;
import model.AFD;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Transicion;

public class AFDController implements Initializable {

    @FXML
    private Label labelAlfabeto;
    @FXML
    private Label labelEstados;
    @FXML
    private Label labelEstadoInicial;
    @FXML
    private Label labelEstadosFinales;
    @FXML
    private Button buttonEstados;
    @FXML
    private Button buttonAlfabeto;
    @FXML
    private Button buttonEInicial;
    @FXML
    private Button buttonEFinal;
    @FXML
    private Button buttonTransiciones;
    @FXML
    private Button buttonNuevoAFD;
    @FXML
    private VBox vBoxBotones;
    @FXML
    private VBox vBoxValidar;
    @FXML
    private TextField textCadena = new TextField();
    @FXML
    private TextArea textArea;

    private AFD afd;

    @FXML
    private void accionAlfabeto(ActionEvent event) {
        Optional<String> result = Mensaje.leer("Alfabeto",
                "Ingrese los simbolos del alfabeto separados por una coma",
                "Ejemplo: a,b,c,d");
        if (result.isPresent()) {
            if (result.get().length() != 0) {
                String elimiacionDeEspacios = result.get().replace(" ", "");
                String[] separacionSimbolos = elimiacionDeEspacios.split(",");
                crearAlfabeto(separacionSimbolos);
            } else {
                Mensaje.error("El alfabeto no puede estar vacio");
            }
        }
    }

    @FXML
    private void accionEstados(ActionEvent event) {
        Optional<String> result = Mensaje.leer("Estados",
                "Ingrese los estados del automata finito separados por una coma",
                "Ejemplo: q0,q1,q2,q3");
        if (result.isPresent()) {
            if (result.get().length() != 0) {
                String elimiacionDeEspacios = result.get().replace(" ", "");
                String[] separacionSimbolos = elimiacionDeEspacios.split(",");
                crearEstados(separacionSimbolos);
            } else {
                Mensaje.error("Los estados no pueden estar vacio");
            }
        }
    }

    @FXML
    private void accionEInicial(ActionEvent event) {
        Optional<String> result = Mensaje.leer("Estado inicial", null,
                "Defina el estado inicial");
        if (result.isPresent()) {
            if (result.get().length() != 0) {
                try {
                    afd.getEstados().agregarEstadoInicial(result.get());
                    labelEstadoInicial.setText(afd.getEstados().getEstadoInicial());
                    botones(false, true, true, true, true, false, true);
                } catch (Exception ex) {
                    Mensaje.error(ex.getMessage());
                }
            } else {
                Mensaje.error("El estado no puede estar vacio");
            }
        }
    }

    @FXML
    private void accionEFinal(ActionEvent event) {
        Optional<String> result = Mensaje.leer("Estados finales",
                "Defina el o los estados finales separados por coma",
                "Ejemplo: q2,q3");
        if (result.isPresent()) {
            if (result.get().length() != 0) {
                String elimiacionDeEspacios = result.get().replace(" ", "");
                String[] separacionSimbolos = elimiacionDeEspacios.split(",");
                try {
                    afd.getEstados().agregarEstadosFinales(separacionSimbolos);
                    labelEstadosFinales.setText(afd.getEstados().estadosFinales());
                    botones(false, true, true, true, true, true, false);
                } catch (Exception ex) {
                    Mensaje.error(ex.getMessage());
                }
            } else {
                Mensaje.error("Los estados no pueden estar vacio");
            }
        }
    }

    @FXML
    private void accionTransiciones(ActionEvent event) {
        crearTransiciones();
        botones(true, false, true, true, true, true, true);
        for (Transicion transicione : afd.getTransiciones()) {
            System.out.println(transicione.toString());
        }
    }

    @FXML
    private void accionNuevoAFD(ActionEvent event) {
        afd = new AFD();
        botones(false, true, false, true, true, true, true);
        labelAlfabeto.setText("vacio");
        labelEstados.setText("vacio");
        labelEstadoInicial.setText("vacio");
        labelEstadosFinales.setText("vacio");
    }

    @FXML
    private void accionValidar() {
        if (afd.noPerteneceACadena(textCadena.getText())) {
            Mensaje.error("La cadena ingresada no corresponde con el alfabeto");
        } else {
            validacionCadena();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        botones(false, false, true, true, true, true, true);

    }

    private void crearAlfabeto(String[] simbolos) {
        try {
            afd.getAlfabeto().agregarSimbolo(simbolos);
            labelAlfabeto.setText(afd.getAlfabeto().toString());
            botones(false, true, true, false, true, true, true);
        } catch (Exception ex) {
            Mensaje.error(ex.getMessage());
        }
    }

    private void crearEstados(String[] estados) {
        afd.getEstados().agregarEstado(estados);
        labelEstados.setText(afd.getEstados().toString());
        botones(false, true, true, true, false, true, true);
    }

    private void crearTransiciones() {
        Optional<String> result;
        List<String> lista = new ArrayList<String>(afd.getAlfabeto().getSimbolo());
        for (String estado : afd.getEstados().getEstados()) {
            int i = 0;
            while (i < lista.size()) {
                result = Mensaje.leerBotonDeshabilitado("Transiciones",
                        "Para ingresar vacio click en el botón Aceptar",
                        "δ(" + estado + ", " + lista.get(i) + ") ↦");
                if (result.isPresent()) {
                    if (result.get().length() == 0) {
                        afd.getTransiciones().add(new Transicion(estado, lista.get(i),
                                null));
                        i++;
                    } else if (!afd.getEstados().getEstados().contains(result.get())) {
                        Mensaje.error("El estado debe pertenecer al conjunto de estados");
                    } else {
                        afd.getTransiciones().add(new Transicion(estado, lista.get(i),
                                result.get()));
                        i++;
                    }
                }
            }
        }
    }

    private void validacionCadena() {
        String estado = afd.getEstados().getEstadoInicial();
        String simbolo;
        String siguienteEstado = "";
        String pasos = "W = " + textCadena.getText() + "\n";

        for (int i = 0; i < textCadena.getText().length(); i++) {
            simbolo = String.valueOf(textCadena.getText().charAt(i));

            pasos += "δ(" + estado + ", " + simbolo + ") ";

            siguienteEstado = afd.funcionTransicion(estado, simbolo);

            pasos += "= " + siguienteEstado + "\n";
            estado = siguienteEstado;
            if (siguienteEstado == null) {
                pasos += "Vacio, se detiene el proceso\n";
                break;
            }
        }

        if (siguienteEstado != null && afd.getEstados().getEstadosFinales()
                .contains(siguienteEstado)) {
            pasos += "W es Aceptada\n\n";
        } else {
            pasos += "W es Rechazada\n\n";
        }
        textArea.appendText(pasos);
    }

    public void botones(boolean vBoxValidar, boolean vBoxBotones,
            boolean buttonAlfabeto, boolean buttonEstados,
            boolean buttonEInicial, boolean buttonEFinal,
            boolean buttonTransiciones) {
        this.vBoxValidar.setVisible(vBoxValidar);
        this.vBoxBotones.setVisible(vBoxBotones);
        this.buttonAlfabeto.setDisable(buttonAlfabeto);
        this.buttonEstados.setDisable(buttonEstados);
        this.buttonEInicial.setDisable(buttonEInicial);
        this.buttonEFinal.setDisable(buttonEFinal);
        this.buttonTransiciones.setDisable(buttonTransiciones);
    }

}
