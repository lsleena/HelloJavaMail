package com.leena;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Creates an email object for sending via SendGrid.
 * Be sure to update Sendgrid credentials.
 * <p>
 * Usage:
 * SGEmail email = new SGEmail();
 * email.to("neil@tabuleapp.com, "XYZ");
 * email.from("contact@tabuleapp.com", "Tabule");
 * email.subject("This is a subject");
 * email.text("This is a plain text body.");
 * email.html("This is an html body.");
 * email.send();
 *
 * @version 1.0
 */
public class SengridEmailExample {

    private static final String SMTP_AUTH_USER = "lsleena";
    private static final String SMTP_AUTH_PWD = "";
    private static final String SMTP_HOST_NAME = "smtp.sendgrid.net";

    private InternetAddress to;
    private InternetAddress from;
    private String subject;
    private String html;
    private String text;

    public void to(String email, String name) {
        try {
            to = new InternetAddress(email, name);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void to(String email) {
        try {
            to = new InternetAddress(email);
        } catch (AddressException e) {
            e.printStackTrace();
        }
    }

    public void from(String email, String name) {
        try {
            from = new InternetAddress(email, name);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void from(String email) {
        try {
            from = new InternetAddress(email);
        } catch (AddressException e) {
            e.printStackTrace();
        }
    }

    public void subject(String subject) {
        this.subject = subject;
    }

    public void html(String html) {
        this.html = html;
    }

    public void text(String text) {
        this.text = text;
    }

    public void send() {
        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", SMTP_HOST_NAME);
            props.put("mail.smtp.port", 587);
            props.put("mail.smtp.auth", "true");

            Authenticator auth = new SMTPAuthenticator();
            Session mailSession = Session.getInstance(props, auth);
            Transport transport = mailSession.getTransport();

            MimeMessage message = new MimeMessage(mailSession);

            Multipart multipart = new MimeMultipart("alternative");

            if (text != null) {

                BodyPart part1 = new MimeBodyPart();
                part1.setText(text);
                multipart.addBodyPart(part1);
            }

            if (html != null) {

                BodyPart part2 = new MimeBodyPart();
                part2.setContent(html, "text/html");
                multipart.addBodyPart(part2);
            }

            message.setContent(multipart);
            message.setFrom(from);
            message.setSubject(subject);
            message.addRecipient(Message.RecipientType.TO, to);

            transport.connect();
            transport.sendMessage(message,
                    message.getRecipients(Message.RecipientType.TO));
            transport.close();

        } catch (MessagingException e) {

            e.printStackTrace();
        }
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {

            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;

            return new PasswordAuthentication(username, password);
        }
    }

    public static void main(String[] args) {

        SengridEmailExample email = new SengridEmailExample();
        email.to("leena.patil@kwartile.com", "Leena Patil");
        email.from("leena.patil@kwartile.com", "Leena Patil");
        email.subject("Leena is sending test email");
        email.text("Leena is sending test email on Monday Morning");
        email.html("Leena is sending test email");
        email.send();
    }
}