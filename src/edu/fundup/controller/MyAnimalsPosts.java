package edu.fundup.controller;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXSlider;

import animatefx.animation.SlideInRight;
import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Animeaux;
import edu.fundup.model.entity.Member;
import edu.fundup.model.iservice.IServiceanimeaux;
import edu.fundup.model.service.ServiceAnimeauxImpl;
import edu.fundup.model.service.ServiceCalculatorImpl;
import edu.fundup.utils.FuFactory;
import edu.fundup.utils.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

public class MyAnimalsPosts extends GridPane implements Alerts {

	public GridPane SUB_GRID = new GridPane();
	public GridPane GENERAL_GRID = new GridPane();
	public VBox VBOX_SUB = new VBox();
	private static final String ITEM = "Item ";
	private int counter = 0;
	private IServiceanimeaux serv = FuFactory.getAnimalDriver();
	private HBox HBOX_SETTINGS = new HBox();
	private HBox HBOX_NAVIGATION = new HBox();
	private GridPane GridPane_HEADER = new GridPane();
	private JFXButton BUTTON_BACK = new JFXButton();
	private JFXButton BUTTON_MY_LIST = new JFXButton();
	private JFXButton BUTTON_ALL = new JFXButton();
	private JFXButton BUTTON_STAT = new JFXButton();
	private HBox HBOX_RIGHT = new HBox();
	private HBox HBOX_BOTTOM = new HBox();
	private GridPane post;
	private ArrayList<Animeaux> deletedAnim = new ArrayList<>();
	ScrollPane scp = new ScrollPane();
	int i = 0;
	int j = 1;
	int v = 0;
	int z = 1;

