package edu.fundup.model.entity;

import java.util.Date;

public class  Reclamation {

    private int id;
    private int iduser;
    private int idobjet;
    private int idtr;
    private String etat;
    private Date date;

    public Reclamation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdobjet() {
        return idobjet;
    }

    public void setIdobjet(int idobjet) {
        this.idobjet = idobjet;
    }

    public int getIdtr() {
        return idtr;
    }

    public void setIdtr(int idtr) {
        this.idtr = idtr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setEtat(){
        this.etat = etat;
    }

    public String getEtat(){
        return etat;
    }
    @Override
    public String toString() {
        return "TO DOOOO";
    }
}