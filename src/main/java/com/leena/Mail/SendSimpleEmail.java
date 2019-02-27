package com.leena.Mail;

import com.leena.Utility.GetSession;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendSimpleEmail implements Execute {


    GetSession session = new GetSession();

    Session session1 = session.createSession();

    public void execute() {

        //Compose the message

        try {
            MimeMessage message = new MimeMessage(session1);
            message.setFrom(new InternetAddress("Leena.patil@kwartile.com"));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress("Leena.patil@kwartile.com"));
            message.setSubject("javatpoint");
            message.setText("This is simple program of sending email using JavaMail API lets see if this works well");

            //send the message

            Transport.send(message);

            System.out.println("message sent successfully...");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
