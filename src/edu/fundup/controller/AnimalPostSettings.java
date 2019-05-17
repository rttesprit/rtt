package edu.fundup.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.spreadsheet.Grid;

import com.itextpdf.text.ListLabel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Animeaux;
import edu.fundup.model.entity.Member;
import edu.fundup.model.entity.PostAnimalSettings;
import edu.fundup.model.iservice.IServiceSettings;
import edu.fundup.utils.FuFactory;
import edu.fundup.utils.UserSession;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AnimalPostSettings extends GridPane {

	private IServiceSettings serv = FuFactory.getSettingDriver();
	private ScrollPane scp = new ScrollPane();
	private Label LBL_NOTIFICATION = new Label("Notification Settings");
	private Label LBL_COMMENTSNOTIF = new Label("Post comments notify");
	private Label LBL_ISSUPPORT = new Label("Post supported notify");
	private Label LBL_ISREACHGOALSUPP = new Label("Post reach support goal notify");
	private Label LBL_ISVISIBLEPOST = new Label("Disable post");
	private Label LBL_SUPPORT_GOAL = new Label("Disable post");
	private GridPane GRID_NOTIF = new GridPane();
	private JFXToggleButton TOGG_COMMENT_NOTIF = new JFXToggleButton();
	private JFXToggleButton TOGG_SUPPORTED = new JFXToggleButton();
	private JFXToggleButton TOGG_ISREACH_SUPP_GOAL = new JFXToggleButton();
	private JFXTextField FIELD_SUPPORT_GOAL = new JFXTextField();
	private JFXToggleButton TOGG_VISIBLE = new JFXToggleButton();

	private GridPane updateAnimPost = new GridPane();
	private Label LBL_updateAnimPost = new Label("Update post");
	private Label LBL_IMAGE = new Label("Image");
	private Label LBL_VIDEO = new Label("Video");
	private JFXButton Label_ImageVideo = new JFXButton();
	private Label LBL_TITLE = new Label("Title");
	private JFXTextField FIELD_Campaign_Title = new JFXTextField();
	private Label LBL_LOCATION = new Label("Location");
	private JFXComboBox<String> COMBO_LOCATION = new JFXComboBox<>();
	private Label LBL_FIELD_GOAL = new Label("Goal");
	private Label LBL_CAMPAIGN_END_DATE = new Label("End date");
	private JFXDatePicker DATEPICKER_CAMPAIGN_DATE = new JFXDatePicker();
	private Label LBL_PET_TYPE = new Label("Pet TYPE");
	private JFXTextField FIELD_PET_TYPE = new JFXTextField();
	private Label LBL_FUNDRAISING_REASON = new Label("Fundarising Reason");
	private JFXComboBox<String> COMBO_FUNDRAISING_REASON = new JFXComboBox<>();
	private ArrayList<String> LIST_FUNDRISING_REASONS = new ArrayList<>();
	private JFXButton BUTTON_SUBMIT = new JFXButton("Save");
	private JFXButton BUTTON_SUBMIT_SETTING = new JFXButton("Save");
	private JFXButton BUTTON_RESETALL = new JFXButton("Reset");
	private ArrayList<String> LIST_ANIMAL_TYPE = new ArrayList<>();
	double width = 0;
	private GridPane updateGrid = new GridPane();
	private GridPane updateLine1 = new GridPane();
	private GridPane updateLine2 = new GridPane();
	private GridPane updateLine3 = new GridPane();
	JFXTextField FIELD_VIDEO = new JFXTextField();
	JFXTextField FIELD_IMAGE = new JFXTextField();
	PostAnimalSettings sett ;
	public AnimalPostSettings(Animeaux anim) {
		UserSession.getInstance().setMember(new Member(555));
		Member member =  UserSession.getInstance().getMember();
		member.setRole("Paperless");
	    sett = new PostAnimalSettings();
		try {
			sett = serv.getByPostId(anim);
		} catch (DataBaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(sett != null) {
		TOGG_COMMENT_NOTIF.setSelected(sett.isCommentnotif());
		TOGG_ISREACH_SUPP_GOAL.setSelected(sett.isReachsupporters());
		TOGG_VISIBLE.setSelected(sett.isVisiblePost());
		}
		
		TOGG_ISREACH_SUPP_GOAL.setOnAction(e -> {
			if (TOGG_ISREACH_SUPP_GOAL.isSelected()) {
				FIELD_SUPPORT_GOAL.setVisible(true);
			} else if(sett == null || !TOGG_ISREACH_SUPP_GOAL.isSelected()) {
				FIELD_SUPPORT_GOAL.setVisible(false);
			}
		});
		BUTTON_SUBMIT_SETTING.setId("SUBMIT-BUTTON");
		GRID_NOTIF.add(LBL_NOTIFICATION, 0, 0);
		GRID_NOTIF.add(LBL_COMMENTSNOTIF, 0, 1);
		GRID_NOTIF.add(TOGG_COMMENT_NOTIF, 1, 1);

		GRID_NOTIF.add(LBL_ISVISIBLEPOST, 0, 2);
		GRID_NOTIF.add(TOGG_VISIBLE, 1, 2);
		FIELD_SUPPORT_GOAL.setPromptText("Supporters number goal");
		FIELD_IMAGE.setLabelFloat(true);
		
		GRID_NOTIF.add(LBL_ISSUPPORT, 0, 3);
		GRID_NOTIF.add(TOGG_SUPPORTED, 1, 3);
		
		GRID_NOTIF.add(LBL_ISREACHGOALSUPP, 0, 4);
		GRID_NOTIF.add(TOGG_ISREACH_SUPP_GOAL, 1, 4);
		GRID_NOTIF.add(FIELD_SUPPORT_GOAL, 0, 5);
		

		GRID_NOTIF.add(BUTTON_SUBMIT_SETTING, 1, 5);
		BUTTON_SUBMIT_SETTING.setOnAction(e -> {
			String goal= FIELD_SUPPORT_GOAL.getText();
			 PostAnimalSettings settings = new PostAnimalSettings();
		
			 if(TOGG_ISREACH_SUPP_GOAL.isSelected()) {
				  settings = new PostAnimalSettings(member.getId(), anim.getIdamin(), TOGG_COMMENT_NOTIF.isSelected(),
						 TOGG_SUPPORTED.isSelected(),TOGG_ISREACH_SUPP_GOAL.isSelected(), Integer.parseInt(goal),TOGG_VISIBLE.isSelected());
			 }else {
				  settings = new PostAnimalSettings(member.getId(), anim.getIdamin(), TOGG_COMMENT_NOTIF.isSelected(), 
						 TOGG_SUPPORTED.isSelected(),TOGG_VISIBLE.isSelected());
			 }
			try {
				sett = serv.getByPostId(anim);
				if(sett.getPostid() == 0) {
				serv.create(settings);
				}else {
					settings.setSettid(sett.getSettid());
				serv.update(settings);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		width = (FundUp.GLOBAL_SCENE.getWidth() - 350);
		GRID_NOTIF.setMinWidth(width);
		GRID_NOTIF.setStyle("-fx-background-color:white");
		GRID_NOTIF.setPadding(new Insets(40, 15, 40, 15));
		GRID_NOTIF.setHgap(width / 1.6);
		BUTTON_SUBMIT.setId("SUBMIT-BUTTON");
		BUTTON_RESETALL.setId("BUTTON-RESET");
		// this.setMargin(GRID_NOTIF, new Insets(topRightBottomLeft));
		this.setPadding(new Insets(50, 20, 50, 20));
		FIELD_Campaign_Title.setText(anim.getTitre());
		COMBO_LOCATION.getItems().add(anim.getLocation());
		COMBO_FUNDRAISING_REASON.getItems().add(anim.getFundtype());
		// COMBO_FUNDRAISING_REASON.getSelectionModel().select(anim.getFundtype());
		FIELD_PET_TYPE.setText(anim.getNom());
		COMBO_FUNDRAISING_REASON.getSelectionModel().select(anim.getFundtype());
		COMBO_LOCATION.getSelectionModel().select(anim.getLocation());
		LBL_updateAnimPost.setStyle("-fx-font-weight: BOLD;");
		LBL_NOTIFICATION.setStyle("-fx-font-weight: BOLD;");
		updateLine1.add(LBL_TITLE, 0, 0);
		updateLine1.add(FIELD_Campaign_Title, 0, 1);	
		updateLine1.add(LBL_FUNDRAISING_REASON, 1, 0);
		updateLine1.add(COMBO_FUNDRAISING_REASON, 1, 1);
		updateLine1.add(LBL_CAMPAIGN_END_DATE, 2, 0);
		updateLine1.add(DATEPICKER_CAMPAIGN_DATE, 2, 1);		
		updateLine1.add(LBL_LOCATION, 3, 0);
		updateLine1.add(COMBO_LOCATION, 3, 1);
		updateLine1.add(LBL_PET_TYPE, 4, 0);
		updateLine1.add(FIELD_PET_TYPE, 4, 1);
		updateLine2.add(LBL_IMAGE, 0, 2);
		updateLine2.add(FIELD_IMAGE, 0, 3);
		updateLine2.add(LBL_VIDEO, 1, 2);
		updateLine2.add(FIELD_VIDEO, 1, 3);
		updateLine2.add(BUTTON_RESETALL, 2, 3);
		updateLine2.add(BUTTON_SUBMIT, 3, 3);
		
//		updateLine2.setMargin(LBL_VIDEO, new Insets(40,0,0,0));
//		updateLine2.setMargin(LBL_IMAGE, new Insets(0,0,0,40));
//		updateLine2.setMargin(FIELD_IMAGE, new Insets(0,0,0,40));
		//updateLine2.setMargin(FIELD_VIDEO, new Insets(10,0,0,0));

		updateAnimPost.add(updateLine1, 0, 1);
		updateAnimPost.add(updateLine2, 0, 2);
		updateAnimPost.add(LBL_updateAnimPost, 0, 0);
		
		updateLine1.setHgap(200);
		updateLine2.setHgap(150);
		this.add(this.GRID_NOTIF, 0, 0);
		this.add(this.updateAnimPost, 0, 1);
		updateAnimPost.setVgap(100);
		this.setVgap(100);
		updateAnimPost.setHgap(550);

		updateAnimPost.setMinWidth(width);
		updateAnimPost.setStyle("-fx-background-color:white");
		updateAnimPost.setPadding(new Insets(40, 15, 40, 15));
	}

	public GridPane getGRID_NOTIF() {
		return GRID_NOTIF;
	}

}
