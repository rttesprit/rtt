package edu.fundup.controller;

import static edu.fundup.controller.Acceuil.seConnecterBtn;
import edu.fundup.model.entity.Member;
import edu.fundup.utils.UserSession;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.ComboBox;


public class UserInfoBoxController extends HBox{

    public Member connectedm;

    public UserInfoBoxController() throws IOException {

        connectedm = UserSession.getInstance().getMember();

        if(connectedm != null) {
            System.out.println("From the set Box user is : " + connectedm.toString());

            if ( !( ((connectedm.getfirst_name())==null) && (connectedm.getlast_name()==null) ) ) {
                System.out.println("Condition !( ((connectedm.getfirst_name())==null) && (connectedm.getlast_name()==null) ) From userBoxInfo "+ !( ((connectedm.getfirst_name())==null) && (connectedm.getlast_name()==null) ));
                Label fullname = new Label(connectedm.getfirst_name() + " " + connectedm.getlast_name());
                this.getChildren().add(fullname);
                HBox.setMargin(fullname, new Insets(10, 10, 10, 10));
            } else {
                Label name = new Label(connectedm.getName());
                System.out.println("name from userBox"+ name);
                this.getChildren().add(name);
                HBox.setMargin(name, new Insets(10, 10, 10, 10));
            }

            ImageView iv = new ImageView();
            iv.setFitHeight(200);
            iv.setFitWidth(200);
            File file = new File("C:/wamp64/www/charity/user/photos/" + connectedm.getPhoto());
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            iv.setImage(image);
            Circle circle = new Circle(25);
            ImagePattern pattern = new ImagePattern(image);
            circle.setFill(pattern);
            this.getChildren().add(circle);
            HBox.setMargin(iv, new Insets(10, 10, 10, 10));

            String[] items = {"Profile","Log out"};
            ComboBox btn = new ComboBox((FXCollections.observableArrayList(items)));
            btn.setMaxWidth(30);
            btn.setMinWidth(30);
            HBox.setMargin(btn, new Insets(10, 10, 10, 10));


            btn.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

                // if the item of the list is changed
                public void changed(ObservableValue ov, Number value, Number new_value)
                {

                    // set the text for the label to the selected item
                    //l1.setText(st[new_value.intValue()] + " selected");
                }
            });
            btn.setOnAction(event -> {
                if (btn.valueProperty().get().equals("Profile")) {
                    UserProfile profile = new UserProfile();
                    Acceuil.rightPane.getChildren().clear();
                    Acceuil.rightPane.getChildren().add(profile);
                    btn.valueProperty().set(null);
                    
                }  else if (btn.valueProperty().get().equals("Log out")){

                    UserSession.getInstance().cleanUserSession();
                    Acceuil.userbox.getChildren().clear();

                    LoginController lc = new LoginController();
                    Acceuil.rightPane.getChildren().clear();
                    Acceuil.rightPane.getChildren().addAll(lc);
                    Acceuil.userbox.getChildren().add(seConnecterBtn);
                }
            });
            this.getChildren().add(btn);
        }
        this.setMinHeight(100);
        this.setMinWidth(300);

        this.setMaxHeight(80);
        this.setMaxWidth(400);
        this.setAlignment(Pos.CENTER);

        this.getStyleClass().add("UserInfoBox");
        this.getStylesheets().add("/edu/fundup/ressources/css/userinfobox.css");
    }
}
