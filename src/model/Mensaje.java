package model;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.StageStyle;

/**
 *
 * @author Antony
 */
public class Mensaje {

    public static void error(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static Optional<String> leer(String titulo, String cabecera,
            String contenido) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(titulo);
        dialog.setHeaderText(cabecera);
        dialog.setContentText(contenido);
        return dialog.showAndWait();
    }

    public static Optional<String> leerBotonDeshabilitado(String titulo, String cabecera,
            String contenido) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(titulo);
        dialog.setHeaderText(cabecera);
        dialog.setContentText(contenido);
        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
        return dialog.showAndWait();
    }
}
