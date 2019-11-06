
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Principal extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Principal.fxml"));
        primaryStage.setTitle("Reto AFD");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 349, 388));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
