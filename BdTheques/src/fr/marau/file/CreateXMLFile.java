/**
 * 
 */
package fr.marau.file;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import fr.marau.data.ElementLudotheque;
import fr.marau.data.Ludotheque;
import fr.marau.data.CategoryLudotheque;

/**
 * @author Christophe Rivier
 * 
 */
public class CreateXMLFile {

	Properties _prop;
	/**
	 * 
	 */
	public CreateXMLFile(Ludotheque ludo, String fileName, Properties prop ) {
		_prop = prop;
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			Element rootElement = document.createElement("BDTheque");
			document.appendChild(rootElement);

			for( CategoryLudotheque c : ludo.lst ){
				Element cat = document.createElement("categorie");
				cat.setAttribute("name", c.getCategory());
				for (ElementLudotheque p : c.getLudo()) {
					Element em = document.createElement("entite");
					em.setAttribute("title", p.getTitre());
					if( p.getSerie()!=null )
						em.setAttribute("serie",p.getSerie());
					Element description = document.createElement("description");
					if (p.getDescription() != null)
						description.appendChild(document.createTextNode(p
								.getDescription()));
					em.appendChild(description);
					Element auteur = document.createElement("auteur");
					if (p.getAuteur() != null)
						auteur.appendChild(document.createTextNode(p.getAuteur()));
					em.appendChild(auteur);
					cat.appendChild(em);
				}
				rootElement.appendChild(cat);
			}
			writeFile( document, fileName );
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private void writeFile(Document document, String fileName ) {
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			File test = new File(fileName);
			try {
				test.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// OutputStream test = new OutputStreamWriter();
			StreamResult result = new StreamResult(test);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
