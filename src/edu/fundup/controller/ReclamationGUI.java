package edu.fundup.controller;

import com.jfoenix.controls.*;
import edu.fundup.model.entity.TypeReclamation;
import edu.fundup.model.service.TypeReclamationService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ReclamationGUI extends HBox {

    public ReclamationGUI(Stage stage){

        Button btn  = new Button("reclamation");


        btn.setOnMouseClicked(event -> {
            StackPane secondaryLayout = new StackPane();





            // showing reclamation types
            ToggleGroup radioGroup = new ToggleGroup();
            VBox vBox = new VBox();

            TypeReclamationService ps = new TypeReclamationService();
            ArrayList<TypeReclamation> arrayList = ps.getTypeReclmationList();
            for (TypeReclamation tr: arrayList) {
                System.out.println(tr.getDescription());
                RadioButton radioButton = new RadioButton(tr.getDescription());
                radioButton.setToggleGroup(radioGroup);
                radioButton.setUserData(tr.getId());
                vBox.getChildren().add(radioButton);
            }

            radioGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                if (radioGroup.getSelectedToggle()!=null){
                    System.out.println(radioGroup.getSelectedToggle().getUserData().toString());
                }
            });

            secondaryLayout.getChildren().add(vBox);
            Scene secondScene = new Scene(secondaryLayout);



            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Second Stage");
            newWindow.setScene(secondScene);

            // Specifies the modality for new window.
            newWindow.initModality(Modality.WINDOW_MODAL);

            // Specifies the owner Window (parent) for new window
            newWindow.initOwner(stage);

            // Set position of second window, related to primary window.
            newWindow.setX(stage.getX() + 200);
            newWindow.setY(stage.getY() + 100);

            newWindow.show();

        });

        this.getChildren().add(btn);


    }

}
