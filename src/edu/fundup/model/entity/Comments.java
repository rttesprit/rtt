package edu.fundup.model.entity;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Comments {

	private String username;
	private String dateTime;
	private String comment;
	private boolean like;
	private int postowner;
	private int comid;
	private int postid;
	private int userid;



	public int getUserid() {
		return userid;
	}



	public void setUserid(int userid) {
		this.userid = userid;
	}




	
	
	
	public Comments(String username, String comment, boolean like, int postowner, int postid, int userid) {
		this.username = username;
		this.comment = comment;
		this.like = like;
		this.postowner = postowner;
		this.postid = postid;
		this.userid = userid;
	}



	public int getPostid() {
		return postid;
	}



	public void setPostid(int postid) {
		this.postid = postid;
	}



	public int getPostowner() {
		return postowner;
	}

	public void setPostowner(int postowner) {
		this.postowner = postowner;
	}

	public int getComid() {
		return comid;
	}

	public void setComid(int comid) {
		this.comid = comid;
	}

	public Comments() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isLike() {
		return like;
	}

	public void setLike(boolean like) {
		this.like = like;
	}

}
