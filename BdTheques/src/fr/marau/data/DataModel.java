package fr.marau.data;



import javax.swing.table.AbstractTableModel;

public class DataModel extends AbstractTableModel {

	/**
	 * 
	 */

	private String[] columnNames = { "Titre", "Auteur", "Serie",
			"Description Commentaire", "Editeur" };
	private static final long serialVersionUID = 1L;
	private CategoryLudotheque lstEl;

	public DataModel(CategoryLudotheque lst) {
		lstEl = lst;
	}
	@Override
	public int getColumnCount() {
		return 5;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getRowCount() {
		return lstEl.getLudo().size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		String ret = "";
		ElementLudotheque el = (ElementLudotheque) lstEl.getLudo().get(arg0);
		switch (arg1) {
		case 0:
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
	
	public void setValueAt(int arg0, int arg1){
		
	}
	public void modify(CategoryLudotheque lst) {
		lstEl = lst;
		fireTableDataChanged();
	}
}
