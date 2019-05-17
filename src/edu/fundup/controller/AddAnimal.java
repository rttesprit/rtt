package edu.fundup.controller;

import javafx.scene.layout.GridPane;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.base.IFXValidatableControl;
import com.jfoenix.validation.RequiredFieldValidator;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeInDown;
import animatefx.animation.Flash;
import animatefx.animation.GlowText;
import animatefx.animation.RubberBand;
import animatefx.animation.Shake;
import animatefx.animation.SlideInDown;
import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideInRight;
import animatefx.animation.SlideInUp;
import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Animeaux;
import edu.fundup.model.service.ServiceAnimeauxImpl;
import edu.fundup.model.service.TwilioService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import java.io.IOException;
import java.net.ProtocolException;

public class AddAnimal extends GridPane implements Alerts {

	// -------------- PROPS------------------//
	private GridPane generalgrid;
	private GridPane sidepane;
	private VBox labelsbox;
	private JFXButton Label_Verify;
	private JFXButton Label_TellYourStory;
	private JFXButton Label_ImageVideo;
	private JFXButton Label_InviteContact;
	private JFXButton Label_Share_Onfacebook;
	private JFXButton Setup_Payement;
	private HBox HBOX_HEADER;
	private GridPane gridPane1;
	private GridPane gridPane2;
	private Label LBL_HEADER;
	private Label LBL_SUB_HEADER;
	private GridPane GRID_GENRAL;
	private Label LBL_TITLE;
	private JFXTextField FIELD_Campaign_Title;
	private Label LBL_LOCATION;
	private JFXComboBox<String> COMBO_LOCATION;
	private ArrayList<String> LIST_LOCATION;
	private Label LBL_FIELD_GOAL;
	private JFXTextField FIELD_GOAL;
	private Label LBL_CAMPAIGN_END_DATE;
	private JFXDatePicker DATEPICKER_CAMPAIGN_DATE;
	private Label LBL_PET_TYPE;
	private JFXTextField FIELD_PET_TYPE;
	private Label LBL_FUNDRAISING_REASON;
	private JFXComboBox<String> COMBO_FUNDRAISING_REASON;
	private ArrayList<String> LIST_FUNDRISING_REASONS;
	private JFXButton BUTTON_SUBMIT;
	private JFXButton BUTTON_RESETALL;
	private Label LBL_ANIMAL_TYPE;
	private ArrayList<String> LIST_ANIMAL_TYPE;
	ScrollPane scp = new ScrollPane();

