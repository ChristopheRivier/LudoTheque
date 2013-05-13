/**
 * 
 */
package fr.marau.data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Christophe Rivier
 *
 */
public class CategoryLudotheque {
	
	private String _category;
	/**
	 * @return the _category
	 */
	public String getCategory() {
		return _category;
	}
	/**
	 * @param _category the _category to set
	 */
	public void setCategory(String _category) {
		this._category = _category;
	}
	private List<ElementLudotheque> _ludo;
	
	public List<ElementLudotheque> getLudo(){
		return _ludo;
	}
	/**
	 * 
	 */
	public CategoryLudotheque( String catName ) {
		_category = new String(catName);
		_ludo = new LinkedList<ElementLudotheque>();
	}

}
