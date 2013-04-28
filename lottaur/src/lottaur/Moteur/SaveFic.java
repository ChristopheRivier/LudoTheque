/*
 * SaveFic.java
 *
 * Created on 12 mars 2006, 20:53
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lottaur.Moteur;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 *
 * @author christophe
 */
public class SaveFic {
   
    private BufferedInputStream FicStr=null;
    private String nameFic=null;
    
    SaveFic( String name, InputStream Fic ){
        nameFic = new String(name);
        FicStr = new BufferedInputStream(Fic);
    }  
    public String toString(){
        return nameFic;
    }
    public String getName() throws IOException{
      if (nameFic == null) {
        nameFic = File.createTempFile("xx", ".out").getName();
      }
      return nameFic;
    }
    public void saveFile(File fic) throws IOException {
        nameFic = getName();
        
        // Do no overwrite existing file
        File file = fic;
        if( file==null )
            file = new File(nameFic);
        for (int i=0; file.exists(); i++) {
          file = new File(nameFic+i);
        }
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        int aByte;
        while ((aByte = FicStr.read()) != -1) {
          bos.write(aByte);
        }
        bos.flush();
        bos.close();
        FicStr.close();
    }
    /**
    * Sauvegarde dans le fichier file de la chaine de caract�re dataString
   */
    public static boolean writeFile (File file, String dataString){
        try {
            PrintWriter out =
             new PrintWriter (new BufferedWriter (new FileWriter (file)));
            out.print (dataString);
            out.flush ();
            out.close ();
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }
    /**
     * R�cup�ration du fichier file sous forme de chaine de caract�re
     */
    public static String readFile (File file) {

        StringBuffer fileBuffer;
        String fileString=null;
        String line;

        try {
          FileReader in = new FileReader (file);
          BufferedReader dis = new BufferedReader (in);
          fileBuffer = new StringBuffer () ;

          while ((line = dis.readLine ()) != null) {
                fileBuffer.append (line + "\n");
          }

          in.close ();
          fileString = fileBuffer.toString ();
        }
        catch  (IOException e ) {
          return null;
        }
        return fileString;
    } // readFile
}
