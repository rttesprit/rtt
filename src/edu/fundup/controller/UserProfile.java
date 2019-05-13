package edu.fundup.controller;

import edu.fundup.model.entity.Member;
import edu.fundup.model.entity.Post;
import edu.fundup.model.entity.Reclamation;
import edu.fundup.model.service.MemberService;
import edu.fundup.model.service.ReclamationService;
import edu.fundup.utils.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import org.apache.commons.lang3.RandomStringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

public class UserProfile extends HBox {

    public Member connectedm;

    public UserProfile() {

        connectedm = UserSession.getInstance().getMember();
        MemberService ms = new MemberService();

        editProfilePaperless ep = new editProfilePaperless(connectedm);
        editProfileContributor ec = new editProfileContributor(connectedm);
        editProfileEntreprise ee = new editProfileEntreprise(connectedm);

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

        circle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fc = new FileChooser();

                FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
                File selectedFile = fc.showOpenDialog(null);
                try {
                    BufferedImage bufferedImage = ImageIO.read(selectedFile);
                    Image newImage = SwingFXUtils.toFXImage(bufferedImage, null);
                    pic.setImage(newImage);
                    ImagePattern newPattern = new ImagePattern(newImage);
                    circle.setFill(newPattern);
                    // Saving photo

                    String userPhotoName = saveToFileImageNormal(newImage);
                    System.out.println(userPhotoName);
                    connectedm.setPhoto(userPhotoName);
                    ms.updatePhoto(userPhotoName,connectedm.getId());

                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        Label label = new Label(""); // initializeing

        if(connectedm !=null) {
            if (!(connectedm.getName() == null)) {
                label = new Label(connectedm.getName());
                label.getStyleClass().add("label-name");
            } else {
                label = new Label(connectedm.getfirst_name() + " " + connectedm.getlast_name());
                label.getStyleClass().add("label-name");
            }
        }

        Button editBtn = new Button("Edit");
        left.getChildren().addAll(circle,label,editBtn);
        editBtn.getStyleClass().add("edit");
        // ---------------------------------

        VBox right = new VBox();
        HBox navbar = new HBox();
        VBox content = new VBox();
        right.getChildren().addAll(navbar,content);

        //******************************

        Button overview = new Button("Overview");
        Button postsBtn = new Button("Posts");
        Button eventsBtn = new Button("Events");
        Button adoptionsBtn = new Button("Adoptions");
        Button contributions = new Button("Contributions");
        Button favourite = new Button("Favourite");
        Button reclamationsBtn = new Button("Reclamations");

        overview.getStyleClass().add("button-navbar");
        postsBtn.getStyleClass().add("button-navbar");
        eventsBtn.getStyleClass().add("button-navbar");
        adoptionsBtn.getStyleClass().add("button-navbar");
        contributions.getStyleClass().add("button-navbar");
        favourite.getStyleClass().add("button-navbar");
        reclamationsBtn.getStyleClass().add("button-navbar");


        navbar.getChildren().addAll(overview,favourite,postsBtn,eventsBtn,adoptionsBtn,reclamationsBtn);



        // //////////////// Edit PROFILE /////////////////////
        VBox editContent = new VBox();
        editContent.setStyle("-fx-border-color: black");

        TextField name = new TextField();
        TextField mail = new TextField();
        TextField password = new TextField();
        TextField passwordConfirm = new TextField();
        TextField first_name = new TextField();
        TextField last_name = new TextField();
        TextField address = new TextField();
        TextField city = new TextField();
        TextField country = new TextField();
        ChoiceBox payment_type = new ChoiceBox(FXCollections.observableArrayList(
                "Master card", "Visa", "Paypal"));
        payment_type.setMaxWidth(200);
        payment_type.setMaxHeight(50);
        TextField credit_card_number = new TextField();
        TextField cvv_num = new TextField();
        TextField president = new TextField();

        Label nameLabel = new Label("Name :");
        Label mailLabel = new Label("E-Mail :");
        Label passwordLabel = new Label("Password : ");
        Label passwordConfirmLabel = new Label("Re-enter Password :");
        Label first_nameLabel = new Label("First name : ");
        Label last_nameLabel = new Label("Last name : ");
        Label addressLabel = new Label("Address : ");
        Label cityLabel = new Label("City : ");
        Label countryLabel = new Label("Country : ");
        Label payment_typeLabel = new Label("Payment type :");
        Label credit_card_numberLabel = new Label("Credit card number :");
        Label cvv_numLabel = new Label("Cvv number :");
        Label presidentLabel = new Label();

        Button changePwd = new Button("Change password");
        Button disableAccount = new Button("Disable Account");
        Button becomeContributor = new Button("Become Contributor");
        Button saveBtn = new Button("Save");
        Button cancelBtn = new Button("Cancel");

        changePwd.getStyleClass().add("primary");
        disableAccount.getStyleClass().add("danger");
        becomeContributor.getStyleClass().add("success");
        saveBtn.getStyleClass().add("success");
        cancelBtn.getStyleClass().add("warning");

        changePwd.setMaxHeight(120);
        changePwd.setMinHeight(50);
        changePwd.setMaxWidth(200);
        disableAccount.setMaxHeight(120);
        disableAccount.setMinHeight(50);
        disableAccount.setMaxWidth(200);
        becomeContributor.setMaxHeight(120);
        becomeContributor.setMinHeight(50);
        becomeContributor.setMaxWidth(200);

        cancelBtn.setMaxHeight(50);
        cancelBtn.setMaxWidth(80);
        saveBtn.setMaxHeight(50);
        saveBtn.setMaxWidth(80);

        disableAccount.setOnAction(disable ->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Disabling account");
            alert.setHeaderText("Are you sure to disable your account ?");
            alert.setContentText("You will be disconnected and redirected to home page");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                connectedm.setEnable(0);
                ms.disableUser(connectedm);
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        });

        VBox editBox = new VBox();
        editBox.setAlignment(Pos.CENTER);
        Button annulerEditBtn = new Button("Cancel");
        annulerEditBtn.getStyleClass().add("cancel");

        annulerEditBtn.setOnAction(a->{
            editBtn.setVisible(true);
            editBox.getChildren().clear();
            left.getChildren().remove(editBox);
            if (content.getChildren().contains(ep)){
                content.getChildren().clear();
            } else if(content.getChildren().contains(ec)){
                content.getChildren().clear();
            } else if(content.getChildren().contains(ee)) {
                content.getChildren().clear();
            }
        });

        editBtn.setOnAction(e-> {

            editBtn.setVisible(false);

            Button saveP = new Button("Save");
            saveP.getStyleClass().add("success");

            Button cancelP = new Button("Cancel");
            cancelP.getStyleClass().add("warning");

            HBox buttonsP = new HBox();
            buttonsP.getChildren().addAll(cancelP,saveP);
            buttonsP.setAlignment(Pos.CENTER);
            HBox.setMargin(saveP,new Insets(0,0,0,60));
            VBox.setMargin(buttonsP,new Insets(50,0,0,0));


            if (connectedm.getRole().equals("Paperless")){
            content.getChildren().clear();
            editBox.getChildren().addAll(becomeContributor,changePwd,disableAccount,annulerEditBtn);
            left.getChildren().add(editBox);
            VBox.setMargin(becomeContributor, new Insets(20,0,20,0));
            VBox.setMargin(changePwd, new Insets(20,0,20,0));
            VBox.setMargin(disableAccount, new Insets(20,0,20,0));

                becomeContributor.setOnAction(action-> {
                    content.getChildren().clear();
                    GridPane leftgrid = new GridPane();
                    leftgrid.setVgap(15);
                    leftgrid.setHgap(15);
                    leftgrid.setAlignment(Pos.CENTER);

                    leftgrid.add(payment_typeLabel,0,1);
                    leftgrid.add(payment_type,1,1);

                    leftgrid.add(credit_card_numberLabel,0,2);
                    leftgrid.add(credit_card_number,1,2);

                    leftgrid.add(cvv_numLabel,0,3);
                    leftgrid.add(cvv_num,1,3);

                    saveBtn.setOnAction(save->{
                        if ( !( payment_type.getValue()==null || cvv_num.getText().equals("") || (credit_card_number.equals("")))) {
                            ms.BecomeContributorMember(payment_type.getValue().toString(), credit_card_number.getText(), cvv_num.getText(), connectedm);
                            connectedm.setRole("Contributor");
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Congratulations");
                            alert.setHeaderText(null);
                            alert.setContentText("Congratulations ! You have become a contributor member.");
                            alert.showAndWait();
                            content.getChildren().clear();
                            editBox.getChildren().remove(becomeContributor);
                        }
                        else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Invalid fields");
                            alert.setHeaderText(null);
                            alert.setContentText("All fields are required");
                            alert.showAndWait();
                        }
                    });

                    VBox container = new VBox();
                    container.setMaxWidth(500);
                    container.setMaxHeight(500);
                    container.setAlignment(Pos.CENTER);
                    HBox buttons = new HBox();
                    buttons.setAlignment(Pos.CENTER);
                    buttons.getChildren().addAll(cancelBtn,saveBtn);
                    HBox.setMargin(cancelBtn, new Insets(0, 20, 0, 0));
                    HBox.setMargin(saveBtn, new Insets(0, 0, 0, 50));
                    container.getChildren().addAll(leftgrid,buttons);
                    container.setMargin(leftgrid,new Insets(20,0,0,0));
                    VBox.setMargin(leftgrid, new Insets(100,0,0,0));
                    content.getChildren().addAll(container);

                    cancelBtn.setOnAction(cancel->{
                        content.getChildren().remove(container);
                    });

                });

                content.setAlignment(Pos.CENTER);
                content.getChildren().add(ep);
            }
            else if (connectedm.getRole().equals("Contributor")) {
                editBox.getChildren().addAll(changePwd, disableAccount, annulerEditBtn);
                left.getChildren().add(editBox);
                VBox.setMargin(changePwd, new Insets(20, 0, 20, 0));
                VBox.setMargin(disableAccount, new Insets(20, 0, 20, 0));

                content.setAlignment(Pos.CENTER);
                content.getChildren().add(ec);

            } else if (connectedm.getRole().equals("Entreprise")) {
                editBox.getChildren().addAll(changePwd, disableAccount, annulerEditBtn);
                left.getChildren().add(editBox);
                VBox.setMargin(changePwd, new Insets(20, 0, 20, 0));
                VBox.setMargin(disableAccount, new Insets(20, 0, 20, 0));

                content.setAlignment(Pos.CENTER);
                content.getChildren().add(ee);
            }
        });

