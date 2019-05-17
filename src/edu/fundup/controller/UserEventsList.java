//package edu.fundup.controller;
//
//
//import edu.fundup.model.entity.Events;
//
//import edu.fundup.model.iservice.IServiceEvents;
//import edu.fundup.model.service.ServiceEvents;
//import javafx.scene.control.Button;
//
//import javafx.scene.control.ScrollPane;
//import javafx.scene.layout.GridPane;
//
//import java.util.ArrayList;
//import javafx.animation.Interpolator;
//import javafx.animation.KeyFrame;
//import javafx.animation.KeyValue;
//import javafx.animation.Timeline;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.transform.Rotate;
//import javafx.util.Duration;
//import javafx.scene.Node;
//import javafx.scene.input.MouseEvent;
//
//
//public class UserEventsList extends ScrollPane {
//
//    public static HBox contenu;
//    public int idEvent;
//    public static GridPane gp ;
//    private Rotate rotateFront;
//    private Rotate rotateBack;
//    private Timeline timeline;
//    ArrayList<Integer> ids = new ArrayList<>();
//
//
//    public UserEventsList() {
//
//        rotateFront = new Rotate(0, Rotate.X_AXIS);
//        rotateFront.setPivotY(150);
//
//        rotateBack = new Rotate(180, Rotate.X_AXIS);
//        rotateBack.setPivotY(150);
//        timeline = new Timeline();
//
//        this.setHbarPolicy(ScrollBarPolicy.NEVER);
//        this.setVbarPolicy(ScrollBarPolicy.ALWAYS);
//
//        gp = new GridPane();
//        gp.setHgap(10);
//        gp.setVgap(10);
//
//        gp.setStyle("-fx-padding: 10;"
//                + "-fx-border-style: solid inside;"
//                + "-fx-border-width: 2;"
//                + "-fx-border-insets: 5;"
//                + "-fx-border-radius: 5;"
//                + "-fx-border-color: black;");
//
//
//        // IServiceEvents sp = new ServiceEvents();
//        // ArrayList<Events> list = sp.getAll();
//        int x = 0;
//        int y = 0;
//
//        /*for (Events ev : list) {
//
//        IServiceEvents sp = new ServiceEvents();
//        ArrayList<Events> list = sp.getAll();
//        int x = 0;
//        int y = 0;
//
//        for (Events ev : list) {
//
//
//            VBox v1 = items(ev);
//            gp.setPadding(new Insets(5, 0, 5, 0));
//            v1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//                try {
//                    idEvent = ev.getId_event();
//                    System.out.println(idEvent);
//                    flipTile(event, v1);
//                } catch (InterruptedException ex) {
//                }
//                registerListeners(v1);
//
//            });
//            if (x == 4) {
//                x = 0;
//                y++;
//            }
//
//            gp.add(v1, x, y, 1, 1);
//
//            x++;
//
//        }*/
//
//       
//
//        // hb = new Evente(idEvent);
//
//        contenu.getChildren().addAll(hb);
//        contenu.getChildren().remove(0);
//
//    }
//
//    public int getIdEventAnInt() {
//        return idEvent;
//    }
//
//}
//
