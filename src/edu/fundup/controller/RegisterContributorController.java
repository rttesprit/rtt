package edu.fundup.controller;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RegisterContributorController extends HBox {
    public RegisterContributorController(){

        Button registerButton = new Button("Enregistrement");
        registerButton.getStyleClass().add("primary");

        /******* VBox 1 **********/
        VBox box1 = new VBox();

        Label text = new Label("Données personnelles");

        Label loginLabel =  new Label("Nom d'utilisateur : ");
        TextField login = new TextField();

        Label passwordLabel =  new Label("Mot de passe : ");
        PasswordField password = new PasswordField();

        Label mailLabel =  new Label("Email : ");
        TextField mail = new TextField();

        GridPane grid1 = new GridPane();
        grid1.getStyleClass().add("grid");

        grid1.setAlignment(Pos.CENTER);

        grid1.add(text,0,0);

        grid1.add(loginLabel,0,1);
        grid1.add(login,1,1);

        grid1.add(passwordLabel,0,2);
        grid1.add(password,1,2);

        grid1.add(mailLabel,0,3);
        grid1.add(mail,1,3);

        box1.getChildren().add(grid1);
        //*************************

        /******* VBox 2 **********/
        VBox box2 = new VBox();
        Label text2 = new Label("Données du profil");

        Label last_nameLabel =  new Label("Nom : ");
        TextField last_name = new TextField();

        Label first_nameLabel =  new Label("Prénom : ");
        TextField first_name = new TextField();

        Label addressLabel = new Label("Adresse : ");
        Label cityLabel = new Label("Région : ");
        Label countryLabel = new Label("Pays : ");

        TextField address = new TextField();
        TextField city = new TextField();
        TextField country = new TextField();

        GridPane grid2 = new GridPane();
        grid2.getStyleClass().add("grid");

        grid2.setAlignment(Pos.CENTER);

        grid2.add(text2,0,0);

        grid2.add(last_nameLabel,0,1);
        grid2.add(last_name,1,1);

        grid2.add(first_nameLabel,0,2);
        grid2.add(first_name,1,2);

        grid2.add(countryLabel,0,3);
        grid2.add(country,1,3);

        grid2.add(cityLabel,0,4);
        grid2.add(city,1,4);

        grid2.add(addressLabel,0,5);
        grid2.add(address,1,5);

        box2.getChildren().addAll(grid2,registerButton);
        //************************************

        /******* VBox 3 **********/
        VBox box3 = new VBox();
        GridPane grid3 = new GridPane();

        Label text3 = new Label("Données du paiement");

        Label payment_typeLabel = new Label("Type du paiement :");
        Label credit_card_numberLabel = new Label("N° carte de crédit : ");
        Label cvv_numLabel = new Label("N° CVV : ");

        TextField payment_type = new TextField();
        TextField credit_card_num = new TextField();
        TextField cvv_num = new TextField();

        grid3.add(text3,0,0);

        grid3.add(payment_typeLabel,0,1);
        grid3.add(payment_type,1,1);

        grid3.add(credit_card_numberLabel,0,2);
        grid3.add(credit_card_num,1,2);

        grid3.add(cvv_numLabel,0,3);
        grid3.add(cvv_num,1,3);

        box3.getChildren().add(grid3);
        //******************************************


        this.getStyleClass().add("this");
        // ************* TAILLE
        //this.setMinWidth(480);
        //this.setMinHeight(620);

        BorderPane bp = new BorderPane();
        HBox parent = new HBox();
        box1.setAlignment(Pos.BASELINE_CENTER);
        box2.setAlignment(Pos.BASELINE_CENTER);
        box3.setAlignment(Pos.BASELINE_CENTER);
        parent.setAlignment(Pos.BASELINE_CENTER);
        parent.getChildren().addAll(box1,box2,box3);


        this.setAlignment(Pos.CENTER);
        this.getStylesheets().add("/edu/fundup/ressources/stylesheet/register.css");
        this.getChildren().add(parent);

        box1.setMinWidth(200);
        box1.setMinHeight(200);

        box2.setMinWidth(200);
        box2.setMinHeight(200);

        box3.setMinWidth(200);
        box3.setMinHeight(200);

        /*registerButton.setOnAction(new EventHandler<ActionEvent>() {
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
