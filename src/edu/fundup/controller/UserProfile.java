package edu.fundup.controller;

import static edu.fundup.controller.FundUp.GLOBAL_STAGE;
import edu.fundup.model.entity.Events;
import edu.fundup.model.entity.Member;
import edu.fundup.model.entity.Post;
import edu.fundup.model.entity.Reclamation;
import edu.fundup.model.iservice.IServiceEvents;
import edu.fundup.model.service.MemberService;
import edu.fundup.model.service.ReclamationService;
import edu.fundup.model.service.ServiceEvents;
import edu.fundup.utils.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

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
        circle.getStyleClass().add("changeimage");
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
        editBtn.getStyleClass().add("edit");

        
        Button logoutAdmin = new Button("Déconnexion");
        logoutAdmin.getStyleClass().add("warnning");
        logoutAdmin.setMinHeight(50);
        logoutAdmin.setMaxHeight(50);
        logoutAdmin.setMinWidth(120);
        logoutAdmin.setMaxWidth(100);
            
        logoutAdmin.setOnAction(e -> {
            UserSession.getInstance().cleanUserSession();
            Acceuil.userbox.getChildren().clear();

            LoginController lc = new LoginController();
            Acceuil.rightPane.getChildren().clear();
            Acceuil.rightPane.getChildren().addAll(lc);
            
            Platform.exit();
            System.exit(0);
        });
        
        if(connectedm.getRole().equals("Admin")){
            left.getChildren().addAll(circle,label,logoutAdmin);
        }else { 
            left.getChildren().addAll(circle, label, editBtn);
        }
        // ---------------------------------

        VBox right = new VBox();
        HBox navbar = new HBox();
        VBox content = new VBox();
        right.getChildren().addAll(navbar,content);

        //******************************
        Button UsersBtn = new Button("Utilisateurs");
        Button postsBtn = new Button("Postes");
        Button eventsBtn = new Button("Evennements");
        Button adoptionsBtn = new Button("Adoptions");
        Button contributions = new Button("Contributions");
        Button reclamationsBtn = new Button("Reclamations");

        Button MypostsBtn = new Button("Mes postes");
        Button MyeventsBtn = new Button("Mes évennements");
        Button MyadoptionsBtn = new Button("Mes adoptions");
        Button Mycontributions = new Button("Mes contributions");
        Button MyreclamationsBtn = new Button("Mes reclamations");

        UsersBtn.getStyleClass().add("button-navbar");
        postsBtn.getStyleClass().add("button-navbar");
        eventsBtn.getStyleClass().add("button-navbar");
        adoptionsBtn.getStyleClass().add("button-navbar");
        contributions.getStyleClass().add("button-navbar");
        reclamationsBtn.getStyleClass().add("button-navbar");

        MypostsBtn.getStyleClass().add("button-navbar");
        MyeventsBtn.getStyleClass().add("button-navbar");
        MyadoptionsBtn.getStyleClass().add("button-navbar");
        Mycontributions.getStyleClass().add("button-navbar");
        MyreclamationsBtn.getStyleClass().add("button-navbar");

        if (connectedm.getRole().equals("Admin")){
            navbar.getChildren().addAll(UsersBtn,postsBtn,eventsBtn,adoptionsBtn,reclamationsBtn);
        } else {
            navbar.getChildren().addAll(MypostsBtn, MyeventsBtn, MyadoptionsBtn, MyreclamationsBtn);
        }


        // //////////////// Edit PROFILE /////////////////////
        VBox editContent = new VBox();

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

        Label nameLabel = new Label("Nom :");
        Label mailLabel = new Label("E-Mail :");
        Label passwordLabel = new Label("Mot de passe : ");
        Label passwordConfirmLabel = new Label("Confirmer mot de passe :");
        Label first_nameLabel = new Label("Prénom : ");
        Label last_nameLabel = new Label("Nom : ");
        Label addressLabel = new Label("adresse : ");
        Label cityLabel = new Label("Ville : ");
        Label countryLabel = new Label("Pays : ");
        Label payment_typeLabel = new Label("Type de paiement :");
        Label credit_card_numberLabel = new Label("Numéro carte de crédit :");
        Label cvv_numLabel = new Label("numéro Cvv :");
        Label presidentLabel = new Label("Président");

        Button changePwd = new Button("Changer mot de passe");
        Button disableAccount = new Button("Désactiver le compte");
        Button becomeContributor = new Button("Devenir contributeur");
        Button saveBtn = new Button("Sauvegarder");
        Button cancelBtn = new Button("Annuler");

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
            alert.setTitle("Désactivation du compte");
            alert.setHeaderText("Êtes-vous sûr de désactiver votre compte ?");
            alert.setContentText("Vous serez déconnecté et redirigé vers la page d'accueil");

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
        Button annulerEditBtn = new Button("Annuler");
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
            content.getChildren().clear();

            Button saveP = new Button("Save");

            saveP.getStyleClass().add("success");

            Button cancelP = new Button("Annuler");
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
                            alert.setContentText("Toutes nos félicitations ! Vous êtes devenu membre contributeur.");
                            alert.showAndWait();
                            content.getChildren().clear();
                            editBox.getChildren().remove(becomeContributor);
                        }
                        else{
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Champs non valides");
                            alert.setHeaderText(null);
                            alert.setContentText("Tous les champs sont requis");
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
                content.getChildren().clear();
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

           oldPwd.setPromptText("Mot de passe actuel");
           newPwd.setPromptText("Nouveau mot de passe");
           newPwdConfirm.setPromptText("Nouveau mot de passe");

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
                   alert.setTitle("Succés");
                   alert.setHeaderText("Succés");
                   alert.setContentText("Votre mot de passe a été changé avec succès");
                   alert.showAndWait();

               } else {
                   System.out.println("d5al else");
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setTitle("Erreur");
                   alert.setHeaderText("Champs erronés");
                   alert.setContentText("Les champs que vous avez entrés sont incorrects, veuillez réessayer");
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

        TextField searchUsers = new TextField();
        searchUsers.setMaxWidth(250);
        VBox.setMargin(searchUsers,new Insets(30,0,30,0));
        searchUsers.setPromptText("Filtrer");
        
        TextField searchEvents = new TextField();
        searchEvents.setMaxWidth(250);
        VBox.setMargin(searchEvents,new Insets(30,0,30,0));
        searchEvents.setPromptText("Filtrer");

        ListView<Reclamation> reclamations = new ListView<>();
        reclamations.setMinHeight(700);
        reclamations.setMinWidth(800);
        reclamations.setMaxHeight(800);
        reclamations.setMaxWidth(800);

        ListView<Post> posts = new ListView<>();
        posts.setMinHeight(700);
        posts.setMinWidth(800);
        posts.setMaxHeight(800);
        posts.setMaxWidth(800);

        ListView<Member> users = new ListView<>();
        users.setMinHeight(700);
        users.setMinWidth(800);
        users.setMaxHeight(800);
        users.setMaxWidth(800);

        UsersBtn.setOnAction(e-> {
            TableView table = new TableView();

            table.setEditable(false);
            table.setPrefSize(500, 800);

            TableColumn t0 = new TableColumn("role");
            TableColumn t1 = new TableColumn("Nom d'utilisateur ");
            TableColumn t2 = new TableColumn("Email ");
            TableColumn t3 = new TableColumn("Date d'inscription");


            t0.setCellValueFactory(
                    new PropertyValueFactory<Member,String>("Role")
            );
            t1.setCellValueFactory(
                    new PropertyValueFactory<Member,String>("Login")
            );
            t2.setCellValueFactory(
                    new PropertyValueFactory<Member,String>("mail")
            );
            t3.setCellValueFactory(
                    new PropertyValueFactory<Member,String>("register_date")
            );
            

            final ObservableList<Member> members = FXCollections.observableArrayList(ms.getAllMembers());

            FilteredList<Member> filteredData = new FilteredList<>(members, p -> true);
            searchUsers.setOnKeyReleased(p -> {
                searchUsers.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    System.out.println(newValue);
                    if (!newValue.isEmpty()) {
                        List<Member> obsRr = members.stream().filter(o -> (o.getLogin().toLowerCase().contains(newValue.toLowerCase()) || o.getRole().toLowerCase().contains(newValue.toLowerCase() ))).collect((Collectors.toList()));
                        obsRr.forEach(o -> {
                            System.out.println("processed list, only even numbers: " + o);
                        });
                        ObservableList<Member> sortedList = FXCollections.observableArrayList(obsRr);
                        table.setItems(sortedList);
                    }
                    else {
                        table.setItems(members);
                    }
                });

            });

            table.setItems(members);
            table.getColumns().addAll(t0, t1, t2, t3);

            table.setRowFactory( tv -> {
                TableRow<Member> row = new TableRow<>();
                row.setOnMouseClicked(event -> {

                    if (event.getClickCount() == 2 && (! row.isEmpty()) )
                    {
                        Member item = row.getItem();
                        System.out.println("Member item = row.getItem()"+ item.toString());

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Disable User");
                        alert.setHeaderText(null);
                        alert.setContentText("Are you sure you would like to disable User ?");
                        alert.showAndWait();

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){

                            ms.disableUser(item);

                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setTitle("L'utilisateur a été désactivé");
                            alert2.setHeaderText(null);
                            alert2.setContentText("L'utilisateur a été désactivé");
                            alert2.showAndWait();

                        } else {
                            // ... user chose CANCEL or closed the dialog
                        }
                    }
                });
                return row ;
            });
            content.getChildren().clear();
            content.getChildren().addAll(searchUsers,table);
        });
        
        eventsBtn.setOnAction(e-> {
            TableView table = new TableView();

            table.setEditable(false);
            table.setPrefSize(500, 800);

            TableColumn t0 = new TableColumn("Titre ");
            TableColumn t1 = new TableColumn("Catégorie ");
            TableColumn t2 = new TableColumn("Lieu ");
            TableColumn t3 = new TableColumn("Description");
            TableColumn t4 = new TableColumn("Date");

            t0.setCellValueFactory(
                    new PropertyValueFactory<Member,String>("title")
            );
            t1.setCellValueFactory(
                    new PropertyValueFactory<Member,String>("categorie")
            );
            t2.setCellValueFactory(
                    new PropertyValueFactory<Member,String>("location")
            );
            t3.setCellValueFactory(
                    new PropertyValueFactory<Member,String>("description")
            );
            t4.setCellValueFactory(
                    new PropertyValueFactory<Member,String>("event_date")
            );
            
            IServiceEvents se = new ServiceEvents();
            final ObservableList<Events> events = FXCollections.observableArrayList(se.getAll());

            FilteredList<Events> filteredData = new FilteredList<>(events, p -> true);
            searchEvents.setOnKeyReleased(p -> {
                searchUsers.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    System.out.println(newValue);
                    if (!newValue.isEmpty()) {
                        List<Events> obsRr = events.stream().filter(o -> (o.getCategorie().toLowerCase().contains(newValue.toLowerCase()) || o.getTitle().toLowerCase().contains(newValue.toLowerCase() ))).collect((Collectors.toList()));
                        obsRr.forEach(o -> {
                            System.out.println("processed list, only even numbers: " + o);
                        });
                        ObservableList<Events> sortedList = FXCollections.observableArrayList(obsRr);
                        table.setItems(sortedList);
                    }
                    else {
                        table.setItems(events);
                    }
                });

            });

            table.setItems(events);
            table.getColumns().addAll(t0, t1, t2, t3);

            content.getChildren().clear();
            content.getChildren().addAll(searchEvents,table);
        });

        MypostsBtn.setOnAction(e -> {
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

        MyreclamationsBtn.setOnAction(e -> {
            left.getChildren().removeAll(becomeContributor,disableAccount,changePwd);
            content.getChildren().clear();
            ReclamationListGUI rec;
            try {
                rec = new ReclamationListGUI(GLOBAL_STAGE);
                content.getChildren().add(rec);
            } catch (Exception ex) {
                Logger.getLogger(UserProfile.class.getName()).log(Level.SEVERE, null, ex);
            }  
        });

        //UserEventsList us = new UserEventsList();
        MyeventsBtn.setOnAction(e -> {
//            content.getChildren().clear(); content.getChildren().add(us);
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

    // API APACHE COMMONG LANG3. STRING UTILS
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