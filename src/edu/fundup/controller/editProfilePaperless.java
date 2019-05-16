package edu.fundup.controller;

import edu.fundup.model.entity.Member;
import edu.fundup.model.service.MemberService;
import edu.fundup.utils.RegisterValidation;
import edu.fundup.utils.UserSession;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class editProfilePaperless extends VBox {

    MemberService ms = new MemberService();

    TextField name = new TextField();
    TextField mail = new TextField();
    TextField password = new TextField();
    TextField passwordConfirm = new TextField();
    TextField first_name = new TextField();
    TextField last_name = new TextField();
    TextField address = new TextField();
    TextField city = new TextField();
    TextField country = new TextField("cc");
    ChoiceBox payment_type = new ChoiceBox(FXCollections.observableArrayList(
            "Master card", "Visa", "Paypal"));

    TextField credit_card_number = new TextField();
    TextField cvv_num = new TextField();
    TextField president = new TextField();

    Label nameLabel = new Label("Name :");
    Label mailLabel = new Label("E-Mail :");
    Label passwordLabel = new Label("Password : ");
    Label passwordConfirmLabel = new Label("Re-enter Password :");
    Label first_nameLabel = new Label("First name : ");
    Label last_nameLabel = new Label("Last name : ");
    Label addressLabel = new Label("Address : ");
    Label cityLabel = new Label("City : ");
    Label countryLabel = new Label("Country : ");
    Label payment_typeLabel = new Label("Payment type :");
    Label credit_card_numberLabel = new Label("Credit card number :");
    Label cvv_numLabel = new Label("Cvv number :");
    Label presidentLabel = new Label();

    Label fieldsRequiredError = new Label("All fields are required");


    Button saveP = new Button("Save");
    Button cancelP = new Button("Cancel");

    GridPane grid = new GridPane();

    HBox buttonsP = new HBox();

    public editProfilePaperless(Member connectedm) {

        saveP.setPrefWidth(200);
        saveP.setFont(new Font(40));
        cancelP.setPrefWidth(200);
        cancelP.setFont(new Font(40));
        saveP.setStyle("-fx-pref-height: 40; -fx-font-size: 14;");
        cancelP.setStyle("-fx-pref-height: 40;");

        fieldsRequiredError.setMaxWidth(280);
        fieldsRequiredError.getStyleClass().add("tag");

        saveP.getStyleClass().add("success");
        cancelP.getStyleClass().add("warning");

        buttonsP.getChildren().addAll(cancelP,saveP);
        buttonsP.setAlignment(Pos.CENTER);
        HBox.setMargin(saveP,new Insets(0,0,0,60));
        VBox.setMargin(buttonsP,new Insets(50,0,0,0));

        payment_type.setMaxWidth(200);
        payment_type.setMaxHeight(50);

        grid.setVgap(25);
        grid.setHgap(25);
        grid.setAlignment(Pos.CENTER);

        grid.add(mailLabel, 0, 0);
        grid.add(mail, 1, 0);

        grid.add(first_nameLabel, 0, 1);
        grid.add(first_name, 1, 1);

        grid.add(last_nameLabel, 0, 2);
        grid.add(last_name, 1, 2);

        grid.add(addressLabel, 0, 3);
        grid.add(address, 1, 3);

        grid.add(cityLabel, 0, 4);
        grid.add(city, 1, 4);

        grid.add(countryLabel, 0, 5);
        grid.add(country, 1, 5);

        mail.setText(connectedm.getMail());
        first_name.setText(connectedm.getfirst_name());
        last_name.setText(connectedm.getlast_name());
        city.setText(connectedm.getCity());
        address.setText(connectedm.getAddress());
        country.setText(connectedm.getCountry());

        VBox container = new VBox();

        container.setMinHeight(600);
        container.setMaxHeight(600);
        container.setMinWidth(500);
        container.setMaxWidth(500);

        container.getChildren().addAll(grid,buttonsP);
        container.setAlignment(Pos.CENTER);

        StackPane stack = new StackPane();
        stack.getChildren().add(container);
        stack.setStyle("-fx-background-radius: 30px; -fx-background-color: rgb(0,0,0, 0.25)");


        stack.setMinHeight(600);
        stack.setMaxHeight(600);
        stack.setMinWidth(500);
        stack.setMaxWidth(500);

        this.getChildren().add(stack);
        this.setAlignment(Pos.CENTER);
        this.setPrefSize(6000,6000);

        this.setStyle("-fx-background-image: url('/edu/fundup/ressources/images/desktop.jpg'); -fx-background-size: cover;");
        this.getStylesheets().add("/edu/fundup/ressources/css/register.css");

        saveP.setOnAction(save -> {
            if ( RegisterValidation.validateMail(mail.getText()) && (!first_name.getText().equals("")) && (!last_name.getText().equals("")) &&
                    (!country.getText().equals("")) && (!city.getText().equals("")) && (!address.getText().equals("")) &&
                    (!credit_card_number.getText().equals("")) && (!cvv_num.getText().equals("")) )
            {
                connectedm.setmail(mail.getText());
                connectedm.setfirst_name(first_name.getText());
                connectedm.setlast_name(last_name.getText());
                connectedm.setCity(city.getText());
                connectedm.setAddress(address.getText());
                connectedm.setCountry(country.getText());

                ms.updateUser(connectedm);

                if (this.getChildren().contains(fieldsRequiredError)){
                    this.getChildren().remove(fieldsRequiredError);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Your information has been updated");
                alert.showAndWait();
            }
            else{
                this.getChildren().add(fieldsRequiredError);
            }
        });

    }
}