package edu.fundup.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.fundup.model.entity.TypeReclamation;
import edu.fundup.model.service.TypeReclamationService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TypeReclamationAdmin extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(294.0);
        anchorPane.setPrefWidth(770.0);

        LoadStyleSheets loadStyleSheets = new LoadStyleSheets(anchorPane);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
      //  hBox.setPrefHeight(100.0);
       // hBox.maxWidth(770.0);
       // hBox.minWidth(200.0);
        //hBox.setSpacing(10.0);



        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        //vBox.prefWidth(700);
        //vBox.prefHeight(270.0);


        JFXButton ajouterbtn = new JFXButton("Ajouter");
        vBox.setPadding(new Insets(10,50,20,30));


        JFXButton effacerbtn = new JFXButton("Effacer");
        vBox.getChildren().addAll(ajouterbtn,effacerbtn);
        vBox.setSpacing(70.0);

        hBox.getChildren().add(vBox);
        HBox hBox1 = new HBox();
        TableView<TypeReclamation> tableView = new TableView<>();
       // tableView.prefHeight(273.0);
        //tableView.prefWidth(564.0);
        tableView.setEditable(true);
        TableColumn<TypeReclamation,String> objetCol = new TableColumn<>("POST/EVENT");
        objetCol.setPrefWidth(150);
        TableColumn<TypeReclamation,String> descCol = new TableColumn<>("Description");
        descCol.setPrefWidth(500);
        descCol.setEditable(true);
        descCol.setCellFactory(TextFieldTableCell.forTableColumn());

        ObservableList<TypeReclamation> typeReclamations1 = FXCollections.observableArrayList();
        TypeReclamationService typeReclamationService = new TypeReclamationService();
        ArrayList<TypeReclamation> typeReclamations = typeReclamationService.getTypeReclmationList();

        for (TypeReclamation tt:typeReclamations) {
            typeReclamations1.add(tt);
            System.out.println("aa"+tt.getObjet()+"aa");
        }
        objetCol.setCellValueFactory(new PropertyValueFactory<>("objet"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableView.setItems(typeReclamations1);

        tableView.getColumns().addAll(objetCol,descCol);
        hBox1.setAlignment(Pos.CENTER_LEFT);
        //hBox1.prefWidth(700.0);
        //hBox1.prefHeight(276.0);
        hBox1.getChildren().addAll(hBox,tableView);
        anchorPane.getChildren().add(hBox1);

        ajouterbtn.setOnMouseClicked(event ->
                addTypeReclamation(primaryStage)
        );
        Scene scene = new Scene(anchorPane);
        primaryStage.setTitle("tttt");
        primaryStage.setScene(scene);
        primaryStage.show();



    }

    public static void main(String[] args) {
        launch(args);
    }


    public void addTypeReclamation(Stage stage){
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.prefHeight(400);
        anchorPane.prefWidth(600);

        JFXButton saveBtn = new JFXButton("Valider");
        saveBtn.setLayoutX(248.0);
        saveBtn.setLayoutY(241);

        JFXTextField descTF = new JFXTextField("Description");
        descTF.setLayoutX(190);
        descTF.setLayoutY(173);

        JFXComboBox<String> typeCB = new JFXComboBox<>();
        typeCB.setLayoutX(230);
        typeCB.setLayoutY(95);

        typeCB.getItems().addAll("POST","EVENT");

        anchorPane.getChildren().addAll(descTF,saveBtn,typeCB);

        Scene secondScene = new Scene(anchorPane);


        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Reclamation");
        newWindow.setScene(secondScene);

        // Specifies the modality for new window.
        //newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(stage);

        // Set position of second window, related to primary window.
        newWindow.setX(stage.getX() + 200);
        newWindow.setY(stage.getY() + 100);

        newWindow.show();


    }
}
