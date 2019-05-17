package edu.fundup.controller;

import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import com.jfoenix.controls.JFXRippler.RipplerMask;
import com.jfoenix.controls.JFXRippler.RipplerPos;
import edu.fundup.model.entity.Notification;
import edu.fundup.model.service.NotificationService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PopupDemo extends HBox {

    public PopupDemo(Stage primaryStage) throws Exception {

        JFXBadge jfxBadge = new JFXBadge();
        JFXHamburger show = new JFXHamburger();
        show.setPadding(new Insets(15, 5, 10, 5));
        JFXRippler rippler = new JFXRippler(show, RipplerMask.CIRCLE, RipplerPos.BACK);

        ListView<Label> list = new ListView<>();

        NotificationService notificationService = new NotificationService();
        ArrayList<Notification>  notifList = notificationService.getUnreadNotifications();

        showNotif(notifList,list);




        AnchorPane container = new AnchorPane();
        jfxBadge.setControl(rippler);
        //jfxBadge.getChildren().add(rippler);
        jfxBadge.setText(""+notifList.size());
        jfxBadge.getStyleClass().add("icon");
        jfxBadge.getStyleClass().add("icon-danger");

        container.getChildren().add(jfxBadge);
        StackPane main = new StackPane();
        main.prefHeight(100);
        main.prefWidth(500);
        main.getChildren().add(container);

        LoadStyleSheets loadStyleSheets = new   LoadStyleSheets(main);


        VBox vBox1 = new VBox();
        vBox1.getChildren().add(list);

        Label label11 = new Label("Show All notification");
        vBox1.getChildren().add(label11);
        label11.setOnMouseClicked(event -> showNotif(notificationService.getNotifications(),list));

        JFXPopup popup = new JFXPopup(vBox1);


        rippler.setOnMouseClicked(e -> {
            popup.show(rippler, PopupVPosition.TOP, PopupHPosition.LEFT);
            for(Notification notif : notifList){
                notificationService.markNotificationAsRead(notif);
            }
            jfxBadge.setText("0");
        });

        this.getChildren().add(main);



    }
private static void showNotif(ArrayList<Notification> notifList,ListView<Label> list){
    int max =0;
    if(notifList.size()<=0) {
        Label labelll = new Label("Pas de notification");
        list.getItems().add(labelll);
        max=30;
        list.setMaxHeight(45.0);

    }
    else {

        for (Notification notif : notifList) {

            if (max <notif.getObject().length())
                max = notif.getObject().length();


            if (notifList.size()>=10)
                list.setMaxHeight(250.0);

            Label label = new Label(notif.getObject());

            label.setOnMouseClicked(event -> System.out.println(label));
            list.getItems().add(label);


        }

    }

    list.setPrefWidth(max*10);
    System.out.println(max);
}

}
