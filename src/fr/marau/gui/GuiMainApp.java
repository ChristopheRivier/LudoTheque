package fr.marau.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JViewport;

import fr.marau.data.DataModel;
import fr.marau.data.Ludotheque;
import fr.marau.data.TypeEl;
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
	private JButton btn = new JButton("Add Line");
	private JButton btnModif = new JButton("Modif line");
	private JPanel p = new JPanel();    
	private PresentorApp pres;

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
		pres = new PresentorApp(this);
		pres.initLudotheque(properties);

		btn.setVisible(true);
		btn.addActionListener(pres);
		
		this.setLayout(new BorderLayout());
		p.setLayout(new BorderLayout());
		p.setVisible(false);
		p.add(btn, BorderLayout.EAST);

		btnModif.setVisible(true);
		btnModif.addActionListener(pres);
		
		p.add(btnModif, BorderLayout.WEST);
		add(p, BorderLayout.NORTH);
		tabbedpane.setTabPlacement(JTabbedPane.TOP);
		tabbedpane.setForeground(Color.BLUE);

		add(tabbedpane, BorderLayout.CENTER);
	}

	public Ludotheque getLudo() {
		return pres.getLudo();
	}

	public void Load(){
		pres.Load();
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
		item1 = new MenuItem("Load");
		item1.setActionCommand("Load");
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
				}else if ( arg0.getActionCommand().equals("Load")){
					win.Load();
				}
			}
		});
		
		test.add(menu1);
		frame.setMenuBar(test);
	}

	public void setBtnVisible(boolean b) {
		p.setVisible(b);	
	}

	public void addDataModel(String category, DataModel tmp) {
		lstModel.add( tmp );
		tabbedpane.addTab(category,createTable(tmp));
	}
	
	public String getSelectedCategory(){
		return tabbedpane.getTitleAt(tabbedpane.getSelectedIndex());
	}

	public DataModel getDataModel(TypeEl typeEl) {
		String type = new String();
		switch(typeEl){
		case CD:
			type = "CD";
			break;
		case DVD:
			type="DVD";
			break;
		case LIVRE:
			type="Livre";
			break;
		case JEUX:
			type="Jeux";
			break;
		case BD:
			type="BD";
			break;
		}
		tabbedpane.setSelectedIndex(tabbedpane.indexOfTab(type));
		
		JScrollPane t = (JScrollPane) tabbedpane.getComponent(tabbedpane.indexOfTab(type));
		JViewport view = t.getViewport();
		JTable f = (JTable)view.getView();
		DataModel model = (DataModel)f.getModel();
		
		return model;
	}
}
