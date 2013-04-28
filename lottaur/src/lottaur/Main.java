package lottaur;

import java.awt.EventQueue;

import lottaur.webmail.WebmailApp;
import lottaur.webmail.presenter.pres_LotTaur;
import lottaur.webmail.presenter.pres_def_serveur;

/*
 * Main.java
 *
 * Created on 8 f�vrier 2006, 15:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 * 
 * @author CRivier
 */
public class Main {
	static Splash sp = null;
	static WebmailApp web = null;
	static Thread th2 = null;

	/** Creates a new instance of Main */
	public Main() {
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		sp = new Splash(10);
		sp.run();

		final pres_LotTaur def = new pres_def_serveur();
		def.CreateView();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				sp.dispose();
				def.setVisibleView();
			}
		});
	}
}
