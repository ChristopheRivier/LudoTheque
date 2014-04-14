package fr.marau.gui;

import fr.marau.data.ElementLudotheque;
import fr.marau.data.TypeEl;

public class PresentorElBD {
	private IElementBiblio element = null;
	private ElementLudotheque data = new ElementLudotheque();
	private PresentorApp parent;

	public PresentorElBD(PresentorApp p, IElementBiblio inter ){
		element = inter;
		element.setPresentor(this);
		parent = p;
	}
	public PresentorElBD(PresentorApp p) {
		element = new GuiElementBD("test");
		element.setPresentor(this);
		parent = p;
	}
	public ElementLudotheque getData(){
		return data;
	}
	public void setData( ElementLudotheque el ){
		data=el;
	}
	public void getDataFromGui(){
		data.setAuteur(element.getAutor());
		data.setDescription(element.getComment());
		data.setEditeur(element.getEditor());
		data.setSerie(element.getSerie());
		data.setTitre(element.getTitle());
	}
	
	public void getDataFromBiblio(){
		element.setAutor(data.getAuteur());
		element.setComment(data.getDescription());
		element.setEditor(data.getEditeur());
		element.setSerie(data.getSerie());
		element.setTitle(data.getTitre());
	}
	public void sauvElement() {
		ElementLudotheque l = new ElementLudotheque();
		l.setAuteur(element.getAutor());
		l.setDescription(element.getComment());
		l.setEditeur(element.getEditor());
		l.setSerie(element.getSerie());
		l.setTitre(element.getTitle());
		parent.addElement(element.getTypeEl(),l);
		element.visible(false);
	}
	public void visible() {
		element.visible(true);
	}
	/*
	 * defined the category from a string
	 */
	public void initCategory(String selectedCategory) {
		TypeEl l;
		if ( selectedCategory.equals( "CD")){		
			l=TypeEl.CD;
		}else if( selectedCategory.equals("Livre"))
			l=TypeEl.LIVRE;
		else if( selectedCategory.equals("DVD" ))
			l=TypeEl.DVD;
		else if( selectedCategory.equals("Jeux" ))
			l=TypeEl.JEUX;
		else
			l=TypeEl.BD;
		element.setTypeEl(l);
	}
}
