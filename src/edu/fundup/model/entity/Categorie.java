/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.model.entity;

/**
 *
 * @author hhamzaoui
 */
public class Categorie implements Comparable<Categorie>{
    
    private int idcat;
    private int idsoucat;
    private String nomcat;
    private String description;

        public Categorie(){
            
        }
        
        

    public Categorie(int idcat, int idsoucat, String nomcat, String description) {
        this.idcat = idcat;
        this.idsoucat = idsoucat;
        this.nomcat = nomcat;
        this.description = description;
    }

    public Categorie(int idsoucat, String nomcat, String description) {
        this.idsoucat = idsoucat;
        this.nomcat = nomcat;
        this.description = description;
    }

    public int getIdcat() {
        return idcat;
    }

    public void setIdcat(int idcat) {
        this.idcat = idcat;
    }

    public int getIdsoucat() {
        return idsoucat;
    }

    public void setIdsoucat(int idsoucat) {
        this.idsoucat = idsoucat;
    }

    public String getNomcat() {
        return nomcat;
    }

    public void setNomcat(String nomcat) {
        this.nomcat = nomcat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Categorie{" + "idcat=" + idcat + ", idsoucat=" + idsoucat + ", nomcat=" + nomcat + ", description=" + description + "}";
    }

    @Override
    public int compareTo(Categorie t) {
        return this.getIdsoucat()-t.getIdsoucat();
    }
    
    
}
