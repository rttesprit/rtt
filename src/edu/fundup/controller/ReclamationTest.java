package edu.fundup.controller;

import com.jfoenix.controls.JFXBadge;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;


public class ReclamationTest  extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        ReclamationGUI reclamationGUI = new ReclamationGUI(primaryStage);
        Button btn2 = new Button("bb");
        JFXBadge jfxBadge = new JFXBadge();
        jfxBadge.setText("5");


        TilePane root = new TilePane();
        root.setPadding(new Insets(10,10,10,10));
        root.setHgap(20);
        root.setVgap(30);
        root.getChildren().add(reclamationGUI);
        root.getChildren().add(jfxBadge);
        Scene scene = new Scene(root);

        primaryStage.setTitle("JFX Popup Demo");
        primaryStage.setScene(scene);

        primaryStage.show();



    }
}
