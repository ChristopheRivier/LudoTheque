package fr.marau.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class GuiElementBD extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	GuiElementBD(String title) {
		super(title);
		init();
	}

	void init() {
		setBounds(32, 32, 300, 200);
		setLayout(new GridLayout(0,1));
		// addWindowListener(closeWindow);
		JPanel panBtn = new JPanel();
		panBtn.setLayout(new GridLayout(1, 0));
		ButtonGroup bg = new ButtonGroup();
		JRadioButton br1 = new JRadioButton("BD");
		JRadioButton br2 = new JRadioButton("CD");
		JRadioButton br3 = new JRadioButton("DVD");
		JRadioButton br4 = new JRadioButton("Livre");
		JRadioButton br5 = new JRadioButton("Jeux");
		// ajout des boutons radio dans le groupe bg
		bg.add(br1);
		bg.add(br2);
		bg.add(br3);
		bg.add(br4);
		bg.add(br5);
		// test.add(bg);
		panBtn.add(br1);
		panBtn.add(br2);
		panBtn.add(br3);
		panBtn.add(br4);
		panBtn.add(br5);
		add(panBtn);
		
		JPanel panTitle = new JPanel();
		panTitle.setLayout(new GridLayout(1,0));
		JLabel lblTitle = new JLabel("Titre:");
		JTextField txtTitle = new JTextField();
		panTitle.add(lblTitle);
		panTitle.add(txtTitle);
		add( panTitle);

		JPanel panAutor = new JPanel();
		panAutor.setLayout(new GridLayout(1,0));
		JLabel lblAutor = new JLabel("Autor:");
		JTextField txtAutor = new JTextField();
		panAutor.add(lblAutor);
		panAutor.add(txtAutor);
		add( panAutor);

		JPanel panSerie = new JPanel();
		panSerie.setLayout(new GridLayout(1,0));
		JLabel lblSerie = new JLabel("Serie:");
		JTextField txtSerie = new JTextField();
		panSerie.add(lblSerie);
		panSerie.add(txtSerie);
		add( panSerie);

		JPanel panComment = new JPanel();
		panComment.setLayout(new GridLayout(1,0));
		JLabel lblComment = new JLabel("Comment:");
		JTextField txtComment = new JTextField();
		panComment.add(lblComment);
		panComment.add(txtComment);
		add( panComment);

		JPanel panEditor = new JPanel();
		panEditor.setLayout(new GridLayout(1,0));
		JLabel lblEditor = new JLabel("Editor:");
		JTextField txtEditor = new JTextField();
		panEditor.add(lblEditor);
		panEditor.add(txtEditor);
		add( panEditor);


	}

}
