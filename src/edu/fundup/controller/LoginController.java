package edu.fundup.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import edu.fundup.model.entity.Member;
import edu.fundup.model.service.MemberService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.sql.SQLException;

/**
 * FXML Controller class
 *
 * @author Guideinfo
 */
public class LoginController extends VBox {

    public LoginController(){


        VBox loginComponent = new VBox();
        this.setPrefSize(400,400);
        this.setMaxWidth(500);
        this.setMaxHeight(500);
        this.getStyleClass().add("loginComponent");

        Label seConnecter = new Label("Se Connecter");
        seConnecter.setTextFill(Color.web("#0076a3"));
        seConnecter.getStyleClass().add("seConnecter");

        Label Username = new Label("Nom d'utilisateur :");
        Label pwd = new Label("Mot de passe :");

        TextField login = new TextField();
        PasswordField password = new PasswordField();

        login.setPromptText("Nom d'utilisateur");
        password.setPromptText("Mot de passe");

        login.getStyleClass().add("text-field");
        password.getStyleClass().add("text-field");

        login.setPrefWidth(300);
        login.setFont(new Font(20));
        login.setMaxSize(300,300);

        password.setPrefWidth(300);
        password.setFont(new Font(20));
        password.setMaxSize(300,300);

        Button goBack = new Button("Go Back");
        /*ImageView img = new ImageView("/edu/fundup/ressources/image/goBack.png");
        img.setFitHeight(20);
        img.setFitWidth(20);
        goBack.setGraphic(img);*/


        goBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("back");
            }
        });
        Button loginButton = new Button("Se connecter");
        loginButton.getStyleClass().add("sk-btn");
        loginButton.getStyleClass().add("sk-btn-toolbar");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MemberService ms = new MemberService();
                Member m = new Member(login.getText(),password.getText());
                System.out.println("login : "+m.getlogin()+" "+"pass :"+m.getPassword());
                try {
                    ms.SignIn(m);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        FlowPane flp = new FlowPane();
        flp.getChildren().add(goBack);
        loginComponent.getChildren().addAll(flp,seConnecter,Username,login,pwd,password,loginButton);
        loginComponent.setAlignment(Pos.CENTER);

        loginComponent.setPadding(new Insets(20,20,20,20));
        this.getStylesheets().add("/edu/fundup/ressources/css/theme.css");
        this.getChildren().add(loginComponent);
    }
}
