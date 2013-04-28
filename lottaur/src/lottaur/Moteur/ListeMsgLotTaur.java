/*
 * ListeMsgLotTaur.java
 *
 * Created on 19 mars 2006, 21:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lottaur.Moteur;

import javax.mail.Folder;
import javax.mail.Message;

/**
 *
 * @author christophe
 */
public class ListeMsgLotTaur {
    private MessageLotTaur[] _Msg=null;
    private Folder _repertoire=null;
    
    /** Creates a new instance of ListeMsgLotTaur */
    public ListeMsgLotTaur( Folder repertoire ) {
        _repertoire = repertoire;
        if( repertoire!=null){
            try{
             if (!repertoire.isOpen()) {
                    repertoire.open(Folder.READ_WRITE);
                }
                // get the messages
                Message[] msgs = repertoire.getMessages();
                int nbmax = msgs.length;
                _Msg = new MessageLotTaur[nbmax];
                for( int i=0; i<repertoire.getMessageCount(); ++i ){
                    _Msg[i] = new MessageLotTaur(msgs[i],repertoire);
                }
            if( repertoire!=null )
                repertoire.close(false);
            }catch( Exception ex ){
                System.out.println("Erreur de cr�ation de ListeMessageLotTaur");
                ex.printStackTrace();
            }
        }
    }

    public int getNbMsg() {
        if( _Msg != null)
            return _Msg.length;
        else 
            return 0;
    }

    public MessageLotTaur getMsg(int i) {
        return _Msg[i];
    }
    
}
