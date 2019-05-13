package edu.fundup.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import edu.fundup.model.entity.Reclamation;
import edu.fundup.model.entity.TypeReclamation;
import edu.fundup.model.service.ReclamationService;
import edu.fundup.model.service.TypeReclamationService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;

public class ReclamationGUI extends HBox {

    public ReclamationGUI(Stage stage , String type , int iduser, int idobjet){



        JFXDialog jfxDialog = new JFXDialog();
        Boolean hasDone = false;
        TypeReclamationService typeReclamationService  = new TypeReclamationService();
        ArrayList<TypeReclamation> typeReclamations = typeReclamationService.getTypeReclamationListWithType(type);
        ReclamationService reclamationService = new ReclamationService();
        Reclamation reclamation1 = new Reclamation();
        reclamation1.setIdobjet(idobjet);
        reclamation1.setIduser(iduser);

        for(TypeReclamation rec: typeReclamations){
            reclamation1.setIdtr(rec.getId());
            if(reclamationService.reclamationExist(reclamation1)){
                hasDone = true;
                break;
            }

        }


        JFXButton btn  = new JFXButton("reclamation");
        JFXButton btnEnvoyer = new JFXButton("Envoyer");
        JFXButton btnAnnuler = new JFXButton("Annuler");
        btnAnnuler.getStyleClass().add("danger");

        if(hasDone)
        btn.setDisable(true);

        btnAnnuler.setOnAction(e -> {
            final Node source = (Node) e.getSource();
            final Stage stage1 = (Stage) source.getScene().getWindow();
            stage1.close();
        });


        Boolean finalHasDone = hasDone;
        btn.setOnMouseClicked(event -> {
            StackPane secondaryLayout = new StackPane();





            // showing reclamation types
            ToggleGroup radioGroup = new ToggleGroup();
            VBox vBox = new VBox();

            TypeReclamationService ps = new TypeReclamationService();
            ArrayList<TypeReclamation> arrayList = ps.getTypeReclamationListWithType(type);
            for (TypeReclamation tr: arrayList) {
                System.out.println(tr.getDescription());
                RadioButton radioButton = new RadioButton(tr.getDescription());
                radioButton.setToggleGroup(radioGroup);
                radioButton.setUserData(tr.getId());
                vBox.getChildren().add(radioButton);
            }

            radioGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                if (radioGroup.getSelectedToggle()!=null){
                    System.out.println(radioGroup.getSelectedToggle().getUserData().toString());
                }
            });

            btnEnvoyer.setOnAction(event1 -> {
                String verif="";
                if (finalHasDone||verif.equalsIgnoreCase("true")){
                    jfxDialog.setContent(createDialogContent("error","Error"));

                }else{


                String value="";
                if (radioGroup.getSelectedToggle()!=null){
                    value= radioGroup.getSelectedToggle().getUserData().toString();
                    System.out.println(value);
                    Reclamation reclamation = new Reclamation();
                    reclamation.setDate(new Date());
                    reclamation.setIduser(iduser);
                    reclamation.setIdobjet(idobjet);

                    reclamation.setIdtr(Integer.parseInt(value));

                    ReclamationService rs = new ReclamationService();
                    rs.addReclamation(reclamation);
                    jfxDialog.setContent(createDialogContent("done","Reclamation Envoyer"));
                    verif ="true";
                    btn.setDisable(true);
                    btnEnvoyer.setDisable(true);
                    btnAnnuler.setText("Fermer");

                }else {
                    jfxDialog.setContent(createDialogContent("warning","Choix invalide"));
                }


                }
                jfxDialog.show(secondaryLayout);



            });

            HBox hBox = new HBox();
            hBox.setPadding(new Insets(20,0,0,0));
            hBox.getChildren().add(btnEnvoyer);
            hBox.getChildren().add(btnAnnuler);
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(50.0);
            vBox.getChildren().add(hBox);
            vBox.setPadding(new Insets(10,50,20,30));
            vBox.setSpacing(10);

            secondaryLayout.getChildren().add(vBox);

            Scene secondScene = new Scene(secondaryLayout);
            LoadStyleSheets loadStyleSheets = new LoadStyleSheets(secondaryLayout);


            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Reclamation");
            newWindow.setScene(secondScene);

            // Specifies the modality for new window.
            newWindow.initModality(Modality.WINDOW_MODAL);

            // Specifies the owner Window (parent) for new window
            newWindow.initOwner(stage);

            // Set position of second window, related to primary window.
            newWindow.setX(stage.getX() + 200);
            newWindow.setY(stage.getY() + 100);

            newWindow.show();

        });

        this.getChildren().add(btn);


    }

private Region createDialogContent(String type,String message){
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

    StackPane root = new StackPane();
    root.setPadding(Insets.EMPTY);

    VBox box = new VBox();
    box.setStyle("-fx-background-radius : 10 0 10 0; -fx-background-color : white;");

    box.getChildren().addAll(header, container);
    root.getChildren().add(box);

        return root;
}


}
