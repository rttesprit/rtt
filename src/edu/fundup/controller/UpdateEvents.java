/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import static edu.fundup.controller.Acceuil.Title;
import static edu.fundup.controller.Acceuil.contenu;
import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Events;
import edu.fundup.model.entity.Member;
import edu.fundup.utils.UserSession;
import edu.fundup.model.iservice.IServiceEvents;
import edu.fundup.model.service.ServiceEvents;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author lhannachi
 */
public class UpdateEvents extends VBox {

    private TextField titre;
    private TextArea description;
    private TextField lieu;
    private DatePicker Date;
    private TextField participant;
    private ComboBox category;
    private TextField montant;
    private FileChooser filechooser;
    private Events e;
    private Alert alert;

    private JFXButton BTN_UPDATE_EVENTS;
    private JFXButton BTN_ANNULER;
    private JFXButton BTN_UPLOAD;
    private Member connectedm ;

    
    public UpdateEvents(int id) throws DataBaseException {
        connectedm = (Member) UserSession.getInstance().getMember();
        IServiceEvents se = new ServiceEvents();
        Events amodifier = se.findById(id);
        e=new Events();
        this.getStylesheets().add("/edu/fundup/ressources/css/theme.css");
        this.setSpacing(100);
        
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

        BTN_UPDATE_EVENTS = new JFXButton("Modifier");
        BTN_ANNULER = new JFXButton("Annuler");
        BTN_UPLOAD = new JFXButton("Upload");
        
        titre.setFont(new Font(20));
        titre.getStyleClass().add("text-field");
        titre.setPrefWidth(100);
        titre.setText(amodifier.getTitle());

        lieu.setFont(new Font(20));
        lieu.getStyleClass().add("text-field");
        lieu.setPrefWidth(100);
        lieu.setText(amodifier.getLocation());
        
        Date.getStyleClass().add("text-field");
        Date.setMaxWidth(213);
        Date.setMinHeight(40);
        
        participant.setFont(new Font(20));
        participant.getStyleClass().add("text-field");
        participant.setPrefWidth(80);
        participant.setText(amodifier.getParticipant()+"");
        
        montant.setFont(new Font(20));
        montant.getStyleClass().add("text-field");
        montant.setPrefWidth(80);
        montant.setText(amodifier.getMontant()+"");
        
        category.getStyleClass().add("combo-box");
        category.setMinWidth(100);
        category.setMinHeight(40);
        category.getItems().addAll("Sportif","Concert","Spectacle","Medicale");
        category.setValue(amodifier.getCategorie());

        description.setFont(new Font(20));
        description.getStyleClass().add("text-area");
        description.setPrefWidth(400);
        description.setText(amodifier.getDescription());

        BTN_UPDATE_EVENTS.getStyleClass().add("success");
        BTN_UPDATE_EVENTS.setPrefWidth(290);
        BTN_UPDATE_EVENTS.setFont(new Font(20));

        BTN_ANNULER.getStyleClass().add("info");
        BTN_ANNULER.setPrefWidth(290);
        BTN_ANNULER.setFont(new Font(20));

        BTN_UPLOAD.getStyleClass().add("info");
        BTN_UPLOAD.setPrefWidth(290);
        BTN_UPLOAD.setFont(new Font(20));

        e.setFile_url(amodifier.getFile_url());
        

        BTN_ANNULER.setOnMouseClicked(ez
                -> {
            this.getChildren().clear();

            Evente ev = new Evente(id);
            contenu = new HBox();
            ev.setMinWidth(600);
            ev.setPadding(new Insets(4, 10, 10, 4));
            contenu.setAlignment(Pos.CENTER);
            contenu.getChildren().add(ev);
            this.getChildren().addAll(contenu);
        });
        
        BTN_UPLOAD.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File selectedFile = filechooser.showOpenDialog(null);
                
                if (selectedFile != null) {
                    FileUploader fu = new FileUploader("localhost:8080/pidev");

                    //Upload
                    String fileNameInServer = null;
                    try { 
                        
                        fileNameInServer = fu.upload(selectedFile.getAbsolutePath());
                        e.setFile_url(fileNameInServer);
                     
                        
                    } catch (Exception ex) {
                    }
                }
                
                
            }
        });
        
        BTN_UPDATE_EVENTS.setOnMouseClicked((ee) -> {
            
            
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
                        e.setCategorie(amodifier.getCategorie());
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
                        e.setEvent_date(amodifier.getEvent_date());
                    }else{
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
                    
                    
                    e.setId_user(connectedm.getId());
                    e.setId_categorie(1);
            
                    se.update(e, amodifier.getId_event());
                        
            this.getChildren().clear();

            Evente ev = new Evente(id);
            contenu = new HBox();
            ev.setMinWidth(600);
            ev.setPadding(new Insets(4, 10, 10, 4));
            contenu.setAlignment(Pos.CENTER);
            contenu.getChildren().add(ev);
            this.getChildren().addAll(contenu);
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
        button.getChildren().addAll(BTN_UPDATE_EVENTS, BTN_ANNULER);
        
        Title.setText("Modifier Evenement : "+amodifier.getTitle());           
        this.getChildren().addAll(ltitre, titre, h2, h1, ldescription, description, button);
    }
}
