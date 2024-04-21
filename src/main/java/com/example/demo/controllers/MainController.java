package com.example.demo.controllers;

import javafx.fxml.FXML;


public class MainController  {


    public NewsFeedController newsFeedController;

    public LiveUpdatesController liveUpdatesController;


    @FXML
    public void initialize() {
        this.newsFeedController = new NewsFeedController();
        this.liveUpdatesController = new LiveUpdatesController();
        this.newsFeedController.injectMainController(this);
    }


}