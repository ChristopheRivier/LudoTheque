package fr.marau.gui;

import fr.marau.data.TypeEl;

public interface IElementBiblio {

	public abstract void setPresentor(PresentorElBD presentorEl) ;

	public abstract void setTitle(String title);

	public abstract String getTitle();

	public abstract void setAutor(String Autor);

	public abstract String getAutor();

	public abstract void setComment(String comment);

	public abstract String getComment();

	public abstract void setSerie(String serie);

	public abstract String getSerie();

	public abstract void setEditor(String editor);

	public abstract String getEditor();

	public abstract void setTypeEl(TypeEl el);

	public abstract TypeEl getTypeEl();

	public abstract void visible();

}