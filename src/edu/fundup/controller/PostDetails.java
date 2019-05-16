package edu.fundup.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;

import animatefx.animation.SlideInRight;
import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Animeaux;
import edu.fundup.model.entity.Comments;
import edu.fundup.model.entity.Member;
import edu.fundup.model.entity.Membre;
import edu.fundup.model.iservice.IServiceComment;
import edu.fundup.model.iservice.IServiceanimeaux;
import edu.fundup.model.service.ServiceAnimeauxImpl;
import edu.fundup.model.service.ServiceCalculatorImpl;
import edu.fundup.model.service.ServiceCommentsImpl;
import edu.fundup.utils.FuFactory;
import edu.fundup.utils.UserSession;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.web.WebView;

public class PostDetails extends GridPane{
	private GridPane GRIDPANE_GENERAL;
	private GridPane SUBGRID_GENERAL ;
	private GridPane SUB_GRIDGENERAL_RIGHTSIDE;
	private GridPane SUB_GRIDGENERAL_LEFTSIDE;
	private GridPane SUB_GRIDGENERAL_COMMENTS;
	private GridPane GRID_QR_CODE;
	private GridPane SUB_GRIDGENERAL_BUTTOM;
	private JFXButton BUTTON_CONTRIBUTE;
	private Label LBL_DONNORS;
	private Label LBL_DONNORS_NUMB;
	private Label LBL_CONTRIBUTIONS;
	private JFXSlider SLIDER_GOALS;
	private Label LBL_GOAL;
	private Label LBL_MONEY_GOAL;
	private Label LBL_DAYS_LEFT;
	private Label LBL_NUMBER_LEFT;
	private GridPane GRIDPANE_DONNORS;
	private GridPane GRIDPANE_GOAL;
	private GridPane GRIDPANE_SLIDER;
	private HBox HBOX_SLIDER_GOAL;
	private Label LBL_GOAL_UNDERSLIDER;
 	private Label LBL_DAYS_LEFT_SLIDER;
 	private AnchorPane ANCHOR_VIDEO;
 	private Label LBL_TITLE;
 	private Label LBL_DESC;
 	private JFXButton BUTTON_BACK = new JFXButton();
 	private JFXButton BUTTON_SETTING = new JFXButton();
 	private JFXButton deletbutton = new JFXButton();
 	private JFXButton editButton = new JFXButton();
 	private JFXButton comment = new JFXButton("comment");
 	private JFXTextField fieldComment = new JFXTextField();
 	String commentString = "";
	double Gridwidth = 0;
	ScrollPane scp = new ScrollPane();
    ArrayList<Comments> commentsList;

	

	private Label LBL_HEADER;
	private String nom;
	private String etat;
	private double montant;
	private int iduser;
	private String photo;
	private String video;
	private int idcat;
    private String titre;
    private String location;
    private int goal;
    private String fundtype;
    private String endFundDate;
    private String description;
	private JFXMasonryPane root = new JFXMasonryPane();
    private IServiceComment servc = FuFactory.getCommentDriver();
	private HBox HBOX_NAVIGATION = new HBox();
	private JFXButton BUTTON_MY_LIST = new JFXButton();
	private JFXButton BUTTON_ALL = new JFXButton();
	private JFXButton BUTTON_STAT = new JFXButton();
	private IServiceanimeaux serv = FuFactory.getAnimalDriver();

