package com.leena;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendHTMLEmail {

    public static void main(String[] args) {


        String to = "leena.patil@kwartile.com";
        String from = "leena.patil@kwartile.com";

        final String host = "smtp.sendgrid.net";
        final String port = "587";
        final String username = "lsleena";
        final String password = "@Kwartile1a";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            // Create a default MimeMessage object

            Message message = new MimeMessage(session);

            // Set From: header field of the header

            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field

            message.setSubject("Testing Subject");

            // Send the actual HTML message, as big as you like

            message.setContent(
                    "<h1>This is actual message embedded in HTML tags</h1>",
                    "text/html");

            // Send message
            Transport.send(message);

            System.out.println("Sent HTML message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
