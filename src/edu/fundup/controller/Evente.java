/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.controller;

import static edu.fundup.controller.Acceuil.Title;
import static edu.fundup.controller.Acceuil.contenu;
import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Events;
import edu.fundup.model.entity.RatingEvents;
import edu.fundup.model.iservice.IServiceEvents;
import edu.fundup.model.service.ServiceEvents;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import org.controlsfx.control.Rating;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import edu.fundup.model.entity.Member;
import edu.fundup.utils.UserSession;
import java.awt.print.PrinterException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 /**
 *
 * @author lhannachi
 */
public class Evente extends HBox {
    
    private Rating rating;
    private Label titre;
    private Label montant;
    private Label participant;
    private Label lieu;
    private Label categorie;
    private Label date;
    private Label desc;
    private Label description;
    
    public static JFXButton BTN_PARTICIPER;
    public static JFXButton BTN_RECLAMER;
    public static JFXButton BTN_MODIFIER;
    public static JFXButton BTN_SUPPRIMER;
    public static JFXButton BTN_LIST_PARTICIPANT;
    
    private static int nbMax;
    private Member connectedm;
    
    private ScrollPane sp;
    
    public Evente(int id) {
        connectedm = (Member) UserSession.getInstance().getMember();
        this.getStylesheets().add("/edu/fundup/ressources/css/theme.css");
        this.setSpacing(100);
        IServiceEvents se = new ServiceEvents();
        Events event = se.findById(id);
        sp = new ScrollPane();
        sp.getStylesheets().add("/edu/fundup/ressources/css/theme.css");
        sp.setStyle("scroll-pane");
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        sp.setPadding(new Insets(50, 50, 50, 50));
        
        VBox firstBox = new VBox();
        firstBox.setSpacing(30);
        
        HBox hb = new HBox();
        VBox vgauche = new VBox();
        VBox vdroite = new VBox();
        
        hb.setPadding(new Insets(10));
        hb.setSpacing(150);
        hb.setAlignment(Pos.CENTER);
        
        vdroite.setSpacing(10);
        
        
        //--------API image+video From Server -------
        
        ImageView imageView = new ImageView();
        Image image = null;
        try {
            String url = "http://localhost:8080/pidev/uploads/" + event.getFile_url();
            image = new Image(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        imageView.setImage(image);
        imageView.setFitWidth(700);
        imageView.setFitHeight(500);
        Media media = new Media("http://localhost:8080/pidev/uploads/" + event.getFile_url()); //Instantiating Media class  

        //Instantiating MediaPlayer class   
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        //Instantiating MediaView class   
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        mediaView.setOnMousePressed((s) -> {
            mediaPlayer.setMute(true);
        });
        //-----------------------------------------
        
        if (event.getFile_url().contains(".mp4")) {
            mediaView.setFitWidth(700);
            mediaView.setFitWidth(500);
            vgauche.getChildren().addAll(mediaView);

        } else {
            vgauche.getChildren().addAll(imageView);
        }
        
        //-------API RATING-------------
        
        rating = new Rating();
            rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                
                RatingEvents r = new RatingEvents(id, connectedm.getId(),(double) newValue);
                IServiceEvents es = new ServiceEvents();
                if (!es.testRaitngs(id, connectedm.getId()))
                {
                    es.rating(r);
                }
                else
                {
                    es.updateRating(r, id, connectedm.getId());
                }
                System.out.println(newValue);
                
            }
                
            });
        //---------------------------
        
        firstBox.setAlignment(Pos.CENTER);
        titre = new Label(event.getTitle());
        titre.setStyle("-fx-font-weight: bold");
        titre.setFont(Font.font("Helvetica Neue", 100));
        
        montant = new Label(event.getMontant()+" TND");
        montant.setStyle("-fx-font-weight: bold");
        montant.setFont(Font.font("Helvetica Neue", 60));

        participant = new Label(nbMax+"/"+event.getParticipant()+" Participants");
        participant.setStyle("-fx-font-weight: bold");
        participant.setFont(Font.font("Helvetica Neue", 50));
        
