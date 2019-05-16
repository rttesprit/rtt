package edu.fundup.controller;

import edu.fundup.utils.Tools;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class InscriptionController extends VBox {

    public InscriptionController() {

        VBox container = new VBox();

        StackPane background = new StackPane();
        background.setMinHeight(500);
        background.setMinWidth(500);
        background.setMaxHeight(500);
        background.setMaxWidth(500);
        background.setAlignment(Pos.CENTER);

        VBox root = new VBox();
        root.setMinHeight(500);
        root.setMinWidth(500);
        root.setMaxHeight(500);
        root.setMaxWidth(500);
        root.setAlignment(Pos.CENTER);

        Tools tool = new Tools();
        Tooltip tp1 = tool.createToolTip("Register as a Paperless Member");
        Tooltip tp2 = tool.createToolTip("Register as a Contributor Member");
        Tooltip tp3 = tool.createToolTip("Register as an Entreprise Member");

        Button registerPaperless = new Button("Become Paperless Member");
        Button registerContributor = new Button("Become Contributor Member");
        Button registerEntreprise = new Button("Become an Entreprise Member");


        registerPaperless.getStyleClass().add("success");
        registerPaperless.setMinHeight(80);
        registerPaperless.setMaxHeight(80);
        registerPaperless.setMinWidth(300);
        registerPaperless.setMaxWidth(300);

        registerPaperless.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("mouse Entered");
            }
        });

        registerPaperless.setTooltip(tp1);

        registerContributor.getStyleClass().add("info");
        registerContributor.setMinHeight(80);
        registerContributor.setMaxHeight(80);
        registerContributor.setMinWidth(300);
        registerContributor.setMaxWidth(300);
        registerContributor.setTooltip(tp2);


        registerEntreprise.getStyleClass().add("warning");
        registerEntreprise.setMinHeight(80);
        registerEntreprise.setMaxHeight(80);
        registerEntreprise.setMinWidth(300);
        registerEntreprise.setMaxWidth(300);
        registerEntreprise.setTooltip(tp3);


        registerPaperless.setOnAction(rgpaction -> {
            RegisterPaperlessMember rgp = new RegisterPaperlessMember();
            container.getChildren().clear();
            container.getChildren().addAll(rgp);
            this.getChildren().remove(background);
        });
        registerContributor.setOnAction(rgcaction -> {
            RegisterContributorController rgc = new RegisterContributorController();
            container.getChildren().clear();
            container.getChildren().add(rgc);
            this.getChildren().remove(background);
        });
        registerEntreprise.setOnAction(rgeaction -> {
            RegisterEntrepriseController rge = new RegisterEntrepriseController();
            container.getChildren().clear();
            container.getChildren().add(rge);
            this.getChildren().remove(background);
        });

        VBox.setMargin(registerPaperless, new Insets(0, 0, 30, 0));
        VBox.setMargin(registerEntreprise, new Insets(30, 0, 0, 0));

        this.setPrefSize(1000,1000);
        this.setAlignment(Pos.CENTER);

        root.getChildren().addAll(registerPaperless, registerContributor, registerEntreprise);
        background.getChildren().add(root);

        background.setStyle("-fx-background-radius: 30px; -fx-background-color: rgba(0,0,0, 0.4);");
        this.setStyle("-fx-background-image: url('/edu/fundup/ressources/images/contributor.jpg'); -fx-background-size: cover;");

        container.setMaxWidth(600);
        container.setMaxHeight(600);

        this.getChildren().addAll(background,container);
    }
}
