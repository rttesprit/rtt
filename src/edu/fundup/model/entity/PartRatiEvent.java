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
    private String prénom;
    private String email;
    private Double rating;

    public PartRatiEvent(String nom, String prénom, String email, Double rating) {
        this.nom = nom;
        this.prénom = prénom;
        this.email = email;
        this.rating = rating;
    }

    public PartRatiEvent(String nom, String prénom, String email) {
        this.nom = nom;
        this.prénom = prénom;
        this.email = email;
    }

    public PartRatiEvent() {
    }

    public String getNom() {
        return nom;
    }

    public String getPrénom() {
        return prénom;
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

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

   
    
    
            
    
}
