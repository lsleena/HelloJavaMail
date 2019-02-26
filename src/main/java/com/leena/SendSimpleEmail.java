package com.leena;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendSimpleEmail {

    public static void main(String[] args) {

        final String host = "smtp.sendgrid.net";
        final String port ="587";
        final String user = "lsleena";
        final String password = "";

        Properties props = new Properties();
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.port",port);
        props.put("mail.smtp.auth", "true");

        // Get the session object

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });

        //Compose the message

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Leena.patil@kwartile.com"));

            message.addRecipient(Message.RecipientType.TO,new InternetAddress("Leena.patil@kwartile.com"));
            message.setSubject("javatpoint");
            message.setText("This is simple program of sending email using JavaMail API lets see if this works well");

            //send the message

            Transport.send(message);

            System.out.println("message sent successfully...");

        } catch (MessagingException e) {e.printStackTrace();}
    }
}
