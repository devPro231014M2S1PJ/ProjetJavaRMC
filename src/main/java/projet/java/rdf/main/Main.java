package projet.java.rdf.main;

import java.util.regex.Pattern;

import projet.java.rdf.loadModel.Indexation;
import projet.java.rdf.loadModel.Models;
import projet.java.rdf.search.RdfGraph;
import projet.java.rdf.search.SearchInIndex;
import projet.java.rdf.search.SparqlQuiry;
import projet.java.rdf.window.RdfWindow;

public class Main {

/***********************************************************************************************************/
	
	public static String[] quiryParse(String query,String parser){
		
		Pattern p = Pattern.compile("("+parser+")+");
		return  p.split(query);
		
	}
/******************************************************************************************************/
    
	public static boolean compare(String s1,String s2){
    	
   	   if(s1.length()!=s2.length()) return (false);
   	 
   	   else {
   		 
   		  for(int i=0;i<s2.length();i++) if(s1.charAt(i)!=s2.charAt(i)) return false;
   		 
   		  return true;
   	 }
   	 
    } 
/**************************************************************************************************************/
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	   Models models=new Models("/home/amine/Bureau/dataset");
	   new Indexation(models,"/media/amine/6E5B-167C/Index/Dowload/indexLucen");
	   SparqlQuiry sparqlQuiry=new SparqlQuiry(models);
	   //sparqlQuiry.search("actor amine actor aziz ");
	   sparqlQuiry.test();

	   SearchInIndex searchInIndex=new SearchInIndex("/media/amine/6E5B-167C/Index/Dowload/indexLucen");
	   RdfGraph rdfGraph=new RdfGraph(models.getAllStingModels(true));
	   new RdfWindow(searchInIndex,models,rdfGraph);
       	}
	
	
}
