/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.controller;

/**
 *
 * @author admin
 */

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import edu.fundup.model.entity.Comments;
import edu.fundup.model.entity.Member;
import edu.fundup.model.iservice.IServiceComment;
import edu.fundup.model.service.ServiceCommentsImpl;
import edu.fundup.utils.FuFactory;
import edu.fundup.utils.UserSession;
import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Animeaux;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class CommentsGrid extends GridPane {

	private Label username;
	private Label dateTime;
	private Label comment;
	// private JFXButton like ;
	private HBox line1;
	private HBox line2;
	private HBox minitline;
	private HBox minitline2;
	private JFXButton delet ;
	int i;
	int j;
	private Boolean islike = false;
	private IServiceComment servc = FuFactory.getCommentDriver();

	public CommentsGrid(ArrayList<Comments> commentsList, int anim) {
		UserSession.getInstance().setMember(new Member(555));
		Member member =  UserSession.getInstance().getMember();
		member.setRole("Paperless");
		i = 1;
		j = 2;
		Label labelComment = new Label("Comments:");
		this.add(labelComment, 0, 0);
		labelComment.setStyle("-fx-font-weight: BOLD;");

		for (Comments c : commentsList) {
			if(c.getPostid() == anim) {
			JFXButton like = new JFXButton();

			if (c.isLike()) {
				islike = true;
				like.setId("MY_LIKE_BUTTON");
			} else {
				islike = false;
				like.setId("MY_DISLIKE_BUTTON");
			}
			username = new Label(c.getUsername());
			dateTime = new Label(c.getDateTime());
			comment = new Label(c.getComment());
			delet = new JFXButton("delet");
			line1 = new HBox();
			line2 = new HBox();
			minitline = new HBox();
			minitline2 = new HBox();

			this.setPadding(new Insets(15, 15, 15, 15));
			this.setStyle("-fx-background-color:white");
			username.setStyle("-fx-font-weight: BOLD;");
			like.setOnAction(e -> {
				if (islike) {
					c.setLike(false);
					try {
						servc.update(c);
					} catch (DataBaseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					like.setId("MY_DISLIKE_BUTTON");
					islike = false;
				} else {
					c.setLike(true);
					try {
						servc.update(c);
					} catch (DataBaseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					like.setId("MY_LIKE_BUTTON");
					islike = true;

				}
			});

			if (c.getPostowner() == 555 /*member.getId()**/) {
				minitline2.getChildren().addAll(like, delet);
				minitline.getChildren().addAll(username, dateTime);
				line1.getChildren().addAll(minitline, minitline2);
			} else {
				minitline2.getChildren().addAll(like);
				minitline.getChildren().addAll(username, dateTime);
				line1.getChildren().addAll(minitline, minitline2);
			}
			
			line1.setSpacing(520);
			line2.getChildren().addAll(comment);
			this.setVgap(25);
			this.add(line1, 0, i += 2);
			this.add(line2, 0, j += 2);
		}
	}
	}
}

