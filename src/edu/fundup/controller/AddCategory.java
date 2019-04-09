/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.controller;

import static edu.fundup.controller.Acceuil.LAB_POST;
import static edu.fundup.controller.LoginGUI.BTN_LOGIN;
import static edu.fundup.controller.LoginGUI.TXT_PASSWORD;
import static edu.fundup.controller.LoginGUI.TXT_USER;
import static edu.fundup.controller.LoginGUI.loadLoginGuiLang;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author hhamzaoui
 */
public class AddCategory extends VBox {

    public static Button BTN_ADD_Cat;

    public AddCategory() {

        this.getStylesheets().add("/edu/fundup/ressources/css/theme.css");

        this.setAlignment(Pos.CENTER);

        this.setPadding(new Insets(20, 10, 10, 20));
        BTN_ADD_Cat=new Button("add category");
        BTN_ADD_Cat.getStyleClass().add("danger");
        BTN_ADD_Cat.setPrefWidth(290);
        BTN_ADD_Cat.setMinHeight(100);
        loadLoginGuiLang();
        this.getChildren().addAll(BTN_ADD_Cat);
    }
    
      public static void loadLoginGuiLang() {
        //LoginGUI.BTN_LOGIN.setText("login");

    }
}
