package fr.marau.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fr.marau.data.CategoryLudotheque;
import fr.marau.data.DataModel;
import fr.marau.data.Ludotheque;
import fr.marau.file.CreateXMLFile;

/**
 * Main gui for application
 * 
 * @author Christophe Rivier
 * 
 */
public class GuiMainApp extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<DataModel> lstModel = new Vector<DataModel>();
	private JTabbedPane tabbedpane = new JTabbedPane();
	private Ludotheque lst;

	private JComponent createTable(DataModel aMod) {
		JTable tab = new JTable(aMod);
		tab.setPreferredScrollableViewportSize(new Dimension(500, 70));
		tab.setFillsViewportHeight(true);
		tab.setAutoscrolls(true);
		tab.setDefaultRenderer(Object.class, new TableRenderBD());
		JScrollPane scrollPane = new JScrollPane(tab);

		return scrollPane;
	}

	// private JPanel jpan
	public GuiMainApp(Properties properties) {
		lst = new Ludotheque(properties);
		JButton btn = new JButton("Load");
		this.setLayout(new BorderLayout());
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					lst.getLstFromXml();
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
					DataModel tmp = new DataModel(p);
					lstModel.add( tmp );
					tabbedpane.addTab(p.getCategory(),createTable(tmp));
				}
			}
		});
		add(btn, BorderLayout.NORTH);

		tabbedpane.setTabPlacement(JTabbedPane.TOP);
		tabbedpane.setForeground(Color.BLUE);

		add(tabbedpane, BorderLayout.CENTER);
	}

	public Ludotheque getLudo() {
		return lst;
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	public static void createAndShowGUI() {
		java.io.File file = new java.io.File("marau.properties");
		final java.util.Properties properties = new java.util.Properties();
		try {
			file.createNewFile();
			properties.load(new java.io.FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (properties.getProperty("XMLFile") == null || properties.getProperty("XMLFile").isEmpty()) {
			properties.setProperty("XMLFile", "test.xml");
		}

		// Create and set up the window.
		JFrame frame = new JFrame("Marau");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Add contents to the window.
		final GuiMainApp win = new GuiMainApp(properties);
		win.setOpaque(true); // content panes must be opaque
		frame.setContentPane(win);
		// GuiMainApp win = new GuiMainApp();
		// frame.add(win);
		final int defaultWidth = 800;
		final int defaultHeight = 400;
		frame.setSize(defaultWidth, defaultHeight);
		frame.setLocationRelativeTo(null);

		URL imgPath = win.getClass().getClassLoader()
				.getResource("fr/marau/gui/velo2.jpg");
		Image img = Toolkit.getDefaultToolkit().getImage(imgPath);
		frame.setIconImage(img);

		// Display the window.
		frame.setVisible(true);

		MenuBar test = new MenuBar();
		Menu menu1 = new Menu("Fichier");
		MenuItem item1 = new MenuItem("Ouvrir");
		item1.setActionCommand("Open");
		menu1.add(item1);
		item1 = new MenuItem("Save");
		item1.setActionCommand("save");
		menu1.add(item1);
		item1 = new MenuItem("Save As");
		item1.setActionCommand("saveAs");
		menu1.add(item1);
		menu1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getActionCommand().equals("save")) {
					new CreateXMLFile(win.getLudo(), properties.getProperty("XMLFile"), properties);
				} else if (arg0.getActionCommand().equals("Open")) {
					final JFileChooser fc = new JFileChooser();
					int returnVal = fc.showOpenDialog(win);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						properties.setProperty("XMLFile", file.getPath());
					}
				}else if (arg0.getActionCommand().equals("saveAs")){
					final JFileChooser fc = new JFileChooser();
					int returnVal = fc.showOpenDialog(win);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						new CreateXMLFile(win.getLudo(), file.getPath(), properties);
					}
				}
			}
		});
		test.add(menu1);
		frame.setMenuBar(test);
	}
}
