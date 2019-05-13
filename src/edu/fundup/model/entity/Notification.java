package edu.fundup.model.entity;

import java.util.Date;

public class Notification {
    private int id;
    private int id_user;
    private Date creation_date;
    private String object;
    private  int is_read;


    public Notification() {
    }

    public Notification(int id, int id_user, Date creation_date, String object, int is_read) {
        this.id = id;
        this.id_user = id_user;
        this.creation_date = creation_date;
        this.object = object;
        this.is_read = is_read;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public int getIs_read() {
        return is_read;
    }

    public void setIs_read(int is_read) {
        this.is_read = is_read;
    }
}