        changePwd.setOnAction(ev ->{
           editBox.getChildren().clear();
           VBox changepwdBox = new VBox();
           changepwdBox.setAlignment(Pos.CENTER);

           Button cancelChpwd = new Button("Cancel");
           cancelChpwd.getStyleClass().add("warning");
           Button saveChpwd = new Button("Save");
           saveChpwd.getStyleClass().add("success");


           PasswordField oldPwd = new PasswordField();
           PasswordField newPwd = new PasswordField();
           PasswordField newPwdConfirm = new PasswordField();

           oldPwd.setPromptText("Actual password");
           newPwd.setPromptText("New password");
           newPwdConfirm.setPromptText("New password");

           cancelChpwd.setOnAction(c -> {
               left.getChildren().remove(editBox);
               editBox.getChildren().clear();
               editBtn.setVisible(true);
           });

           saveChpwd.setOnAction(s -> {
               if ((oldPwd.getText().equals(connectedm.getPassword())) && (newPwd.getText().equals(newPwdConfirm.getText())) && (!oldPwd.getText().equals(newPwd.getText()))) {
                   System.out.println("d5al service");

                   ms.updatePassword(newPwd.getText(),connectedm.getId());
                   connectedm.setPassword(newPwd.getText());
                   left.getChildren().remove(editBox);
                   editBox.getChildren().clear();
                   editBtn.setVisible(true);

                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setTitle("Succes");
                   alert.setHeaderText("Success");
                   alert.setContentText("Your password has been successfully changed");
                   alert.showAndWait();

               } else {
                   System.out.println("d5al else");
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Error");
                   alert.setHeaderText("Wrong fields");
                   alert.setContentText("The fields you entered are incorrect, please try again");
                   alert.showAndWait();
               }
           });

            GridPane chPwdGrid = new GridPane();
            chPwdGrid.setVgap(15);
            chPwdGrid.setHgap(15);
            chPwdGrid.setAlignment(Pos.CENTER);

            chPwdGrid.add(oldPwd,0,0);

            chPwdGrid.add(newPwd,0,1);

            chPwdGrid.add(newPwdConfirm,0,2);

            HBox buttons = new HBox();
            buttons.getChildren().addAll(cancelChpwd,saveChpwd);
            buttons.setAlignment(Pos.CENTER);
            editBox.getChildren().clear();
            changepwdBox.getChildren().addAll(chPwdGrid,buttons);
            HBox.setMargin(cancelChpwd,new Insets(0, 15,0,0));
            VBox.setMargin(buttons, new Insets(15, 0, 0, 0));
            editBox.getChildren().add(changepwdBox);
        });
        // //////////////////////////////////////////////////


