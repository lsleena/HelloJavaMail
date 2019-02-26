package com.leena;

/*

Steps:

1. Get a Session

2. Create a default MimeMessage object and set From, To, Subject in the message.

3. Set the actual message as below:

     messageBodyPart.setText("This is message body");
4. Create a MimeMultipart object. Add the above messageBodyPart with actual message set in it, to this multipart object.

5. Next add the attachment by creating a Datahandler as follows:

    messageBodyPart = new MimeBodyPart();
    String filename = "/Users/leenapatil/workspace/HelloJavaMail/src/main/resources/test.txt";
    DataSource source = new FileDataSource(filename);
    messageBodyPart.setDataHandler(new DataHandler(source));
    messageBodyPart.setFileName(filename);
    multipart.addBodyPart(messageBodyPart);
6. Next set the multipart in the message as follows:

     message.setContent(multipart);
7. Send the message using the Transport object.
 */

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class SendAttachmentInEmail {

    public static void main(String[] args) {

        // Recipient's email ID needs to be mentioned.

        String to = "leena.patil@kwartile.com";
        String from = "leena.patil@kwartile.com";

        // Credentials

        final String host = "smtp.sendgrid.net";
        final String port ="587";
        final String username = "lsleena";
        final String password = "";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");

        // Get the Session Object

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,password);
                    }
                });

        try {

            //Create MIME message object
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            message.setSubject("Testing Attachment Email");

            // create message part

            BodyPart messageBodyPart = new MimeBodyPart();

            // set actual message

            messageBodyPart.setText("This message is for Sending Email with Attachment");

            // set multipart message

            Multipart multipart = new MimeMultipart();

            // Part I - Set text message part

            multipart.addBodyPart(messageBodyPart);

            // Part II - Set attachment

            messageBodyPart = new MimeBodyPart();
            String filename = "/Users/leenapatil/workspace/HelloJavaMail/src/main/resources/test.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);


            // Send the complete message parts

            message.setContent(multipart);


            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (Exception e) {

        }


    }
}
