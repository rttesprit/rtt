/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.controller;

import static edu.fundup.controller.Acceuil.contenu;
import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Events;
import edu.fundup.model.iservice.IServiceEvents;
import edu.fundup.model.service.ServiceEvents;
import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author lhannachi
 */
public class ListEvents extends ScrollPane {
    public int idEvent;
    public static GridPane gp ;
    private Rotate rotateFront;
    private Rotate rotateBack;
    private Timeline timeline;
    ArrayList<Integer> ids = new ArrayList<>();

    public ListEvents() throws DataBaseException {
        this.setStyle("scroll-pane");
        this.setHbarPolicy(ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        gp= new GridPane();
        gp.getStylesheets().add("/edu/fundup/ressources/css/theme.css");
        gp.setStyle("-fx-padding: 10;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: black;");
        gp.setHgap(10);
        gp.setVgap(10);
        IServiceEvents sp = new ServiceEvents();
        ArrayList<Events> list = sp.getAll();
        int x = 0;
        int y = 0;

        for (Events ev : list) {
            
            VBox v1 = items(ev);
            gp.setPadding(new Insets(5, 0, 5, 0));
            v1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                idEvent =ev.getId_event();
                HBox hb = null;
                hb = new Evente(idEvent);
                contenu.getChildren().addAll(hb);
                contenu.getChildren().remove(0);          
            });
            if (x == 4) {
                x = 0;
                y++;
            }
 
            gp.add(v1, x, y, 1, 1);

            x++;
        }
        this.setContent(gp);
        

    }

    public VBox items(Events ev) {
        Image image = null;
        try {
            String url = "http://localhost:8080/pidev/uploads/" + ev.getFile_url();
            image = new Image(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(350);
        imageView.setFitHeight(300);
        ids.add(ev.getId_event());
        VBox item = new VBox();
        item.setAlignment(Pos.CENTER);
        item.setSpacing(20);
        HBox hb1 = new HBox();
        HBox hb2 = new HBox();
        hb1.setAlignment(Pos.BASELINE_LEFT);
        hb2.setAlignment(Pos.BASELINE_LEFT);
        hb1.setMinWidth(30);
        hb2.setMinWidth(30);
        hb1.setSpacing(150);
        hb2.setSpacing(100);
        Label titre = new Label("Ttitle: "+ev.getTitle());
        Label participant = new Label("Participants: "+ev.getParticipant());
        Label lieu = new Label("Emplacement: "+ev.getLocation());
        Label jValid = new Label("Date: "+ev.getEvent_date());
        hb1.getChildren().addAll(titre, participant);
        hb2.getChildren().addAll(lieu, jValid);

        item.getChildren().addAll(imageView, hb1, hb2);
        return item;
    }

  

   
    public int getIdEvent() {
        return idEvent;
    }

    

}