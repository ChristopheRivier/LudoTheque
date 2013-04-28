/*
 * pres_def_serveur.java
 *
 * Created on 8 mai 2006, 00:06
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lottaur.webmail.presenter;

import lottaur.Moteur.ConnectPopLotTaur;
import lottaur.Moteur.ElementLotTaur;
import lottaur.Moteur.ParamConnect;
import lottaur.Moteur.SAXFactoryLotTaur;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import lottaur.webmail.def_serveur;

/**
 *
 * @author christophe
 */
public class pres_def_serveur implements pres_LotTaur{
    private def_serveur _view = null;
    private Document _doc=null;
    
    /** Creates a new instance of pres_def_serveur */
    public pres_def_serveur() {
        try{
            //r�cup�re le fichier de sauvegarde par d�faut
            File fil = new File("lottaur.xml");
            recupDocFile(fil);
        }
        catch(Exception e){e.printStackTrace();}
        RecupNbMessage();
    }
    
    public void MajNbMessage(){
        RecupNbMessage();
        if( _view!=null )
            _view.MajModelServeur();
    }
    private void RecupNbMessage(){
        //Connection
        ConnectPopLotTaur pop = new ConnectPopLotTaur();
        ParamConnect para = null;
        para = new ParamConnect();
        Element root = _doc.getRootElement();
        List lst = root.getChildren("pop");
        Element elPop = null;
        for( int i=0; i<lst.size(); ++i ){
            Element tmp = (Element)lst.get(i);
            para.setServeur(tmp.getAttributeValue("name"));
            List lstUser = tmp.getChildren("user");
            for( int j=0; j<lstUser.size(); ++j ){
                Element tmpuser = (Element)lstUser.get(j);
                para.setUser(tmpuser.getAttributeValue("name"));
                para.setPass(tmpuser.getAttributeValue("pass"));
                try{
                    pop.setParamConnect(para);
                    int nbmsg = pop.getNbMessage();
                    String str = new String();
                    str += nbmsg;
                    tmpuser.setAttribute("nbmsg", str);
                }catch(Exception e){e.printStackTrace();}
            }
        }                        
       
        
    }
    public void recupDocFile(File fil) throws JDOMException,IOException{
        //On cr�e une instance de SAXBuilder
        SAXBuilder sxb = new SAXBuilder();
         //s'il existe on l'utilise
        if( fil.exists() ){
            sxb.setFactory(new SAXFactoryLotTaur());
            _doc = sxb.build(fil);
        }else{
            //on cr�e un �l�ment simple
            Element root = new Element("lottaur");
            _doc = new Document(root);
        }
    }

    public void CreateView() {
        _view = new def_serveur(this);
    }

    public void setVisibleView() {
        if( _view!=null )
            _view.setVisible(true);
    }

    public Document getDocXML() {
        return _doc;
    }

    public void AddConnect() {
        if( _view!=null ){
            pres_LotTaur pres = new pres_addConnect(_view);
            pres.CreateView();
            pres.setVisibleView();   
            Object r = pres.getRetour();
            if( r!=null && r instanceof ParamConnect ){
                addParamConnect((ParamConnect)r);
            }
        }
    }
    public Object getRetour(){
        return null;
    }
    
    public void addParamConnect( ParamConnect para ){
        if( para!=null ){
            String pop = para.getServeur();
            Element root = _doc.getRootElement();
            List lst = root.getChildren("pop");
            Element elPop = null;
            for( int i=0; i<lst.size(); ++i ){
                Element tmp = (Element)lst.get(i);
                if( tmp.getAttributeValue("name").equals(pop) ){
                    elPop = tmp;
                }
            }
            if( elPop==null ){
                //ajout du pop
                elPop = new ElementLotTaur("pop");
                elPop.setAttribute( "name", pop );
                root.addContent(elPop);
            }
            Element user = new ElementLotTaur("user");
            user.setAttribute( "name", para.getUser() );
            user.setAttribute( "pass", para.getPass() );
            elPop.addContent(user);
            if( _view!=null )
                _view.MajModelServeur();
        }
    }

    public void DelConnect(String nameEl, String namePop) {
        Element root = _doc.getRootElement();
        String nomPop = namePop;
        boolean bPopSeul = false;
        if( namePop==null ){
            nomPop = nameEl;
            bPopSeul = true;
        }
        //Recherche du pop � supprimer
        List lst = root.getChildren("pop");
        Element elPop = null;
        for( int i=0; i<lst.size(); ++i ){
            Element tmp = (Element)lst.get(i);
            if( tmp.getAttributeValue("name").equals(nomPop) ){
                if( bPopSeul ){
                    //Suppression de l'�lement
                    lst.remove(i);
                }else{
                    elPop = tmp;
                }
            }
        }
        //Recherche de l'�lement
        if( elPop!=null ){
            lst = elPop.getChildren("user");
            for( int i=0; i<lst.size(); ++i ){
                Element tmp = (Element)lst.get(i);
                if( tmp.getAttributeValue("name").equals(nameEl) ){
                    lst.remove(i); 
                }
            }
         
        }
        if( _view!=null )
            _view.MajModelServeur();
    }
}
