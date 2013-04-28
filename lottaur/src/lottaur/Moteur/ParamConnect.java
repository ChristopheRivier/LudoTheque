/*
 * ParamConnect.java
 *
 * Created on 29 avril 2006, 00:38
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lottaur.Moteur;

import com.sun.mail.util.BASE64DecoderStream;

/**
 *
 * @author christophe
 */
public class ParamConnect {
    private String _user=null;
    /** le param�tre password est sauvegard� sous forme encod� !!!! */
    private String _pass=null;
    private String _serveur=null;
    
    /** Creates a new instance of ParamConnect */
    public ParamConnect() {
    }
    public ParamConnect(String user, String pass, String serveur){
        this._user = new String(user);
        this._pass = new String(pass);
        this._serveur = new String(serveur);
    }
    public ParamConnect( ParamConnect pa ){
        this._user = new String(pa.getUser());
        this._pass = new String(pa.getPass());
        this._serveur = new String(pa.getServeur());
    }

    public String getServeur() {
        return _serveur;
    }

    /**
     * r�cup�ration du password encode
     */
    public String getPass(){
        return _pass;
    }
    /**
     * R�cup�ration du password d�code
     */
    public String getPassClean() {
//        BASE64DecoderStream pas = new BASE64Decoder();
//        String str=new String();
//        try {
//                str = new String(pas.decodeBuffer(_pass));
//            } catch(Exception ex){
//                ex.printStackTrace();
//        }
        return _pass;
    }

    public void setPassClean( String str ){
        
//        sun.misc.BASE64Encoder pas = new sun.misc.BASE64Encoder();
//        _pass = pas.encode(str.getBytes());
    	_pass = str;
    }
    public String getUser() {
        return _user;
    }
    
    public String getStrConnect(){
//        sun.misc.BASE64Decoder pas = new sun.misc.BASE64Decoder();
        String str=new String(_pass);
//        try {
//                str = new String(pas.decodeBuffer(_pass));
//            } catch(Exception ex){
//                ex.printStackTrace();
//        }
        return new String("pop3://"+_user+":"+str+"@"+_serveur);
    }
    public void setServeur(String str){
        _serveur = new String(str);
    }
    public void setPass( String str ){
        _pass = new String(str);
    }
    public void setUser(String str){
        _user = new String(str);
    }
}
