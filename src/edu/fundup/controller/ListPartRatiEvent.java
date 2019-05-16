/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.controller;

import com.jfoenix.controls.JFXButton;
import static edu.fundup.controller.Acceuil.contenu;
import edu.fundup.model.entity.JoinEvents;
import edu.fundup.model.entity.Member;
import edu.fundup.model.entity.PartRatiEvent;
import edu.fundup.model.iservice.IServiceEvents;
import edu.fundup.model.service.MemberService;
import edu.fundup.model.service.ServiceEvents;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author lhannachi
 */
public class ListPartRatiEvent extends HBox{
    
    private TableView table;
    private JFXButton BTN_TERMINER;

    public ListPartRatiEvent(int id)
    {
        BTN_TERMINER = new JFXButton("Terminer");
        BTN_TERMINER.getStyleClass() .add("primary");
        BTN_TERMINER.setPrefWidth(400);
        BTN_TERMINER.setPrefHeight(150);
        
        IServiceEvents se = new ServiceEvents();
        ArrayList<JoinEvents> joinEvents = se.getAllJoiners(id);
         PartRatiEvent a = new PartRatiEvent();
         MemberService m = new MemberService();      
        
        table = new TableView();
        table.setEditable(false);
        table.setPrefSize(500, 800);
        
        
        TableColumn t1 = new TableColumn("Nom ");
        TableColumn t2 = new TableColumn("Prénom ");
        TableColumn t3 = new TableColumn("Email ");
        TableColumn t4 = new TableColumn("Rating ");
        t1.setCellValueFactory(
                            new PropertyValueFactory<PartRatiEvent,String>("nom")
        );
        t2.setCellValueFactory(
                            new PropertyValueFactory<PartRatiEvent,String>("prénom")
        );
        t3.setCellValueFactory(
                            new PropertyValueFactory<PartRatiEvent,String>("email")
        );
        t4.setCellValueFactory(
                            new PropertyValueFactory<PartRatiEvent,Double>("rating")
        );
        final ObservableList<PartRatiEvent> data = FXCollections.observableArrayList();
        double rating ;
        
        for (JoinEvents j : joinEvents){
            rating =se.getRating(id, j.getId_user());
            Member M = m.getUserById(j.getId_user());
            a.setNom(M.getfirst_name());
            a.setPréom(M.getlast_name());
            a.setEmail(M.getMail());
            a.setRating(rating);
            data.add(a);
        }
        
        table.setItems(data);
        table.getColumns().addAll(t1, t2, t3, t4);
        
        BTN_TERMINER.setOnMouseClicked((event) -> {
           contenu.getChildren().clear();
           HBox hb = new Evente(id);
           contenu.getChildren().add(hb);
        });
        VBox v = new VBox();
        v.setSpacing(25);
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(table, BTN_TERMINER);
        this.getChildren().addAll(v);
    }
    
}
