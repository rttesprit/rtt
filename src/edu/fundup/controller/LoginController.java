package edu.fundup.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import edu.fundup.model.entity.Member;
import edu.fundup.model.service.MemberService;
import edu.fundup.utils.Tools;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.event.MouseMotionAdapter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Guideinfo
 */
public class LoginController extends VBox {

    private ResourceBundle bundle;
    private Locale locale;

    Label signinLabel;
    Label usernameLabel;
    Label pwdLabel;
    Label validateLabel;
    Label newOne;

    Button loginButton;
    private void loadLang(String lang){
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("Internationalization.lang", locale);
        signinLabel.setText(bundle.getString("signin"));
        usernameLabel.setText(bundle.getString("username"));
        pwdLabel.setText(bundle.getString("password"));
        validateLabel.setText(bundle.getString("validate"));
        newOne.setText(bundle.getString("new"));
        loginButton.setText(bundle.getString("validate"));
    }

    public LoginController(){

        VBox root = new VBox();
        root.setMinHeight(500);
        root.setMinWidth(500);
        root.setMaxHeight(500);
        root.setMaxWidth(500);

        StackPane background = new StackPane();
        background.setMinHeight(500);
        background.setMinWidth(500);
        background.setMaxHeight(500);
        background.setMaxWidth(500);
        background.setStyle("-fx-background-radius: 30px; -fx-background-color: rgba(0,0,0, 0.4);");

        signinLabel = new Label("Sign in");
        signinLabel.setTextFill(Color.web("#fdf1b8"));
        signinLabel.setStyle("-fx-font-size: 30;");
        GridPane grid = new GridPane();
        grid.getStyleClass().add("grid");
        grid.setVgap(15);
        grid.setHgap(15);
        grid.setAlignment(Pos.CENTER);

        usernameLabel = new Label("Username :");
        usernameLabel.setTextFill(Color.web("#fdf1b8"));
        usernameLabel.setStyle("-fx-font-size: 20;");

        pwdLabel = new Label("Password :");
        pwdLabel.setTextFill(Color.web("#fdf1b8"));
        pwdLabel.setStyle("-fx-font-size: 20;");

        newOne = new Label("Are you new ? Register from here.");
        newOne.setTextFill(Color.web("#fdf1b8"));
        newOne.setUnderline(true);
        newOne.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                newOne.setScaleX(1.2);
                newOne.setScaleY(1.2);
            }
        });

        newOne.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                newOne.setScaleX(1);
                newOne.setScaleY(1);
            }
        });
        newOne.setOnMouseClicked(event -> {
            this.getChildren().clear();
            InscriptionController inscri = new InscriptionController();
            this.getChildren().add(inscri);
        });

        TextField login = new TextField();
        PasswordField password = new PasswordField();

        login.setPromptText("Username");
        password.setPromptText("Password");

        // login.getStyleClass().add("text-field");
        // password.getStyleClass().add("text-field");

        login.setPrefWidth(300);
        login.setFont(new Font(20));
        login.setMaxSize(300,300);

        password.setPrefWidth(300);
        password.setFont(new Font(20));
        password.setMaxSize(300,300);


        grid.add(signinLabel, 0, 0);

        grid.add(usernameLabel, 0, 1);
        grid.add(login, 1, 1);

        grid.add(pwdLabel, 0, 2);
        grid.add(password, 1, 2);


        loginButton = new Button("Sign in");
        loginButton.getStyleClass().add("success");
        loginButton.setMinHeight(50);
        loginButton.setMaxHeight(50);
        loginButton.setMinWidth(100);
        loginButton.setMaxWidth(100);
        // loginButton.getStyleClass().add("sk-btn");
        // loginButton.getStyleClass().add("sk-btn-toolbar");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MemberService ms = new MemberService();
                Member m = new Member(login.getText(),password.getText());
                System.out.println("login : "+m.getLogin()+" "+"pass :"+m.getPassword());
                try {
                    ms.SignIn(m);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        Button goBack = new Button("Go Back");


        goBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("back");
            }
        });

        root.getChildren().addAll(grid,newOne,loginButton);
        VBox.setMargin(grid,new Insets(0, 0, 50, 0));
        VBox.setMargin(newOne,new Insets(0, 0, 20, 0));
        VBox.setMargin(loginButton,new Insets(0, 0, 100, 0));
        VBox.setMargin(goBack,new Insets(0, 0, 0, 0));
        root.setAlignment(Pos.CENTER);

        background.getChildren().add(root);
        this.getChildren().add(background);

        this.setStyle("-fx-background-image: url('/edu/fundup/ressources/images/contributor.jpg'); -fx-background-size: cover;");

        this.getStyleClass().add("loginComponent");
        this.setPrefSize(1000,1000);
        this.setAlignment(Pos.CENTER);
        this.getStylesheets().add("/edu/fundup/ressources/css/theme.css");
    }
}
