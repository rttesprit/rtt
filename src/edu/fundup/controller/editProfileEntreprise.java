package edu.fundup.controller;

import edu.fundup.model.entity.Member;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class editProfileEntreprise extends VBox {

    TextField name = new TextField();
    TextField mail = new TextField();
    TextField password = new TextField();
    TextField passwordConfirm = new TextField();
    TextField first_name = new TextField();
    TextField last_name = new TextField();
    TextField address = new TextField();
    TextField city = new TextField();
    TextField country = new TextField();
    ChoiceBox payment_type = new ChoiceBox(FXCollections.observableArrayList(
            "Master card", "Visa", "Paypal"));

    TextField credit_card_number = new TextField();
    TextField cvv_num = new TextField();
    TextField president = new TextField();
    DatePicker foundation_date = new DatePicker();

    Label foundation_dateLabel = new Label("Foundation date : ");
    Label nameLabel = new Label("Association name :");
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
    Label presidentLabel = new Label("President : ");

    Button saveP = new Button("Save");
    Button cancelP = new Button("Cancel");

    GridPane grid = new GridPane();

    HBox buttonsP = new HBox();

    public editProfileEntreprise(Member connectedm){

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

        grid.add(nameLabel,0,1);
        grid.add(name,1,1);

        grid.add(presidentLabel,0,2);
        grid.add(president,1,2);

        grid.add(foundation_dateLabel,0,3);
        grid.add(foundation_date,1,3);

        grid.add(countryLabel,0,4);
        grid.add(country,1,4);

        grid.add(cityLabel,0,5);
        grid.add(city,1,5);

        grid.add(addressLabel,0,6);
        grid.add(address,1,6);

        grid.add(payment_typeLabel, 0, 7);
        grid.add(payment_type, 1, 7);

        grid.add(credit_card_numberLabel, 0, 8);
        grid.add(credit_card_number, 1, 8);

        grid.add(cvv_numLabel, 0, 9);
        grid.add(cvv_num, 1, 9);

        mail.setText(connectedm.getmail());
        name.setText(connectedm.getName());
        president.setText(connectedm.getPresident());
        city.setText(connectedm.getCity());
        address.setText(connectedm.getAddress());
        country.setText(connectedm.getCountry());

        if (!(connectedm.getFoundation_date()==null)) {
            LocalDate localDate = LocalDate.parse(connectedm.getFoundation_date());
            foundation_date.setValue(localDate);
        }
        payment_type.setValue(connectedm.getPayment_type());
        credit_card_number.setText(connectedm.getCredit_card_number());
        cvv_num.setText(connectedm.getCvv_num());

        this.getChildren().addAll(grid,buttonsP);
        this.setAlignment(Pos.CENTER);
        this.setPrefSize(6000,6000);
    }
}
