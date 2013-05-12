package fr.marau.data;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class DataModel extends AbstractTableModel {
	private final String[] entetes = {"Titre", "Auteur", "Série", "Description", "Editeur"};  

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<ElementLudotheque> lstEl = new Vector<ElementLudotheque>();
	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return lstEl.size();
	}

	public String getColumnName(int columnIndex){
		return entetes[columnIndex];
	}
	@Override
	public Object getValueAt(int arg0, int arg1) {
		String ret ="";
		ElementLudotheque el = (ElementLudotheque) lstEl.get(arg0);
		switch( arg1 ){
		case 0 :
			ret = el.getTitre();
			break;
		case 1:
			ret = el.getAuteur();
			break;
		case 2:
			ret = el.getSerie();
			break;
		case 3:
			ret = el.getDescription();
			break;
		case 4:
			ret = el.getEditeur();
			break;		
		}
		return ret;
	}
}
