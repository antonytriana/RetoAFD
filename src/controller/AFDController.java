package controller;

import model.Mensaje;
import model.AFD;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    private AFD afd = new AFD();

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
                    buttonEInicial.setDisable(true);
                    buttonEFinal.setDisable(false);
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
        Optional<String> result = Mensaje.leer("Estados finales", null,
                "Defina el o los estados finales");
        if (result.isPresent()) {
            if (result.get().length() != 0) {
                String elimiacionDeEspacios = result.get().replace(" ", "");
                String[] separacionSimbolos = elimiacionDeEspacios.split(",");
                try {
                    afd.getEstados().agregarEstadosFinales(separacionSimbolos);
                    labelEstadosFinales.setText(afd.getEstados().estadosFinales());
                    buttonEFinal.setDisable(true);
                    buttonTransiciones.setDisable(false);
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

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonEstados.setDisable(true);
        buttonEInicial.setDisable(true);
        buttonEFinal.setDisable(true);
        buttonTransiciones.setDisable(true);

    }

    private void crearAlfabeto(String[] simbolos) {
        try {
            afd.getAlfabeto().agregarSimbolo(simbolos);
            labelAlfabeto.setText(afd.getAlfabeto().toString());
            buttonEstados.setDisable(false);
            buttonAlfabeto.setDisable(true);
        } catch (Exception ex) {
            Mensaje.error(ex.getMessage());
        }
        System.out.println(afd.getAlfabeto());
    }

    private void crearEstados(String[] estados) {
        afd.getEstados().agregarEstado(estados);
        labelEstados.setText(afd.getEstados().toString());
        buttonEInicial.setDisable(false);
        buttonEstados.setDisable(true);
    }
}
