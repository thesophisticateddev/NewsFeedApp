module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires atlantafx.base;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.materialdesign2;
    requires org.kordamp.ikonli.feather;
    requires org.kordamp.ikonli.material2;
    requires javafaker;
    requires org.jetbrains.annotations;
    requires java.sql;
    requires retrofit2;
    requires com.fasterxml.jackson.databind;
    requires retrofit2.converter.jackson;
    requires log4j;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo.dto;
    exports com.example.demo;
    exports com.example.demo.client;
    opens com.example.demo.client to javafx.fxml;
    exports com.example.demo.controllers;
    opens com.example.demo.controllers to javafx.fxml;
    exports com.example.demo.components;
    opens com.example.demo.components to javafx.fxml;
}