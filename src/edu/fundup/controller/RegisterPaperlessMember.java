package edu.fundup.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import edu.fundup.model.entity.Member;
import edu.fundup.model.service.MemberService;
import edu.fundup.utils.CustomAnimation;
import edu.fundup.utils.RegisterValidation;
import javafx.animation.Animation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import org.apache.commons.lang3.RandomStringUtils;


import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.ResourceBundle;
import java.util.function.Supplier;


public class RegisterPaperlessMember extends HBox {

    ImageView pic;

    public RegisterPaperlessMember() {

        /*3 STEPS*/
        Label step1 = new Label("Step 1/3");
        Label step2 = new Label("Step 2/3");
        Label step3 = new Label("Step 3/3");
        /********/
        FontAwesomeIcon icon = new FontAwesomeIcon();
        /******* VBox 1 **********/
        VBox box1 = new VBox();

        Label username = new Label(" Username :");
        Label passwordLabel = new Label("Password :");
        Label passwordConfirmationLabel = new Label("Re-enter pass :");
        Label emailLabel = new Label("Email : ");

        TextField login = new TextField();
        login.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        PasswordField passwordConfirmation = new PasswordField();
        passwordConfirmation.setPromptText("Password");
        TextField email = new TextField();
        email.setPromptText("Email");

        GridPane grid1 = new GridPane();
        grid1.getStyleClass().add("grid");
        grid1.setVgap(15);
        grid1.setHgap(15);
        grid1.setAlignment(Pos.CENTER);

        grid1.add(new Label("Account data"), 0, 0);

        grid1.add(username, 0, 1);
        grid1.add(login, 1, 1);

        grid1.add(passwordLabel, 0, 2);
        grid1.add(password, 1, 2);

        grid1.add(passwordConfirmationLabel, 0, 3);
        grid1.add(passwordConfirmation, 1, 3);

        grid1.add(emailLabel, 0, 4);
        grid1.add(email, 1, 4);

        Button nextToBox2 = new Button();
        nextToBox2.getStyleClass().add("rounded");

        box1.setMinWidth(500);
        box1.setMinHeight(500);

        box1.getChildren().addAll(step1, grid1);

        StackPane stack1 = new StackPane();
        stack1.setStyle("-fx-background-color: rgb(0,0,0, 0.1)");

        stack1.getChildren().addAll(box1, nextToBox2);
        stack1.setMargin(nextToBox2, new Insets(480, 15, 25, 425));


        //*************************

        /******* VBox 2 **********/
        VBox box2 = new VBox();

        Label first_nameLabel = new Label("First name : ");
        Label last_nameLabel = new Label("Last name : ");
        Label addressLabel = new Label("Address : ");
        Label countryLabel = new Label("Country : ");
        Label cityLabel = new Label("City : ");

        TextField last_name = new TextField();
        last_name.setPromptText("Last name");
        TextField first_name = new TextField();
        first_name.setPromptText("First name");
        TextField address = new TextField();
        address.setPromptText("Address");
        TextField city = new TextField();
        city.setPromptText("City");
        TextField country = new TextField();
        country.setPromptText("Country");

        GridPane grid2 = new GridPane();
        grid2.getStyleClass().add("grid");
        grid2.setVgap(15);
        grid2.setHgap(15);
        grid2.setAlignment(Pos.CENTER);

        grid2.add(new Label("Profile data"), 0, 0);

        grid2.add(first_nameLabel, 0, 1);
        grid2.add(first_name, 1, 1);

        grid2.add(last_nameLabel, 0, 2);
        grid2.add(last_name, 1, 2);

        grid2.add(countryLabel, 0, 3);
        grid2.add(country, 1, 3);

        grid2.add(cityLabel, 0, 4);
        grid2.add(city, 1, 4);

        grid2.add(addressLabel, 0, 5);
        grid2.add(address, 1, 5);

        Button backToBox1 = new Button();
        backToBox1.getStyleClass().add("rounded");

        Button btn = new Button("Register");
        btn.getStyleClass().add("sk-btn");
        btn.getStyleClass().add("sk-btn-toolbar");


        ImageView pi = new ImageView();

        pi.setFitWidth(50);
        pi.setFitHeight(50);
        grid2.add(new Label("Set your image here :"), 0, 6);
        grid2.add(pi, 1, 6);

        pi.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                FileChooser fc = new FileChooser();

                FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
                File selectedFile = fc.showOpenDialog(null);
                try {
                    BufferedImage bufferedImage = ImageIO.read(selectedFile);
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    pi.setImage(image);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });


        box2.setMinWidth(500);
        box2.setMinHeight(500);

        StackPane stack2 = new StackPane();
        stack2.setStyle("-fx-background-color: rgb(0,0,0, 0.1)");


        box2.getChildren().addAll(step2, grid2);
        stack2.getChildren().addAll(box2, backToBox1, btn);

        stack2.setMargin(backToBox1, new Insets(480, 425, 25, 15));
        stack2.setMargin(btn, new Insets(425, 0, 0, 0));

        //************************************

