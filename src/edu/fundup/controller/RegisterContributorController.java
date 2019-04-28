package edu.fundup.controller;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;


public class RegisterContributorController extends HBox {
    public RegisterContributorController(){


        /******* VBox 1 **********/
        VBox box1 = new VBox();

        TextField login = new TextField();
        login.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        TextField email = new TextField();
        email.setPromptText("Email");

        GridPane grid1 = new GridPane();
        grid1.getStyleClass().add("grid");
        grid1.setVgap(15);
        grid1.setHgap(15);
        grid1.setAlignment(Pos.CENTER);

        grid1.add(new Label("Account data"),0,0);

        grid1.add(new Label("Username : "),0,1);
        grid1.add(login,1,1);

        grid1.add(new Label("Password : "),0,2);
        grid1.add(password,1,2);

        grid1.add(new Label("Email : "),0,3);
        grid1.add(email,1,3);

        box1.getChildren().add(grid1);
        //*************************

        /******* VBox 2 **********/
        VBox box2 = new VBox();

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

        grid2.add(new Label("Profile data"),0,0);

        grid2.add(new Label("First name : "),0,1);
        grid2.add(first_name,1,1);

        grid2.add(new Label("Last name : "),0,2);
        grid2.add(last_name,1,2);

        grid2.add(new Label("Country : "),0,3);
        grid2.add(country,1,3);

        grid2.add(new Label("City : "),0,4);
        grid2.add(city,1,4);

        grid2.add(new Label("Address : "),0,5);
        grid2.add(address,1,5);

        box2.getChildren().addAll(grid2);
        box2.setFillWidth(true);
        //************************************

        /******* VBox 3 **********/
        VBox box3 = new VBox();
        GridPane grid3 = new GridPane();
        grid3.setVgap(15);
        grid3.setHgap(15);

        TextField payment_type = new TextField();
        payment_type.setPromptText("Payment type");
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

        JFXButton btn = new JFXButton("Register");
        btn.setStyle(" -jfx-button-type: FLAT;\n" +
                "     -fx-background-color: white;\n" +
                "     -fx-text-fill: black;");
        btn.getStyleClass().add("registerBtn");

        box3.setMargin(btn,new Insets(40,0,10,100));
        box3.getChildren().addAll(grid3,btn);

        //******************************************

        this.getStyleClass().add("this");
        // ************* TAILLE
        //this.setMinWidth(480);
        //this.setMinHeight(620);

        this.setStyle( "-fx-padding: 200px 100px 100px 100px;" );

        this.getChildren().addAll(box1,box2,box3);



        this.setAlignment(Pos.CENTER);
        this.getStylesheets().add("/edu/fundup/ressources/css/register.css");

        box1.setMinWidth(400);
        box1.setMinHeight(400);

        box2.setMinWidth(400);
        box2.setMinHeight(400);

        box3.setMinWidth(400);
        box3.setMinHeight(400);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MemberService ms = new MemberService();
                Member m = new Member(
                        login.getText(), password.getText(), email.getText(),
                        first_name.getText(), last_name.getText(), address.getText(), city.getText(), country.getText(),
                        "photopath", payment_type.getText(), credit_card_num.getText(),cvv_num.getText()
                );
                m.setRole("Contributor");
                m.setEnable(1);
                ms.RegisterContributor(m);
            }
        });

        /*btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(login.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR,
                            "Form Error!", "Please enter your name");
                    return;
                }
                if(emailField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                            "Form Error!", "Please enter your email id");
                    return;
                }
                if(passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(),
                            "Form Error!", "Please enter a password");
                    return;
                }

                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(),
                        "Registration Successful!", "Welcome " + nameField.getText());
            }
        });*/

    }
}
