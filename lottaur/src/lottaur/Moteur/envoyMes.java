/*
 * envoyMes.java
 *
 * Created on 11 mars 2006, 22:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lottaur.Moteur;
import javax.activation.DataHandler;

import javax.activation.DataSource;

import javax.activation.FileDataSource;

import javax.mail.BodyPart;

import javax.mail.Message;

import javax.mail.Multipart;

import javax.mail.Session;

import javax.mail.Transport;

import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeBodyPart;

import javax.mail.internet.MimeMessage;

import javax.mail.internet.MimeMultipart;




/**
 *
 * @author christophe
 */
public class envoyMes {
    
    /** Creates a new instance of envoyMes */
    public envoyMes() {
        
/*

String filename="c:/waza.xls";



//    envoi par SMTP du fichier xls, avec JavaMail

//    envoi de mail "� la main"

    Properties prop = System.getProperties();

    prop.put("mail.smtp.host","36.64.15.97");

    Session session = Session.getDefaultInstance(prop,null);

//    cr�atoin du message

    Message message = new MimeMessage(session);

    message.setFrom(new InternetAddress("SAMI"));

    message.setSubject("SAMI editions");

    message.addRecipient(Message.RecipientType.TO,

			 new InternetAddress("tartanpion@waza.fr"));

//    partie texte du message

    BodyPart messageBodyPart = new MimeBodyPart();

    messageBodyPart.setContent("voici l'edition demande","text/plain");

//    que l'on joint au message

    Multipart multipart = new MimeMultipart();

    multipart.addBodyPart(messageBodyPart);

//    pi�ce jointe

    messageBodyPart = new MimeBodyPart();

    DataSource source = new FileDataSource(filename);

    messageBodyPart.setDataHandler(new DataHandler(source));

    messageBodyPart.setFileName(filename);

//    que l'on attache egalement

    multipart.addBodyPart(messageBodyPart);

    message.setContent(multipart);

    Transport.send(message);
*/

    }
    
}
