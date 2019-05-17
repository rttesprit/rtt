/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.controller;

import animatefx.animation.SlideInRight;
import com.jfoenix.controls.JFXButton;
import static edu.fundup.controller.Acceuil.LAB_EVENT;
import edu.fundup.model.entity.Member;
import edu.fundup.utils.AutoCompleteTextField;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;


import edu.fundup.utils.ObservableUser;
import javafx.scene.control.*;

import edu.fundup.utils.UserSession;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
/**
 *
 * @author hhamzaoui
 */
public class Acceuil extends HBox implements Observer {

    public static HBox contenu;

    public static Button LAB_POST;
    public static Button LAB_EVENT;
    public static Button LAB_ADOPTION;
    public static Button LAB_RECLAMATION;
    public static Button LAB_ABOUT;

    public static Button LOGIN = new Button("Login");

    public static AutoCompleteTextField TXT_SEARCH;
    public static Label Title;
    public static VBox rightPane = new VBox();
    public static VBox leftPane = new VBox();
    public static VBox rightPaneChild = new VBox();
    public static VBox leftPaneChild = new VBox();


    public static Button INSCRIPTION = new Button("Inscription");
    public static VBox navButtons = new VBox();
    public static HBox navBar = new HBox();
    public static BorderPane bpNavBar = new BorderPane();
    public static HBox right = new HBox();
    public static HBox filter = new HBox();
    public static ScrollPane listEvents ;
    public static HBox userbox = new HBox();
    public static GridPane catt = new MyAnimalsPosts();

    Member onlineMember = UserSession.getInstance().getMember();

    public Acceuil() {
        userbox.setMinHeight(100);
        userbox.setMaxHeight(100);
        userbox.setMinWidth(300);

        rightPaneChild.setAlignment(Pos.CENTER);


        // Create the Subject and Observers.
        ObservableUser observableUser = new ObservableUser(onlineMember);
        // Add the Observer
        observableUser.addObserver(this);

        // Make changes to the Subject.
        /*observableUser.setOnlineMember(new Member(150));
        observableUser.setOnlineMember(new Member(151));*/



        // ------------initialisation------------
        LAB_POST = new Button("Posts");
        LAB_EVENT = new Button("Evennements");
        LAB_ADOPTION = new Button("Adpotions");
        LAB_RECLAMATION = new Button("Reclamation");
        LAB_ABOUT = new Button("About us");
        TXT_SEARCH = new AutoCompleteTextField();
        Title = new Label("Page Name...");
        LOGIN = new Button();
        INSCRIPTION = new JFXButton();


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
        right = new HBox();

        right.setAlignment(Pos.BASELINE_RIGHT);

        right.setMaxWidth(Double.MAX_VALUE);



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


        // ------------Logic------------
        Alert alert = new Alert(Alert.AlertType.WARNING);




        


        rightPaneChild.getChildren().addAll(LOGIN, INSCRIPTION);
        rightPane.getChildren().addAll(right);


         

            //rightPane.getChildren().addAll(right, bc, affiche);
        
        // ********************* MAIN FOR INTEGRATION ***********************
        

        contenu = new HBox();
        contenu.setAlignment(Pos.CENTER);
        
        LAB_ADOPTION.setOnMouseClicked(e
                -> {
           rightPane.getChildren().clear(); 
           //catt.getStyleClass().add("/edu/fundup/ressources/css/StyleJubran.css");     	
           rightPane.getChildren().addAll(catt);
            //   new SlideInRight(rightPane).play(); 
           new  SlideInRight(rightPane).play();
        
                }
                );

       
        // FIRST PAGE LOGIN ***********************************************
        
       

        leftPane.getChildren().addAll(userbox,TXT_SEARCH, LAB_POST, LAB_EVENT, LAB_ADOPTION, LAB_RECLAMATION, LAB_ABOUT);


        this.getChildren().addAll(leftPane, rightPane);

        // ********************* MAIN FOR INTEGRATION ***********************
    
       

    /**
     *
     */

    }
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Member) {
            onlineMember = (Member) arg;
            // refresh elements on User Change
            rightPaneChild.getChildren().removeAll(LOGIN,INSCRIPTION);

            System.out.println("FROM ACCEUIL Member changed to " + onlineMember.toString());
        } else {
            System.out.println("FROM ACCEUIL  Member: Some other change to subject!");
        }
    }
    
        public static void loadLoginGuiLang() {
        Acceuil.TXT_SEARCH.setPromptText("    Search..........");
        Acceuil.LOGIN.setText("Login");
        Acceuil.INSCRIPTION.setText("Inscription");

    }

}
    