        categorie = new Label("Type : "+event.getCategorie());
        categorie.setStyle("-fx-font-weight: bold");
        categorie.setFont(Font.font("Helvetica Neue", 30));
        
        lieu = new Label("> "+event.getLocation());
        lieu.setStyle("-fx-font-weight: bold");
        lieu.setFont(Font.font("Helvetica Neue", 30));
        
        date = new Label(""+event.getEvent_date());
        date.setStyle("-fx-font-weight: bold");
        date.setFont(Font.font("Helvetica Neue", 30));
        
        desc = new Label("Description");
        desc.setStyle("-fx-font-weight: bold");
        desc.setFont(Font.font("Helvetica Neue", 40));
        
        description = new Label(event.getDescription() + ".");
        description.setWrapText(true);
        description.setMaxWidth(700);
        
        BTN_PARTICIPER = new JFXButton("Je Participe ");
        BTN_PARTICIPER.setMinWidth(vgauche.getWidth());
        BTN_PARTICIPER.getStyleClass().add("primary");
        BTN_PARTICIPER.setPrefWidth( 400);
        BTN_PARTICIPER.setPrefHeight(150);
        
        BTN_RECLAMER = new JFXButton("Reclamer");
        BTN_RECLAMER.setMinWidth(vgauche.getWidth());
        BTN_RECLAMER.getStyleClass() .add("primary");
        BTN_RECLAMER.setPrefWidth(400);
        BTN_RECLAMER.setPrefHeight(150);
        
        BTN_LIST_PARTICIPANT = new JFXButton("List Participants");
        BTN_LIST_PARTICIPANT.setMinWidth(vgauche.getWidth());
        BTN_LIST_PARTICIPANT.getStyleClass() .add("primary");
        BTN_LIST_PARTICIPANT.setPrefWidth(400);
        BTN_LIST_PARTICIPANT.setPrefHeight(150);
        
        BTN_MODIFIER = new JFXButton("Modifier");
        BTN_MODIFIER.setMinWidth(vgauche.getWidth());
        BTN_MODIFIER.getStyleClass() .add("success");
        BTN_MODIFIER.setPrefWidth(400);
        BTN_MODIFIER.setPrefHeight(150);
        
        BTN_SUPPRIMER = new JFXButton("Supprimer");
        BTN_SUPPRIMER.setMinWidth(vgauche.getWidth());
        BTN_SUPPRIMER.getStyleClass() .add("danger");
        BTN_SUPPRIMER.setPrefWidth(400);
        BTN_SUPPRIMER.setPrefHeight(150);

        BTN_MODIFIER.setOnMouseClicked((s) -> {

            try {
                VBox update = new UpdateEvents(id);
                contenu = new HBox();
                update.setMinWidth(400);
                update.setSpacing(20);
                update.setPadding(new Insets(4, 10, 10, 4));
                contenu.setAlignment(Pos.CENTER);
                contenu.getChildren().add(update);
                this.getChildren().clear();
                this.getChildren().addAll(contenu);
                
                
                
            } catch (DataBaseException ex) {
            }
        } );
        
        BTN_LIST_PARTICIPANT.setOnMouseClicked((s) -> {
            contenu.getChildren().clear();
            HBox list = new ListPartRatiEvent(id);
            contenu.getChildren().add(list);
            
        });
        
        BTN_SUPPRIMER.setOnMouseClicked((s) -> {
                se.remove(id);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Annulation de l'évènement");
                alert2.setHeaderText(null);
                alert2.setContentText("Votre évènement a étè annulé !");
                alert2.showAndWait();
                
            });
        
