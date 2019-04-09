/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.controller;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author hhamzaoui
 */
public class HomeGui extends VBox{
    
      public HomeGui() {

        Label label1 = new Label();

        //-------------Styling-------------------
        this.getStylesheets().add("/ressources/css/theme.css");
        label1.getStyleClass().add("primary");

        //-------------logic--------------------

    }

    
}