	public AddAnimal() {
		this.GRID_GENRAL = new GridPane();
		this.HBOX_HEADER = new HBox();
		this.gridPane1 = new GridPane();
		this.gridPane2 = new GridPane();
		this.BUTTON_SUBMIT = new JFXButton("NEXT");
		this.BUTTON_RESETALL = new JFXButton("RESET");
		this.LBL_HEADER = new Label("Tell Your Story");
		this.LBL_SUB_HEADER = new Label("Edit Your Fundraising Page");
		this.LBL_TITLE = new Label("Campaign Title ");
		this.FIELD_Campaign_Title = new JFXTextField();
		this.LBL_LOCATION = new Label("Where is your campaign located?");
		this.COMBO_LOCATION = new JFXComboBox<>();
		this.LBL_FIELD_GOAL = new Label("GOAL");
		this.FIELD_GOAL = new JFXTextField();
		this.LBL_CAMPAIGN_END_DATE = new Label("Campaign End Date");
		this.DATEPICKER_CAMPAIGN_DATE = new JFXDatePicker();
		this.LBL_FUNDRAISING_REASON = new Label("What are you fundraising for?");
		this.COMBO_FUNDRAISING_REASON = new JFXComboBox<>();
		this.LBL_ANIMAL_TYPE = new Label("Animal type");
		this.LIST_FUNDRISING_REASONS = new ArrayList<>();
		this.LIST_FUNDRISING_REASONS.add("ANIMAL SHELTERS");
		this.LIST_FUNDRISING_REASONS.add("ANIMAL SURGERY");
		this.LIST_FUNDRISING_REASONS.add("RESCUE GROUPS");
		this.LIST_FUNDRISING_REASONS.add("SERVICE ANIMALS");
		this.LIST_FUNDRISING_REASONS.add("VET BILLS");
		this.LIST_FUNDRISING_REASONS.add("ADOPTION FEES");
		this.LIST_LOCATION = new ArrayList<>();
		this.LIST_LOCATION.add("Gouvernorat de l'Ariana");
		this.LIST_LOCATION.add("Gouvernorat de B�ja");
		this.LIST_LOCATION.add("Gouvernorat de Bizerte");
		this.LIST_LOCATION.add("Gouvernorat de Gab�s");
		this.LIST_LOCATION.add("Gouvernorat de Gafsa");
		this.LIST_LOCATION.add("Gouvernorat de Jendouba");
		this.LIST_LOCATION.add("Gouvernorat de Kairouan");
		this.LIST_LOCATION.add("Gouvernorat de Kasserine");
		this.LIST_LOCATION.add("Gouvernorat de K�bili");
		this.LIST_LOCATION.add("Gouvernorat du Kef");
		this.LIST_LOCATION.add("Gouvernorat de Mahdia");
		this.LIST_LOCATION.add("Gouvernorat de la Manouba");
		this.LIST_LOCATION.add("Gouvernorat de M�denine");
		this.LIST_LOCATION.add("Gouvernorat de Monastir");
		this.LIST_LOCATION.add("Gouvernorat de Nabeul");
		this.LIST_LOCATION.add("Gouvernorat de Sfax");
		this.LIST_LOCATION.add("Gouvernorat de Sidi Bouzid");
		this.LIST_LOCATION.add("Gouvernorat de Sousse");
		this.LIST_LOCATION.add("Gouvernorat de Tataouine");
		this.LIST_LOCATION.add("Gouvernorat de Tozeur");
		this.LIST_LOCATION.add("Gouvernorat de Tozeur");
		this.LIST_LOCATION.add("Gouvernorat de Tunis");
		this.LIST_LOCATION.add(" Gouvernorat de Zaghouan");

		for (String item : LIST_FUNDRISING_REASONS) {
			this.COMBO_FUNDRAISING_REASON.getItems().add(item);
		}
		for (String item : LIST_LOCATION) {
			this.COMBO_LOCATION.getItems().add(item);
		}

		// ************************** ID *********************************************//
		// //
		// *************************************************************************************//
		this.HBOX_HEADER.setId("HBOX-HEADER");
		this.LBL_HEADER.setId("LBL-HEADER");
		this.LBL_SUB_HEADER.setId("LBL_SUB_HEADER");
		this.LBL_TITLE.getStyleClass().add("TITLE-LABELS");
		this.FIELD_Campaign_Title.getStylesheets().add("ALLGRID-FIELD");
		this.LBL_LOCATION.getStyleClass().add("TITLE-LABELS");
		this.COMBO_LOCATION.getStyleClass().add("TWO-ROW-FIELD");
		this.LBL_FIELD_GOAL.getStyleClass().add("TITLE-LABELS");
		this.FIELD_GOAL.getStyleClass().add("TWO-ROW-FIELD");
		this.LBL_CAMPAIGN_END_DATE.getStyleClass().add("TITLE-LABELS");
		this.DATEPICKER_CAMPAIGN_DATE.getStyleClass().add("TWO-ROW-FIELD");
		this.LBL_FUNDRAISING_REASON.getStyleClass().add("TITLE-LABELS");
		this.COMBO_FUNDRAISING_REASON.getStyleClass().add("TWO-ROW-FIELD");
		this.BUTTON_SUBMIT.setId("BUTTON-NEXT");
		this.BUTTON_RESETALL.setId("BUTTON-RESET");
		this.LBL_ANIMAL_TYPE.getStyleClass().add("TITLE-LABELS");

		// ************************** EVENTS
		// *********************************************//
		// //
		// *************************************************************************************//

		// ------------------- FIELD CHECK ------------------------------

		RequiredFieldValidator validators = new RequiredFieldValidator();
		FIELD_Campaign_Title.setValidators(validators);
		FIELD_GOAL.setValidators(validators);
		FIELD_Campaign_Title.setValidators(validators);
		//COMBO_FUNDRAISING_REASON.setValidators(validators);
//		COMBO_LOCATION.setValidators(validators);
		ArrayList<Control> tobeValidated = new ArrayList<>();
		tobeValidated.add(FIELD_Campaign_Title);
		tobeValidated.add(FIELD_GOAL);
		tobeValidated.add(FIELD_Campaign_Title);
		tobeValidated.add(COMBO_FUNDRAISING_REASON);
		tobeValidated.add(COMBO_LOCATION);
		validators.setMessage("No input given ");

		/*for (Control c : tobeValidated) {
			c.focusedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					// TODO Auto-generated method stub
					if (!newValue) {

						((IFXValidatableControl) c).validate();
						if (!((IFXValidatableControl) c).validate()) {
							BUTTON_SUBMIT.setDisable(true);
							new Shake(c).play();
						}
					}
				}
			});
		}
*/
		BUTTON_SUBMIT.setOnAction(e -> {
			new GlowText(LBL_FIELD_GOAL, Color.ORANGE, Color.ORANGERED).setCycleCount(3).setSpeed(0.5)
					.setResetOnFinished(true).play();
			if (COMBO_FUNDRAISING_REASON.getSelectionModel().getSelectedIndex() == 0) {
				this.GRID_GENRAL.getChildren().clear();
				this.getChildren().add(createSpecificationForm(""));
				new SlideInRight(this).play();
			}
		});

		// ************************** STYLE
		// *******************************************//
		// //
		// *************************************************************************************//

		this.FIELD_GOAL.setPromptText("GOAL (DT)");
		this.FIELD_Campaign_Title.setPromptText("Campaign Title");
		this.GRID_GENRAL.setPadding(new Insets(30, 30, 30, 30));
		this.GRID_GENRAL.setVgap(50);
		this.gridPane1.add(LBL_LOCATION, 0, 0);
		this.gridPane1.add(COMBO_LOCATION, 0, 1);
		this.gridPane1.add(LBL_FIELD_GOAL, 1, 0);
		this.gridPane1.add(FIELD_GOAL, 1, 1);
		this.gridPane1.setHgap(250);
		this.gridPane1.setVgap(30);
		this.gridPane2.add(LBL_FUNDRAISING_REASON, 0, 0);
		this.gridPane2.add(COMBO_FUNDRAISING_REASON, 0, 1);
		this.gridPane2.add(LBL_CAMPAIGN_END_DATE, 1, 0);
		this.gridPane2.add(DATEPICKER_CAMPAIGN_DATE, 1, 1);
		this.gridPane2.add(LBL_ANIMAL_TYPE, 0, 2);
		this.gridPane2.add(BUTTON_SUBMIT, 1, 3);
		this.gridPane2.setHgap(250);
		this.gridPane2.setVgap(30);
		this.HBOX_HEADER.getChildren().add(LBL_HEADER);
		this.GRID_GENRAL.add(HBOX_HEADER, 0, 0);
		this.GRID_GENRAL.add(LBL_SUB_HEADER, 0, 1);
		this.GRID_GENRAL.add(LBL_TITLE, 0, 2);
		this.GRID_GENRAL.add(FIELD_Campaign_Title, 0, 3);
		this.GRID_GENRAL.add(gridPane1, 0, 4);
		this.GRID_GENRAL.add(gridPane2, 0, 5);
		this.GRID_GENRAL.setMargin(LBL_TITLE, new Insets(0,0,0,300));
		this.GRID_GENRAL.setMargin(FIELD_Campaign_Title, new Insets(0,0,0,300));
		this.GRID_GENRAL.setMargin(gridPane1, new Insets(0,0,0,300));
		this.GRID_GENRAL.setMargin(gridPane2, new Insets(0,0,0,300));

		// this.GRID_GENRAL.setMargin(BUTTON_VERIF_CAMPAIGN, new Insets(250,
		// 50,60,750));

		
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(GRID_GENRAL);

		// this.GRID_GENRAL.setMargin(FIELD_VERIF_CODE, new Insets(100, 170,60,300));

	}

	//////////////////////////////////////

	public GridPane createSpecificationForm(String lblText) {
		GridPane GEN_GRID =  new AddAnimalSpecification(FIELD_GOAL.getText(),
				 COMBO_FUNDRAISING_REASON.getSelectionModel().getSelectedItem()
				, COMBO_LOCATION.getSelectionModel().getSelectedItem(), DATEPICKER_CAMPAIGN_DATE.getValue().toString()).getGEN_GRID();	
		return GEN_GRID;
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
	
	
}
