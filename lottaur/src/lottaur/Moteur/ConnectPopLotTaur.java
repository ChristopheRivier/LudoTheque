/*
 * ConnectPopLotTaur.java
 *
 * Created on 14 mai 2006, 14:43
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lottaur.Moteur;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

/**
 *
 * @author christophe
 */
public class ConnectPopLotTaur {
    private Folder repertoire=null;
    private Session session=null;
    Store store=null;
    /** Creates a new instance of ConnectPopLotTaur */
    public ConnectPopLotTaur(){
        Folder repertoire = null;
        session = Session.getDefaultInstance(System.getProperties(), null);
    }
    private void ConnectPop( ParamConnect para ) throws MessagingException{
        URLName urln = new URLName(para.getStrConnect());
        try{
        store = session.getStore(urln);
        }catch(Exception ex){ex.printStackTrace();}

        // connect to the Store if we need to
        if (!store.isConnected()) {
            store.connect();
        }
        // get the default folder, 
        repertoire = store.getFolder("INBOX");
    }
    public void setParamConnect(ParamConnect para) throws MessagingException{
        if( store!=null )
            store.close();
        ConnectPop(para);
    }
    public int getNbMessage(){
        int nbMsg = 0;
        if( repertoire!=null ){
            try{
                if (!repertoire.isOpen()) {
                    repertoire.open(Folder.READ_WRITE);
                }
                nbMsg = repertoire.getMessageCount();
                repertoire.close(false);
            }catch(Exception e){e.printStackTrace();}
        }
        return nbMsg;
    }
    public Folder getRepertoire(){
        return repertoire;
    }
}
