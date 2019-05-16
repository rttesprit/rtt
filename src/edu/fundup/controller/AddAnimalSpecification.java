package edu.fundup.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.base.IFXValidatableControl;
import com.jfoenix.validation.RequiredFieldValidator;

import animatefx.animation.Shake;
import animatefx.animation.SlideInRight;
import edu.fundup.exception.DataBaseException;
import edu.fundup.model.entity.Animeaux;
import edu.fundup.model.iservice.IServiceanimeaux;
import edu.fundup.model.service.ServiceAnimeauxImpl;
import edu.fundup.model.service.TwilioService;
import edu.fundup.utils.FuFactory;
import edu.fundup.utils.UserSession;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import rest.file.uploader.tn.FileUploader;

public class AddAnimalSpecification extends GridPane {
	private ArrayList<String> LIST_ANIMAL_TYPE;
	private JFXTextField FIELD_Campaign_Title;
	private JFXTextField FIELD_GOAL;
	private JFXTextField FIELD_PET_TYPE;
	private JFXComboBox<String> COMBO_FUNDRAISING_REASON;
	private JFXComboBox<String> COMBO_LOCATION;
	private JFXDatePicker DATEPICKER_CAMPAIGN_DATE;
	private Label LBL_ANIMAL_TYPE;
	public GridPane GEN_GRID = new GridPane();
	private IServiceanimeaux serv = FuFactory.getAnimalDriver();

