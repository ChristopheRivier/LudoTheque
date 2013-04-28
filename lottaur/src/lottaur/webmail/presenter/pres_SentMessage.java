/*
 * pres_SentMessage.java
 *
 * Created on 19 juin 2006, 23:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lottaur.webmail.presenter;

import lottaur.Moteur.ParamConnect;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import lottaur.webmail.SentMessage;

/**
 *
 * @author christophe
 */
public class pres_SentMessage implements pres_LotTaur{
     private javax.swing.JFrame _owner = null;
     private SentMessage _view=null;
     private ParamConnect _paramConnect=null;
    /** Creates a new instance of pres_SentMessage */
    public pres_SentMessage(javax.swing.JFrame owner, ParamConnect para) {
        _owner = owner;
        _paramConnect = para;
    }

    public void CreateView() {
        _view = new SentMessage(_owner,this);
    }

    public void setVisibleView() {
        if( _view!=null )
            _view.setVisible(true);
    }

    public Object getRetour() {
        return null;
    }
    /**
     * @return code erreur si pas d'envoie
     */
    public int sentMessage(String dest, String ccdest, String sujet, String mess ){
        if( dest.length()==0 ) return 0; //pas d'envoie
        //Pr�paration de la connection
        Properties prop = new Properties();
        
//        System.out.println(_paramConnect.getSmtp());
//        prop.put("mail.smtp.host",_paramConnect.getSmtp());

        Session session = Session.getDefaultInstance(prop,null);
        session.setDebug(true); //activer le mode verbeux !
        
        try {
            Message message = new MimeMessage(session);
            String ad;
            ad = new String(_paramConnect.getUser());
            ad += "@";
            String tmp = new String(_paramConnect.getServeur());
            tmp.replaceFirst("pop.",ad );
            message.setFrom(new InternetAddress(tmp));
      
            message.setSubject(sujet);
      
            message.addRecipient(Message.RecipientType.TO,
			 new InternetAddress(dest));
            
            
            //    partie texte du message
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(mess,"text/plain");

            //    que l'on joint au message
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            Transport.send(message);
        } catch (AddressException ex) {
            ex.printStackTrace();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        _view.dispose(); //tout se passe bien je ferme la fen�tre
        return -1; //-1 renvoie Ok
    }
}