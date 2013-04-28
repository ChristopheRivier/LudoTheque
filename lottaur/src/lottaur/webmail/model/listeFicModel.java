/*
 * listeFicModel.java
 *
 * Created on 12 mars 2006, 21:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lottaur.webmail.model;

import java.util.Vector;
import javax.swing.AbstractListModel;

/**
 *
 * @author christophe
 */
public class listeFicModel extends AbstractListModel{
    
    Vector _lst=null;
    /** Creates a new instance of listeFicModel */
    public listeFicModel() {
    }
    public void setList(Vector lst){
        _lst = lst;
        for( int i=0;i<_lst.size();++i ){
            fireContentsChanged(_lst.get(i),i,_lst.size());
        }
    }

    public int getSize() {
        if( _lst==null ){
            return 0;
        }else{
            return _lst.size();
        }
    }

    public Object getElementAt(int index) {
        if(_lst==null){
            return null;
        }else{
            return _lst.get(index);
        }
    }
    
}
