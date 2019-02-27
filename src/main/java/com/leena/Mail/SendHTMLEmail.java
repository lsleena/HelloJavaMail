package com.leena.Mail;


import com.leena.Utility.GetSession;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendHTMLEmail implements Execute {

    GetSession session = new GetSession();

    Session session1 = session.createSession();
    public void execute() {

        try {

            // Create a default MimeMessage object

            Message message = new MimeMessage(session1);

            // Set From: header field of the header

            message.setFrom(new InternetAddress("email"));

            // Set To: header field of the header

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("email"));

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