        backToBox1.setOnAction(event -> {
            this.getChildren().remove(stack2);
            this.getChildren().add(stack1);
        });
        backToBox1.getStyleClass().add("rounded");

        nextToBox2.setOnAction(event -> {
            if (RegisterValidation.validateName(login.getText()) &&
                    RegisterValidation.validatePassword(password.getText()) &&
                    RegisterValidation.identicPassword(password.getText(), passwordConfirmation.getText())
            ) {
                this.getChildren().remove(stack1);
                this.getChildren().add(stack2);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalidate fields");
                alert.setHeaderText(null);
                alert.setContentText("You must validate all the fields");
                alert.showAndWait();
            }
        });

        Label logErrorLab = new Label("Please enter a valid username ");
        Label identicErrorLab = new Label("Your password fields must be identical ");
        Label passwordErrorLab = new Label(" Your password length must be in (6-15) and must contain special charachters");

        logErrorLab.getStyleClass().add("wronglabel");
        identicErrorLab.getStyleClass().add("wronglabel");
        passwordErrorLab.getStyleClass().add("wronglabel");

        login.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (RegisterValidation.validateName(login.getText())) {

                    login.getStyleClass().remove("error");
                    login.getStyleClass().add("va");

                    stack1.getChildren().remove(logErrorLab);
                    username.getStyleClass().remove("wronglabel");

                    System.out.println("esm shih");

                } else {
                    if ((!(RegisterValidation.validateName(login.getText()))) && (newPropertyValue != true)) {
                        login.getStyleClass().remove("va");
                        login.getStyleClass().add("error");
                        username.getStyleClass().add("wronglabel");
                        stack1.getChildren().add(logErrorLab);
                        stack1.setMargin(logErrorLab, new Insets(220, 0, 0, 0));
                        System.out.println("new Property boucle 2 : " + newPropertyValue);
                        System.out.println("old Property boucle 2 : " + oldPropertyValue);
                        System.out.println("*****");
                    }
                }
            }
        });

        passwordConfirmation.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (RegisterValidation.identicPassword(password.getText(), passwordConfirmation.getText())) {

                    passwordConfirmation.getStyleClass().remove("error");

                    passwordConfirmation.getStyleClass().add("va");

                    stack1.getChildren().remove(identicErrorLab);
                    passwordConfirmationLabel.getStyleClass().remove("wronglabel");

                    System.out.println("pass identiques");

                } else {
                    if ((!(RegisterValidation.identicPassword(password.getText(), passwordConfirmation.getText()))) && (newPropertyValue != true)) {
                        passwordConfirmation.getStyleClass().remove("va");
                        passwordConfirmation.getStyleClass().add("error");
                        passwordConfirmationLabel.getStyleClass().add("wronglabel");
                        stack1.getChildren().add(identicErrorLab);
                        stack1.setMargin(identicErrorLab, new Insets(320, 0, 0, 0));
                        System.out.println("new Property boucle 2 : " + newPropertyValue);
                        System.out.println("old Property boucle 2 : " + oldPropertyValue);
                        System.out.println("*****");
                    }
                }
            }
        });

        password.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (RegisterValidation.validatePassword(password.getText())) {

                    password.getStyleClass().remove("error");
                    password.getStyleClass().add("va");

                    stack1.getChildren().remove(passwordErrorLab);
                    passwordLabel.getStyleClass().remove("wronglabel");

                    System.out.println("pass shih");

                } else {
                    if ((!(RegisterValidation.validatePassword(password.getText()))) && (newPropertyValue != true)) {
                        password.getStyleClass().remove("va");
                        password.getStyleClass().add("error");
                        passwordLabel.getStyleClass().add("wronglabel");
                        stack1.getChildren().add(passwordErrorLab);
                        stack1.setMargin(passwordErrorLab, new Insets(270, 0, 0, 0));
                        System.out.println("new Property boucle 2 : " + newPropertyValue);
                        System.out.println("old Property boucle 2 : " + oldPropertyValue);
                        System.out.println("*****");
                    }
                }
            }
        });


        this.setStyle("-fx-padding: 200px 100px 100px 100px;");

        this.getChildren().addAll(stack1);

        this.setAlignment(Pos.CENTER);
        this.getStylesheets().add("/edu/fundup/ressources/css/register.css");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Member m = new Member(
                        login.getText(), password.getText(), email.getText(),
                        first_name.getText(), last_name.getText(), address.getText(), city.getText(), country.getText(),
                        ""
                );

                // Enabling user and setting his role
                m.setEnable(1);
                m.setRole("Paperless");

                // Setting photo
                Image userPhoto = pi.getImage();
                try {
                    String userPhotoName = saveToFileImageNormal(userPhoto);
                    m.setPhoto(userPhotoName);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Registering
                MemberService ms = new MemberService();
                ms.RegisterPaperlessMember(m);
            }
        });
    }

    public static String saveToFileImageNormal(Image image) throws SQLException {

        File dir = new File("C:/wamp64/www/charity/user/photos");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), "jpg");
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    }
}