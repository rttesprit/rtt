/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fundup.model.entity;

/**
 *
 * @author Guideinfo
 */
public class Member {

    private int id;
    private String role;
    private String login;
    private String name;
    private String mail;
    private String password;
    private String first_name;
    private String last_name;
    private String address;
    private String city;
    private String country;
    private String payment_type;
    private String credit_card_number;
    private String cvv_num;
    private String president;
    private String foundation_date;
    private int enable;
    private String description;
    private String photo;

    private String register_date;
    // REGISTER DATE IS ONLY ON BDD

    // Constructor to test Observable
    public Member(int id){
        this.id=id;
    }

    // CONSTRUCTOR for SIGNED IN MEMBER
    public Member(int id,String role,String login,String name,String mail,String password,String first_name,String last_name,String address,String city,String payment_type,String credit_card_number,String cvv_num,String president,String foundation_date,int enable,String photo,String register_date){
        this.id=id;
        this.role=role;
        this.login=login;
        this.name=name;
        this.mail=mail;
        this.password=password;
        this.first_name=first_name;
        this.last_name=last_name;
        this.address=address;
        this.city=city;
        this.country=country;
        this.payment_type=payment_type;
        this.credit_card_number=credit_card_number;
        this.cvv_num=cvv_num;
        this.president=president;
        this.foundation_date=foundation_date;
        this.enable=enable;
        this.photo=photo;
        this.register_date=register_date;
    }
    // CONSTRUCTOR FOR PaperLess Member WITHOUT ID
    public Member(String login, String password, String mail, String first_name, String last_name, String address, String city, String country,String photo) {
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.photo = photo;
    }
    // CONSTRUCTOR FOR PaperLess Member
    public Member(int id, String login, String password, String mail, String first_name, String last_name, String address, String city, String country, String photo) {
        this(login,password,mail,first_name,last_name,address,city,country,photo);
        this.id = id;
    }

    // CONSTRUCTOR FOR CONTRIBUTOR WITHOUT ID
    public Member(String login, String password, String mail, String first_name, String last_name, String address, String city, String country,String photo, String payment_type, String credit_card_number, String cvv_num) {
        this(login,password,mail,first_name,last_name,address,city,country,photo);
        this.payment_type = payment_type;
        this.credit_card_number = credit_card_number;
        this.cvv_num = cvv_num;
    }
    // CONSTRUCTOR FOR CONTRIBUTOR
    public Member(int id, String login, String password, String mail, String first_name, String last_name, String address, String city, String country, String photo, String payment_type, String credit_card_number, String cvv_num) {
        this(login,password,mail,first_name,last_name,address,city,country,photo);
        this.id=id;
        this.payment_type = payment_type;
        this.credit_card_number = credit_card_number;
        this.cvv_num = cvv_num;
    }

    // CONSTRUCTOR for ASSOCIATION and ENTREPRISE WITHOUT ID
    public Member(String login, String name, String password, String mail, String address, String city, String country, String photo, String payment_type, String credit_card_number, String cvv_num, String president, String foundation_date) {
        this.login=login;
        this.name = name;
        this.password = password;
        this.mail = mail;
        this.address = address;
        this.city = city;
        this.country = country;
        this.payment_type = payment_type;
        this.credit_card_number = credit_card_number;
        this.cvv_num = cvv_num;
        this.president = president;
        this.foundation_date = foundation_date;
        this.photo = photo;
    }
    // CONSTRUCTOR for ASSOCIATION and ENTREPRISE
    public Member(int id, String login, String name, String password, String mail, String address, String city, String country, String photo,String payment_type, String credit_card_number, String cvv_num, String president, String foundation_date) {
        this(login,name,password,mail,address,city,country,photo,payment_type,credit_card_number,cvv_num,president,foundation_date);
        this.id=id;
    }
    // Consctructor whith all propreties
    public Member(int id, String role, String login, String name, String mail, String password, String first_name, String last_name,
                  String address, String city, String country, String payment_type, String credit_card_number, String cvv_num,
                  String president, String foundation_date, int enable, String photo) {
        this.id = id;
        this.role = role;
        this.login = login;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.payment_type = payment_type;
        this.credit_card_number = credit_card_number;
        this.cvv_num = cvv_num;
        this.president = president;
        this.foundation_date = foundation_date;
        this.enable = enable;
        this.photo = photo;
    }

    // for login
    public Member(String login,String password){
        this.login=login;
        this.password=password;
    }

    public Member(String nom, String mail, String role, String last_name, String first_name, String photo_path) {
        this.name = nom;
        this.mail = mail;
        this.role=role;
        this.last_name= last_name;
        this.first_name = first_name;
        this.photo = photo_path;
    }


    public int getId(){
        return id;
    }
    public String getlogin() {
        return login;
    }
    public void setlogin(String login) {
        this.login = login;
    }
    public String getmail() {
        return mail;
    }
    public void setmail(String mail) {
        this.mail = mail;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getfirst_name() {
        return first_name;
    }
    public void setfirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getlast_name() {
        return last_name;
    }
    public void setlast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getPayment_type() {
        return payment_type;
    }
    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }
    public String getCredit_card_number() {
        return credit_card_number;
    }
    public void setCredit_card_number(String credit_card_number) {
        this.credit_card_number = credit_card_number;
    }
    public String getCvv_num() {
        return cvv_num;
    }
    public void setCvv_num(String cvv_num) {
        this.cvv_num = cvv_num;
    }
    public String getPresident() {
        return president;
    }
    public void setPresident(String president) {
        this.president = president;
    }
    public String getFoundation_date() {
        return foundation_date;
    }
    public void setFoundation_date(String foundation_date) {
        this.foundation_date = foundation_date;
    }
    public int getEnable() {
        return enable;
    }
    public void setEnable(int enable) {
        this.enable = enable;
    }
    public String getRegister_date(){ return this.register_date; }

    @Override
    public String toString(){
        return "[ id :"+this.getId()+" role : "+this.getRole()+" login : "+this.getlogin()+ " @mail : "+this.getmail()+"password : "+this.getPassword()+
                " first_name : "+this.getfirst_name()+" last_name : "+this.getlast_name()+" address "+this.getAddress()+" city : "+this.getCity()+
                " country : "+this.getCountry()+" payment_type : "+this.getPayment_type()+"credit_card_number"+this.getCredit_card_number()+
                " cvv_num : "+this.getCvv_num()+" president : "+this.getPresident()+" foundation_date "+this.getFoundation_date()+
                "enable : "+this.getEnable()+" photo_path"+"register_date"+this.getRegister_date()+ "]";
    }

}
