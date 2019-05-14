
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.controller;

import static edu.fundup.controller.Acceuil.contenu;
import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Events;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author lhannachi
 */
public class SearchEvent extends ScrollPane {
    public int idEvent;
    public static GridPane gp ;
    ArrayList<Integer> ids = new ArrayList<>();

    public SearchEvent(ArrayList<Events> list ) throws DataBaseException {

        this.setStyle("scroll-pane");

        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
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
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("http://127.0.0.1:8080/pidev/uploads/"+ev.getFile_url()));
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
        hb1.setSpacing(200);
        hb2.setSpacing(100);
        Label titre = new Label(ev.getTitle());
        Label montant = new Label(ev.getMontant() + "");
        Label lieu = new Label(ev.getLocation());
        Label jValid = new Label(ev.getEvent_date()+ "");
        hb1.getChildren().addAll(titre, montant);
        hb2.getChildren().addAll(lieu, jValid);

        item.getChildren().addAll(imageView, hb1, hb2);
        return item;
    }
    public int getIdEvent() {
        return idEvent;
    }

    

}