/*
 * SAXFactoryLotTaur.java
 *
 * Created on 7 mai 2006, 15:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lottaur.Moteur;

import java.util.Map;
import org.jdom.Attribute;
import org.jdom.CDATA;
import org.jdom.Comment;
import org.jdom.Content;
import org.jdom.DefaultJDOMFactory;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.EntityRef;
import org.jdom.JDOMFactory;
import org.jdom.Namespace;
import org.jdom.Parent;
import org.jdom.ProcessingInstruction;
import org.jdom.Text;

/**
 *
 * @author christophe
 */
public class SAXFactoryLotTaur extends DefaultJDOMFactory{
    
    /** Creates a new instance of SAXFactoryLotTaur */
    public SAXFactoryLotTaur() {    
    }
    
    public Element element(String string, Namespace namespace) {
        return new ElementLotTaur(string,namespace);
    }

    public Element element(String string) {
        return new ElementLotTaur(string);
    }

    public Element element(String string, String string0) {
        return new ElementLotTaur(string,string0);
    }

    public Element element(String string, String string0, String string1) {
        return new ElementLotTaur(string,string0,string1);
    }
    
}
