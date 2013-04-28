/*
 * listeMsgModel.java
 *
 * Created on 14 f�vrier 2006, 14:46
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lottaur.webmail.model;

import lottaur.Moteur.ListeMsgLotTaur;
import lottaur.Moteur.MessageLotTaur;
import java.util.Date;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.swing.table.AbstractTableModel;
import javax.mail.Folder;
import javax.mail.Message;

/**
 *
 * @author Christophe Rivier
 */

public class listeMsgModel extends AbstractTableModel{
    
    Folder repertoire;
    private String[] _sujet;
    private String[] _from;
    private String[] _date;
    private ListeMsgLotTaur _lst;
    
    String[]	colNames = { "Sujet", "De", "Date"};
    Class[]	colTypes = { String.class, String.class, String.class };
    /** Creates a new instance of listeMsgModel */
    public listeMsgModel(){
    }
    public void setListeMsg(ListeMsgLotTaur lst ){
        _lst = lst;
         
        int nb = _lst.getNbMsg();
        _sujet = new String[nb];
        _from = new String[nb];
        _date = new String[nb];
        for( int i=0; i<nb; ++i ){
            MessageLotTaur msg = _lst.getMsg(i);
            _sujet[i] = new String(msg.getSubject());            
            _from[i] = new String( msg.getFrom());
            _date[i] = new String(msg.getSentDate());
        }
        fireTableDataChanged();
    }
    public String getColumnName(int column){
        return colNames[column];
    }
    public int getRowCount() {
        if( _sujet==null )
            return 0;
        else{
            return _sujet.length;
        }
    }

    public int getColumnCount() {
        return colNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        //on r�cup�re le message correspondant � la ligne
        String str="";
        try{
            switch(columnIndex){
                case 0 :
                    str = _sujet[rowIndex];
                    break;
                case 1 :
                    str = _from[rowIndex];
                    break;
                case 2 :
                default :
                    str = _date[rowIndex];
                    break;
            }
        }catch( Exception ex ){
            ex.printStackTrace();
        }
        return str;
    }
}