	public MyAnimalsPosts(/* String title,String desc,String date */) {
		JFXNodesList buttonsNode = new JFXNodesList();
		HBOX_NAVIGATION.getChildren().addAll(BUTTON_BACK, BUTTON_MY_LIST, BUTTON_ALL,BUTTON_STAT,buttonsNode);

		
		buttonsNode.setRotate(-90); // to the right
		JFXButton val1 = new JFXButton();
		JFXButton val2 = new JFXButton();
		JFXButton val3 = new JFXButton();
		JFXButton val4 = new JFXButton();
		JFXButton val5 = new JFXButton();
		val1.setId("MY_SETTINGS_BUTTON");
		val2.setId("MY_LIST_BUTTON");
		val3.setId("MY_LIST2_BUTTON");
		val4.setId("MY_TABLE_BUTTON");
		val4.setId("DELETT_POST_BUTTON");
		BUTTON_BACK.getStyleClass().add("BACK-BUTTON");
		BUTTON_MY_LIST.setId("MY_POSTS_BUTTON");
		BUTTON_ALL.setId("CATEGORIE_BUTTON");
		BUTTON_STAT.setId("MY_ADD_BUTTON");
		// ************* events ////////////////
		val2.setOnAction(e -> {
			ShowList();
		});

		val3.setOnAction(e -> {
			showMyPosts();
		});

		val4.setOnAction(e -> {
			try {
				deletePost();
			} catch (DataBaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		BUTTON_BACK.setOnAction(e -> {
			Acceuil.catt.getChildren().clear();
			Acceuil.catt.getChildren().add(new AddAnimal());
			new SlideInRight(Acceuil.catt).play();
		});

		BUTTON_ALL.setOnAction(e -> {
			showAll();
		});
		BUTTON_MY_LIST.setOnAction(e -> {
			showMyPosts();
		});
		
		BUTTON_STAT.setOnAction(e -> {
			Acceuil.catt.getChildren().clear();
			Acceuil.catt.getChildren().add(new AddAnimal());
			new SlideInRight(Acceuil.catt).play();
		});

		buttonsNode.addAnimatedNode(val1);
		buttonsNode.addAnimatedNode(val2);
		buttonsNode.addAnimatedNode(val3);
		buttonsNode.addAnimatedNode(val4);
		GridPane_HEADER.add(HBOX_NAVIGATION, 0, 0);
		GridPane_HEADER.add(HBOX_SETTINGS, 1, 0);

		GridPane_HEADER.setMargin(HBOX_SETTINGS, new Insets(0, 0, 0, 400));
		SUB_GRID.setPadding(new Insets(30, 30, 30, 30));
		SUB_GRID.setStyle("-fx-min-width: 1000px;-fx-min-height: 100px;");
		SUB_GRID.setHgap(50);
		SUB_GRID.setVgap(50);

		// *************** SHOWING POSTS *******************//
		// ShowListListed(1);
		showAll();

		GENERAL_GRID.add(GridPane_HEADER, 0, 0);
		this.getChildren().addAll(GENERAL_GRID);
	}

	private void deletePost() throws DataBaseException {
		// TODO Auto-generated method stub
		/*
		 * 
		 * ObservableList<Animeaux> dataRemoved = FXCollections.observableArrayList();
		 * ArrayList<Animeaux> data; try { data = serv.getAll();
		 * 
		 * for(Animeaux bean : data) { if(bean.getCheckBox().isSelected()) {
		 * dataRemoved.add(bean); } } for(Animeaux animal : dataRemoved) {
		 * serv.delete(animal);
		 * 
		 * } } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		for (Animeaux e : deletedAnim) {
			serv.delete(e);

		}

	}

	public GridPane getGrid() {
		return GENERAL_GRID;
	}

	@Override
	public void showAlert(AlertType alertType, Window owner, String title, String message) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}

	private void ShowList() {
		int i = 1;
		JFXListView<GridPane> list = new JFXListView<>();
		list.setStyle("-fx-pref-width: 850px;-fx-min-width: 850px;-fx-pref-height: 900px;");
		SUB_GRID.getChildren().clear();
		try {
			ArrayList<Animeaux> animList = serv.getAll();
			for (Animeaux anim : animList) {
				post = new GridPane();
				ImageView img = new ImageView(new Image(anim.getPhoto(), true));
				img.setFitHeight(50);
				img.setFitWidth(50);
				Label name = new Label(anim.getTitre());
				Label titre = new Label(anim.getTitre());
				Label goal = new Label(String.valueOf(anim.getGoal()));
				Label location = new Label(anim.getLocation());
				JFXSlider slider = new JFXSlider(0, anim.getGoal(), anim.getMontant());

				post.add(img, 0, 0);
				post.add(titre, 1, 0);
				post.add(goal, 2, 0);
				post.add(slider, 0, 1);
				post.add(location, 1, 1);
				list.getItems().add(post);
				SUB_GRID.add(post, 1, i++);

			}
			scp.setContent(SUB_GRID);
			scp.setPrefSize(2000, 1300);
			scp.setLayoutX(20);
			scp.setLayoutY(20);
			scp.setPannable(true);
			scp.setVisible(true);
			scp.setHbarPolicy(ScrollBarPolicy.ALWAYS);
			scp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			VBOX_SUB.getChildren().add(scp);
			GENERAL_GRID.add(VBOX_SUB, 0, 1);
			list.setVerticalGap(20.0);
			list.depthProperty().set(++counter % 2);
			list.setExpanded(true);
		} catch (Exception ex) {
			//showAlert(Alert.AlertType.ERROR, Acceuil.catt.getScene().getWindow(), "Error!", ex.toString());
		}
	}

	private void showMyPosts() {
		v = 0;
		z = 1;
		UserSession.getInstance().setMember(new Member(555));
		Member member =  UserSession.getInstance().getMember();
		member.setRole("Paperless");
		try {
			SUB_GRID.getChildren().clear();
			ArrayList<Animeaux> animList = serv.getAll();
			if (!animList.isEmpty()) {

				animList.stream().filter(e -> e.getIduser() == member.getId()).forEach(e -> {
					JFXCheckBox ch = new JFXCheckBox();
					// if((anim.getIduser() == id) && mode == 0) {
					post = new GridPane();
					post.getChildren().clear();
					post.getStyleClass().add("ANIMAL_POSTS");
					post.setPadding(new Insets(5, 5, 5, 5));
					ImageView img = new ImageView(new Image(e.getPhoto(), true));
					img.setFitHeight(200);
					img.setFitWidth(220);
					Label name = new Label(e.getTitre());
					Label titre = new Label(e.getTitre());
					Label goal = new Label(String.valueOf(e.getGoal()));
					Label location = new Label(e.getLocation());
					JFXSlider slider = new JFXSlider(0, e.getGoal(), e.getMontant());
					post.add(img, 1, 1);
					post.add(titre, 1, 2);
					post.add(goal, 1, 3);
					post.add(ch, 2, 2);
					post.add(slider, 1, 4);
					post.add(location, 1, 5);
					post.setAlignment(Pos.CENTER);
					if (v == 5) {
						v = 0;
						z++;
					}
					post.setOnMouseClicked(event -> {
						Acceuil.catt.getChildren().clear();
						Acceuil.catt.getChildren().add(new PostDetails(e));
						new SlideInRight(Acceuil.catt).play();
					});
					ch.setOnAction(event -> {
						this.deletedAnim.add(e);
					});
					SUB_GRID.add(post, v++, z);
				});
			} else {
				ImageView imgEmptyList = new ImageView(new Image("/edu/fundup/ressources/images/noitem.png"));
				imgEmptyList.setFitHeight(400);
				imgEmptyList.setFitWidth(500);
				SUB_GRID.getChildren().add(imgEmptyList);
				SUB_GRID.setMargin(imgEmptyList,
						new Insets(50, FundUp.GLOBAL_SCENE.getWidth() / 2, 0, FundUp.GLOBAL_SCENE.getWidth() / 2));
			}
			scp.setContent(SUB_GRID);
			scp.setPrefSize(2000, 1300);
			scp.setLayoutX(20);
			scp.setLayoutY(20);
			scp.setPannable(true);
			scp.setVisible(true);
			scp.setHbarPolicy(ScrollBarPolicy.ALWAYS);
			scp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			VBOX_SUB.getChildren().add(scp);
			GENERAL_GRID.add(VBOX_SUB, 0, 1);
		} catch (Exception ex) {
			//showAlert(Alert.AlertType.ERROR, Acceuil.catt.getScene().getWindow(), "Error!", ex.toString());
		}
	}

	public void showAll() {
		v = 0;
		z = 1;

		try {
			SUB_GRID.getChildren().clear();
			ArrayList<Animeaux> animList = serv.getAll();
		//	if (!animList.isEmpty() && animList.stream().anyMatch(e -> e.getIduser() == id)) {

				for (Animeaux e : animList) {
					post = new GridPane();
					post.getChildren().clear();
					post.getStyleClass().add("ANIMAL_POSTS");
					post.setPadding(new Insets(5, 5, 5, 5));
					ImageView img = new ImageView(new Image(e.getPhoto(), true));
					img.setFitHeight(200);
					img.setFitWidth(220);
					Label name = new Label(e.getTitre());
					Label titre = new Label(e.getTitre());
					Label goal = new Label(String.valueOf(e.getGoal()));
					Label location = new Label(e.getLocation());
					JFXSlider slider = new JFXSlider(0, e.getGoal(), e.getMontant());
					post.add(img, 1, 1);
					post.add(titre, 1, 2);
					post.add(goal, 1, 3);
					post.add(slider, 1, 4);
					post.add(location, 1, 5);
					post.setAlignment(Pos.CENTER);
					if (v == 5) {
						v = 0;
						z++;
					}
					post.setOnMouseClicked(event -> {
						Acceuil.catt.getChildren().clear();
						Acceuil.catt.getChildren().add(new PostDetails(e));
						new SlideInRight(Acceuil.catt).play();
					});
					SUB_GRID.add(post, v++, z);

				}
			scp.setContent(SUB_GRID);
			scp.setPrefSize(2000, 1300);
			scp.setLayoutX(20);
			scp.setLayoutY(20);
			scp.setPannable(true);
			scp.setVisible(true);
			scp.setHbarPolicy(ScrollBarPolicy.ALWAYS);
			scp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			VBOX_SUB.getChildren().add(scp);
			GENERAL_GRID.add(VBOX_SUB, 0, 1);
		} catch (Exception ex) {
			// showAlert(Alert.AlertType.ERROR, Acceuil.catt.getScene().getWindow(),
			// "Error!", ex.toString());
			ex.getStackTrace();

		}
	}

}
