package fr.marau.gui;

import fr.marau.data.ElementLudotheque;

public class PresentorElBD {
	private IElementBiblio element;
	private ElementLudotheque data = new ElementLudotheque();
	
	public PresentorElBD(IElementBiblio inter ){
		element = inter;
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
}
