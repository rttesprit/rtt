package edu.fundup.controller;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.fundup.model.entity.Reclamation;
import edu.fundup.model.service.ReclamationService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ReclamationListGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {



         TreeTableView<ReclamationList> reclamationList = new TreeTableView<>();

        TreeTableColumn<ReclamationList,String> typeReclamation = new TreeTableColumn<>("Type Reclamation");
        typeReclamation.setPrefWidth(150);
        typeReclamation.setCellValueFactory(param -> param.getValue().getValue().getTypeReclamation());


        TreeTableColumn<ReclamationList,String> dateReclamation = new TreeTableColumn<>("Date Reclamation");
        dateReclamation.setPrefWidth(150);
        dateReclamation.setCellValueFactory(param -> param.getValue().getValue().getDateReclamation());

        TreeTableColumn<ReclamationList,String> descReclamation = new TreeTableColumn<>("Description Reclamation");
        descReclamation.setPrefWidth(300);
        descReclamation.setCellValueFactory(param -> param.getValue().getValue().getDateReclamation());


        ReclamationService reclamationService = new ReclamationService();
        ArrayList<Reclamation> recList = reclamationService.findReclamationByIdUser(1);

        ObservableList<ReclamationList> reclamationLists = FXCollections.observableArrayList();

        for (Reclamation r:recList) {
            System.out.println(r.getIdobjet());
            reclamationLists.add(new ReclamationList(r.getIdtr(),r.getDate(),r.getIdobjet()));
        }



        final TreeItem<ReclamationList> root = new RecursiveTreeItem<ReclamationList>(reclamationLists, RecursiveTreeObject::getChildren);
        reclamationList.getColumns().setAll(typeReclamation,dateReclamation,descReclamation);

        reclamationList.setRoot(root);
        reclamationList.setShowRoot(false);


        Scene scene = new Scene(reclamationList);

        primaryStage.setTitle("JFX Popup Demo");
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
