package com.leena.Utility;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class GetSession {

    // Recipient's email ID needs to be mentioned.

    String to = "leena.patil@kwartile.com";
    String from = "leena.patil@kwartile.com";

    // Credentials

    final String host = "smtp.sendgrid.net";
    final String port ="587";
    final String username = "lsleena";
    final String password = "";

    public Session createSession() {

        Properties props = new Properties();

        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");

        // Get the Session Object
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        return session;
    }
}
