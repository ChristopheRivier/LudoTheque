/*
 * ElementLotTaur.java
 *
 * Created on 1 mai 2006, 12:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lottaur.Moteur;

import org.jdom.Element;
import org.jdom.Namespace;

/**
 *
 * @author christophe
 */
public class ElementLotTaur extends Element{
    
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/** Creates a new instance of ElementLotTaur */
    public ElementLotTaur( String str ) {
        super(str);
    }
    public ElementLotTaur( String str,String str1 ) {
        super(str,str1);
    }
    public ElementLotTaur( String str,String str1, String str2 ) {
        super(str,str1,str2);
    }
    public  ElementLotTaur(String string, Namespace namespace) {
        super(string,namespace);
    }
    
    public String toString(){
        String str = getAttributeValue("name");
        String strRetour = new String();
        strRetour = str;
        String strNb = getAttributeValue("nbmsg");
        if( strNb!=null )
            strRetour += "        : " + strNb;
        if( str==null || str!=null && str.length()==0 )
            strRetour = getName();
        return strRetour;
    }
}    
