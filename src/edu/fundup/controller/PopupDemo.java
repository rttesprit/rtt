package edu.fundup.controller;

import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import com.jfoenix.controls.JFXRippler.RipplerMask;
import com.jfoenix.controls.JFXRippler.RipplerPos;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PopupDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        JFXHamburger show = new JFXHamburger();
        show.setPadding(new Insets(10, 5, 10, 5));
        JFXRippler rippler = new JFXRippler(show, RipplerMask.CIRCLE, RipplerPos.BACK);

        JFXListView<JFXButton> list = new JFXListView<>();
        VBox vBox = new VBox();

        for (int i = 1; i < 5; i++) {

            JFXButton label = new JFXButton("*NOT READ* Notification Notification "+i);
            label.setOnMouseClicked(event -> System.out.println(label));
            vBox.getChildren().add(label);


        }


        AnchorPane container = new AnchorPane();
        container.getChildren().add(rippler);
        AnchorPane.setLeftAnchor(rippler, 200.0);
        AnchorPane.setTopAnchor(rippler, 210.0);

        StackPane main = new StackPane();
        main.getChildren().add(container);

        JFXPopup popup = new JFXPopup(vBox);
        rippler.setOnMouseClicked(e -> popup.show(rippler, PopupVPosition.TOP, PopupHPosition.LEFT));

        final Scene scene = new Scene(main, 800, 800);

        primaryStage.setTitle("JFX Popup Demo");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
