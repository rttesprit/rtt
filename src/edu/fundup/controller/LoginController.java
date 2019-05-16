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
        Button frBtn = new Button("Francais");
        frBtn.setOnAction(e->{loadLang("fr");});

        VBox root = new VBox();
        root.setMinWidth(500);
        root.setMinHeight(500);

        StackPane background = new StackPane();
        background.setStyle("-fx-background-color: rgb(0,0,0, 0.1)");

        signinLabel = new Label("Sign in");
        signinLabel.setTextFill(Color.web("#0076a3"));

        GridPane grid = new GridPane();
        grid.getStyleClass().add("grid");
        grid.setVgap(15);
        grid.setHgap(15);
        grid.setAlignment(Pos.CENTER);

        usernameLabel = new Label("Username :");
        pwdLabel = new Label("Password :");
        newOne = new Label("Are you new ? Register from here.");
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
            root.getChildren().clear();

            Button registerPaperless = new Button("Become Paperless Member");

            registerPaperless.getStyleClass().add("success");
            registerPaperless.setMinHeight(80);
            registerPaperless.setMaxHeight(80);
            registerPaperless.setMinWidth(300);
            registerPaperless.setMaxWidth(300);

            registerPaperless.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    System.out.println("mouse Entered");
                }
            });

            Tools tool = new Tools();
            Tooltip tp1 = tool.createToolTip("Register as a Paperless Member");
            Tooltip tp2 = tool.createToolTip("Register as a Contributor Member");
            Tooltip tp3 = tool.createToolTip("Register as an Entreprise Member");

            registerPaperless.setTooltip(tp1);

            newOne.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    registerPaperless.setScaleX(1);
                    registerPaperless.setScaleY(1);
                }
            });


            Button registerContributor = new Button("Become Contributor Member");
            registerContributor.getStyleClass().add("info");
            registerContributor.setMinHeight(80);
            registerContributor.setMaxHeight(80);
            registerContributor.setMinWidth(300);
            registerContributor.setMaxWidth(300);
            registerContributor.setTooltip(tp2);


            Button registerEntreprise = new Button("Become an Entreprise Member");
            registerEntreprise.getStyleClass().add("warning");
            registerEntreprise.setMinHeight(80);
            registerEntreprise.setMaxHeight(80);
            registerEntreprise.setMinWidth(300);
            registerEntreprise.setMaxWidth(300);
            registerEntreprise.setTooltip(tp3);

            Button cancel = new Button("cancel");
            registerPaperless.setOnAction(rgpaction ->{
                RegisterPaperlessMember rgp = new RegisterPaperlessMember();
                root.getChildren().clear();
                root.getChildren().addAll(rgp,cancel);
                this.getChildren().remove(background);
                this.getChildren().add(root);
            });
            registerContributor.setOnAction(rgcaction ->{
                RegisterContributorController rgc = new RegisterContributorController();
                root.getChildren().clear();
                root.getChildren().addAll(rgc,cancel);
                this.getChildren().remove(background);
                this.getChildren().add(root);
            });
            registerEntreprise.setOnAction(rgeaction ->{
                RegisterEntrepriseController rge = new RegisterEntrepriseController();
                root.getChildren().clear();
                root.getChildren().addAll(rge,cancel);
                this.getChildren().remove(background);
                this.getChildren().add(root);
            });

            VBox.setMargin(registerPaperless, new Insets(0,0,30,0));
            VBox.setMargin(registerEntreprise, new Insets(30,0,0,0));

            root.getChildren().addAll(registerPaperless,registerContributor,registerEntreprise);
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

        root.getChildren().addAll(grid,newOne,loginButton,frBtn,goBack);
        VBox.setMargin(grid,new Insets(0, 0, 20, 0));
        VBox.setMargin(newOne,new Insets(0, 0, 20, 0));
        VBox.setMargin(loginButton,new Insets(0, 0, 100, 0));
        VBox.setMargin(goBack,new Insets(0, 0, 0, 0));
        root.setAlignment(Pos.CENTER);

        background.getChildren().add(root);
        this.getChildren().add(background);

        this.setMinWidth(500);
        this.setMinHeight(500);
        this.setMaxHeight(500);
        this.setMaxWidth(500);
        this.getStyleClass().add("loginComponent");

        this.getStylesheets().add("/edu/fundup/ressources/css/theme.css");
    }
}