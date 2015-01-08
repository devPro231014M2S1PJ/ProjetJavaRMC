package projet.java.rdf.window;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JPanFactory extends JPanel{
	   
	   private Dimension dim;
/*************************************************************************************/
	   public JPanFactory(Color couleur, int width, int height) {
	      
		  this.setBackground(couleur);
	      this.dim=new Dimension(width,height);
	      this.setLayout(new BorderLayout());
	      this.setSize(dim);
	   }
	   
/***************************************************************************************/
	public void setSize(int width,int height) {
		this.dim.height=height;
		this.dim.width=width;
	  }   

}
