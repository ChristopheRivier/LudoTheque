/*
 * TreeModelServeur.java
 *
 * Created on 29 avril 2006, 01:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lottaur.webmail.model;

import lottaur.Moteur.ElementLotTaur;
import lottaur.Moteur.ParamConnect;
import java.util.List;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import org.jdom.Element;

/**
 *
 * @author christophe
 */
public class TreeModelServeur implements TreeModel{
    private Element _parent=null;
    /** Creates a new instance of TreeModelServeur */
    public TreeModelServeur(Element root) {
        _parent = root;
    }
    public Object getRoot() {
       return _parent;
    }

    public Object getChild(Object parent, int index) {
        Element p = (Element)parent;
        List lst = p.getChildren();
        if( lst.size()<index )
            return null;
        else
            return lst.get(index);
    }

    public int getChildCount(Object parent) {
        Element p = (Element)parent;
        return p.getChildren().size();
    }

    public boolean isLeaf(Object node) {
        Element p = (Element)node;
        return p.getChildren().size()==0?true:false;
    }

    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    public int getIndexOfChild(Object parent, Object child) {
        Element p = (Element)parent;
        List lst = p.getChildren();
        for( int i=0; i<lst.size(); ++i ){
            if( lst.get(i).equals(child))
                return i;
        }
        return -1;
    }

    public void addTreeModelListener(TreeModelListener l) {
    }

    public void removeTreeModelListener(TreeModelListener l) {
    }
}
