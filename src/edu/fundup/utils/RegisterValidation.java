package edu.fundup.utils;

import edu.fundup.model.service.MemberService;
import javafx.scene.control.Alert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterValidation {

    // Validation Methods **********************

    // Mail
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateMail(String mail) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(mail);
        return matcher.find();
    }

    public static boolean checkUsername(String username,ArrayList<String> logins){
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(username);
        if (!username.isEmpty() && m.find() && m.group().equals(username)) {
            for (String e : logins) {
                if (e.equals(username)) {
                    return false;
                }
            }
            return true;
        }
        else
            return false;
    }

    public static boolean identicPassword(String pass1,String pass2){
        if (pass1.equalsIgnoreCase(pass2)){
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean validatePassword(String pass) {

        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}";
        if (pass.matches(pattern) && pass.equals(pass)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateDate(LocalDate localdate) {
        if (localdate == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Date");
            alert.setHeaderText(null);
            alert.setContentText("Enter valid date");
            alert.showAndWait();
            return false;
        }

        Date date = java.sql.Date.valueOf(localdate);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
        Calendar c = Calendar.getInstance();
        if (c.getTime().after(date) == true && c.getTime().equals(date) == false) {
            return true;

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Date");
            alert.setHeaderText(null);
            alert.setContentText("Enter valid date");
            alert.showAndWait();
            return false;

        }
    }
    public static boolean validateDateAlreadyPassed(LocalDate date)  throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = sdf.parse(date.toString());
        Date date2 = sdf.parse(LocalDate.now().toString());

        System.out.println("date1 : " + sdf.format(date1));
        System.out.println("date2 : " + sdf.format(date2));

        if (date1.compareTo(date2) > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Date");
            alert.setHeaderText(null);
            alert.setContentText("The date hasn't come yet !");
            alert.showAndWait();
            return false;
        }
        else {
            return true;
        }
    }
    public static boolean fieldNotNull(String field){
        return !(field.isEmpty());
    }
    // *****************************************
}
