package io.javasmithy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App extends Application {
    private final Properties MAVEN_PROPERTIES = new Properties();
    private final InputStream PROPS_INPUT_STREAM = getClass().getResourceAsStream("META-INF/maven/io.javasmithy/ClocksmithFX/pom.properties");

    @Override
    public void start(Stage stage) {
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(new AppController().getView()));
        stage.setAlwaysOnTop(true);
//        stage.setWidth(500);
//        stage.setHeight(250);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}