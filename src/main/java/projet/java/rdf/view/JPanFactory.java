package projet.java.rdf.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JPanFactory extends JPanel{
	private int width;
	private int height;

	public JPanFactory(Color couleur, int width, int height) {
		this.setBackground(couleur);
		this.width = width;
		this.height = height;
		this.setLayout(new BorderLayout()); 
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}   

}
