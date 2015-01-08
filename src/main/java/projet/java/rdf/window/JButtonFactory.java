package projet.java.rdf.window;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")

public class JButtonFactory extends JButton{
/**************************************************************************************************/	
	public JButtonFactory(String buttonName, Color fontCouleur, 
			                    Color backgroundCouleur, int width, int height){
		
		super();
		
		JLabel  buttLabel = new JLabel(buttonName);
		
		this.setForeground(fontCouleur);
		this.setBackground(backgroundCouleur);
		this.setPreferredSize(new Dimension(width, height)); 
		this.setBorder(new EmptyBorder(5,10,5,10));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(buttLabel);
    }
	
}
