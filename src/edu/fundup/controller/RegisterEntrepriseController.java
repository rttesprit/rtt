package edu.fundup.controller;

import edu.fundup.model.entity.Member;
import edu.fundup.model.service.MemberService;
import edu.fundup.utils.RegisterValidation;
import edu.fundup.utils.SendMail;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.text.ParseException;
import java.util.ArrayList;

public class RegisterEntrepriseController  extends HBox {
    public RegisterEntrepriseController(){

        MemberService ms = new MemberService();
        /*3 STEPS*/
         Label step1 = new Label("Step  1/4");
         Label step2 = new Label("Step  2/4");
         Label step3 = new Label("Step  3/4");
         Label step4 = new Label("Final step");
        /********/

        /******* VBox 1 **********/
        VBox box1 = new VBox();

        Label username = new Label(" Login :");
        Label passwordLabel = new Label("Password :");
        Label passwordConfirmationLabel = new Label("Re-enter pass :");
        Label emailLabel = new Label("E-Mail : ");

        TextField login = new TextField();
        login.setPromptText("Login");
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

        grid1.add(new Label("Account data"), 0, 0);

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

        box1.getChildren().addAll(step1,grid1);
        VBox.setMargin(step1,new Insets(10,0,30,0));
        StackPane stack1 = new StackPane();
        stack1.setStyle("-fx-background-color: rgb(0,0,0, 0.1)");

        stack1.getChildren().addAll(box1,nextToBox2);
        stack1.setMargin(nextToBox2,new Insets(480,15,25,425));

        //*************************


        /******* VBox 2 **********/
        VBox box2 = new VBox();

        Label nameLabel = new Label("Name : ");
        Label presidentLabel = new Label("President : ");
        Label addressLabel = new Label("Address : ");
        Label countryLabel = new Label("Country : ");
        Label cityLabel = new Label("City : ");
        Label foundation_dateLabel = new Label("Foundation : ");

        TextField name = new TextField();
        name.setPromptText("Name");
        TextField president = new TextField();
        president.setPromptText("President");
        TextField address = new TextField();
        address.setPromptText("Address");
        TextField city = new TextField();
        city.setPromptText("City");
        TextField country = new TextField();
        country.setPromptText("Country");
        DatePicker foundation_date = new DatePicker();
        foundation_date.setPromptText("Foundation date");

        GridPane grid2 = new GridPane();
        grid2.getStyleClass().add("grid");
        grid2.setVgap(15);
        grid2.setHgap(15);
        grid2.setAlignment(Pos.CENTER);

        grid2.add(new Label("Profile data"),0,0);

        grid2.add(nameLabel,0,1);
        grid2.add(name,1,1);

        grid2.add(presidentLabel,0,2);
        grid2.add(president,1,2);

        grid2.add(foundation_dateLabel,0,3);
        grid2.add(foundation_date,1,3);

        grid2.add(countryLabel,0,4);
        grid2.add(country,1,4);

        grid2.add(cityLabel,0,5);
        grid2.add(city,1,5);

        grid2.add(addressLabel,0,6);
        grid2.add(address,1,6);

        box2.setMinWidth(500);
        box2.setMinHeight(500);
        StackPane stack2 = new StackPane();
        stack2.setStyle("-fx-background-color: rgb(0,0,0, 0.1)");

        Button backToBox1 = new Button();
        backToBox1.setStyle("-fx-background-image: url('/edu/fundup/ressources/images/arrow-flat2.png'); -fx-border-width: 0;" +
                " -fx-background-color: none; -fx-background-repeat: no-repeat; -fx-background-size: 40px; -fx-background-position: center; ");

        Button nextToBox3 = new Button();
        nextToBox3.setStyle("-fx-background-image: url('/edu/fundup/ressources/images/arrow-flat.png'); -fx-border-width: 0;" +
                " -fx-background-color: none; -fx-background-repeat: no-repeat; -fx-background-size: 40px; -fx-background-position: center; ");

        nextToBox3.getStyleClass().add("rounded");

        HBox buttonsBox = new HBox();
        buttonsBox.getChildren().addAll(backToBox1,nextToBox3);

        buttonsBox.setMargin(nextToBox3,new Insets(0,25,25,185));
        buttonsBox.setMargin(backToBox1,new Insets(0,185,25,25));

        box2.getChildren().addAll(step2,grid2);
        VBox.setMargin(step2,new Insets(10,0,0,0));
        stack2.getChildren().addAll(box2,buttonsBox);
        stack2.setMargin(buttonsBox,new Insets(480,0,0,0));


        //************************************

        /******* VBox 3 **********/
        VBox box3 = new VBox();
        GridPane grid3 = new GridPane();
        grid3.setVgap(15);
        grid3.setHgap(15);
        grid3.setAlignment(Pos.CENTER);

        ChoiceBox payment_type = new ChoiceBox(FXCollections.observableArrayList(
                "Master card", "Visa", "Paypal")
        );

        TextField credit_card_num = new TextField();
        credit_card_num.setPromptText("Credit card number");
        TextField cvv_num = new TextField();
        cvv_num.setPromptText("CVV number");

        grid3.add(new Label("Payment details"),0,0);

        grid3.add(new Label("Payment type"),0,1);
        grid3.add(payment_type,1,1);

        grid3.add(new Label("Credit card number :"),0,2);
        grid3.add(credit_card_num,1,2);

        grid3.add(new Label("CVV number :"),0,3);
        grid3.add(cvv_num,1,3);

        Button btn = new Button("Register");
        btn.getStyleClass().add("sk-btn");
        btn.getStyleClass().add("sk-btn-toolbar");


        Button backToBox2 = new Button();
        backToBox2.setStyle("-fx-background-image: url('/edu/fundup/ressources/images/arrow-flat2.png'); -fx-border-width: 0;" +
                " -fx-background-color: none; -fx-background-repeat: no-repeat; -fx-background-size: 40px; -fx-background-position: center; ");

        backToBox2.getStyleClass().add("rounded");

        box3.setMargin(btn,new Insets(50,180,10,180));
        box3.getChildren().addAll(step3,grid3,btn);
        VBox.setMargin(step3,new Insets(10,0,30,0));
        box3.setMinWidth(500);
        box3.setMinHeight(500);
        StackPane stack3 = new StackPane();
        stack3.setStyle("-fx-background-color: rgb(0,0,0, 0.1)");
        stack3.getChildren().addAll(box3,backToBox2);
        stack3.setMargin(backToBox2,new Insets(480,425,25,15));


        // ___________________NEXT & BACK CONDITIONS___________________


        nextToBox3.setOnAction(event -> {
            try {
                if (RegisterValidation.validateDate(foundation_date.getValue()) &&
                        RegisterValidation.validateDateAlreadyPassed(foundation_date.getValue()) &&
                        RegisterValidation.fieldNotNull(country.getText())&&
                        RegisterValidation.fieldNotNull(address.getText())&&
                        RegisterValidation.fieldNotNull(city.getText())&&
                        RegisterValidation.fieldNotNull(president.getText())&&
                        RegisterValidation.fieldNotNull(name.getText()))
                {
                    this.getChildren().remove(stack2);
                    this.getChildren().add(stack3);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalidate fields");
                    alert.setHeaderText(null);
                    alert.setContentText("All fields are required");
                    alert.showAndWait();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        backToBox1.setOnAction(event -> {this.getChildren().remove(stack2); this.getChildren().add(stack1);} );
        backToBox2.setOnAction(event -> {this.getChildren().remove(stack3); this.getChildren().add(stack2);});
        //************ BOX 1 VALIDATION
        ArrayList<String> logins = ms.getAllLogin();

        nextToBox2.setOnAction(event -> {

            if (RegisterValidation.checkUsername(login.getText(),logins) &&
                    RegisterValidation.validatePassword(password.getText()) &&
                    RegisterValidation.identicPassword(password.getText(),passwordConfirmation.getText()) &&
                    RegisterValidation.validateMail(email.getText())
            )
            {
                this.getChildren().remove(stack1);
                this.getChildren().add(stack2);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalidate fields");
                alert.setHeaderText(null);
                alert.setContentText("You must validate all the fields");
                alert.showAndWait();
            }
        });

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("payment_type.getValue()==null"+ payment_type.getValue()==null);
                System.out.println("cvv_num.getText().equals(\"\")"+ cvv_num.getText().equals(""));
                System.out.println("(credit_card_num.equals(\"\")"+ credit_card_num.equals(""));
                System.out.println("condition"+!( payment_type.getValue()==null || cvv_num.getText().equals("") || (credit_card_num.equals(""))));


                if ( !( payment_type.getValue()==null || cvv_num.getText().equals("") || (credit_card_num.equals("")))  )
                {
                    Member m = new Member(
                            login.getText(), name.getText(), password.getText(), email.getText(),
                            address.getText(), city.getText(), country.getText(),
                            "head.png", payment_type.getValue().toString(), credit_card_num.getText(), cvv_num.getText(), president.getText(), foundation_date.getValue().toString()
                    );
                    System.out.println("Entreprise :"+ m.toString());
                    m.setRole("Entreprise");
                    m.setEnable(1);
                    System.out.println(m.getPresident() + " " + m.getFoundation_date());
                    SendMail sm = new SendMail();
                    sm.SendEmail(email.getText(),login.getText());

                    stack3.getChildren().clear();
                    box3.getChildren().clear();
                    Label lab = new Label("You’re almost there. Confirm your account by setting the code that you have received by mail here");

                    lab.setMaxWidth(400);
                    lab.setWrapText(true);
                    TextField validation = new TextField();
                    validation.setMaxWidth(200);
                    Label wrongcode = new Label("Wrong code is entered, please try again !");
                    wrongcode.getStyleClass().add("wrong");
                    Button validateBtn = new Button("Validate");
                    validateBtn.getStyleClass().add("success");
                    validation.setPromptText("code (example : 58181)");

                    VBox.setMargin(validation,new Insets(20,0,20,0));
                    VBox.setMargin(validateBtn,new Insets(20,0,40,0));
                    VBox.setMargin(step4,new Insets(0,380,0,0));

                    box3.setAlignment(Pos.CENTER);

                    box3.getChildren().addAll(step4,lab,validation,validateBtn);
                    stack3.getChildren().add(box3);

                    validateBtn.setOnAction(a->{
                        if (validation.getText().equals(ms.getCode(m.getmail()))){
                            box3.getChildren().remove(wrongcode);
                            validation.getStyleClass().remove("error");
                            ms.RegisterEntreprise(m);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Success");
                            alert.setHeaderText(null);
                            alert.setContentText("Registration has been successfully completed.");
                            alert.showAndWait();
                        } else if (!(validation.getText().equals(ms.getCode(m.getmail())))){
                            box3.getChildren().add(wrongcode);
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

        Label logErrorLab = new Label("Please enter a valid and unique login ");
        Label identicErrorLab = new Label("Your password fields must be identical ");
        Label mailLab = new Label("Please set your valid E-mail");

        Label passwordErrorLab = new Label("Your password length must be in (6-15) and must contain special charachters");
        passwordErrorLab.setWrapText(true);

        logErrorLab.setMaxWidth(480);
        identicErrorLab.setMaxWidth(480);
        passwordErrorLab.setMaxWidth(480);
        mailLab.setMaxWidth(480);

        logErrorLab.getStyleClass().add("wronglabel");
        identicErrorLab.getStyleClass().add("wronglabel");
        passwordErrorLab.getStyleClass().add("wronglabel");
        mailLab.getStyleClass().add("wronglabel");

        login.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (RegisterValidation.checkUsername(login.getText(),logins)) {

                    login.getStyleClass().remove("error");
                    login.getStyleClass().add("va");

                    stack1.getChildren().remove(logErrorLab);
                    username.getStyleClass().remove("wronglabel");

                    System.out.println("esm shih");

                }

                else if( !(RegisterValidation.checkUsername(login.getText(),logins)) && (newPropertyValue!=true) )
                {
                        login.getStyleClass().remove("va");
                        login.getStyleClass().add("error");
                        username.getStyleClass().add("wronglabel");
                        stack1.getChildren().add(logErrorLab);
                        stack1.setMargin(logErrorLab,new Insets(170,0,0,0));
                        System.out.println("new Property boucle 2 : "+newPropertyValue);
                        System.out.println("old Property boucle 2 : "+oldPropertyValue);
                        System.out.println("*****");
                }
            }
        });

        passwordConfirmation.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (RegisterValidation.identicPassword(password.getText(),passwordConfirmation.getText())) {

                    passwordConfirmation.getStyleClass().remove("error");

                    passwordConfirmation.getStyleClass().add("va");

                    stack1.getChildren().remove(identicErrorLab);
                    passwordConfirmationLabel.getStyleClass().remove("wronglabel");

                    System.out.println("pass identiques");

                }
                else
                {
                    if((!(RegisterValidation.identicPassword(password.getText(),passwordConfirmation.getText()))) && (newPropertyValue!=true) ){
                        passwordConfirmation.getStyleClass().remove("va");
                        passwordConfirmation.getStyleClass().add("error");
                        passwordConfirmationLabel.getStyleClass().add("wronglabel");
                        stack1.getChildren().add(identicErrorLab);
                        stack1.setMargin(identicErrorLab,new Insets(350,0,0,0));
                        System.out.println("new Property boucle 2 : "+newPropertyValue);
                        System.out.println("old Property boucle 2 : "+oldPropertyValue);
                        System.out.println("*****");
                    }
                }
            }
        });

        email.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (RegisterValidation.validateMail(email.getText())) {

                    email.getStyleClass().remove("error");
                    email.getStyleClass().add("va");

                    stack1.getChildren().remove(mailLab);
                    emailLabel.getStyleClass().remove("wronglabel");

                    System.out.println("mail shih");

                }

                else if( !((RegisterValidation.validateMail(email.getText()))) && (newPropertyValue!=true) )
                {
                    email.getStyleClass().remove("va");
                    email.getStyleClass().add("error");
                    emailLabel.getStyleClass().add("wronglabel");
                    stack1.getChildren().add(mailLab);
                    stack1.setMargin(mailLab,new Insets(400,0,0,0));
                    System.out.println("new Property boucle 2 : "+newPropertyValue);
                    System.out.println("old Property boucle 2 : "+oldPropertyValue);
                    System.out.println("*****");
                }
            }
        });

        password.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (RegisterValidation.validatePassword(password.getText())) {

                    password.getStyleClass().remove("error");
                    password.getStyleClass().add("va");

                    stack1.getChildren().remove(passwordErrorLab);
                    passwordLabel.getStyleClass().remove("wronglabel");

                    System.out.println("pass shih");

                }
                else
                {
                    if((!(RegisterValidation.validatePassword(password.getText()))) && (!newPropertyValue) ){
                        password.getStyleClass().remove("va");
                        password.getStyleClass().add("error");
                        passwordLabel.getStyleClass().add("wronglabel");
                        stack1.getChildren().add(passwordErrorLab);
                        stack1.setMargin(passwordErrorLab,new Insets(260,0,0,0));
                        System.out.println("new Property boucle 2 : "+newPropertyValue);
                        System.out.println("old Property boucle 2 : "+oldPropertyValue);
                        System.out.println("*****");
                    }
                }
            }
        });

        this.setStyle( "-fx-padding: 200px 100px 100px 100px;" );

        this.getChildren().addAll(stack1);

        this.setAlignment(Pos.CENTER);
        this.getStylesheets().add("/edu/fundup/ressources/css/register.css");
    }
}
