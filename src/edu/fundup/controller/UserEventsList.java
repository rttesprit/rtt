package edu.fundup.controller;


import edu.fundup.model.entity.Events;
import edu.fundup.model.iservice.IServiceEvents;
import edu.fundup.model.service.ServiceEvents;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
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
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class UserEventsList extends ScrollPane {

    public static HBox contenu;
    public int idEvent;
    public static GridPane gp ;
    private Rotate rotateFront;
    private Rotate rotateBack;
    private Timeline timeline;
    ArrayList<Integer> ids = new ArrayList<>();


    public UserEventsList() {

        rotateFront = new Rotate(0, Rotate.X_AXIS);
        rotateFront.setPivotY(150);

        rotateBack = new Rotate(180, Rotate.X_AXIS);
        rotateBack.setPivotY(150);
        timeline = new Timeline();

        this.setHbarPolicy(ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollBarPolicy.ALWAYS);

        gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);

        gp.setStyle("-fx-padding: 10;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: black;");


        IServiceEvents sp = new ServiceEvents();
        ArrayList<Events> list = sp.getAll();
        int x = 0;
        int y = 0;

        for (Events ev : list) {

            VBox v1 = items(ev);
            gp.setPadding(new Insets(5, 0, 5, 0));
            v1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                try {
                    idEvent = ev.getId_event();
                    System.out.println(idEvent);
                    flipTile(event, v1);
                } catch (InterruptedException ex) {
                }
                registerListeners(v1);

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
        Label titre = new Label("Ttitle: " + ev.getTitle());
        Label participant = new Label("Participants: " + ev.getParticipant());
        Label lieu = new Label("Emplacement: " + ev.getLocation());
        Label jValid = new Label("Date: " + ev.getEvent_date());
        hb1.getChildren().addAll(titre, participant);
        hb2.getChildren().addAll(lieu, jValid);

        item.getChildren().addAll(imageView, hb1, hb2);
        return item;
    }

    private void registerListeners(VBox v1) {

        rotateFront.angleProperty().addListener((o, ov, nv) -> {
            if (nv.doubleValue() < 90) {
                v1.setVisible(true);
            } else {
                v1.setVisible(false);
            }
        });
    }
    public int getIdEvent() {
        return idEvent;
    }
    private void flipTile(MouseEvent event,VBox v) throws InterruptedException {
        v.getTransforms().add(rotateFront);
        if (rotateFront.getAngle() > 0) {
            // Flip back to front
            KeyValue kv0Front = new KeyValue(rotateFront.angleProperty(), rotateFront.getAngle(), Interpolator.EASE_BOTH);
            KeyValue kv1Front = new KeyValue(rotateFront.angleProperty(), 0, Interpolator.EASE_BOTH);
            KeyValue kv0Back = new KeyValue(rotateBack.angleProperty(), rotateBack.getAngle(), Interpolator.EASE_BOTH);
            KeyValue kv1Back = new KeyValue(rotateBack.angleProperty(), 180, Interpolator.EASE_BOTH);
            KeyFrame kf0 = new KeyFrame(Duration.ZERO, kv0Front, kv0Back);
            KeyFrame kf1 = new KeyFrame(Duration.millis(2000), kv1Front, kv1Back);
            timeline.getKeyFrames().setAll(kf0, kf1);
        } else {
            // Flip front to back
            KeyValue kv0Front = new KeyValue(rotateFront.angleProperty(), rotateFront.getAngle(), Interpolator.EASE_BOTH);
            KeyValue kv1Front = new KeyValue(rotateFront.angleProperty(), 180, Interpolator.EASE_BOTH);
            KeyValue kv0Back = new KeyValue(rotateBack.angleProperty(), rotateBack.getAngle(), Interpolator.EASE_BOTH);
            KeyValue kv1Back = new KeyValue(rotateBack.angleProperty(), 0, Interpolator.EASE_BOTH);
            KeyFrame kf0 = new KeyFrame(Duration.ZERO, kv0Front, kv0Back);
            KeyFrame kf1 = new KeyFrame(Duration.millis(2000), kv1Front, kv1Back);
            timeline.getKeyFrames().setAll(kf0, kf1);
        }
        timeline.play();
        Thread.sleep(1000);
        Node source = (Node) event.getSource();
        Integer colIndex = gp.getColumnIndex(source);
        Integer rowIndex = gp.getRowIndex(source);
        HBox hb = null;
        hb = new Evente(idEvent);
        contenu.getChildren().addAll(hb);
        contenu.getChildren().remove(0);

    }

    public int getIdEventAnInt() {
        return idEvent;
    }
}    