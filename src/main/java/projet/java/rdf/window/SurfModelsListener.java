package projet.java.rdf.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import projet.java.rdf.loadModel.Models;
// cette class reagie au evenement pour le parcour des donné rdf
public class SurfModelsListener implements ActionListener{
/********************************************************************************************************************/
	private JButtonFactory buttPrecedent,buttSuivant;
	private Models models;
	private DefaultTableModel arrayOfData;
	
    public SurfModelsListener(JButtonFactory buttPrecedent,JButtonFactory buttSuivant,
    		                  Models models, DefaultTableModel arrayOfData) {
    	
		// TODO Auto-generated constructor stub
    	this.buttPrecedent=buttPrecedent;
    	this.buttSuivant=buttSuivant;
    	
    	this.models=models;
    	this.arrayOfData=arrayOfData;
    	this.putTriplet(this.models.current());
    	
    	this.buttSuivant.addActionListener(this);
    	this.buttPrecedent.addActionListener(this);
    	
	}
/****************************************************************************************************************/

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(this.buttPrecedent)) this.putTriplet(models.previous());
		if(arg0.getSource().equals(this.buttSuivant))   this.putTriplet(models.next());
		
	}

/****************************************************************************************************************/
	// mettre les donne dans le "DefaultTableModel" pour etre afficher
	private void putTriplet(ArrayList<String[]> triplets){
		String[] triplet;
	   int lenght=this.arrayOfData.getRowCount(); 
	   for(int i=0;i<lenght;i++)this.arrayOfData.removeRow(0);
	   Iterator<String[]> iter=triplets.iterator();
	   while (iter.hasNext()){
		   triplet=iter.next();
		   this.arrayOfData.addRow(triplet);
		   
	   }
	
	  
	}
/*****************************************************************************************************************/
}
