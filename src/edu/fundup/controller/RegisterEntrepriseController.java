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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

public class RegisterEntrepriseController  extends HBox {
    public RegisterEntrepriseController(){

        MemberService ms = new MemberService();
        ArrayList<String> logins = ms.getAllLogin();

        /*STEPS*/
        Label accountData = new Label("Step 1/4      Account Data");
        Label profileData = new Label("Step 2/4      Profile Data");
        Label paymentData = new Label("Step 3/4      Payment Data");
        Label finalStep = new Label("Final step      Confirmation");

        StackPane titleBox1 = new StackPane();
        titleBox1.getChildren().add(accountData);
        titleBox1.setMinHeight(60);
        titleBox1.setMaxHeight(60);
        accountData.getStyleClass().add("titleLabel");
        titleBox1.getStyleClass().add("titleBox");

        StackPane titleBox2 = new StackPane();
        titleBox2.getChildren().add(profileData);
        titleBox2.setMinHeight(60);
        titleBox2.setMaxHeight(60);
        profileData.getStyleClass().add("titleLabel");
        titleBox2.getStyleClass().add("titleBox");

        StackPane titleBox3 = new StackPane();
        titleBox3.getChildren().add(paymentData);
        titleBox3.setMinHeight(60);
        titleBox3.setMaxHeight(60);
        paymentData.getStyleClass().add("titleLabel");
        titleBox3.getStyleClass().add("titleBox");

        StackPane titleBox4 = new StackPane();
        titleBox4.getChildren().add(finalStep);
        titleBox4.setMinHeight(60);
        titleBox4.setMaxHeight(60);
        finalStep.getStyleClass().add("titleLabel");
        titleBox4.getStyleClass().add("titleBox");


        /******* VBox 1 **********/
        VBox box1 = new VBox();

        Label username = new Label("Username :");
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

        grid1.add(username, 0, 1);
        grid1.add(login, 1, 1);

        grid1.add(passwordLabel, 0, 2);
        grid1.add(password, 1, 2);

        grid1.add(passwordConfirmationLabel, 0, 3);
        grid1.add(passwordConfirmation, 1, 3);

        grid1.add(emailLabel, 0, 4);
        grid1.add(email, 1, 4);

        Button nextToBox2 = new Button();
        nextToBox2.getStyleClass().add("nextArrow");

        box1.setMinWidth(500);
        box1.setMinHeight(500);

        box1.getChildren().addAll(titleBox1,grid1);
        VBox.setMargin(grid1,new Insets(35,0,30,0));

        StackPane stack1 = new StackPane();
        stack1.setStyle("-fx-background-color: rgb(0,0,0, 0.5)");

        stack1.getChildren().addAll(box1,nextToBox2);
        stack1.setMargin(nextToBox2,new Insets(480,15,25,425));

        //*************************


        /******* VBox 2 **********/
        VBox box2 = new VBox();

        Label nameLabel = new Label("Association name : ");
        Label presidentLabel = new Label("President : ");
        Label addressLabel = new Label("Address : ");
        Label countryLabel = new Label("Country : ");
        Label cityLabel = new Label("City : ");
        Label foundation_dateLabel = new Label("Foundation : ");

        TextField name = new TextField();
        name.setPromptText("Association name");
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
        stack2.setStyle("-fx-background-color: rgb(0,0,0, 0.5)");

        Button backToBox1 = new Button();
        backToBox1.getStyleClass().add("backArrow");

        Button nextToBox3 = new Button();
        nextToBox3.getStyleClass().add("nextArrow");

        HBox buttonsBox = new HBox();
        buttonsBox.getChildren().addAll(backToBox1,nextToBox3);

        buttonsBox.setMargin(nextToBox3,new Insets(0,25,25,185));
        buttonsBox.setMargin(backToBox1,new Insets(0,185,25,25));

        box2.getChildren().addAll(titleBox2,grid2);
        VBox.setMargin(grid2,new Insets(35,0,30,0));

        stack2.getChildren().addAll(box2,buttonsBox);
        stack2.setMargin(buttonsBox,new Insets(480,0,0,0));


        /******* VBox 3 **********/
        StackPane stack3 = new StackPane();

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

        grid3.add(new Label("Payment type"),0,1);
        grid3.add(payment_type,1,1);

        grid3.add(new Label("Credit card number :"),0,2);
        grid3.add(credit_card_num,1,2);

        grid3.add(new Label("CVV number :"),0,3);
        grid3.add(cvv_num,1,3);

        Button btn = new Button("Register");
        btn.getStyleClass().add("success");
        btn.setMinHeight(50);
        btn.setMaxHeight(50);
        btn.setMinWidth(200);
        btn.setMaxWidth(200);


        Button backToBox2 = new Button();
        backToBox2.getStyleClass().add("backArrow");
        backToBox2.setOnAction(event -> {this.getChildren().remove(stack3); this.getChildren().add(stack2);});

        box3.getChildren().addAll(titleBox3,grid3,btn);

        VBox.setMargin(grid3,new Insets(35,0,30,0));
        box3.setMargin(btn,new Insets(120,180,10,180));

        box3.setMinWidth(500);
        box3.setMinHeight(500);
        stack3.setStyle("-fx-background-color: rgb(0,0,0, 0.5)");
        stack3.getChildren().addAll(box3,backToBox2);
        stack3.setMargin(backToBox2,new Insets(480,425,25,15));


        // ___________________NEXT & BACK CONDITIONS___________________

        //************ BOX 1 VALIDATION

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
        Label fieldsRequiredError = new Label("All fields are required");
        Label wrongcode = new Label("You have set a wrong code, please try again !");
        wrongcode.getStyleClass().add("tag");

        passwordErrorLab.setWrapText(true);
        logErrorLab.setWrapText(true);

        logErrorLab.setMaxWidth(480);
        identicErrorLab.setMaxWidth(480);
        passwordErrorLab.setMaxWidth(480);
        mailLab.setMaxWidth(480);
        fieldsRequiredError.setMaxWidth(280);

        logErrorLab.getStyleClass().add("tag");
        passwordErrorLab.getStyleClass().add("tag");
        identicErrorLab.getStyleClass().add("tag");
        mailLab.getStyleClass().add("tag");
        fieldsRequiredError.getStyleClass().add("tag");


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
                        stack1.setMargin(logErrorLab, new Insets(190, 0, 0, 0));

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
                        stack1.setMargin(identicErrorLab, new Insets(190, 0, 0, 0));
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
                        stack1.setMargin(passwordErrorLab, new Insets(330, 0, 0, 0));
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
                    stack1.setMargin(mailLab,new Insets(330,0,0,0));
                    System.out.println("new Property boucle 2 : "+newPropertyValue);
                    System.out.println("old Property boucle 2 : "+oldPropertyValue);
                    System.out.println("*****");
                }
            }
        });

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
                    stack2.getChildren().add(fieldsRequiredError);
                    stack2.setMargin(fieldsRequiredError, new Insets(420, 0, 0, 0));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        backToBox1.setOnAction(event -> {this.getChildren().remove(stack2); this.getChildren().add(stack1);} );
        backToBox2.setOnAction(event -> {this.getChildren().remove(stack3); this.getChildren().add(stack2);});

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if ( !( payment_type.getValue()==null || cvv_num.getText().equals("") || (credit_card_num.getText().equals("")))  )
                {
                    Member m = new Member(
                            login.getText(), name.getText(), password.getText(), email.getText(),
                            address.getText(), city.getText(), country.getText(),
                            "head.png", payment_type.getValue().toString(), credit_card_num.getText(), cvv_num.getText(), president.getText(), foundation_date.getValue().toString()
                    );
                    m.setRole("Entreprise");
                    m.setEnable(1);

                    stack3.getChildren().clear();
                    box3.getChildren().clear();

                    VBox choiceBox = new VBox();
                    choiceBox.setMinWidth(500);
                    choiceBox.setMinHeight(500);

                    Button smsChoice = new Button("Confirm Account by SMS");
                    smsChoice.getStyleClass().add("success");
                    Button mailChoice = new Button("Confirm Account by Mail");
                    mailChoice.getStyleClass().add("success");

                    mailChoice.setMinHeight(80);
                    mailChoice.setMaxHeight(80);
                    mailChoice.setMinWidth(300);
                    mailChoice.setMaxWidth(300);
                    smsChoice.setMinHeight(80);
                    smsChoice.setMaxHeight(80);
                    smsChoice.setMinWidth(300);
                    smsChoice.setMaxWidth(300);


                    mailChoice.setOnAction(e-> {
                        SendMail sm = new SendMail();
                        sm.SendEmail(email.getText(),login.getText());

                        stack3.getChildren().clear();
                        box3.getChildren().clear();
                        Label lab = new Label("You’re almost there. Confirm your account by setting the code that you have received by mail here");

                        lab.setMaxWidth(400);
                        lab.setWrapText(true);
                        TextField validation = new TextField();
                        validation.setMaxWidth(200);
                        Button validateBtn = new Button("Validate");
                        validateBtn.setStyle("-fx-pref-height: 28px;\n" +
                                "    -fx-pref-width: 200px;");
                        validateBtn.getStyleClass().add("success");
                        validation.setPromptText("code (example : 58181)");

                        VBox.setMargin(validation,new Insets(20,0,20,0));
                        VBox.setMargin(validateBtn,new Insets(20,0,40,0));

                        box3.setAlignment(Pos.CENTER);

                        box3.getChildren().addAll(titleBox4,lab,validation,validateBtn);
                        stack3.getChildren().add(box3);

                        validateBtn.setOnAction(a->{
                            if (validation.getText().equals(ms.getCode(m.getMail()))){
                                box3.getChildren().remove(wrongcode);
                                validation.getStyleClass().remove("error");
                                ms.RegisterEntreprise(m);
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Success");
                                alert.setHeaderText(null);
                                alert.setContentText("Registration has been successfully completed.");
                                alert.showAndWait();
                            } else if (!(validation.getText().equals(ms.getCode(m.getMail())))){
                                Random randomGenerator = new Random();
                                Random r = new Random();
                                Color randomColor = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
                                String hex = Integer.toHexString(randomColor.getRGB() & 0xffffff);
                                if (hex.length() < 6) {
                                    hex = "0" + hex;
                                }
                                hex = "#" + hex;
                                if(box3.getChildren().contains(wrongcode)){
                                    box3.getChildren().remove(wrongcode);
                                    wrongcode.setStyle("-fx-background-color:"+hex+"!important;");
                                    box3.getChildren().add(wrongcode);
                                }else {
                                    box3.getChildren().add(wrongcode);
                                    validation.getStyleClass().add("error");
                                }
                            }
                        });
                    });
                    smsChoice.setOnAction(s -> {

                        stack3.getChildren().clear();
                        box3.getChildren().clear();

                        Label lab = new Label("You’re almost there. Please enter your phone number to send you a confirmation code");
                        lab.setMaxWidth(400);
                        lab.setWrapText(true);

                        TextField num = new TextField();
                        num.setMaxWidth(200);
                        num.setPromptText("(example: 29576707)");

                        TextField validation = new TextField();
                        validation.setMaxWidth(200);
                        Button validateBtn = new Button("Validate");
                        validateBtn.setStyle("-fx-pref-height: 28px;\n" +
                                "    -fx-pref-width: 200px;");
                        validateBtn.getStyleClass().add("success");
                        validation.setPromptText("code (example : 58181)");
                        VBox.setMargin(validation,new Insets(20,0,20,0));
                        VBox.setMargin(validateBtn,new Insets(20,0,40,0));

                        Button send = new Button("Send SMS");
                        send.getStyleClass().add("success");
                        send.setMinHeight(50);
                        send.setMaxHeight(50);
                        send.setMinWidth(200);
                        send.setMaxWidth(200);

                        Label numError = new Label("Please enter a valid Phone number");
                        numError.getStyleClass().add("tag");

                        send.setOnAction(action -> {
                            if (!num.getText().equals("") && num.getText().length()==8 ) {
                                if (box3.getChildren().contains(numError)){
                                    box3.getChildren().remove(numError);
                                }
                                // GENERATING CODE & INSERT INTO DB
                                Random rand = new Random(System.currentTimeMillis());
                                int code = rand.nextInt(99999);
                                String strCode = code+"";
                                System.out.println("code " + code + " ");
                                //*****************************************

                                // API to Send SMS
                                String apiKey = "apikey=wUT757O+wtE-YKr4aSx9q7xzBVodX96c4JmXVuJq7Y";
                                String message = "&message=This is an SMS Confirmation for your Register at Tunisia Charity, here is your code"+ code;
                                String sender = "&sender=TUN Charity";
                                String numbers = "&numbers=+216" + num.getText();

                                // Send data
                                HttpURLConnection conn = null;
                                try {
                                    conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                String data = apiKey + numbers + message + sender;
                                conn.setDoOutput(true);
                                try {
                                    conn.setRequestMethod("POST");
                                } catch (ProtocolException e) {
                                    e.printStackTrace();
                                }
                                conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                                try {
                                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                BufferedReader rd = null;
                                try {
                                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                final StringBuffer stringBuffer = new StringBuffer();
                                String line = null;
                                while (true) {
                                    try {
                                        if (!((line = rd.readLine()) != null)) break;
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    //stringBuffer.append(line);
                                    // JOptionPane.showMessageDialog(null, "message" + line);
                                }
                                box3.getChildren().removeAll(num,send);
                                box3.getChildren().addAll(validation,validateBtn);
                                lab.setText("An SMS has been sent to you, please confirm your account by setting the code that you have received !");
                                validateBtn.setOnAction(e-> {
                                    if (validation.getText().equals(strCode)){

                                        box3.getChildren().remove(wrongcode);
                                        validation.getStyleClass().remove("error");
                                        ms.RegisterEntreprise(m);
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Success");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Registration has been successfully completed.");
                                        alert.showAndWait();
                                    } else if (!validation.getText().equals(strCode)){

                                        box3.getChildren().add(wrongcode);
                                        validation.getStyleClass().add("error");
                                    }
                                });
                            } else {
                                Random randomGenerator = new Random();
                                Random r = new Random();
                                Color randomColor = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
                                String hex = Integer.toHexString(randomColor.getRGB() & 0xffffff);
                                if (hex.length() < 6) {
                                    hex = "0" + hex;
                                }
                                hex = "#" + hex;

                                if (box3.getChildren().contains(numError)) {
                                    box3.getChildren().remove(numError);
                                    numError.setStyle("-fx-background-color:"+hex+"!important;");
                                    box3.getChildren().add(numError);
                                } else {
                                    box3.getChildren().add(numError);
                                }
                            }
                        });

                        box3.setAlignment(Pos.CENTER);
                        box3.getChildren().addAll(titleBox4,lab,num,send);

                        VBox.setMargin(titleBox4, new Insets(0,0,20,0));
                        VBox.setMargin(lab, new Insets(0,0,20,0));
                        VBox.setMargin(num, new Insets(0,0,20,0));

                        stack3.getChildren().add(box3);
                    });

                    box3.getChildren().addAll(titleBox4,smsChoice,mailChoice);
                    box3.setAlignment(Pos.CENTER);
                    VBox.setMargin(titleBox4, new Insets(0,0,20,0));
                    VBox.setMargin(smsChoice, new Insets(0,0,80,0));
                    stack3.getChildren().add(box3);
                }
                else {
                    stack3.getChildren().add(fieldsRequiredError);
                    stack3.setMargin(fieldsRequiredError, new Insets(130, 0, 0, 0));
                }
            }
        });


        this.setStyle( "-fx-padding: 200px 100px 100px 100px;" );

        this.getChildren().add(stack1);

        this.setAlignment(Pos.CENTER);
        this.getStylesheets().add("/edu/fundup/ressources/css/register.css");
    }
}
