package com.leena;

import com.leena.Mail.*;


public class MailApp {

    public static void main(String[] args) {

        SendHTMLEmail html = new SendHTMLEmail();
        html.execute();

        SendSimpleEmail simpleEmail = new SendSimpleEmail();
        simpleEmail.execute();

        SendInlineImagesInEmail imageMail = new SendInlineImagesInEmail();
        imageMail.execute();

    }

}
