package edu.fundup.model.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TypeReclamation {
    private int id;
    private StringProperty description = new SimpleStringProperty("");
    private StringProperty  objet  = new SimpleStringProperty("");

    public TypeReclamation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getObjet() {
        return objet.get();
    }

    public void setObjet(String objet) {
        this.objet.set(objet);
    }
}
