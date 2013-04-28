/*
 * Ressource.java
 *
 * Created on 4 mai 2006, 11:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lottaur.ressources;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author CRivier
 * Cr�ation d'un singleton pour la r�cup�ration de l'ensemble des ressources
 */
public class Ressource {
    
    private static Ressource _instance = null;
    private Ressource(){
        
    }
    /** R�cup�ration du singleton */
    public static Ressource getInstance(){
        if( _instance==null )
            _instance = new Ressource();
        return _instance;
    }
    
    public Image getIcon(){
        ImageIcon monIcon = new ImageIcon(getClass().getResource("icone.png"));
        return monIcon.getImage();
    }
    
    public Image getSplash(){
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("logo.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return img;
    }
}
