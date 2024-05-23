package just.demo3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root= fxmlLoader.load();
        // Charger l'icône
        Image icon = new Image(Objects.requireNonNull(getClass().getResource("icon2.png")).toExternalForm());

        // Définir l'icône de l'application
        stage.getIcons().add(icon);

        Data_transf.Loader_principal=fxmlLoader;
        Scene scene = new Scene(root);
        stage.setTitle("Pharma for all");
        stage.setResizable(true);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}