    int userid ;
	public AddAnimalSpecification(String goal,String fundreason,String theLocation,String theDate) {
		JFXComboBox<String> COMBO_ANIMAL_TYPE = new JFXComboBox<>();
		GridPane SUB_GRID1 = new GridPane();
		GridPane SUB_GRID2 = new GridPane();
		HBox HBOX_HEADER = new HBox();
		HBox HBOX_BUTTONS = new HBox();
		HBox HBOX_FOOTER = new HBox();
		HBox HBOX_FOOTER_BUTTONS = new HBox();
		JFXComboBox<String> COMBO_TYPE = new JFXComboBox<>();
		JFXTextField FIELD_VIDEO = new JFXTextField();
		JFXTextField FIELD_IMAGE = new JFXTextField();
		JFXTextArea AFIELD_DESC = new JFXTextArea();
		JFXButton BUTTON_SUBMIT = new JFXButton("Submit");
		JFXButton BUTTON_RESET = new JFXButton("Reset");
		JFXButton BUTTON_BACK = new JFXButton();
		JFXButton BUTTON_SHOW_CAMPAIGN = new JFXButton();
		JFXButton BUTTON_CATEGORIE = new JFXButton();
		JFXToggleButton TOGGLE_SMS = new JFXToggleButton();
		TOGGLE_SMS.setText("SMS");
		Label LBL_VIDEO = new Label("Video Link");
		Label LBL_IMG = new Label("Image Link");
		Label LBL_DSC = new Label("Description");
		Label LBL_COMBO = new Label(" type");
		Label LBL_HEADER = new Label("Specification");
		FIELD_Campaign_Title = new JFXTextField();
		ArrayList<Control> ControlsList = new ArrayList<>();
		ArrayList<Control> fieldsList = new ArrayList<>();
		LIST_ANIMAL_TYPE = new ArrayList<>();
		ControlsList.add(FIELD_VIDEO);
		ControlsList.add(FIELD_IMAGE);
		ControlsList.add(AFIELD_DESC);
		ControlsList.add(COMBO_TYPE);
		ControlsList.add(COMBO_ANIMAL_TYPE);
		FIELD_PET_TYPE = new JFXTextField();
		LBL_ANIMAL_TYPE = new Label("Type");
		FIELD_GOAL = new JFXTextField();
		LIST_ANIMAL_TYPE = new ArrayList<>();
		LIST_ANIMAL_TYPE.add("dog");
		LIST_ANIMAL_TYPE.add("cat");
		LIST_ANIMAL_TYPE.add("cow");
		LIST_ANIMAL_TYPE.add("sheep");
		LIST_ANIMAL_TYPE.add("rabbit");
		LIST_ANIMAL_TYPE.add("duck");
		LIST_ANIMAL_TYPE.add("hen");
		LIST_ANIMAL_TYPE.add("horse");
		LIST_ANIMAL_TYPE.add("donkey");
		LIST_ANIMAL_TYPE.add("goat");
		LIST_ANIMAL_TYPE.add("guinea pig");
		LIST_ANIMAL_TYPE.add("chameleon");
		LIST_ANIMAL_TYPE.add("snake");
		LIST_ANIMAL_TYPE.add("turtle");
		LIST_ANIMAL_TYPE.add("fish");
		LIST_ANIMAL_TYPE.add("camel");
		LIST_ANIMAL_TYPE.add("fish");
		fieldsList.add(FIELD_IMAGE);
		fieldsList.add(FIELD_VIDEO);
		fieldsList.add(FIELD_Campaign_Title);
		fieldsList.add(FIELD_GOAL);
		fieldsList.add(FIELD_PET_TYPE);
		fieldsList.add(COMBO_ANIMAL_TYPE);
		fieldsList.add(COMBO_TYPE);
		fieldsList.add(COMBO_FUNDRAISING_REASON);
		fieldsList.add(COMBO_LOCATION);
		fieldsList.add(AFIELD_DESC);
	//	userid = UserSession.getInstance().getMember().getId();
		userid = 555;
		for (String item : LIST_ANIMAL_TYPE) {
			COMBO_ANIMAL_TYPE.getItems().add(item);
		}

		// ------------ ID ------------------

		HBOX_HEADER.setId("HBOX-HEADER");
		LBL_HEADER.setId("LBL-HEADER");
		LBL_VIDEO.getStyleClass().add("TITLE-LABELS");
		LBL_IMG.getStyleClass().add("TITLE-LABELS");
		LBL_DSC.getStyleClass().add("TITLE-LABELS");
		LBL_HEADER.getStyleClass().add("TITLE-LABELS");
		LBL_COMBO.getStyleClass().add("TITLE-LABELS");
		COMBO_ANIMAL_TYPE.getStyleClass().add("TWO-ROW-FIELD");
		COMBO_TYPE.getStyleClass().add("TWO-ROW-FIELD");
		FIELD_VIDEO.getStyleClass().add("TWO-ROW-FIELD");
		FIELD_IMAGE.getStyleClass().add("TWO-ROW-FIELD");
		AFIELD_DESC.setId("DESC_FIELD");
		BUTTON_SUBMIT.setId("SUBMIT-BUTTON");
		BUTTON_RESET.setId("BUTTON-RESET");
		BUTTON_BACK.getStyleClass().add("BACK-BUTTON");
		BUTTON_SHOW_CAMPAIGN.setId("MY_POSTS_BUTTON");
		BUTTON_CATEGORIE.setId("CATEGORIE_BUTTON");
		TOGGLE_SMS.setId("TOGGLE_SMS");
		FIELD_IMAGE.setPromptText("hello");
		FIELD_IMAGE.setLabelFloat(true);
		
		
		
		// -------------- Event -----------------//
		RequiredFieldValidator validators = new RequiredFieldValidator();
		COMBO_ANIMAL_TYPE.setValidators(validators);
		FIELD_VIDEO.setValidators(validators);
		FIELD_IMAGE.setValidators(validators);
		AFIELD_DESC.setValidators(validators);

		for (Control c : ControlsList) {
			c.focusedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					// TODO Auto-generated method stub
					if (!newValue) {
						((IFXValidatableControl) c).validate();
						if (!((IFXValidatableControl) c).validate()) {
							new Shake(c).play();
						}
					}
				}
			});
		}

		BUTTON_SUBMIT.setOnAction(e -> {
			// Date date =
			// Date.from(DATEPICKER_CAMPAIGN_DATE.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			// System.out.println(date.toString());
			String animalType = COMBO_ANIMAL_TYPE.getSelectionModel().getSelectedItem();
			String fundType = COMBO_TYPE.getSelectionModel().getSelectedItem();
			int montant = Integer.parseInt(goal);
			String img = FIELD_IMAGE.getText();
			String video = FIELD_VIDEO.getText();
			String campaign = FIELD_Campaign_Title.getText();
			String location = theLocation;
			String fundReason = fundreason;
			String desc = AFIELD_DESC.getText();
			String date = theDate;

			Animeaux anim = new Animeaux(animalType, "Example", 0, userid, img, video, 555, campaign, location, montant,
					fundReason, date, desc, TOGGLE_SMS.isSelected());
			try {
			serv.create(anim);
			}catch (Exception ex) {
               // showAlert(Alert.AlertType.ERROR, GRID_GENRAL.getScene().getWindow(), "Error!", ex.toString());
			}
			if (TOGGLE_SMS.isSelected()) {
				//new TwilioService("51988249", "Jubran");
			}
			for (Control c : fieldsList) {
				if (c instanceof JFXTextField ) {
					((JFXTextField) c).clear();
				} else if (c instanceof JFXComboBox) {
					((JFXComboBox) c).getSelectionModel().clearSelection();
				}
				else if ( c instanceof JFXTextArea) {
					((JFXTextArea) c).clear();
				}
			}
		});

		BUTTON_RESET.setOnAction(e -> {
			for (Control c : fieldsList) {
				if (c instanceof JFXTextField || c instanceof JFXTextArea) {
					((JFXTextField) c).clear();
				} else if (c instanceof JFXComboBox) {
					((JFXComboBox) c).getSelectionModel().clearSelection();
				}
			}
		});

		BUTTON_BACK.setOnAction(e -> {
			Acceuil.catt.getChildren().clear();
			Acceuil.catt.getChildren().add(new AddAnimal());
			new SlideInRight(Acceuil.catt).play();
		});

		BUTTON_CATEGORIE.setOnAction(e -> {
			Acceuil.catt.getChildren().clear();
			GridPane g = new MyAnimalsPosts();
			Acceuil.catt.add(g, 0,0);
			
			new SlideInRight(Acceuil.catt).play();
		});
		
		BUTTON_SHOW_CAMPAIGN.setOnAction(e -> {
	        FileUploader fu = new FileUploader("localhost/exemplefolder");
	        
	        //Upload
	        try {
				String fileNameInServer = fu.upload("C:/Users/Public/TODO.txt");
			} catch (IOException e1) {
				// TODO Auto-generated catch blockC:\Users\admin\Desktop
				e1.printStackTrace();
			}
		});

		// ------------- STYLE -----------------
		GEN_GRID.setPadding(new Insets(30, 30, 30, 30));
		GEN_GRID.setVgap(50);
		SUB_GRID1.setHgap(200);
		SUB_GRID1.setVgap(50);
		SUB_GRID2.setHgap(200);
		SUB_GRID2.setVgap(50);

		HBOX_HEADER.getChildren().add(LBL_HEADER);
		HBOX_BUTTONS.getChildren().addAll(BUTTON_BACK, BUTTON_SHOW_CAMPAIGN, BUTTON_CATEGORIE);
		// HBOX_HEADER.getChildren().add(BUTTON_BACK);
		GEN_GRID.add(HBOX_HEADER, 0, 0);
		GEN_GRID.add(HBOX_BUTTONS, 0, 1);
		SUB_GRID1.add(LBL_ANIMAL_TYPE, 0, 0);
		SUB_GRID1.add(LBL_COMBO, 1, 0);
		SUB_GRID1.add(COMBO_ANIMAL_TYPE, 0, 1);
		SUB_GRID1.add(COMBO_TYPE, 1, 1);
		SUB_GRID2.add(LBL_IMG, 0, 0);
		SUB_GRID2.add(FIELD_IMAGE, 0, 1);
		SUB_GRID2.add(LBL_VIDEO, 1, 0);
		SUB_GRID2.add(FIELD_VIDEO, 1, 1);
		SUB_GRID2.add(LBL_DSC, 0, 2);
		SUB_GRID2.add(AFIELD_DESC, 0, 3);

		HBOX_FOOTER_BUTTONS.getChildren().addAll(TOGGLE_SMS, BUTTON_SUBMIT);
		HBOX_FOOTER.getChildren().addAll(BUTTON_RESET, HBOX_FOOTER_BUTTONS);

		HBOX_FOOTER.setSpacing(50);
		SUB_GRID2.add(HBOX_FOOTER, 0, 4);

		SUB_GRID2.setValignment(HBOX_FOOTER, VPos.CENTER);
		SUB_GRID2.setMargin(LBL_VIDEO, new Insets(0, 0, 0, -570));
		SUB_GRID2.setMargin(FIELD_VIDEO, new Insets(0, 0, 0, -550));
		SUB_GRID2.setMargin(LBL_VIDEO, new Insets(0, 0, 0, -550));
		GEN_GRID.setMargin(HBOX_BUTTONS, new Insets(-20, 0, 0, -0));
		HBOX_FOOTER_BUTTONS.setMargin(TOGGLE_SMS, new Insets(0, 0, 0, -300));
		HBOX_FOOTER_BUTTONS.setMargin(BUTTON_SUBMIT, new Insets(0, -60, 0, 30));
		HBOX_FOOTER_BUTTONS.setSpacing(200);
		HBOX_FOOTER.setSpacing(450);

		GEN_GRID.add(SUB_GRID1, 0, 2);
		GEN_GRID.add(SUB_GRID2, 0, 3);
		this.getChildren().addAll(GEN_GRID);
	}

	public GridPane getGEN_GRID() {
		return GEN_GRID;
	}

	public void setGEN_GRID(GridPane gEN_GRID) {
		GEN_GRID = gEN_GRID;
	}
	
	
}