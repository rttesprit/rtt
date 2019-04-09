/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.controller;

import static edu.fundup.controller.Acceuil.LAB_ABOUT;
import static edu.fundup.controller.Acceuil.LAB_POST;
import static edu.fundup.controller.Acceuil.TXT_SEARCH;
import static edu.fundup.controller.LoginGUI.BTN_LOGIN;
import static edu.fundup.controller.LoginGUI.TXT_PASSWORD;
import static edu.fundup.controller.LoginGUI.TXT_USER;
import static edu.fundup.controller.LoginGUI.loadLoginGuiLang;
import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Categorie;
import edu.fundup.model.service.ServiceCategory;
import java.util.ArrayList;
import java.util.Collections;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author hhamzaoui
 */
public class AddPost extends VBox {

    public static TextField titre;
    public static TextArea description;
    public static TextField image;
    public static TextField lieu;
    public static TextField jour;
    public static TextField montant;
    public static ComboBox category;

    public static Button BTN_ADD_POST;
    public static Button BTN_CLEAR;

    public AddPost() throws DataBaseException {
        this.getStylesheets().add("/edu/fundup/ressources/css/theme.css");

        
        titre = new TextField();
        description = new TextArea();
        image = new TextField();
        lieu = new TextField();
        jour = new TextField();
        montant = new TextField();
        category = new ComboBox();
        Label ltitre = new Label("Titre* :");
        Label ldescription = new Label("Description* :");
        Label llieu = new Label("Lieu* :");
        Label limage = new Label("Image* :");
        Label ljour = new Label("Jour Valide* :");
        Label lmontant = new Label("Montant* :");
        Label lcategory = new Label("Categorie* :");

        BTN_ADD_POST = new Button();
        BTN_CLEAR = new Button();
        titre.setFont(new Font(20));
        titre.getStyleClass().add("text-field");
        titre.setPrefWidth(100);

        lieu.setFont(new Font(20));
        lieu.getStyleClass().add("text-field");
        lieu.setPrefWidth(100);

        jour.setFont(new Font(20));
        jour.getStyleClass().add("text-field");
        jour.setPrefWidth(100);
        jour.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                {
                    if (newValue.matches("\\d*")) {
                        int value = Integer.parseInt(newValue);
                    } else {
                        jour.setText(oldValue);
                    }

                }
            }
        });
        montant.setFont(new Font(20));
        montant.getStyleClass().add("text-field");
        montant.setPrefWidth(100);
        montant.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                {
                    if (newValue.matches("\\d*")) {
                        int value = Integer.parseInt(newValue);
                    } else {
                        montant.setText(oldValue);
                    }

                }
            }
        });
        category.getStyleClass().add("combo-box");
        category.setMinWidth(100);

        description.setFont(new Font(20));
        description.getStyleClass().add("text-area");
        description.setPrefWidth(400);

        BTN_ADD_POST.getStyleClass().add("success");
        BTN_ADD_POST.setPrefWidth(290);
        BTN_ADD_POST.setFont(new Font(20));

        BTN_CLEAR.getStyleClass().add("info");
        BTN_CLEAR.setPrefWidth(290);
        BTN_CLEAR.setFont(new Font(20));

        Tooltip tt = new Tooltip();

        tt.setStyle("-fx-font: normal bold 20 Langdon; "
                + "-fx-base: #AE3522; "
                + "-fx-text-fill: orange;");

        BTN_ADD_POST.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                tt.setText("Publier " + titre.getText());
                BTN_ADD_POST.setTooltip(tt);
            }
        });
        BTN_CLEAR.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                tt.setText("Vider les champs ");
                BTN_CLEAR.setTooltip(tt);
            }
        });

        titre.textProperty().addListener((observable, oldValue, newValue) -> {
            if (titre.getText().equals("")) {
                titre.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");

            } else {
                titre.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");

            }
        });

        lieu.textProperty().addListener((observable, oldValue, newValue) -> {
            if (lieu.getText().equals("")) {
                lieu.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");

            } else {
                lieu.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");

            }
        });

        jour.textProperty().addListener((observable, oldValue, newValue) -> {
            if (jour.getText().equals("")) {
                jour.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");

            } else {
                jour.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");

            }
        });

        montant.textProperty().addListener((observable, oldValue, newValue) -> {
            if (montant.getText().equals("")) {
                montant.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");

            } else {
                montant.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");

            }
        });

        description.textProperty().addListener((observable, oldValue, newValue) -> {
            if (description.getText().equals("")) {
                description.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");

            } else {
                description.setStyle("-fx-border-color: green ; -fx-border-width: 1px ;");

            }
        });

        BTN_CLEAR.setOnMouseClicked(e
                -> {

            titre.setText("");
            description.setText("");
            lieu.setText("");
            image.setText("");
            jour.setText("");
            montant.setText("");

        });

        ServiceCategory sc = new ServiceCategory();
        ArrayList<Categorie> listCat = sc.getAll();
        Collections.sort(listCat);
        for (Categorie categorie : listCat) {
            category.getItems().add(categorie.getIdcat() + "-" + categorie.getIdsoucat() + "-" + categorie.getNomcat());
        }
        HBox button = new HBox();
        button.getStylesheets().add("/edu/fundup/ressources/css/theme.css");

        loadLoginGuiLang();
        button.setAlignment(Pos.CENTER);
        button.setSpacing(10);
        button.getChildren().addAll(BTN_CLEAR, BTN_ADD_POST);

        this.getChildren().addAll(ltitre, titre, lcategory, category, ldescription, description, llieu, lieu, limage, image, ljour, jour, lmontant, montant, button);
    }

    public static void loadLoginGuiLang() {
        AddPost.BTN_ADD_POST.setText("Publier");
        AddPost.BTN_CLEAR.setText("Vider les Champs");
        Acceuil.Title.setText("          Ajouter poste");
        AddPost.titre.setPromptText("Titre de la Poste");
        AddPost.lieu.setPromptText("Où ? (Pays)");
        AddPost.jour.setPromptText("Nombre de jour");
        AddPost.montant.setPromptText("Montant souhaité");
        AddPost.category.setPromptText("----------------------Attribuer une categorie à votre poste---------------------");

    }
}
