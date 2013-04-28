/*
 * pres_addConnect.java
 *
 * Created on 5 mai 2006, 07:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lottaur.webmail.presenter;

import lottaur.Moteur.ParamConnect;
import lottaur.webmail.AddConnection;

/**
 *
 * @author chabrier
 */
public class pres_addConnect implements pres_LotTaur{
    
    private AddConnection _view = null;
    private javax.swing.JFrame _owner = null;
    private ParamConnect _paramSauv;
    /** Creates a new instance of pres_addConnect */
    public pres_addConnect(javax.swing.JFrame owner) {
        _owner = owner;
    }
    
    public void ModifConnect(ParamConnect param ){
        if( _view!=null ){
            //Ajout des param�tres de connections
            _view.setUser(param.getUser());
            _view.setServeur(param.getServeur());
            _view.setPass(param.getPass());
        }
    }
     public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pres_addConnect(null);
            }
        });
    }

    public void CreateView() {
        _view = new AddConnection( _owner, "Connection", this );
    }

    public void setVisibleView() {
        if( _view!=null )
            _view.setVisible(true);
    }

    public void ActionClose() {
        if( _view!=null ){
            _paramSauv = new ParamConnect();
            _paramSauv.setUser(_view.getUser());
            _paramSauv.setPassClean(_view.getPass());
            _paramSauv.setServeur(_view.getServeur());
            _view.dispose();    
        }
    }
    public void ActionCancel(){
        if( _view!=null ){
            _view.dispose();
        }
    }
    public Object getRetour(){
        return _paramSauv;
    }
    
}
