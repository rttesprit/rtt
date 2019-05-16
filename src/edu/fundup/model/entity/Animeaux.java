package edu.fundup.model.entity;

import java.util.Date;

import com.jfoenix.controls.JFXCheckBox;

public class Animeaux {

	private int idamin;
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
	private boolean sms = false;
	private JFXCheckBox checkBox;

	public Animeaux() {
		this.checkBox = new JFXCheckBox();

	}

	public Animeaux(String nom, String etat, double montant, int iduser, String photo, String video, int idcat,
			String titre, String location, int goal, String fundtype, String endFundDate, String description,
			boolean sms) {
		this.nom = nom;
		this.etat = etat;
		this.montant = montant;
		this.iduser = iduser;
		this.photo = photo;
		this.video = video;
		this.idcat = idcat;
		this.titre = titre;
		this.location = location;
		this.goal = goal;
		this.fundtype = fundtype;
		this.endFundDate = endFundDate;
		this.description = description;
		this.sms = sms;
		this.checkBox = new JFXCheckBox();
	}
	
	public Animeaux(int idamin,String nom, String etat, double montant, int iduser, String photo, String video, int idcat,
			String titre, String location, int goal, String fundtype, String endFundDate, String description,
			boolean sms) {
		this(nom, etat, montant, iduser, photo, video, idcat, titre, location, goal, fundtype, endFundDate, description,
				sms);
		this.idamin = idamin;
	}

	public JFXCheckBox getCheckBox() {
		this.checkBox = new JFXCheckBox();

		return checkBox;
	}

	public void setCheckBox(JFXCheckBox checkBox) {
		this.checkBox = checkBox;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public String getFundtype() {
		return fundtype;
	}

	public void setFundtype(String fundtype) {
		this.fundtype = fundtype;
	}

	public String getEndFundDate() {
		return endFundDate;
	}

	public void setEndFundDate(String endFundDate) {
		this.endFundDate = endFundDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEtat() {
		return etat;
	}

	public int getIdamin() {
		return idamin;
	}

	public void setIdamin(int idamin) {
		this.idamin = idamin;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public boolean isSms() {
		return sms;
	}

	public void setSms(boolean sms) {
		this.sms = sms;
	}

	public int getIdcat() {
		return idcat;
	}

	public void setIdcat(int idcat) {
		this.idcat = idcat;
	}

	@Override
	public String toString() {
		return "Animeaux [nom=" + nom + ", etat=" + etat + ", montant=" + montant + ", iduser=" + iduser + ", photo="
				+ photo + ", video=" + video + ", idcat=" + idcat + ", titre=" + titre + ", location=" + location
				+ ", goal=" + goal + ", fundtype=" + fundtype + ", endFundDate=" + endFundDate + ", description="
				+ description + ", sms=" + sms + "]";
	}

}
