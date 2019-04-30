package edu.fundup.controller;

import com.jfoenix.controls.JFXButton;
import edu.fundup.model.entity.Member;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;

public class UserProfile extends HBox {

    static Member user;

    public UserProfile() {


        // ------ LEFT VOBX -----------

        VBox left = new VBox();

        left.setStyle("-fx-background-color: blue");

        ImageView pic = new ImageView();
        pic.setFitWidth(250);
        pic.setFitHeight(250);
        Image img = new Image("/edu/fundup/ressources/images/images.jpg");

        pic.setImage(img);

        pic.getStyleClass().add("pic");

        Label usernameLabel = new Label("Skander Jenhani");
        Button editBtn = new Button("Edit");
        left.getChildren().addAll(pic,usernameLabel,editBtn);
        // ---------------------------------

        VBox right = new VBox();

        left.setStyle("-fx-background-color: green");
        HBox navbar = new HBox();
        HBox content = new HBox();
        right.getChildren().addAll(navbar,content);

        navbar.setStyle("-fx-background-color: yellow");


        content.setStyle("-fx-background-color: saddlebrown");

        //******************************

        Button overview = new Button("Overview");
        Button postsBtn = new Button("Posts");
        Button eventsBtn = new Button("Events");
        Button adoptionsBtn = new Button("Adoptions");
        Button contributions = new Button("Contributions");
        Button favourite = new Button("Favourite");
        Button reclamationsBtn = new Button("Reclamations");
        navbar.getChildren().addAll(overview,favourite,postsBtn,eventsBtn,adoptionsBtn,reclamationsBtn);



        //-------- this root & background -----------

        user = new Member("login","pass",  "mail",  "first name",  "last name", "address",  "city",  "country", "photo");

        left.setPrefSize(6000,6000);
        left.setMinWidth(300);
        left.setMaxWidth(300);
        left.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(usernameLabel, new Insets(50,0,20,0));
        VBox.setMargin(pic, new Insets(20,0,0,0));

        right.setPrefSize(6000,6000);
        HBox.setMargin(right,new Insets(0,0,0,20));


        navbar.setPrefSize(6000,6000);
        VBox.setMargin(navbar, new Insets(0, 0,20,0));
        navbar.setMaxHeight(100);
        navbar.setMinWidth(1200);
        navbar.setAlignment(Pos.CENTER);
        content.setPrefSize(6000,6000);
        this.getStyleClass().add("background");

        this.getChildren().addAll(left,right);
        HBox.setHgrow(this,Priority.ALWAYS);
        this.getStylesheets().add("/edu/fundup/ressources/css/profile.css");

        this.setStyle( "-fx-padding: 20px 20px 20px 20px;" );
    }

}