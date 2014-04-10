package fr.marau.gui;

import java.io.IOException;
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fr.marau.data.CategoryLudotheque;
import fr.marau.data.DataModel;
import fr.marau.data.Ludotheque;

public class PresentorApp implements ActionListener{

	private GuiMainApp gui;
	private Ludotheque lst;
	private PresentorElBD presEl = new PresentorElBD();

	public PresentorApp(GuiMainApp guiMainApp) {
		gui = guiMainApp;
	}
	public void initLudotheque(Properties properties) {
		lst = new Ludotheque(properties);
	}
	/*
	 * Get the list of the ludotheque ; used to save data in file
	 */
	public Ludotheque getLudo() {
		return lst;
	}

	public void loadLudoFromFile() throws SAXException, IOException, ParserConfigurationException{
		lst.getLstFromXml();
	}
	public void Load() {
		gui.setBtnVisible(true);
		try {
			loadLudoFromFile();
			//lst.getLstFromXml();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for( CategoryLudotheque p : lst.lst){
			gui.addDataModel(p.getCategory(),new DataModel(p));
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		presEl.initCategory(gui.getSelectedCategory());
		presEl.visible();
	}
	
}
