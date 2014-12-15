package projet.java.rdf.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

///import projet.java.rdf.Search.RdfGraph;
//import projet.java.rdf.loadModel.Indexation;


public class SearchListener implements ActionListener{
	 
	 private /*JButtonFactory*/JButton buttSearch;
	 private JTextField zoneSearch;
	 private DefaultTableModel arrayOfData;
	 //private Indexation index;
	 //private RdfGraph rdfGraph;
	
/*******************************************************************************************************/	 
	 public SearchListener(/*JButtonFactory*/JButton buttSearch, JTextField zoneSearch//,
			              /* Indexation index, DefaultTableModel arrayOfData,RdfGraph rdfGraph*/)  {
		// TODO Auto-generated constructor stub
		this.buttSearch=buttSearch;
	//	this.index=index;
		this.zoneSearch=zoneSearch;
		this.arrayOfData=arrayOfData;
		this.buttSearch.addActionListener(this);
	//	this.rdfGraph=rdfGraph;
	 }
	
/*******************************************************************************************************/
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String quiry=this.zoneSearch.getText();
		if(quiry.length()>2){
		ArrayList<String> results=null;//=this.index.searchInIndex(quiry);
		this.putResultOfSearch(results);
//		this.rdfGraph.searchTreatment(results);
		
		}
		
	}
/******************************************************************************************************/
	
	private void putResultOfSearch(ArrayList<String> results){
		
		 int lenght=this.arrayOfData.getRowCount(); 
	     for(int i=0;i<lenght;i++)this.arrayOfData.removeRow(0);
	     
	     for(String sResult:results){
	    	 this.arrayOfData.addRow(new Object[]{sResult});
	     }
	}
/**********************************************************************************************************/
}

