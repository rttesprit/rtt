/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.model.entity;

/**
 *
 * @author Steve
 */
public class Post {
    
    private int idpost;
    private int idcat;
    private String titre;
    private String description;
    private String image;
    private String lieu;
    private int jours;
    private double montant;
    private int idUser;

    
    
    public Post(int idpost, int idcat, String titre, String description, String image, String lieu, int jours, double montant, int idUser) {
        this.idpost = idpost;
        this.idcat = idcat;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.lieu = lieu;
        this.jours = jours;
        this.montant = montant;
        this.idUser = idUser;
    }

    public Post() {
    }

    public Post(int idcat, String titre, String description, String image, String lieu, int jours, double montant, int idUser) {
        this.idcat = idcat;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.lieu = lieu;
        this.jours = jours;
        this.montant = montant;
        this.idUser = idUser;
    }

    public int getIdpost() {
        return idpost;
    }

    public void setIdpost(int idpost) {
        this.idpost = idpost;
    }

    public int getIdcat() {
        return idcat;
    }

    public void setIdcat(int idcat) {
        this.idcat = idcat;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getJours() {
        return jours;
    }

    public void setJours(int jours) {
        this.jours = jours;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Post{" + "idpost=" + idpost + ", idcat=" + idcat + ", titre=" + titre + ", description=" + description + ", image=" + image + ", lieu=" + lieu + ", jours=" + jours + ", montant=" + montant + ", idUser=" + idUser + '}';
    }
    
    
    
}
