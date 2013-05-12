package fr.marau.gui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.marau.data.DataModel;

/**
 * Main gui for application
 * @author Christophe Rivier
 *
 */
public class GuiMainApp extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JPanel jpan
	public GuiMainApp(){
		JTable tableau = new JTable(new DataModel());
		 
        add(new JScrollPane(tableau), BorderLayout.CENTER);
		
	}
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	public static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Marau");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Add contents to the window.
		GuiMainApp win = new GuiMainApp();
		frame.add(win);
		final int defaultWidth = 800;
		final int defaultHeight = 200;
		frame.setSize(defaultWidth, defaultHeight);
		frame.setLocationRelativeTo(null);

		URL imgPath = win.getClass().getClassLoader()
				.getResource("fr/marau/gui/velo2.jpg");
		Image img = Toolkit.getDefaultToolkit().getImage(imgPath);
		frame.setIconImage(img);

		// Display the window.
		frame.setVisible(true);
	}
}
