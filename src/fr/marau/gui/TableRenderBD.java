/**
 * 
 */
package fr.marau.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author rivierch
 * 
 */
public class TableRenderBD extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4896456425971787000L;

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if ( (row%2)==0 ){
			setBackground(Color.lightGray );
		}
		else
			setBackground( Color.white );
		setText((String)value );

		return this;
	}
}
