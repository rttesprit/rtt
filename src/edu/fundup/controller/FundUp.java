/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.controller;

import edu.fundup.model.entity.Membre;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

/**
 *
 * @author Steve
 */
public class FundUp extends Application {
         public static Stage stage = null;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     *
     */
    public static Membre USER_ONLINE = null;

    /**
     *
     */
    public static Acceuil Acc_GUI;

    /**
     *
     */
    public static Scene GLOBAL_SCENE;

    /**
     *
     */
    public static Stage GLOBAL_STAGE;

    /**
     *
     */
    public static BorderPane GLOBAL_PANE_BORDER;

    @Override
    public void start(Stage stage) {
        //--------initialisation-----------

        GLOBAL_STAGE = new Stage();

        Acc_GUI = new Acceuil();
       
        GLOBAL_PANE_BORDER = new BorderPane();
        GLOBAL_SCENE = new Scene(GLOBAL_PANE_BORDER);

        //-----------styling----------------
        GLOBAL_STAGE.setTitle("Tunisia Charity");
        GLOBAL_STAGE.getIcons().add(new Image("/edu/fundup/ressources/images/logo.png")); 
        GLOBAL_PANE_BORDER.setStyle("-fx-background-color: #c0cfd1;");
        GLOBAL_PANE_BORDER.setCenter(Acc_GUI);
//        GLOBAL_PANE_BORDER.setPrefWidth(Acc_GUI.getPrefWidth());
//        GLOBAL_PANE_BORDER.setPrefWidth(Acc_GUI.getPrefHeight());

        //------------logic-----------------
        GLOBAL_STAGE.setScene(GLOBAL_SCENE);
        GLOBAL_STAGE.show();
    }

    @Override
    public void stop() {

    }

   
}
