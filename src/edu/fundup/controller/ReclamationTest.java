package edu.fundup.controller;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;


public class ReclamationTest  extends Application {

    public static void main(String[] args) {
        launch(args);
    }




    @Override
    public void start(Stage primaryStage) throws Exception {


        ReclamationGUI reclamationGUI = new ReclamationGUI(primaryStage,"post",1,70);
        ReclamationGUI reclamationGUI2 = new ReclamationGUI(primaryStage,"post",2,71);
        PopupDemo popupDemo = new PopupDemo(primaryStage);


        TilePane root = new TilePane();
        root.setPadding(new Insets(10,10,10,10));
        root.setHgap(20);
        root.setVgap(30);
        root.getChildren().add(reclamationGUI);
        root.getChildren().add(reclamationGUI2);
        root.getChildren().add(popupDemo);
        Scene scene = new Scene(root);
        LoadStyleSheets loadStyleSheets = new LoadStyleSheets(root);
        primaryStage.setTitle("JFX Popup Demo");
        primaryStage.setScene(scene);

        primaryStage.show();



    }




}
