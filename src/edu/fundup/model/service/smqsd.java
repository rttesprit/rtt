package edu.fundup.model.service;


import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class smqsd {

    final String sender = "skander.jenhani@esprit.tn";
    final String senderPass = null;
    final String server = "smtp.tunet.tn";
    final String port ="587";
    String receiverMail = null;
    String subject = null;
    String body = null;

    public smqsd(){}

    // AUTHENTIFICATION for Transfer Mail Protocol
    private class SMTPAuthenticator extends javax.mail.Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(sender, senderPass);
        }
    }

    public smqsd(String receiverMail, String subject, String message) {
        this.receiverMail = receiverMail;
        this.subject = subject;
        this.body = message;

        Properties props = new Properties();
        props.put("mail.smtp.user", sender);
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port",port);
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.required", "true");

        SecurityManager security = System.getSecurityManager();

        System.out.println("sds");
        try {
            Authenticator auth = new SMTPAuthenticator();
            // Connecting to a new Session to send mail
            Session session = Session.getInstance(props, auth);

            // Setting the mail
            Message msg = new MimeMessage(session);
            msg.setText(body);
            msg.setSubject(subject);
            msg.setFrom(new InternetAddress(sender));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(this.receiverMail));
            Transport.send(msg);
            System.out.println("send successfully");
        } catch (Exception ex) {
            System.err.println("Error occurred while sending.!");
        }

    }

    public void SendE_mail(String email , String messageS) throws AddressException, MessagingException {

        String host = "smtp.gmail.com";
        String user = sender;
        String pass = "183JMT2042";
        String to = email;
        String from = sender;
        String subject = "Tunisia Charity";
        boolean sessionDebug = false;

        Properties props = System.getProperties();

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
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
        msg.setText(messageS);

        javax.mail.Transport transport = mailSession.getTransport("smtp");
        transport.connect(host, user, pass);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
        System.out.println("message sent successfully");
    }
}
