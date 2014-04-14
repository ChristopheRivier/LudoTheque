package fr.marau.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fr.marau.data.TypeEl;

public class GuiElementBD extends JFrame implements IElementBiblio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton btn = new JButton("Save");

	private ButtonGroup bg = new ButtonGroup();

	private JRadioButton brBD = new JRadioButton("BD");
	private JRadioButton brCD = new JRadioButton("CD");
	private JRadioButton brDVD = new JRadioButton("DVD");
	private JRadioButton brLivre = new JRadioButton("Livre");
	private JRadioButton brJeux = new JRadioButton("Jeux");

	
	private JTextField txtTitle = new JTextField();
	private JTextField txtAutor = new JTextField();
	private JTextField txtSerie = new JTextField();
	private JTextField txtEditor = new JTextField();
	private JTextField txtComment = new JTextField();

	private PresentorElBD pres;

	GuiElementBD(String title) {
		//super(title);
		super();
		setTitle(title);
		init();
	}

	void init() {
		JFrame t = this;
		
		t.setBounds(32, 32, 300, 200);
		
		t.setLayout(new GridLayout(0,1));
		// addWindowListener(closeWindow);
		JPanel panBtn = new JPanel();
		panBtn.setLayout(new GridLayout(1, 0));
		// ajout des boutons radio dans le groupe bg
		bg.add(brBD);
		bg.add(brCD);
		bg.add(brDVD);
		bg.add(brLivre);
		bg.add(brJeux);
		panBtn.add(brBD);
		panBtn.add(brCD);
		panBtn.add(brDVD);
		panBtn.add(brLivre);
		panBtn.add(brJeux);
		t.add(panBtn);
		
		JPanel panTitle = new JPanel();
		panTitle.setLayout(new GridLayout(1,0));
		JLabel lblTitle = new JLabel("Titre:");
		panTitle.add(lblTitle);
		panTitle.add(txtTitle);
		t.add( panTitle);

		JPanel panAutor = new JPanel();
		panAutor.setLayout(new GridLayout(1,0));
		JLabel lblAutor = new JLabel("Autor:");
		panAutor.add(lblAutor);
		panAutor.add(txtAutor);
		t.add( panAutor);

		JPanel panSerie = new JPanel();
		panSerie.setLayout(new GridLayout(1,0));
		JLabel lblSerie = new JLabel("Serie:");
		panSerie.add(lblSerie);
		panSerie.add(txtSerie);
		t.add( panSerie);

		JPanel panComment = new JPanel();
		panComment.setLayout(new GridLayout(1,0));
		JLabel lblComment = new JLabel("Comment:");
		panComment.add(lblComment);
		panComment.add(txtComment);
		t.add( panComment);

		JPanel panEditor = new JPanel();
		panEditor.setLayout(new GridLayout(1,0));
		JLabel lblEditor = new JLabel("Editor:");
		panEditor.add(lblEditor);
		panEditor.add(txtEditor);
		t.add( panEditor);

		t.add(btn);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pres.sauvElement();
			}
		});
	}
	/* (non-Javadoc)
	 * @see fr.marau.gui.IElementBiblio#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle( String title ){
		txtTitle.setText(title);
	}
	/* (non-Javadoc)
	 * @see fr.marau.gui.IElementBiblio#getTitle()
	 */
	@Override
	public String getTitle(){
		return txtTitle.getText();
	}
	/* (non-Javadoc)
	 * @see fr.marau.gui.IElementBiblio#setAutor(java.lang.String)
	 */
	@Override
	public void setAutor( String Autor ){
		txtAutor.setText( Autor );
	}
	/* (non-Javadoc)
	 * @see fr.marau.gui.IElementBiblio#getAutor()
	 */
	@Override
	public String getAutor(){
		return txtAutor.getText();
	}
	/* (non-Javadoc)
	 * @see fr.marau.gui.IElementBiblio#setComment(java.lang.String)
	 */
	@Override
	public void setComment( String comment ){
		txtComment.setText(comment);
	}
	/* (non-Javadoc)
	 * @see fr.marau.gui.IElementBiblio#getComment()
	 */
	@Override
	public String getComment(){
		return txtComment.getText();
	}
	/* (non-Javadoc)
	 * @see fr.marau.gui.IElementBiblio#setSerie(java.lang.String)
	 */
	@Override
	public void setSerie( String serie ) {
		txtSerie.setText( serie );
	}
	/* (non-Javadoc)
	 * @see fr.marau.gui.IElementBiblio#getSerie()
	 */
	@Override
	public String getSerie(){
		return txtSerie.getText();
	}
	/* (non-Javadoc)
	 * @see fr.marau.gui.IElementBiblio#setEditor(java.lang.String)
	 */
	@Override
	public void setEditor(String editor){
		txtEditor.setText(editor);
	}
	/* (non-Javadoc)
	 * @see fr.marau.gui.IElementBiblio#getEditor()
	 */
	@Override
	public String getEditor(){
		return txtEditor.getText();
	}
	/* (non-Javadoc)
	 * @see fr.marau.gui.IElementBiblio#setType(fr.marau.data.TypeEl)
	 */
	@Override
	public void setTypeEl(TypeEl el){
		switch (el) {
		case CD:
			brCD.setSelected(true);
			break;
		case BD:
			brBD.setSelected(true);
			break;
		case LIVRE:
			brLivre.setSelected(true);
			break;
		case JEUX:
			brJeux.setSelected(true);
			break;
		case DVD:
			brDVD.setSelected(true);
			break;
		default:
			brBD.setSelected(true);
			break;
		}
		
	}
	/* (non-Javadoc)
	 * @see fr.marau.gui.IElementBiblio#getTypeEl()
	 */
	@Override
	public TypeEl getTypeEl(){
		TypeEl ret = TypeEl.BD;
		if( brCD.isSelected())
			ret = TypeEl.CD;
		else if( brDVD.isSelected() )
			ret = TypeEl.DVD;
		else if( brLivre.isSelected() )
			ret = TypeEl.LIVRE;
		else if( brJeux.isSelected() )
			ret = TypeEl.JEUX;
		else
			ret = TypeEl.BD;
		return ret;
	}

	public void setPresentor(PresentorElBD presentorEl) {
		pres = presentorEl;
	}
	
	@Override
	public void visible(boolean b){
		this.setVisible(b);
	}

}