        BTN_PARTICIPER.setOnMouseClicked((s) -> {
                
                
                
                Events e = new Events();
                e= se.findById(id);
                
                if (!se.alreadyJoin(e.getId_event(), e.getId_user()))
                        {se.join(e.getId_event(), e.getId_user());
                        
                        
                        
                    try {
                        participer(e);
                        incrementer();
                    } catch (IOException ex) {
                        Logger.getLogger(Evente.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (PrinterException ex) {
                        Logger.getLogger(Evente.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (DocumentException ex) {
                        Logger.getLogger(Evente.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MessagingException ex) {
                        Logger.getLogger(Evente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                        }
                
                
            });
        
        //--------User controle --------
        if ((connectedm != null) &&connectedm.getId() == event.getId_user()) {

            vdroite.getChildren().addAll(montant, participant, categorie, lieu, date, BTN_LIST_PARTICIPANT, BTN_MODIFIER, BTN_SUPPRIMER );

        } else {

            vdroite.getChildren().addAll(rating, montant, participant, categorie, lieu, date, BTN_PARTICIPER, BTN_RECLAMER);
            //vdroite.getChildren().addAll(rating, montant, participant, categorie, lieu, date, BTN_PARTICIPER, BTN_RECLAMER, BTN_LIST_PARTICIPANT, BTN_MODIFIER, BTN_SUPPRIMER);
        }
              
        hb.getChildren().addAll(vgauche, vdroite);
        
        HBox hb2 = new HBox();
        VBox vv1 = new VBox();
        
        vv1.getChildren().addAll(desc, description);
        

        hb2.getChildren().add(vv1);

        firstBox.getChildren().addAll(titre, hb, hb2);
        
        Title.setText(""); 
        sp.setContent(firstBox);
        this.getChildren().add(sp);
    }
    //-------------PDF + EMAIL API ---------------
        private void participer(Events e) throws IOException, PrinterException, DocumentException, MessagingException {
         try {
            //construct the text body part
            Document d = new Document();
            //AuthentificationController.getInstance().getUser().getUsername()
            String shemain = "C:/Users/lhannachi/Documents/NetBeansProjects/" + e.getTitle()+connectedm.getName()+".pdf";
            String p = "********************************************************\n "
                    + " Vous etes inscrit à  \n  "
                    + "                                  " + e.getTitle()+ " \n "+"\n"+"\n"+"\n"
                    + "Description de l'évènement  \n:"+"                            " + e.getDescription() + " \n "+"\n"
                    + "Date   :   " + e.getEvent_date()+ " \n "+"\n"
                    + "Emplacement   :   " + e.getLocation()+ " \n "
                    +" \n"+" \n"+" \n"+" \n"
                    +" \n"+" \n"+" \n"
                    +"Merci pour votre confiance.\n"
                    +"Consulter toujours notre plateforme FundUp pour plus d'évènement \n"
                    + "********************************************************\n ";

            PdfWriter.getInstance(d, new FileOutputStream(shemain));
            d.open();
            d.add(new Paragraph(p));
            d.close();

            //String to = AuthentificationController.getInstance().getUser().getEmail();
            String to = connectedm.getmail();

            // Sender's email ID needs to be mentioned
            final String username = "tun.charity@gmail.com";
            final String password = "tunisiacharity";
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hannchi.louay@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            message.setSubject("Participation à un évènement");

            //3) create MimeBodyPart object and set your message text        
            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText("This is message body");

            //4) create new MimeBodyPart object and set DataHandler object to this object        
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            //****AuthentificationController.getInstance().getUser().getUsername()
            String filename = "C:/Users/lhannachi/Documents/NetBeansProjects/" + e.getTitle()+connectedm.getName()+ ".pdf";//change accordingly     
            DataSource source = new FileDataSource(filename);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(filename);

            //5) create Multipart object and add MimeBodyPart objects to this object        
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart2);

            //6) set the multiplart object to the message object    
            message.setContent(multipart);

            //7) send message    
            Transport.send(message);

            System.out.println("Done");
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Participation reussite");
                alert.setHeaderText(null);
                alert.setContentText("Vous avez recu un mail de participation à "+e.getTitle());

                alert.showAndWait();
                
                                     

        } catch (DocumentException | FileNotFoundException ex) {
            System.out.println(ex.getMessage());

        }
        }
        private int incrementer()
        {
            return nbMax++;
        }
    

}
