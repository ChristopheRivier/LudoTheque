package lottaur.Moteur;
import com.sun.mail.util.BASE64DecoderStream;
import java.awt.List;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeMultipart;
/*
 * MessageLotTaur.java
 *
 * Created on 1 mars 2006, 17:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author CRivier
 */

public class MessageLotTaur {
   // private Message _msg;
    private String _strText;
    private String _sujet = null;
    private String _from = null;
    private String _strHtml;
    private String _typeText;
    private String _typeHtml;
    private boolean _isHtml = false;
    private boolean _isText = false;
    private boolean _AvecFichierAttach = false;
    private Vector Fichier;
    private String _dt;
    private Folder _repertoire;
    private Message _msg;
    private boolean _corps;
    
    /** Creates a new instance of MessageLotTaur */
    public MessageLotTaur(Message message,Folder rep) {
        _repertoire = rep;
        _msg = message;
        Fichier = new Vector();
        try{
            String mes = message.getSubject();
            if( mes==null )
                mes="(Pas de sujet)";
            _sujet = new String( mes );
            try{
                Address[] ad = message.getFrom();
                if (ad != null && ad.length != 0) {
                    _from = new String( ad[0].toString() );
                }else{
                    _from = new String ("pas d'exp�diteur");
                }
            }catch(Exception ex){
                System.out.println("erreur exp�diteur");
                _from = new String ("pas d'exp�diteur");
                ex.printStackTrace();
            }
            Date date = message.getSentDate();
            String strDate;
            if( date==null )
                strDate ="Pas de date";
            else
                strDate = date.toString();
            _dt = new String(strDate);
            _corps = false;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
    private void ParseMsg(){
        if( _corps==false ){
         if( _repertoire!=null){
            try{
                 if (!_repertoire.isOpen()) {
                        _repertoire.open(Folder.READ_WRITE);
                    }
                try {

                    //Traitement pour extraire l'ensemble des infos dont nous avons besoin
                    dumpPart(_msg);
                }catch (IOException ex) {
                    ex.printStackTrace();
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
                if( _repertoire!=null ){
                   _repertoire.close(false);
                }
            }catch(Exception ex ){
                System.out.println("Erreur d'ouverture de Repertoire sur le serveur");
                ex.printStackTrace();
            }
         }
         _corps=true;
        }
    }
    public String getStrText(){
        ParseMsg();
        return _strText;
    }
    public String getStrHtml(){
        ParseMsg();
        return _strHtml;
    }
    public String getStrDefault(){
        ParseMsg();
        return _strText;
    }
    public boolean isAlternative(){
        ParseMsg();
        return _isText && _isHtml;
    }
    public String getContentTypeDefault(){
        ParseMsg();
        return _typeText;
    }
    public String getContentTypeHtml(){
        ParseMsg();
        return _typeHtml;
    }
    public String getContentTypeText(){
        ParseMsg();
        return _typeText;
    }
    public Vector getList(){
        ParseMsg();
        return Fichier;
    }
    public String getSubject(){
        return _sujet;
    }
    public String getFrom(){
        return _from;
    }
    public String getSentDate(){
        return _dt;
    }

    protected void dumpPart(Part bodyPart) throws IOException, MessagingException {
        Object ob = bodyPart.getContent();
        if (ob == null) {
            System.out.println( "Content:  is null");
        }else if( ob instanceof String ){
            RecupereString(bodyPart);
        }else if( ob instanceof MimeMultipart ){
            Multipart mp = (Multipart)ob;            
            int count = mp.getCount();
            for (int i = 0; i < count; i++) {
                dumpPart(mp.getBodyPart(i));
            }
            System.out.println("multipart");
        }else if( ob instanceof BASE64DecoderStream ){
            if( bodyPart.getFileName()!= ""){
                //saveFile(bodyPart.getFileName(),bodyPart.getInputStream());
                SaveFic fic = new SaveFic(bodyPart.getFileName(),bodyPart.getInputStream());
                Fichier.add(fic);
                System.out.println("fin fichier");
                _AvecFichierAttach = true;
            }
        }else{
            System.out.println("Content: " + ob.getClass().toString());
        }
    }

    private void RecupereString(Part bodyPart) throws IOException, MessagingException {
        Object ob = bodyPart.getContent();
        String str = ob.toString();
        String strType = bodyPart.getContentType();
        Pattern compare = Pattern.compile("text/html.*");
        Matcher mat = compare.matcher(strType);
        if( mat.matches() ){  //Dans le cas ou nous sommes en html
            Pattern p = Pattern.compile("<meta [^>]*>");
            Matcher m = p.matcher(str);
            if( _strHtml==null )
                _strHtml = m.replaceAll("");
            else
                _strHtml += m.replaceAll(""); 
            _typeHtml = bodyPart.getContentType();
            _isHtml = true;
        }else{
            if( _strText==null )
                _strText = str;
            else
                _strText += str;
            _typeText = bodyPart.getContentType();
            _isText = true;
        }
    }

    public boolean AvecFichierAttach() {
        return _AvecFichierAttach;
    }
    private void saveFile(String filename, InputStream input) throws IOException {
    if (filename == null) {
      filename = File.createTempFile("xx", ".out").getName();
    }
    // Do no overwrite existing file
    File file = new File(filename);
    for (int i=0; file.exists(); i++) {
      file = new File(filename+i);
    }
    FileOutputStream fos = new FileOutputStream(file);
    BufferedOutputStream bos = new BufferedOutputStream(fos);

    BufferedInputStream bis = new BufferedInputStream(input);
    int aByte;
    while ((aByte = bis.read()) != -1) {
      bos.write(aByte);
    }
    bos.flush();
    bos.close();
    bis.close();
    }
}