	public PostDetails(Animeaux anim) {
		
		//STATIC MEMBER
		UserSession.getInstance().setMember(new Member(555));
		Member member =  UserSession.getInstance().getMember();
		member.setRole("Paperless");
		
		JFXNodesList buttonsNode = new JFXNodesList();
        HBOX_NAVIGATION.getChildren().addAll(BUTTON_BACK, BUTTON_MY_LIST, BUTTON_ALL,BUTTON_STAT,editButton,buttonsNode);

		if(anim.getIduser() != member.getId()) {
			editButton.setVisible(false);
		}
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
		});

		val3.setOnAction(e -> {
		});

		

		BUTTON_BACK.setOnAction(e -> {
			Acceuil.catt.getChildren().clear();
			Acceuil.catt.getChildren().add(new AddAnimal());
			new SlideInRight(Acceuil.catt).play();
		});

		BUTTON_ALL.setOnAction(e -> {
			Acceuil.catt.getChildren().clear();
			Acceuil.catt.getChildren().add(new MyAnimalsPosts());
			new SlideInRight(Acceuil.catt).play();		});
		

		

		//*********************** INIT *************************
		GRIDPANE_GENERAL = new GridPane();
		SUBGRID_GENERAL = new GridPane();
		HBox HBOX_HEADER = new HBox();
		GRIDPANE_DONNORS = new GridPane();
		LBL_HEADER = new Label(anim.getTitre());
		SUB_GRIDGENERAL_RIGHTSIDE =new GridPane();
		BUTTON_CONTRIBUTE = new JFXButton("Make Contribution");
		LBL_DONNORS_NUMB = new Label();
		// CLICK TO DONNORS
		LBL_DONNORS = new Label("DONORS");
		LBL_DONNORS.setStyle("");
		LBL_DONNORS_NUMB = new Label("1923");
		LBL_GOAL = new Label("RAISED (DT)");
		LBL_MONEY_GOAL = new Label(String.valueOf(anim.getMontant()));
		SLIDER_GOALS = new JFXSlider(0,anim.getGoal(),anim.getMontant());
		HBOX_SLIDER_GOAL = new HBox();
		LBL_GOAL_UNDERSLIDER = new Label("GOAL:"+ String.valueOf(anim.getGoal()));
		LBL_GOAL_UNDERSLIDER.setStyle("-fx-font-weight: BOLD;");
		LBL_DONNORS_NUMB.setStyle("-fx-font-weight: BOLD;");
		LBL_MONEY_GOAL.setStyle("-fx-font-weight: BOLD;");
		LBL_DAYS_LEFT = new Label("Days Left:"+new ServiceCalculatorImpl().calculDate(anim.getEndFundDate()));
		GRIDPANE_GOAL = new GridPane();
		GRIDPANE_SLIDER = new GridPane();
		ANCHOR_VIDEO = new AnchorPane();
        SUB_GRIDGENERAL_LEFTSIDE = new GridPane();
        SUB_GRIDGENERAL_COMMENTS = new GridPane();
		try {
			try {
				commentsList = servc.getAll();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        SUB_GRIDGENERAL_COMMENTS = new CommentsGrid(commentsList,anim.getIdamin());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		if(member.getId() == anim.getIduser()) {
			BUTTON_CONTRIBUTE.setVisible(false);
		}
		
		
		//********************* INIT RIGHT SIDE GRID COMPONENTS *****************
		HBOX_SLIDER_GOAL.getChildren().addAll(LBL_GOAL_UNDERSLIDER,LBL_DAYS_LEFT);
		HBOX_SLIDER_GOAL.setSpacing(50);
		
		//*********************** RIGHT GRID ****************
		GRIDPANE_DONNORS.add(LBL_DONNORS_NUMB,0,0);
		GRIDPANE_DONNORS.add(LBL_DONNORS,0,1);
		
		GRIDPANE_GOAL.add(LBL_MONEY_GOAL,0,0);
		GRIDPANE_GOAL.add(LBL_GOAL,0,1);
		
		GRIDPANE_SLIDER.add(SLIDER_GOALS,0,0);
		GRIDPANE_SLIDER.add(HBOX_SLIDER_GOAL,0,1);
		

		
		SUB_GRIDGENERAL_RIGHTSIDE.add(BUTTON_CONTRIBUTE,0,0);
		SUB_GRIDGENERAL_RIGHTSIDE.add(GRIDPANE_DONNORS,0,1);
		SUB_GRIDGENERAL_RIGHTSIDE.add(GRIDPANE_GOAL,0,2);
		SUB_GRIDGENERAL_RIGHTSIDE.add(GRIDPANE_SLIDER,0,3);
		
		//*********************** Left SIDE GRID *************
		 WebView root = new WebView();
		 String url = "<iframe width=\"800\" height=\"600\" src=\"http://www.youtube.com/embed/ExFWMn9DFSk\" frameborder=\"0\" allowfullscreen></iframe>";
		    root.getEngine().loadContent(url);
		    ANCHOR_VIDEO.getChildren().add(root);
		    LBL_TITLE = new Label(anim.getTitre());
		    LBL_DESC = new Label(anim.getDescription());
		    
		    SUB_GRIDGENERAL_LEFTSIDE.add(ANCHOR_VIDEO,0,0);
		    SUB_GRIDGENERAL_LEFTSIDE.add(LBL_TITLE,0,1);
		    LBL_DESC.setWrapText(true);
		    LBL_DESC.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 14; -fx-text-fill: darkred;-fx-max-width:800");
		    SUB_GRIDGENERAL_LEFTSIDE.add(LBL_DESC,0,3);
		    
		//*****************************************************

		//********************* GENERAL GRID ***************
		Label label = new Label("TITRE");
		HBOX_HEADER.getChildren().add(label);
		GRIDPANE_GENERAL.add(HBOX_NAVIGATION, 0, 0);
		SUBGRID_GENERAL.add(SUB_GRIDGENERAL_LEFTSIDE, 0, 1);
		SUBGRID_GENERAL.add(SUB_GRIDGENERAL_RIGHTSIDE, 1,1);
		GRIDPANE_GENERAL.add(HBOX_HEADER, 0,1);
//		GRIDPANE_GENERAL.add(SUBGRID_GENERAL, 0,2);
		SUBGRID_GENERAL.getChildren().add(HBOX_HEADER);
		
		//************************* EVENTS *****************************

		BUTTON_BACK.setOnAction(e -> {
			Acceuil.catt.getChildren().clear();
			Acceuil.catt.getChildren().add(new MyAnimalsPosts());
			new SlideInRight(Acceuil.catt).play();
		});	
		
		
		editButton.setOnAction(e -> {
			Acceuil.catt.getChildren().clear();
			Acceuil.catt.getChildren().add(new AnimalPostSettings(anim));
			new SlideInRight(Acceuil.catt).play();
		});
		
		comment.setOnAction(e -> {
			 try {
				 Comments com = new Comments("jubran",  fieldComment.getText(), false, anim.getIduser(), anim.getIdamin(), member.getId()) ;
				try {
					servc.create(com);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				 fieldComment.clear();
					try {
						try {
							commentsList = servc.getAll();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				        SUB_GRIDGENERAL_COMMENTS = new CommentsGrid(commentsList,anim.getIdamin());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		BUTTON_CONTRIBUTE.setOnAction(e -> {
			if(member.getRole().equals("Paperless")) {
				System.out.println("go to payments credentiels");
			}else if (member.getRole().equals("Contributor")) {
				System.out.println("go to payments");
			}
		});

	
		
		
		//************************ ID && STYLE***************************
        GRIDPANE_GENERAL.getStylesheets().add("/edu/fundup/ressources/css/Style.css");
		LBL_HEADER.setId("LBL-HEADER");
		HBOX_HEADER.setId("HBOX-HEADER");
		SUB_GRIDGENERAL_LEFTSIDE.setPadding(new Insets(15,15,15,15));
		SUB_GRIDGENERAL_LEFTSIDE.setStyle("-fx-background-color:white");
		SUB_GRIDGENERAL_RIGHTSIDE.setStyle("-fx-background-color:white");
		SUB_GRIDGENERAL_RIGHTSIDE.setPadding(new Insets(15,15,15,15));
		
		BUTTON_SETTING.setId("MY_SETTINGS_BUTTON");
		BUTTON_BACK.getStyleClass().add("BACK-BUTTON");
		editButton.setId("EDIT_BUTTON");
		SUBGRID_GENERAL.setHgap(400);
		
		Gridwidth = FundUp.GLOBAL_SCENE.getWidth()-400;
		//SUBGRID_GENERAL.setMinWidth(Gridwidth);
 		SUBGRID_GENERAL.getStyleClass().add("SUBGRID_GENERAL");
 		SUB_GRIDGENERAL_RIGHTSIDE.setMargin(BUTTON_CONTRIBUTE, new Insets(0,0,80,0));
		SUB_GRIDGENERAL_RIGHTSIDE.setMargin(GRIDPANE_DONNORS, new Insets(0,0,80,0));
		SUB_GRIDGENERAL_RIGHTSIDE.setMargin(GRIDPANE_GOAL, new Insets(0,0,80,0));		
		SUB_GRIDGENERAL_LEFTSIDE.setMargin(HBOX_HEADER, new Insets(0,0,100,0));
		
		SUB_GRIDGENERAL_COMMENTS.setMinWidth(700);
		SUBGRID_GENERAL.add(SUB_GRIDGENERAL_COMMENTS, 0, 3);
		SUBGRID_GENERAL.add(fieldComment, 0, 4);
		SUBGRID_GENERAL.add(comment, 0, 5);
		SUBGRID_GENERAL.setMargin(comment, new Insets(10,100,0,100));
		SUBGRID_GENERAL.setMargin(SUB_GRIDGENERAL_COMMENTS, new Insets(150,0,0,0));		
		SUBGRID_GENERAL.setMargin(SUB_GRIDGENERAL_COMMENTS, new Insets(150,0,0,0));		

//		SUB_GRIDGENERAL_RIGHTSIDE.setAlignment(Pos.TOP_LEFT);
		GRIDPANE_DONNORS.setAlignment(Pos.TOP_LEFT);
		GRIDPANE_GOAL.setAlignment(Pos.TOP_LEFT);
		GRIDPANE_SLIDER.setAlignment(Pos.TOP_LEFT);
		scp.setContent(SUBGRID_GENERAL);
		scp.setPrefSize(2000, 1300);
		scp.setLayoutX(20);
		scp.setLayoutY(20);
		scp.setPannable(true);
		scp.setVisible(true);
		scp.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		scp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		GRIDPANE_GENERAL.add(scp,0,2);
		this.getChildren().add(GRIDPANE_GENERAL);
 
	}


	

}
