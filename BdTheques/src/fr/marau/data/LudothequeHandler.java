package fr.marau.data;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LudothequeHandler extends DefaultHandler {
	// résultats de notre parsing
	private List<CategoryLudotheque> ludo;
	private CategoryLudotheque category;
	private List<ElementLudotheque> lstElement;
	private ElementLudotheque elt;

	// buffer nous permettant de récupérer les données
	private StringBuffer buffer;

	// simple constructeur
	public LudothequeHandler() {
		super();
	}

	// détection d'ouverture de balise
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("emprunt") || qName.equals("BDTheque")) {
			ludo = new LinkedList<CategoryLudotheque>();
		} else if (qName.equals("categorie")) {
			category = new CategoryLudotheque(attributes.getValue("name"));
			lstElement = category.getLudo();
		} else if (qName.equals("entite")) {
			elt = new ElementLudotheque();
			elt.setTitre(attributes.getValue("title"));
			elt.setSerie(attributes.getValue("serie"));
		}else if( qName.equals("auteur")){
			buffer = new StringBuffer();
		}else if( qName.equals("description")){
			buffer = new StringBuffer();
		}
	}

	// détection fin de balise
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equals("entite")) {
			lstElement.add(elt);
			elt = null;
		}else if( qName.equals("auteur")){
			elt.setAuteur(buffer.toString());
			buffer = null;
		}else if( qName.equals("description")){
			elt.setDescription(buffer.toString());
			buffer = null;
		}else if (qName.equals("categorie")) {
			ludo.add(category);
			category = null;
		}
	}

	// détection de caractères
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String lecture = new String(ch, start, length);
		if (buffer != null)
			buffer.append(lecture);
	}

	// début du parsing
	public void startDocument() throws SAXException {
		System.out.println("Début du parsing");
	}

	// fin du parsing
	public void endDocument() throws SAXException {
		System.out.println("Fin du parsing");
		System.out.println("Resultats du parsing");
//		 for(CategoryLudotheque p : ludo){
//		 System.out.println(p.getCategory());
//		 }
	}

	public List<CategoryLudotheque> getLudo() {
		return ludo;
	}
}
