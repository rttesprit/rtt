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

    public void SendEmail(String email, String name) {
        int code = GenerateCode();
        PreparedStatement pst, pst2;
        ResultSet rs, rs2;
        DataSource con;

        try {
            String req = "INSERT INTO sendmail(id,code,mail) VALUES (null ,?,?)";
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
            String user = "tun.charity@gmail.com";
            String pass = "tunisiacharity";
            String to = email;
            String from = "tun.charity@gmail.com";
            String messageText = name+",\n\n"+"Welcome to Tunisia Charity. Please take a second to confirm your email to make sure we've got it right.\n" +
                    "Confirmation Code : " + code + " ";
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
