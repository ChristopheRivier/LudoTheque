package fr.marau.gui;

import fr.marau.data.ElementLudotheque;
import fr.marau.data.TypeEl;

public class PresentorElBD {
	private IElementBiblio element = null;
	private ElementLudotheque data = new ElementLudotheque();
	
	public PresentorElBD(IElementBiblio inter ){
		element = inter;
	}
	public PresentorElBD() {
		element = new GuiElementBD("test");
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
	public void getLine() {
		// TODO Auto-generated method stub
		
	}
	public void visible() {
		element.visible();
	}
	/*
	 * defined the category from a string
	 */
	public void initCategory(String selectedCategory) {
		TypeEl l;
		if ( selectedCategory == "CD"){
			l=TypeEl.CD;
		}else if( selectedCategory=="LIVRE")
			l=TypeEl.LIVRE;
		else if( selectedCategory=="DVD" )
			l=TypeEl.DVD;
		else if( selectedCategory=="JEUX" )
			l=TypeEl.JEUX;
		else
			l=TypeEl.BD;
		element.setTypeEl(l);
	}
}
