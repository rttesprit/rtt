/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.model.entity;

import java.util.Date;

/**
 *
 * @author lhannachi
 */
public class Events {
    private int id_event;
    private int id_user;
    private int id_categorie;
    private String title;
    private Date event_date;
    private String description;
    private String location;
    private String file_url;
    private String categorie;
    private int participant;
    private float montant;

    

    public Events() {
    }

    public Events(int id_event, int id_user, int id_categorie, String title, Date event_date, String description, String location, String file_url, String categorie, int participant, float montant) {
        this.id_event = id_event;
        this.id_user = id_user;
        this.id_categorie = id_categorie;
        this.title = title;
        this.event_date = event_date;
        this.description = description;
        this.location = location;
        this.file_url = file_url;
        this.categorie = categorie;
        this.participant = participant;
        this.montant = montant;
    }

    public Events(int id_user, int id_categorie, String title, Date event_date, String description, String location, String file_url, String categorie, int participant, float montant) {
        this.id_user = id_user;
        this.id_categorie = id_categorie;
        this.title = title;
        this.event_date = event_date;
        this.description = description;
        this.location = location;
        this.file_url = file_url;
        this.categorie = categorie;
        this.participant = participant;
        this.montant = montant;
    }

    

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getParticipant() {
        return participant;
    }

    public void setParticipant(int participant) {
        this.participant = participant;
    }
    
    
    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }
    
    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    
    
}
