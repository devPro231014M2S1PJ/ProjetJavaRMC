package projet.java.rdf.main;

import java.util.regex.Pattern;

import projet.java.rdf.loadModel.Indexation;
import projet.java.rdf.loadModel.Models;
import projet.java.rdf.search.RdfGraph;
import projet.java.rdf.search.SearchInIndex;
import projet.java.rdf.search.SparqlQuiry;

public class Main {
	//class nécessaire pour le program
	public Models models;  // le model rdf 
	public SparqlQuiry sparqlQuery; // les createur de requete sparql
	public SearchInIndex searchInIndex; // la rechérche dans l'index
	public RdfGraph rdfGraph; /*"/home/amine/Bureau/trueDataSet"*/

	public Main(String pathDataSet,String pathIndex){
		// initialisation
		   this.models=new Models(pathDataSet);
		   new Indexation(models,pathIndex);
		   this.sparqlQuery=new SparqlQuiry(models);
		   this.searchInIndex=new SearchInIndex(pathIndex);
		   this.rdfGraph=new RdfGraph(models.getAllStingModels(true));
		 
	}

/***********************************************************************************************************/
	
	public static String[] quiryParse(String query,String parser){
		
		Pattern p = Pattern.compile("("+parser+")+");
		return  p.split(query);
		
	}
/******************************************************************************************************/
/*****************************************************************************************************/
    
}
