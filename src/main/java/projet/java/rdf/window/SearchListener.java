package projet.java.rdf.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import projet.java.rdf.main.Main;


public class SearchListener implements ActionListener{
	 
	 private JButtonFactory buttSearch;
	 private JTextField zoneSearch;
	 private DefaultTableModel arrayOfData;
	 //private SearchInIndex searchInIndex;
	// private RdfGraph rdfGraph;
	 private JTextArea jTextArea;
	 private Main main;
	
/*******************************************************************************************************/	 
	 public SearchListener(JButtonFactory buttSearch, JTextField zoneSearch,JTextArea jTextArea,
			               Main main, DefaultTableModel arrayOfData)  {
		// TODO Auto-generated constructor stub
		this.buttSearch=buttSearch;
		this.main=main;
		this.zoneSearch=zoneSearch;
		this.arrayOfData=arrayOfData;
		this.buttSearch.addActionListener(this);
		this.jTextArea=jTextArea;
	 }
	
/*******************************************************************************************************/
	public void actionPerformed(ActionEvent arg0) {
		//actor vampire
	  ArrayList<String> result=main.searchInIndex.searchManager(zoneSearch.getText());
	  main.rdfGraph.searchTreatment(result);
      jTextArea.setText(main.sparqlQuery.getResult(result));
		
	}
/******************************************************************************************************/
	
	@SuppressWarnings("unused")
	private void putResultOfSearch(ArrayList<String> results){
		
		 int lenght=this.arrayOfData.getRowCount(); 
	     for(int i=0;i<lenght;i++)this.arrayOfData.removeRow(0);
	     
	     for(String sResult:results){
	    	 this.arrayOfData.addRow(new Object[]{sResult});
	     }
	}
/**********************************************************************************************************/
}
