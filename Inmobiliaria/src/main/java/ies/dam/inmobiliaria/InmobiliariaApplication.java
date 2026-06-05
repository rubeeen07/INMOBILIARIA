package ies.dam.inmobiliaria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class InmobiliariaApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                InmobiliariaApplication.class.getResource("inmobiliaria-view.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load(), 950, 560);

        scene.getStylesheets().add(
                Objects.requireNonNull(
                        getClass().getResource("estilos.css")
                ).toExternalForm()
        );

        stage.setTitle("Inmobiliaria - Viviendas disponibles");
        stage.setScene(scene);
        stage.getIcons().add(
                new Image(
                        getClass().getResourceAsStream(
                                "/ies/dam/inmobiliaria/img/iconoAlquiler.png"
                        )
                )
        );
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
