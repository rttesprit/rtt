package edu.fundup.controller;

import javafx.scene.Node;
import javafx.scene.Parent;

public   class LoadStyleSheets {
    public  LoadStyleSheets(Parent node){
        node.getStylesheets().add("edu/fundup/ressources/css/master.css");
        node.getStylesheets().add("edu/fundup/ressources/css/light.css");
        node.getStylesheets().add("edu/fundup/ressources/css/skeleton.css");
        node.getStylesheets().add("edu/fundup/ressources/css/bootstrap.css");
        node.getStylesheets().add("edu/fundup/ressources/css/material-color.css");
        node.getStylesheets().add("edu/fundup/ressources/css/custom.css");
        //node.getStylesheets().add("edu/fundup/ressources/css/fonts.css");
        //node.getStylesheets().add("edu/fundup/ressources/css/helpers.css");
        //node.getStylesheets().add("edu/fundup/ressources/css/material.css");
        //node.getStylesheets().add("edu/fundup/ressources/css/poplight.css");
       node.getStylesheets().add("edu/fundup/ressources/css/shape.css");
        //node.getStylesheets().add("edu/fundup/ressources/css/side.css");
       node.getStylesheets().add("edu/fundup/ressources/css/simple-green.css");









    }
}
