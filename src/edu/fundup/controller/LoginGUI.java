/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.controller;

import edu.fundup.exception.ObjectNotFoundException;
import edu.fundup.model.entity.Membre;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author hhamzaoui
 */
public class LoginGUI extends HBox {

    public static Button BTN_LOGIN;

    public static Label TXT_USER;
    public static PasswordField TXT_PASSWORD;

    public LoginGUI() {

        // ------------initialisation------------
        BTN_LOGIN = new Button("login");
       // TXT_USER = new TextField();
        TXT_PASSWORD = new PasswordField();
        VBox rightPane = new VBox();
        VBox leftPane = new VBox();

        // ------------Styling------------ 
        rightPane.getStylesheets().add("/edu/fundup/ressources/css/theme.css");
        rightPane.setPadding(new Insets(20));
        rightPane.setSpacing(10);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setStyle("-fx-background-color:#34495e");
        rightPane.setPrefSize(300, 600);
        leftPane.setPrefSize(rightPane.getPrefWidth() * 3, rightPane.getPrefHeight());
        leftPane.setSpacing(15);
        leftPane.setPadding(new Insets(20, 10, 10, 20));
        BTN_LOGIN.getStyleClass().add("primary");
        BTN_LOGIN.setPrefWidth(150);
        TXT_USER.setFont(new Font(20));
        TXT_PASSWORD.setFont(new Font(20));

        loadLoginGuiLang();

        // ------------Logic------------
        Alert alert = new Alert(AlertType.WARNING);

        BTN_LOGIN.setOnMouseClicked(e -> {

        });
        leftPane.getChildren().addAll(TXT_USER, TXT_PASSWORD, BTN_LOGIN);
        this.getChildren().addAll(leftPane, rightPane);

    }

    /**
     *
     */
    public static void loadLoginGuiLang() {
        LoginGUI.BTN_LOGIN.setText("login");

    }
}
