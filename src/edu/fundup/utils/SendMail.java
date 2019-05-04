package edu.fundup.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {

    public SendMail() {
    }

    public int GenerateCode() {
        Random rand = new Random(System.currentTimeMillis());

        int red = rand.nextInt(99999);
        return red;
    }

    public void SendEmail(String email) {
        int code = GenerateCode();
        PreparedStatement pst, pst2;
        ResultSet rs, rs2;
        DataSource con;

        try {
            String req = "INSERT INTO sendmail VALUES (null ,code=?,email=?)";
            try {
                pst2 = DataSource.getInstance().getConnection().prepareStatement(req);
                pst2.setInt(1, code);
                pst2.setString(2, email);
                pst2.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("code " + code + " ");
            String host = "smtp.gmail.com";
            String user = "skander.jenhani@esprit.tn";
            String pass = "183JMT2042";
            String to = email;
            String from = "nassim.gastli@esprit.tn";
            String messageText = "This is confirmation number for your Tunisia Charity account. Please insert this number to activate your account = " + code + " ";
            String subject = "Tunisia Charity Email Confirmation ";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(messageText);

            javax.mail.Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message sent successfully");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
