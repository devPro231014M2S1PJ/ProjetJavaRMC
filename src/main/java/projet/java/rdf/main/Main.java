package projet.java.rdf.main;

import java.util.regex.Pattern;

import projet.java.rdf.loadModel.Indexation;
import projet.java.rdf.loadModel.Models;
import projet.java.rdf.search.RdfGraph;
import projet.java.rdf.search.SearchInIndex;
import projet.java.rdf.search.SparqlQuiry;

public class Main {
	public Models models;
	public SparqlQuiry sparqlQuery;
	public SearchInIndex searchInIndex;
	public RdfGraph rdfGraph; /*"/home/amine/Bureau/trueDataSet"*/

	public Main(String pathDataSet,String pathIndex){
		
		   this.models=new Models(pathDataSet);
		   new Indexation(models,pathIndex);
		   this.sparqlQuery=new SparqlQuiry(models);
		   //sparqlQuiry.search("actor amine actor aziz ");

		   this.searchInIndex=new SearchInIndex(pathIndex);
		   this.rdfGraph=new RdfGraph(models.getAllStingModels(true));
		 
	}

/***********************************************************************************************************/
	
	public static String[] quiryParse(String query,String parser){
		
		Pattern p = Pattern.compile("("+parser+")+");
		return  p.split(query);
		
	}
/******************************************************************************************************/
    
	public static boolean compare(String s1,String s2){
    	
   	   if(s1.length()!=s2.length()) 
   		   return (false);
   	   else { 
   		  for(int i=0;i<s2.length();i++) 
   			  if(s1.charAt(i)!=s2.charAt(i)) return false;
   		  return true;
   	   }
    } 
/**************************************************************************************************************/
    
}
