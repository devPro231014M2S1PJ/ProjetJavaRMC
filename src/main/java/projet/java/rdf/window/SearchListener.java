package projet.java.rdf.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import projet.java.rdf.search.RdfGraph;
import projet.java.rdf.search.SearchInIndex;


public class SearchListener implements ActionListener{
	 
	 private JButtonFactory buttSearch;
	 private JTextField zoneSearch;
	 private DefaultTableModel arrayOfData;
	 private SearchInIndex searchInIndex;
	 private RdfGraph rdfGraph;
	
/*******************************************************************************************************/	 
	 public SearchListener(JButtonFactory buttSearch, JTextField zoneSearch,
			               SearchInIndex searchInIndex, DefaultTableModel arrayOfData,RdfGraph rdfGraph)  {
		// TODO Auto-generated constructor stub
		this.buttSearch=buttSearch;
		this.searchInIndex=searchInIndex;
		this.zoneSearch=zoneSearch;
		this.arrayOfData=arrayOfData;
		this.buttSearch.addActionListener(this);
		this.rdfGraph=rdfGraph;
	 }
	
/*******************************************************************************************************/
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String quiry=this.zoneSearch.getText();
		if(quiry.length()>2){
		ArrayList<String> results=this.searchInIndex.searchInIndex(quiry,"class","sujet",20);
		this.putResultOfSearch(results);
		try{
		    this.rdfGraph.searchTreatment(results);
		}catch(IndexOutOfBoundsException e){
			System.out.println("aucun resultat");
			
		}
		
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