        // --------------- CONTENT ----------------
        content.setAlignment(Pos.CENTER);

        TextField searchPosts = new TextField();
        searchPosts.setMaxWidth(250);
        VBox.setMargin(searchPosts,new Insets(30,0,30,0));
        searchPosts.setPromptText("Search by title");


        TextField searchReclamations = new TextField();
        searchReclamations.setMaxWidth(250);
        VBox.setMargin(searchReclamations,new Insets(30,0,30,0));
        searchReclamations.setPromptText("Search by title");


        ListView<Reclamation> reclamations = new ListView<>();
        reclamations.setStyle("-fx-background-color: pink;");
        reclamations.setMinHeight(700);
        reclamations.setMinWidth(800);
        reclamations.setMaxHeight(800);
        reclamations.setMaxWidth(800);

        ListView<Post> posts = new ListView<>();
        posts.setStyle("-fx-background-color: pink;");
        posts.setMinHeight(700);
        posts.setMinWidth(800);
        posts.setMaxHeight(800);
        posts.setMaxWidth(800);

        postsBtn.setOnAction(e -> {
            left.getChildren().removeAll(becomeContributor,changePwd,disableAccount);
            content.getChildren().clear();
            content.getChildren().add(searchPosts);

            // TO DO ///////////////////
            Post post1 = new Post(1,1,"poste 1","ghjghjhgjghg","image","lieu",50,50,53);
            Post post2 = new Post(2,1,"poste 2","sdofsdjgsdm","image2","lieu2",10,40,53);

            ObservableList<Post> Obsposts = FXCollections.observableArrayList();
            Obsposts.add(post1);
            Obsposts.add(post2);
            posts.setItems(Obsposts);
            // TO DO //////////////////

            FilteredList<Post> filteredData = new FilteredList<>(Obsposts, p -> true);
            searchPosts.setOnKeyReleased(p -> {
                searchPosts.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    System.out.println(newValue);
                    if (!newValue.isEmpty()) {
                        List<Post> obsRr = Obsposts.stream().filter(o -> (o.getTitre().toLowerCase().contains(newValue.toLowerCase()))).collect((Collectors.toList()));
                        obsRr.forEach(o -> {
                            System.out.println("processed list, only even numbers: " + o);
                        });
                        ObservableList<Post> sortedList = FXCollections.observableArrayList(obsRr);
                        posts.setItems(sortedList);
                    }
                    else {
                        posts.setItems(Obsposts);
                    }
                });

            });


            content.getChildren().add(posts);
        });

        reclamationsBtn.setOnAction(e -> {
            left.getChildren().removeAll(becomeContributor,disableAccount,changePwd);
            content.getChildren().clear();
            content.getChildren().add(searchReclamations);
            ReclamationService reclamationservice = new ReclamationService();
            content.getChildren().add(reclamations);
        });

        UserEventsList us = new UserEventsList();
        eventsBtn.setOnAction(e -> {
            content.getChildren().clear(); content.getChildren().add(us);
        });
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