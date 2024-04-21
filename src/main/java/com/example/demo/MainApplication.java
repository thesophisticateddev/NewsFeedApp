package com.example.demo;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }

    public static double WIDTH;
    public static double HEIGHT;

    @Override
    public void start(Stage stage) throws IOException {
        try {
            Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("application.fxml"));
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getMaxY() - 120);

            WIDTH = screenBounds.getWidth();
            HEIGHT = screenBounds.getHeight() - 120;
            stage.setTitle("NewsApp");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch();
    }


}