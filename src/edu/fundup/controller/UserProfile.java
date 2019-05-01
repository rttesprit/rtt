package edu.fundup.controller;

import com.jfoenix.controls.JFXButton;
import edu.fundup.model.entity.Member;
import edu.fundup.utils.UserSession;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UserProfile extends HBox {

    public Member connectedm;

    public UserProfile() {

        connectedm = UserSession.getInstance().getMember();

        // ------ LEFT VOBX -----------

        VBox left = new VBox();
        left.getStyleClass().add("left");

        ImageView pic = new ImageView();
        pic.setFitWidth(250);
        pic.setFitHeight(250);

        File file = new File("C:/wamp64/www/charity/user/photos/" + connectedm.getPhoto());
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        pic.setImage(image);
        Circle circle = new Circle(120);
        ImagePattern pattern = new ImagePattern(image);
        circle.setFill(pattern);
        VBox.setMargin(circle, new Insets(20,20,0,20));

        Image img = new Image("/edu/fundup/ressources/images/images.jpg");



        Label label = new Label(""); // initializeing

        if(connectedm !=null) {
            if (!(connectedm.getName() == null)) {
                label = new Label(connectedm.getName());
            } else {
                label = new Label(connectedm.getfirst_name() + " " + connectedm.getlast_name());
            }
        }


        Button editBtn = new Button("Edit");
        left.getChildren().addAll(circle,label,editBtn);
        editBtn.getStyleClass().add("edit");
        // ---------------------------------

        VBox right = new VBox();

        HBox navbar = new HBox();
        HBox content = new HBox();
        right.getChildren().addAll(navbar,content);


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

        left.setPrefSize(6000,6000);
        left.setMinWidth(300);
        left.setMaxWidth(300);
        left.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(label, new Insets(50,0,20,0));

        right.setPrefSize(6000,6000);
        HBox.setMargin(right,new Insets(0,0,0,20));


        navbar.setPrefSize(6000,6000);
        VBox.setMargin(navbar, new Insets(0, 0,20,0));
        navbar.setMaxHeight(100);
        navbar.setMinWidth(800);
        navbar.setAlignment(Pos.CENTER);
        navbar.getStyleClass().add("navbar");
        content.setPrefSize(6000,6000);
        this.getStyleClass().add("background");

        this.getChildren().addAll(left,right);
        HBox.setHgrow(this,Priority.ALWAYS);
        this.getStylesheets().add("/edu/fundup/ressources/css/profile.css");

        this.setStyle( "-fx-padding: 20px 20px 20px 20px;" );
    }

}