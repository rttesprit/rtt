package edu.fundup.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import edu.fundup.model.entity.Reclamation;
import edu.fundup.model.entity.TypeReclamation;
import edu.fundup.model.service.ReclamationService;
import edu.fundup.model.service.TypeReclamationService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class ReclamationGUI extends HBox {

    public ReclamationGUI(Stage stage, String type, int iduser, int idobjet) {

        sendmail();
        JFXDialog jfxDialog = new JFXDialog();
        Boolean hasDone = false;
        TypeReclamationService typeReclamationService = new TypeReclamationService();
        ArrayList<TypeReclamation> typeReclamations = typeReclamationService.getTypeReclamationListWithType(type);
        ReclamationService reclamationService = new ReclamationService();
        Reclamation reclamation1 = new Reclamation();
        reclamation1.setIdobjet(idobjet);
        reclamation1.setIduser(iduser);

        for (TypeReclamation rec : typeReclamations) {
            reclamation1.setIdtr(rec.getId());
            if (reclamationService.reclamationExist(reclamation1)) {
                hasDone = true;
                break;
            }

        }


        JFXButton btn = new JFXButton("reclamation");
        JFXButton btnEnvoyer = new JFXButton("Envoyer");
        JFXButton btnAnnuler = new JFXButton("Annuler");
        btnAnnuler.getStyleClass().add("danger");

        if (hasDone)
            btn.setDisable(true);

        btnAnnuler.setOnAction(e -> {
            final Node source = (Node) e.getSource();
            final Stage stage1 = (Stage) source.getScene().getWindow();
            stage1.close();
        });


        Boolean finalHasDone = hasDone;
        btn.setOnMouseClicked(event -> {
            StackPane secondaryLayout = new StackPane();


            // showing reclamation types
            ToggleGroup radioGroup = new ToggleGroup();
            VBox vBox = new VBox();

            TypeReclamationService ps = new TypeReclamationService();
            ArrayList<TypeReclamation> arrayList = ps.getTypeReclamationListWithType(type);
            for (TypeReclamation tr : arrayList) {
                System.out.println(tr.getDescription());
                RadioButton radioButton = new RadioButton(tr.getDescription());
                radioButton.setToggleGroup(radioGroup);
                radioButton.setUserData(tr.getId());
                vBox.getChildren().add(radioButton);
            }

            radioGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                if (radioGroup.getSelectedToggle() != null) {
                    System.out.println(radioGroup.getSelectedToggle().getUserData().toString());
                }
            });

            btnEnvoyer.setOnAction(event1 -> {
                String verif = "";
                if (finalHasDone || verif.equalsIgnoreCase("true")) {
                    jfxDialog.setContent(new DialogAlert("error", "Error"));

                } else {


                    String value = "";
                    if (radioGroup.getSelectedToggle() != null) {
                        value = radioGroup.getSelectedToggle().getUserData().toString();
                        System.out.println(value);
                        Reclamation reclamation = new Reclamation();
                        reclamation.setDate(new Date());
                        reclamation.setIduser(iduser);
                        reclamation.setIdobjet(idobjet);
                        reclamation.setEtat("En Cours");

                        reclamation.setIdtr(Integer.parseInt(value));

                        ReclamationService rs = new ReclamationService();
                        rs.addReclamation(reclamation);
                        jfxDialog.setContent(new DialogAlert("done", "Reclamation Envoyer"));
                        verif = "true";
                        btn.setDisable(true);
                        btnEnvoyer.setDisable(true);
                        btnAnnuler.setText("Fermer");

                    } else {
                        jfxDialog.setContent(new DialogAlert("warning", "Choix invalide"));
                    }


                }
                jfxDialog.show(secondaryLayout);


            });

            HBox hBox = new HBox();
            hBox.setPadding(new Insets(20, 0, 0, 0));
            hBox.getChildren().add(btnEnvoyer);
            hBox.getChildren().add(btnAnnuler);
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(50.0);
            vBox.getChildren().add(hBox);
            vBox.setPadding(new Insets(10, 50, 20, 30));
            vBox.setSpacing(10);

            secondaryLayout.getChildren().add(vBox);

            Scene secondScene = new Scene(secondaryLayout);
            LoadStyleSheets loadStyleSheets = new LoadStyleSheets(secondaryLayout);


            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Reclamation");
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

    public static void sendmail() {
        //Setting up configurations for the email connection to the Google SMTP server using TLS
        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        //Establishing a session with required user details
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mehd.bk.i@gmail.com", "jhhtxzxjapuetqdh");
            }
        });
        try {
            //Creating a Message object to set the email content
            MimeMessage msg = new MimeMessage(session);
            //Storing the comma seperated values to email addresses
            String to = "mehdi.baklouti@esprit.tn";
            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
            addresses in an array of InternetAddress objects*/
            InternetAddress[] address = InternetAddress.parse(to, true);
            //Setting the recepients from the address variable
            msg.setRecipients(Message.RecipientType.TO, address);
            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
            msg.setSubject("Sample Mail : " + timeStamp);
            msg.setSentDate(new Date());
            msg.setText("Sampel System Generated mail");
            msg.setHeader("XPriority", "1");
            Transport.send(msg);
            System.out.println("Mail has been sent successfully");
        } catch (MessagingException mex) {
            System.out.println("Unable to send an email" + mex);
        }
    }
}
