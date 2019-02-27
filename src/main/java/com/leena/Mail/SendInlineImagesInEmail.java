package com.leena.Mail;

import com.leena.Utility.GetSession;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendInlineImagesInEmail implements Execute {


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

            message.setSubject("Email Inline Image in HTML");

            // This mail has 2 part, the BODY and the embedded image
            BodyPart messageBodyPart = new MimeBodyPart();
            MimeMultipart multipart = new MimeMultipart("related");

            // first part (the html)


            messageBodyPart.setContent("<H1>Hello</H1><img src=\"cid:image\">", "text/html");

            // add htmltext to the multipart

            multipart.addBodyPart(messageBodyPart);

            // second part (the image)

            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(
                    "/Users/leenapatil/workspace/HelloJavaMail/src/main/resources/nicepic.jpeg");

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");

            // add image to the multipart

            multipart.addBodyPart(messageBodyPart);

            // put everything together

            message.setContent(multipart);

            // Send message

            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}