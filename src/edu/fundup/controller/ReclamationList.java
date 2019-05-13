package edu.fundup.controller;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class ReclamationList  extends RecursiveTreeObject <ReclamationList> {
    private StringProperty typeReclamation;
    private StringProperty dateReclamation;
    private StringProperty descReclamation;



    public ReclamationList(int typeReclamation, Date dateReclamation, int descReclamation) {
        this.typeReclamation = new SimpleStringProperty(Integer.toString(typeReclamation));
        this.dateReclamation = new SimpleStringProperty(dateReclamation.toString());
        this.descReclamation = new SimpleStringProperty(Integer.toString(descReclamation));

    }

    public StringProperty getTypeReclamation() {
        return typeReclamation;
    }



    public void setTypeReclamation(String typeReclamation) {
        this.typeReclamation.set(typeReclamation);
    }

    public StringProperty getDateReclamation() {
        return dateReclamation;
    }



    public void setDateReclamation(String dateReclamation) {
        this.dateReclamation.set(dateReclamation);
    }

    public String getDescReclamation() {
        return descReclamation.get();
    }

    public StringProperty descReclamationProperty() {
        return descReclamation;
    }

    public void setDescReclamation(String descReclamation) {
        this.descReclamation.set(descReclamation);
    }
}
