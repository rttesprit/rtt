/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.controller;

import static edu.fundup.controller.FundUp.GLOBAL_PANE_BORDER;
import static edu.fundup.controller.LoginGUI.TXT_USER;

import com.jfoenix.controls.JFXButton;
import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Member;
import edu.fundup.model.entity.Post;
import edu.fundup.model.service.ServicePost;
import edu.fundup.utils.AutoCompleteTextField;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 *
 * @author hhamzaoui
 */
public class Acceuil extends HBox {

    public static Button LAB_POST;
    public static Button LAB_EVENT;
    public static Button LAB_ADOPTION;
    public static Button LAB_RECLAMATION;
    public static Button LAB_ABOUT;
    public static JFXButton LOGIN;
    public static JFXButton INSCRIPTION;
    public static AutoCompleteTextField TXT_SEARCH;
    public static Label Title;

    public Acceuil() {

        // ------------initialisation------------
        LAB_POST = new Button("Posts");
        LAB_EVENT = new Button("Evennements");
        LAB_ADOPTION = new Button("Adpotions");
        LAB_RECLAMATION = new Button("Reclamation");
        LAB_ABOUT = new Button("About us");
        TXT_SEARCH = new AutoCompleteTextField();
        Title = new Label("Page Name...");
        LOGIN = new JFXButton();
        INSCRIPTION = new JFXButton();
        VBox rightPane = new VBox();
        VBox leftPane = new VBox();

        // ------------Styling------------
        leftPane.getStylesheets().add("/edu/fundup/ressources/css/theme.css");
        leftPane.setPadding(new Insets(20));
        leftPane.setSpacing(30);
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setStyle("-fx-background-color:#34495e");
        leftPane.setMinWidth(300);

        leftPane.setPrefSize(300, 600);
        rightPane.getStylesheets().add("/edu/fundup/ressources/css/theme.css");
        rightPane.getStyleClass().add("hbox");

        rightPane.setMinWidth(leftPane.getMinWidth() * 3);

        rightPane.setSpacing(50);
        rightPane.setPadding(new Insets(0, 0, 0, 0));

        TXT_SEARCH.setFont(new Font(20));
        TXT_SEARCH.getStyleClass().add("text-field");
        TXT_SEARCH.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                TXT_SEARCH.getEntries().addAll(Arrays.asList("aa", "bb", "cc", "dd"));
            }
        }
        );
        LAB_POST.getStyleClass()
                .add("danger");
        LAB_POST.setPrefWidth(
                290);
        LAB_POST.setMinHeight(
                100);

        LAB_EVENT.getStyleClass()
                .add("warning");
        LAB_EVENT.setPrefWidth(
                290);
        LAB_EVENT.setMinHeight(
                100);

        LAB_EVENT.setFont(
                new Font(20));

        LAB_ADOPTION.getStyleClass()
                .add("success");
        LAB_ADOPTION.setPrefWidth(
                290);
        LAB_ADOPTION.setFont(
                new Font(20));
        LAB_ADOPTION.setMinHeight(
                100);

        LAB_RECLAMATION.getStyleClass()
                .add("info");
        LAB_RECLAMATION.setPrefWidth(
                290);
        LAB_RECLAMATION.setFont(
                new Font(20));
        LAB_RECLAMATION.setMinHeight(
                100);

        LAB_ABOUT.getStyleClass()
                .add("primary");
        LAB_ABOUT.setPrefWidth(
                290);
        LAB_ABOUT.setFont(
                new Font(20));
        LAB_ABOUT.setMinHeight(
                100);


        LOGIN.setPrefWidth(
                290);
        LOGIN.setFont(
                new Font(20));


        INSCRIPTION.setPrefWidth(
                290);
        INSCRIPTION.setFont(
                new Font(20));


        Title.setStyle("-fx-font: normal bold 30 Langdon; -fx-base: #fff;  ");
        HBox right = new HBox();

        right.setAlignment(Pos.BASELINE_RIGHT);

        right.setMaxWidth(Double.MAX_VALUE);

        VBox rightPaneChild = new VBox();
        VBox leftPaneChild = new VBox();

        rightPaneChild.getStylesheets()
                .add("/edu/fundup/ressources/css/theme.css");
        rightPaneChild.setPrefWidth(leftPane.getPrefWidth() * 4);
        leftPaneChild.getStylesheets()
                .add("/edu/fundup/ressources/css/theme.css");
        leftPaneChild.setPrefSize(
                700, 60);
        // right.setSpacing(right.getMinWidth() - (rightPaneChild.getMinWidth() + leftPane.getMinWidth()));
        rightPane.prefWidthProperty()
                .bind(FundUp.GLOBAL_STAGE.widthProperty());
        rightPaneChild.prefWidthProperty()
                .bind(FundUp.GLOBAL_STAGE.widthProperty().subtract(rightPane.prefWidthProperty().subtract(300)));

        loadLoginGuiLang();

        leftPaneChild.getChildren()
                .addAll(Title);
        if (FundUp.USER_ONLINE
                == null) {
            rightPaneChild.getChildren().addAll(LOGIN, INSCRIPTION);

        } else {

        }

        // ------------Logic------------
        Alert alert = new Alert(Alert.AlertType.WARNING);

        RegisterEntrepriseController logc = new RegisterEntrepriseController();
        rightPane.getChildren()
                .addAll(right,logc);

        LAB_POST.setOnMouseClicked(e
                -> {
            rightPane.getChildren().remove(right);

            ArrayList<Button> listButton = new ArrayList<>();
            Button b1 = new Button("create");
            Button b2 = new Button("liste");
            Button b3 = new Button("liste");
            Button b4 = new Button("liste");
            Button b5 = new Button("liste");

            listButton.add(b1);
            listButton.add(b2);
            listButton.add(b3);
            listButton.add(b4);
            listButton.add(b5);

            try {
                VBox affiche = new AddPost();
                VBox cat = new AddCategory();
                HBox contenu = new HBox();
                affiche.setMinWidth(400);
                affiche.setSpacing(5);
                affiche.setPadding(new Insets(4, 10, 10, 4));
                contenu.setAlignment(Pos.CENTER);
                contenu.getChildren().addAll(affiche, cat);

                rightPane.getChildren().addAll(right, contenu);

            } catch (DataBaseException ex) {
                alert.setContentText(ex.getMessage());
                alert.setHeaderText("Oooops!!!");
                alert.showAndWait();

            }

            //rightPane.getChildren().addAll(right, bc, affiche);
        }
        );
        right.getChildren()
                .addAll(leftPaneChild, rightPaneChild);
        leftPane.getChildren()
                .addAll(TXT_SEARCH, LAB_POST, LAB_EVENT, LAB_ADOPTION, LAB_RECLAMATION, LAB_ABOUT);

        this.getChildren()
                .addAll(leftPane, rightPane);

    }

    /**
     *
     */
    public static void loadLoginGuiLang() {
        Acceuil.TXT_SEARCH.setPromptText("    Search..........");
        Acceuil.LOGIN.setText("Login");
        Acceuil.INSCRIPTION.setText("Inscription");

    }

}
