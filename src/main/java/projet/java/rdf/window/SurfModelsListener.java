package projet.java.rdf.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
//import projet.java.rdf.loadModel.Models;

public class SurfModelsListener implements ActionListener{
/********************************************************************************************************************/
    private /*JButtonFactory*/JButton buttPrecedent,buttSuivant;
	//private Models models;
	private DefaultTableModel arrayOfData;
	
    public SurfModelsListener(/*JButtonFactory*/JButton buttPrecedent,/*JButtonFactory*/JButton buttSuivant//,
    		                  /*Models models, DefaultTableModel arrayOfData*/) {
    	
		// TODO Auto-generated constructor stub
//    	this.buttPrecedent=buttPrecedent;
  //  	this.buttSuivant=buttSuivant;
    	
    //	this.models=models;
    	this.arrayOfData=arrayOfData;
    //	this.putTriplet(this.models.current());
    	
    	this.buttSuivant.addActionListener(this);
    	this.buttPrecedent.addActionListener(this);
    	
	}
/****************************************************************************************************************/

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(this.buttPrecedent)) ;//this.putTriplet(models.previous());
		if(arg0.getSource().equals(this.buttSuivant))   ;//this.putTriplet(models.next());
		
	}

/****************************************************************************************************************/
	
	private void putTriplet(ArrayList<String[]> triplets){
	   
	   int lenght=this.arrayOfData.getRowCount(); 
	   for(int i=0;i<lenght;i++)this.arrayOfData.removeRow(0);
	   
	   for(String[] sTriple:triplets){
	      this.arrayOfData.addRow(new Object[]{sTriple[0],sTriple[1],sTriple[2]});
	   }
	}
/*****************************************************************************************************************/
}
