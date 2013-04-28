/*
 * FiltreFichier.java
 *
 * Created on 3 mai 2006, 20:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lottaur.Moteur;

import java.io.File;
import javax.swing.filechooser.FileFilter;


/**
 *
 * @author christophe
 */
public class FiltreFichier extends FileFilter{
    String[] _extensions;
    String _description;
    /** Creates a new instance of FiltreFichier */
    public FiltreFichier(String []extensions,String description ) {
        _extensions = extensions;
        _description = description;
    }
    
    boolean appartient( String ext ){

      for( int i = 0; i<_extensions.length; ++i){

          if(ext.equals(_extensions[i])) return true;

      }
      return false;
   }
    
    public boolean accept(File f) {
        if(f.isDirectory()) {
           return true;
        }
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
             ext = s.substring(i+1).toLowerCase();
         }
         return ext != null && appartient(ext);
    }

    public String getDescription() {
        return _description;
    }
    
}
