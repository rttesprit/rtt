package edu.fundup.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import edu.fundup.model.entity.Member;
import edu.fundup.model.service.MemberService;
import edu.fundup.utils.RegisterValidation;
import edu.fundup.utils.SendMail;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import org.apache.commons.lang3.RandomStringUtils;


import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class RegisterPaperlessMember extends HBox {

    ImageView pic;

    public RegisterPaperlessMember() {

        MemberService ms = new MemberService();
        ArrayList<String> logins = ms.getAllLogin();

        /*3 STEPS*/
        Label accountData = new Label("Step 1/3      Account Data");
        Label profileData = new Label("Step 2/3      Profile Data");
        Label finalStep = new Label("Final step      Confirmation");

        StackPane titleBox1 = new StackPane();
        titleBox1.getChildren().add(accountData);
        titleBox1.setMinHeight(60);
        titleBox1.setMaxHeight(60);
        accountData.setStyle(" -fx-text-fill: WHITE; -fx-font-family: 'Times New Roman'; -fx-font-size: 24px;");
        titleBox1.setStyle("-fx-background-color: rgba(45,203,112,0.5);\n" +
                "    -fx-border-color: rgba(45,203,112,0.8);\n");

        StackPane titleBox2 = new StackPane();
        titleBox2.getChildren().add(profileData);
        titleBox2.setMinHeight(60);
        titleBox2.setMaxHeight(60);
        profileData.setStyle(" -fx-text-fill: WHITE; -fx-font-family: 'Times New Roman'; -fx-font-size: 24px;");
        titleBox2.setStyle("-fx-background-color: rgba(45,203,112,0.5);\n" +
                "    -fx-border-color: rgba(45,203,112,0.8);\n");

        StackPane titleBox3 = new StackPane();
        titleBox3.getChildren().add(finalStep);
        titleBox3.setMinHeight(60);
        titleBox3.setMaxHeight(60);
        finalStep.setStyle(" -fx-text-fill: WHITE; -fx-font-family: 'Times New Roman'; -fx-font-size: 24px;");
        titleBox3.setStyle("-fx-background-color: rgba(45,203,112,0.5);\n" +
                "    -fx-border-color: rgba(45,203,112,0.8);\n");



        /********/

        /******* VBox 1 **********/
        VBox box1 = new VBox();

        Label username = new Label(" Username :");
        Label passwordLabel = new Label("Password :");
        Label passwordConfirmationLabel = new Label("Re-enter pass :");
        Label emailLabel = new Label("E-Mail : ");



        username.setStyle(" -fx-font-family: 'Lucida Sans Unicode'; ");
        passwordConfirmationLabel.setStyle(" -fx-font-family: 'Lucida Sans Unicode'; ");
        passwordLabel.setStyle(" -fx-font-family: 'Lucida Sans Unicode'; ");
        emailLabel.setStyle(" -fx-font-family: 'Lucida Sans Unicode'; ");


        TextField login = new TextField();
        login.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        PasswordField passwordConfirmation = new PasswordField();
        passwordConfirmation.setPromptText("Password");
        TextField email = new TextField();
        email.setPromptText("E-Mail");

        GridPane grid1 = new GridPane();
        grid1.getStyleClass().add("grid");
        grid1.setVgap(15);
        grid1.setHgap(15);
        grid1.setAlignment(Pos.CENTER);


        grid1.add(username, 0, 1);
        grid1.add(login, 1, 1);

        grid1.add(passwordLabel, 0, 2);
        grid1.add(password, 1, 2);

        grid1.add(passwordConfirmationLabel, 0, 3);
        grid1.add(passwordConfirmation, 1, 3);

        grid1.add(emailLabel, 0, 4);
        grid1.add(email, 1, 4);

        Button nextToBox2 = new Button();
        nextToBox2.setStyle("-fx-background-image: url('/edu/fundup/ressources/images/arrow-flat.png'); -fx-border-width: 0;" +
                " -fx-background-color: none; -fx-background-repeat: no-repeat; -fx-background-size: 40px; -fx-background-position: center; ");
        nextToBox2.getStyleClass().add("rounded");


        box1.setMinWidth(500);
        box1.setMinHeight(500);

        box1.getChildren().addAll(titleBox1, grid1);

        StackPane stack1 = new StackPane();
        stack1.setStyle("-fx-background-color: rgb(0,0,0, 0.1)");

        stack1.getChildren().addAll(box1, nextToBox2);
        stack1.setMargin(nextToBox2, new Insets(480, 15, 25, 425));


        //*************************

        /******* VBox 2 **********/
        VBox box2 = new VBox();

        Label first_nameLabel = new Label("First name : ");
        Label last_nameLabel = new Label("Last name : ");
        Label addressLabel = new Label("Address : ");
        Label countryLabel = new Label("Country : ");
        Label cityLabel = new Label("City : ");

        TextField last_name = new TextField();
        last_name.setPromptText("Last name");
        TextField first_name = new TextField();
        first_name.setPromptText("First name");
        TextField address = new TextField();
        address.setPromptText("Address");
        TextField city = new TextField();
        city.setPromptText("City");
        TextField country = new TextField();
        country.setPromptText("Country");

        GridPane grid2 = new GridPane();
        grid2.getStyleClass().add("grid");
        grid2.setVgap(15);
        grid2.setHgap(15);
        grid2.setAlignment(Pos.CENTER);

        grid2.add(first_nameLabel, 0, 1);
        grid2.add(first_name, 1, 1);

        grid2.add(last_nameLabel, 0, 2);
        grid2.add(last_name, 1, 2);

        grid2.add(countryLabel, 0, 3);
        grid2.add(country, 1, 3);

        grid2.add(cityLabel, 0, 4);
        grid2.add(city, 1, 4);

        grid2.add(addressLabel, 0, 5);
        grid2.add(address, 1, 5);

        Button backToBox1 = new Button();
        backToBox1.setStyle("-fx-background-image: url('/edu/fundup/ressources/images/arrow-flat2.png'); -fx-border-width: 0;" +
                " -fx-background-color: none; -fx-background-repeat: no-repeat; -fx-background-size: 40px; -fx-background-position: center; ");
        backToBox1.getStyleClass().add("rounded");

        Button btn = new Button("Register");
        btn.getStyleClass().add("success");

        box2.setMinWidth(500);
        box2.setMinHeight(500);

        StackPane stack2 = new StackPane();
        stack2.setStyle("-fx-background-color: rgb(0,0,0, 0.1)");


        box2.getChildren().addAll(titleBox2, grid2);
        stack2.getChildren().addAll(box2, backToBox1, btn);

        stack2.setMargin(backToBox1, new Insets(480, 425, 25, 15));
        stack2.setMargin(btn, new Insets(425, 0, 0, 0));

        //************************************

        backToBox1.setOnAction(event -> {
            this.getChildren().remove(stack2);
            this.getChildren().add(stack1);
        });

        nextToBox2.setOnAction(event -> {
            if (RegisterValidation.checkUsername(login.getText(),logins) &&
                    RegisterValidation.validatePassword(password.getText()) &&
                    RegisterValidation.identicPassword(password.getText(),passwordConfirmation.getText()) &&
                    RegisterValidation.validateMail(email.getText())
            ) {
                this.getChildren().remove(stack1);
                this.getChildren().add(stack2);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid fields");
                alert.setHeaderText(null);
                alert.setContentText("All fields are required");
                alert.showAndWait();
            }
        });

        Label logErrorLab = new Label("Username must be unique and contains only letters ");
        Label passwordErrorLab = new Label(" Your password length must be in (6-15) and   must contain special charachters");
        Label identicErrorLab = new Label(" Your password fields must be identical ");
        Label mailLab = new Label("Please set your valid E-mail");

        passwordErrorLab.setWrapText(true);
        logErrorLab.setWrapText(true);

        logErrorLab.setMaxWidth(480);
        identicErrorLab.setMaxWidth(480);
        passwordErrorLab.setMaxWidth(480);
        mailLab.setMaxWidth(480);

        logErrorLab.getStyleClass().add("tag");
        passwordErrorLab.getStyleClass().add("tag");
        identicErrorLab.getStyleClass().add("tag");
        mailLab.getStyleClass().add("tag");


        login.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (RegisterValidation.checkUsername(login.getText(),logins)) {

                    login.getStyleClass().remove("error");

                    stack1.getChildren().remove(logErrorLab);
                    username.getStyleClass().clear();

                    System.out.println("esm shih");

                } else {
                    if ((!RegisterValidation.checkUsername(login.getText(),logins)) && (newPropertyValue!=true)) {
                        login.getStyleClass().add("error");
                        username.getStyleClass().add("wronglabel");

                        if (stack1.getChildren().contains(identicErrorLab)) {
                            stack1.getChildren().remove(identicErrorLab);
                        }
                        stack1.getChildren().add(logErrorLab);

                        stack1.setMargin(logErrorLab, new Insets(180, 0, 0, 0));
                        System.out.println("new Property boucle 2 : " + newPropertyValue);
                        System.out.println("old Property boucle 2 : " + oldPropertyValue);
                        System.out.println("*****");

                    }
                }
            }
        });
        passwordConfirmation.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (RegisterValidation.identicPassword(password.getText(), passwordConfirmation.getText())) {

                    passwordConfirmation.getStyleClass().remove("error");

                    stack1.getChildren().remove(identicErrorLab);
                    passwordConfirmationLabel.getStyleClass().clear();

                    System.out.println("pass identiques");

                } else {
                    if ((!(RegisterValidation.identicPassword(password.getText(), passwordConfirmation.getText()))) && (newPropertyValue!=true) ) {

                        passwordConfirmation.getStyleClass().add("error");
                        passwordConfirmationLabel.getStyleClass().add("wronglabel");
                        if (stack1.getChildren().contains(logErrorLab)){
                            stack1.getChildren().remove(logErrorLab);
                        }
                        stack1.getChildren().add(identicErrorLab);
                        stack1.setMargin(identicErrorLab, new Insets(180, 0, 0, 0));
                        System.out.println("new Property boucle 2 : " + newPropertyValue);
                        System.out.println("old Property boucle 2 : " + oldPropertyValue);
                        System.out.println("*****");
                    }
                }
            }
        });
        password.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (RegisterValidation.validatePassword(password.getText())) {

                    password.getStyleClass().remove("error");

                    stack1.getChildren().remove(passwordErrorLab);
                    passwordLabel.getStyleClass().clear();

                } else {
                    if ((!(RegisterValidation.validatePassword(password.getText()))) && (newPropertyValue!=true)) {
                        password.getStyleClass().add("error");
                        passwordLabel.getStyleClass().add("wronglabel");
                        if (stack1.getChildren().contains(mailLab)) {
                            stack1.getChildren().remove(mailLab);
                        }
                        stack1.getChildren().add(passwordErrorLab);
                        stack1.setMargin(passwordErrorLab, new Insets(320, 0, 0, 0));
                        System.out.println("new Property boucle 2 : " + newPropertyValue);
                        System.out.println("old Property boucle 2 : " + oldPropertyValue);
                        System.out.println("*****");
                    }
                }
            }
        });
        email.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (RegisterValidation.validateMail(email.getText())) {

                    email.getStyleClass().remove("error");

                    stack1.getChildren().remove(mailLab);
                    emailLabel.getStyleClass().clear();

                    System.out.println("mail shih");

                }

                else if( !((RegisterValidation.validateMail(email.getText()))) && (newPropertyValue!=true) )
                {
                    email.getStyleClass().add("error");
                    emailLabel.getStyleClass().add("wronglabel");
                    if (stack1.getChildren().contains(passwordErrorLab)){
                        stack1.getChildren().remove(passwordErrorLab);
                    }
                    stack1.getChildren().add(mailLab);
                    stack1.setMargin(mailLab,new Insets(320,0,0,0));
                    System.out.println("new Property boucle 2 : "+newPropertyValue);
                    System.out.println("old Property boucle 2 : "+oldPropertyValue);
                    System.out.println("*****");
                }
            }
        });

        this.setStyle("-fx-padding: 200px 100px 100px 100px;");

        this.getChildren().addAll(stack1);

        this.setAlignment(Pos.CENTER);
        this.getStylesheets().add("/edu/fundup/ressources/css/register.css");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Member m = new Member(
                        login.getText(), password.getText(), email.getText(),
                        first_name.getText(), last_name.getText(), address.getText(), city.getText(), country.getText(),
                        ""
                );

                // Enabling user and setting his role
                m.setEnable(1);
                m.setRole("Paperless");

                // Registering
                MemberService ms = new MemberService();
                ms.RegisterPaperlessMember(m);
            }
        });

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if ( (!first_name.getText().equals("")) && (!last_name.getText().equals("")) && (!country.getText().equals("")) && (!city.getText().equals("")) && (!address.getText().equals("")) )
                {
                    Member m = new Member(
                            login.getText(), password.getText(), email.getText(),
                            first_name.getText(), last_name.getText(), address.getText(), city.getText(), country.getText(),
                            "head.png"
                    );
                    System.out.println("Paperless :"+ m.toString());
                    m.setRole("Paperless");
                    m.setEnable(1);

                    SendMail sm = new SendMail();
                    sm.SendEmail(email.getText(),login.getText());

                    stack2.getChildren().clear();
                    box2.getChildren().clear();
                    Label lab = new Label("You’re almost there. Confirm your account by setting the code that you have received by mail here");

                    lab.setMaxWidth(400);
                    lab.setWrapText(true);
                    TextField validation = new TextField();
                    validation.setMaxWidth(200);
                    Label wrongcode = new Label("Wrong code is entered, please try again !");
                    wrongcode.getStyleClass().add("wronglabel");
                    Button validateBtn = new Button("Validate");
                    validateBtn.setStyle("-fx-pref-height: 28px;\n" +
                            "    -fx-pref-width: 200px;");
                    validateBtn.getStyleClass().add("success");
                    validation.setPromptText("code (example : 58181)");

                    VBox.setMargin(validation,new Insets(20,0,20,0));
                    VBox.setMargin(validateBtn,new Insets(20,0,40,0));

                    box2.setAlignment(Pos.CENTER);

                    box2.getChildren().addAll(titleBox3,lab,validation,validateBtn);
                    stack2.getChildren().add(box2);

                    validateBtn.setOnAction(a->{
                        if (validation.getText().equals(ms.getCode(m.getmail()))){
                            box2.getChildren().remove(wrongcode);
                            validation.getStyleClass().remove("error");
                            ms.RegisterPaperlessMember(m);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Success");
                            alert.setHeaderText(null);
                            alert.setContentText("Registration has been successfully completed.");
                            alert.showAndWait();
                        } else if (!(validation.getText().equals(ms.getCode(m.getmail())))){
                            box2.getChildren().add(wrongcode);
                            validation.getStyleClass().add("error");
                        }
                    });
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid fields");
                    alert.setHeaderText(null);
                    alert.setContentText("All fields are required");
                    alert.showAndWait();
                }
            }
        });

    }
}