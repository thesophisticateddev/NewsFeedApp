package com.example.demo.controllers;

import atlantafx.base.controls.ModalPane;
import atlantafx.base.theme.Styles;
import com.example.demo.MainApplication;
import com.example.demo.components.Dialog;
import com.github.javafaker.Faker;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material2.Material2OutlinedAL;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.stream.IntStream;

public class LiveUpdatesController {

    public TextArea loggerTxtArea;

    @FXML
    public Button showDialogBtn;

    @FXML
    public StackPane liveUpdatesStackPane;

    @FXML
    public ModalPane modalPane;

    @FXML
    public VBox goldPriceDisplayChart;

    private void showDialogOnClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Checking in");
        alert.setHeaderText("This is title");
        alert.setContentText(Faker.instance().book().toString());
        alert.initOwner(MainApplication.scene.getWindow());
        System.out.println("Showing the alert");
        alert.show();
    }
    private void generateGraph(){

        final var rnd = Faker.instance().random();
        var x = new CategoryAxis();
        x.setLabel("Month");
        var y = new NumberAxis(0,80,10);
        y.setLabel("Value");
        var series1 = new XYChart.Series<String,Number>();
        series1.setName(Faker.instance().stock().nsdqSymbol());

        IntStream.range(1,12).forEach(i -> series1.getData().add(new XYChart.Data<>(Month.of(i).getDisplayName(
                TextStyle.SHORT, Locale.getDefault()),rnd.nextInt(10,80))));

        var series2 = new XYChart.Series<String,Number>();
        series2.setName(Faker.instance().stock().nsdqSymbol());

        IntStream.range(1,12).forEach(i -> series2.getData().add(new XYChart.Data<>(Month.of(i).getDisplayName(
                TextStyle.SHORT, Locale.getDefault()),rnd.nextInt(10,80))));
//        this.goldPriceDisplayChart = new LineChart(x,y);
        var lineChart = new LineChart(x,y);
        lineChart.setTitle("Gold cost monitoring");
        lineChart.setMinHeight(300);
        lineChart.getData().addAll(series1,series2);
        this.goldPriceDisplayChart.getChildren().add(lineChart);
    }
    private void initializeModal(){
        this.modalPane.displayProperty().addListener((obs, old, val) -> {
            if (!val) {
                this.modalPane.setAlignment(Pos.CENTER);
                this.modalPane.usePredefinedTransitionFactories(null);
            }
        });

        Dialog aboutDialog = new Dialog(300, 300);

//        this.showDialogBtn = new Button(null, new FontIcon(Material2OutlinedAL.INFO));5
        this.showDialogBtn.getStyleClass().addAll(Styles.ROUNDED);
        this.showDialogBtn.setOnAction(evt -> {
            this.modalPane.show(aboutDialog);
        });

        Button aboutDialogCloseBtn = new Button(null, new FontIcon(Material2OutlinedAL.CLOSE));
        aboutDialogCloseBtn.getStyleClass().addAll(Styles.ROUNDED);
        aboutDialogCloseBtn.setOnAction(evt -> {
            this.modalPane.hide(true);
        });
        aboutDialog.getChildren().setAll(aboutDialogCloseBtn);

        generateGraph();

    }
    @FXML
    public void initialize() {
        initializeModal();
    }
}
