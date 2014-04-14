/**
 * 
 */
package fr.marau.data;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * @author Christophe Rivier
 *
 */
public class Ludotheque {
	private Properties _prop;
	public List<CategoryLudotheque> lst = new LinkedList<CategoryLudotheque>();
	
	public Ludotheque(Properties properties) {
		_prop = properties;
	}

	public boolean getLstFromXml() throws SAXException, IOException, ParserConfigurationException{
		boolean ret = true;

		SAXParserFactory fabrique = SAXParserFactory.newInstance();
		SAXParser parseur = fabrique.newSAXParser();

		File fichier = new File(_prop.getProperty("XMLFile"));
		
		LudothequeHandler gestionnaire = new LudothequeHandler();
		parseur.parse(fichier, gestionnaire);
		
		lst = gestionnaire.getLudo();
		return ret;
	}
	
	public CategoryLudotheque getCategory( String cat ){
		CategoryLudotheque ret = null;
		for( CategoryLudotheque p: lst ){
			if( p.getCategory()==cat ){
				ret = p;
				break;
			}
		}
		return ret;
	}
}
