/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.model.entity;

/**
 *
 * @author lhannachi
 */
public class PartRatiEvent {
    
    private String nom;
    private String préom;
    private String email;
    private Double rating;

    public PartRatiEvent(String nom, String préom, String email, Double rating) {
        this.nom = nom;
        this.préom = préom;
        this.email = email;
        this.rating = rating;
    }

    public PartRatiEvent(String nom, String préom, String email) {
        this.nom = nom;
        this.préom = préom;
        this.email = email;
    }

    public PartRatiEvent() {
    }

    public String getNom() {
        return nom;
    }

    public String getPréom() {
        return préom;
    }

    public String getEmail() {
        return email;
    }

    public Double getRating() {
        return rating;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPréom(String préom) {
        this.préom = préom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

   
    
    
            
    
}
