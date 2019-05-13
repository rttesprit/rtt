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
public class RatingEvents {
    
    private int id_event;
    private int id_user;
    private Double value;

    public RatingEvents(int id_event, int id_user, Double value) {
        this.id_event = id_event;
        this.id_user = id_user;
        this.value = value;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
    
}
