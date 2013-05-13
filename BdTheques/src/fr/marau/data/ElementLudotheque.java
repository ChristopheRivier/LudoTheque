package fr.marau.data;

public class ElementLudotheque {
	private String titre = null;
	private String serie = null;
	private String auteur = null;
	private String editeur = null;
	private String description = null;
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getSerie() {
		if( serie==null && titre!=null ){
			int i = titre.indexOf(" ");
			if( i>0 )
				serie = titre.substring(0,i);
		}
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getEditeur() {
		return editeur;
	}
	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
