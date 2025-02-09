package com.example.demo;

import atlantafx.base.controls.ModalPane;
import atlantafx.base.theme.PrimerLight;
import atlantafx.base.theme.Styles;
import com.example.demo.components.Dialog;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material2.Material2OutlinedAL;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }

    public static double WIDTH;
    public static double HEIGHT;

    public static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("application.fxml"));
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();
//            StackPane root = fxmlLoader.load();
            Parent root = fxmlLoader.load();
            scene = new Scene(root, screenBounds.getWidth(), screenBounds.getMaxY() - 120);

//            scene = getScene(root,screenBounds.getWidth(), screenBounds.getMaxY() - 120);

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

    private Scene getScene(StackPane root,double width, double height){
//        StackPane root = new StackPane();
        VBox sceneRoot = new VBox();
        ModalPane aboutModalPane = new ModalPane();

        aboutModalPane.setId("aboutModal");
        aboutModalPane.displayProperty().addListener((obs, old, val) -> {
            if (!val) {
                aboutModalPane.setAlignment(Pos.CENTER);
                aboutModalPane.usePredefinedTransitionFactories(null);
            }
        });

        Dialog aboutDialog = new Dialog(300, 300);

        Button aboutDialogOpenBtn = new Button(null, new FontIcon(Material2OutlinedAL.INFO));
        aboutDialogOpenBtn.getStyleClass().addAll(Styles.ROUNDED);
        aboutDialogOpenBtn.setOnAction(evt -> {
            aboutModalPane.show(aboutDialog);
        });

        Button aboutDialogCloseBtn = new Button(null, new FontIcon(Material2OutlinedAL.CLOSE));
        aboutDialogCloseBtn.getStyleClass().addAll(Styles.ROUNDED);
        aboutDialogCloseBtn.setOnAction(evt -> {
            aboutModalPane.hide(true);
        });
        aboutDialog.getChildren().setAll(aboutDialogCloseBtn);

        sceneRoot.getChildren().addAll(aboutDialogOpenBtn);

        root.getChildren().addAll(sceneRoot, aboutModalPane);

        return new Scene(root,width,height);
    }
    public static void main(String[] args) {
        launch();
    }


}