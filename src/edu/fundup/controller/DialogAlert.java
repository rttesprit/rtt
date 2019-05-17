package edu.fundup.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class DialogAlert extends Region {

    public DialogAlert(String type,String message){

        VBox header = new VBox();
        header.setMinHeight(120);
        header.setAlignment(Pos.CENTER);
        Color color;
        ImageView icon;
        if(type.equalsIgnoreCase("done")){
            color = Color.web("#02C852");
            icon = new ImageView(new Image("/edu/fundup/ressources/images/done.png"));
        }else if(type.equalsIgnoreCase("warning")){
            color = Color.web("#FC6E51");
            icon = new ImageView(new Image("/edu/fundup/ressources/images/warning.png"));
        }else {
            color = Color.web("#ED5565");
            icon = new ImageView(new Image("/edu/fundup/ressources/images/error.png"));
        }
        header.setBackground(new Background(new BackgroundFill(color, new CornerRadii(10, 0, 0, 0,false), Insets.EMPTY)));
        icon.setPreserveRatio(true);
        icon.setSmooth(true);
        icon.setFitWidth(151);
        icon.setFitHeight(78);

        header.getChildren().add(icon);

        VBox container = new VBox();
        container.setAlignment(Pos.TOP_CENTER);
        container.setSpacing(20D);

        VBox.setMargin(container, new Insets(10,0,0,0));


        Label text = new Label();
        text.setWrapText(true);
        text.setText(message);
        text.setMaxWidth(420);
        text.getStyleClass().add("h3");
        text.setAlignment(Pos.CENTER);
        text.setStyle("-fx-text-fill : -text-color; ");

        container.getChildren().addAll(text);

        this.setPadding(Insets.EMPTY);

        VBox box = new VBox();
        box.setStyle("-fx-background-radius : 10 0 10 0; -fx-background-color : white;");

        box.getChildren().addAll(header, container);
        this.getChildren().add(box);




    }
}
