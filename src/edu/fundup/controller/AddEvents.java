/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.controller;


import com.jfoenix.controls.JFXButton;
import static edu.fundup.controller.Acceuil.Title;
import static edu.fundup.controller.Acceuil.contenu;
import static edu.fundup.controller.Acceuil.filter;
import static edu.fundup.controller.Acceuil.listEvents;
import static edu.fundup.controller.Acceuil.right;
import static edu.fundup.controller.Acceuil.rightPane;
import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Events;
import edu.fundup.model.entity.Member;
import edu.fundup.utils.UserSession;
import edu.fundup.model.iservice.IServiceEvents;
import edu.fundup.model.service.ServiceEvents;
import java.io.File;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author lhannachi
 */
public class AddEvents extends VBox {

    private TextField titre;
    private TextArea description;
    private TextField lieu;
    private DatePicker Date;
    private TextField participant;
    private TextField montant;
    private ComboBox category;
    private FileChooser filechooser;
    private Events e;
    private JFXButton BTN_ADD_EVENTS;
    private JFXButton BTN_CLEAR;
    private JFXButton BTN_UPLOAD;
    private Alert alert;
    private ScrollPane sp;

    public AddEvents() throws DataBaseException {
        Member connectedm =  (Member) UserSession.getInstance().getMember();
        this.getStylesheets().add("/edu/fundup/ressources/css/theme.css");
        this.setSpacing(100);
        e=new Events();
        sp = new ScrollPane();
        sp.getStylesheets().add("/edu/fundup/ressources/css/theme.css");
        sp.setStyle("scroll-pane");
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setPadding(new Insets(50, 50, 50, 50));
        alert = new Alert(Alert.AlertType.WARNING);
        titre = new TextField();
        description = new TextArea();
        lieu = new TextField();
        Date = new DatePicker();
        participant = new TextField();
        montant = new TextField();
        category = new ComboBox();
        filechooser =new FileChooser();
        Label ltitre = new Label("Titre* :");
        Label ldescription = new Label("Description* :");
        Label llieu = new Label("Lieu* :");
        Label lDate = new Label("Date* :");
        Label lparticipant = new Label("Nb° participants* :");
        Label lmontant = new Label("Montant* :");
        Label lcategory = new Label("Categorie* :");

        BTN_ADD_EVENTS = new JFXButton();
        BTN_CLEAR = new JFXButton();
        BTN_UPLOAD = new JFXButton();
        
        titre.setFont(new Font(20));
        titre.getStyleClass().add("text-field");
        titre.setPrefWidth(100);

        lieu.setFont(new Font(20));
        lieu.getStyleClass().add("text-field");
        lieu.setPrefWidth(100);
        
        Date.getStyleClass().add("text-field");
        Date.setMaxWidth(213);
        Date.setMinHeight(40);
        
        participant.setFont(new Font(20));
        participant.getStyleClass().add("text-field");
        participant.setPrefWidth(80);
        
        montant.setFont(new Font(20));
        montant.getStyleClass().add("text-field");
        montant.setPrefWidth(80);
        
        category.getStyleClass().add("combo-box");
        category.setMinWidth(100);
        category.setMinHeight(40);
        category.getItems().addAll("Sportif","Concert","Spectacle","Medicale");

        description.setFont(new Font(20));
        description.getStyleClass().add("text-area");
        description.setPrefWidth(400);

        BTN_ADD_EVENTS.getStyleClass().add("success");
        BTN_ADD_EVENTS.setPrefWidth(290);
        BTN_ADD_EVENTS.setFont(new Font(20));
        BTN_ADD_EVENTS.setText("Ajouter");

        BTN_CLEAR.getStyleClass().add("info");
        BTN_CLEAR.setPrefWidth(290);
        BTN_CLEAR.setFont(new Font(20));
        BTN_CLEAR.setText("Vider les champs");
        
        BTN_UPLOAD.getStyleClass().add("info");
        BTN_UPLOAD.setPrefWidth(290);
        BTN_UPLOAD.setFont(new Font(20));
        BTN_UPLOAD.setText("Upload");
        
        

        BTN_CLEAR.setOnMouseClicked(ee -> {

                    titre.setText("");
                    description.setText("");
                    lieu.setText("");
                    Date.setValue(LocalDate.now());
                    participant.setText("");
                    category.setValue("");
        });
        
        BTN_UPLOAD.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File selectedFile = filechooser.showOpenDialog(null);

                if (selectedFile != null) {
                    FileUploader fu = new FileUploader("localhost:8080/pidev");

                    //Upload
                    
                    try {
                        String fileNameInServer = null;
                     fileNameInServer = fu.upload(selectedFile.getAbsolutePath());
                        System.out.println(fileNameInServer);
                        e.setFile_url(fileNameInServer);
                        
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        
                    }
                }
            }
        });
        
        BTN_ADD_EVENTS.setOnMouseClicked((event) -> {
                    
                    
                    if (titre.getText() == null || titre.getText().equals("")) {
                        alert.setContentText("SVP Donner un titre");
                        alert.setHeaderText("Oooops!!!");
                        alert.showAndWait();
                        titre.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");

                    } else {
                        e.setTitle(titre.getText());
                    }

                    if (description.getText() == null || description.getText().equals("") || description.getText().length() < 20) {
                        alert.setContentText("SVP Donner une description detaillé a votre poste");
                        alert.setHeaderText("Oooops!!!");
                        alert.showAndWait();
                        description.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
                    } else {
                        e.setDescription(description.getText());
                    }

                    if (category.getValue() == null) {
                        alert.setContentText("Selectionner une categorie");
                        alert.setHeaderText("Oooops!!!");
                        alert.showAndWait();
                        category.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
                    } else {
                        
                        e.setCategorie(category.getValue().toString());
                    }

                    if (lieu.getText()==null || lieu.getText().equals("") ) {
                        alert.setContentText("Ajouter un emplacement");
                        alert.setHeaderText("Oooops!!!");
                        alert.showAndWait();
                        lieu.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
                    } else {
                        e.setLocation(lieu.getText());
                    }

                    if (Date.getValue()==null) {
                        alert.setContentText("Donner un nombre de jour valide");
                        alert.setHeaderText("Oooops!!!");
                        alert.showAndWait();
                        
                    } else {
                        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(Date.getValue());
                        e.setEvent_date(gettedDatePickerDate);
                    }
                    if (montant.getText().equals("") || montant.getText() == null) {
                        alert.setContentText("Donner le montant ");
                        alert.setHeaderText("Oooops!!!");
                        alert.showAndWait();
                        montant.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
                    } else {
                        e.setMontant(Float.valueOf(montant.getText()));
                    }
                    if (participant.getText().equals("") || participant.getText() == null) {
                        alert.setContentText("Donner le nombre de participant ");
                        alert.setHeaderText("Oooops!!!");
                        alert.showAndWait();
                        participant.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
                    } else {
                        e.setParticipant(Integer.valueOf(participant.getText()));
                    }
                    IServiceEvents newEvent = new ServiceEvents();
                    e.setId_user(connectedm.getId());
                    e.setId_categorie(1);
                    newEvent.add(e); 
                    alert.setContentText("Evenement ajoutee avec suuces ! ");
                        alert.setHeaderText("suuces!!!");
                        alert.showAndWait();
                        participant.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
                    this.getChildren().clear();
                    VBox v = new VBox();
                    v.setSpacing(18);
                    v.getChildren().addAll(filter, listEvents);
                    this.getChildren().addAll(v);
                    
        } );
        
        

        HBox h1 = new HBox();
        HBox h2 = new HBox();
        HBox button = new HBox();
        button.getStylesheets().add("/edu/fundup/ressources/css/theme.css");

        button.setAlignment(Pos.CENTER);
        button.setSpacing(10);
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(40);
        
        h2.setAlignment(Pos.CENTER);
        h2.setSpacing(40);
        
        h1.getChildren().addAll(lDate, Date, lmontant, montant, BTN_UPLOAD );
        h2.getChildren().addAll(lcategory, category, llieu, lieu ,lparticipant ,participant);
        button.getChildren().addAll(BTN_CLEAR, BTN_ADD_EVENTS);
        
        Title.setText("Ajouter Evenement");
        VBox V = new VBox();
        V.getStylesheets().add("/edu/fundup/ressources/css/theme.css");
        V.setSpacing(30);
        V.getChildren().addAll(ltitre, titre, h2, h1, ldescription, description, button);
        sp.setContent(V);
        this.getChildren().addAll(sp);
    }

}
