package projet.java.rdf.search;

import java.util.ArrayList;

import projet.java.rdf.loadModel.Models;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.LiteralRequiredException;

public class SparqlQuiry {
	
  private Models models;
  private final String prefix=
		     "prefix rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
		     +"prefix dbpedia:<http://dbpedia.org/property/>"
		     +"prefix skos:<http://www.w3.org/2004/02/skos/core#>"
		     +"prefix oddlinker:<http://data.linkedmdb.org/resource/oddlinker/>"
		     +"prefix db:<http://data.linkedmdb.org/resource/>"
		     +"prefix movie:<http://data.linkedmdb.org/resource/movie/>"
		     +"prefix rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
		     +"prefix foaf:<http://xmlns.com/foaf/0.1/>"
		     +"prefix d2r:<http://sites.wiwiss.fu-berlin.de/suhl/bizer/d2r-server/config.rdf#>"
		     +"prefix owl:<http://www.w3.org/2002/07/owl#>"
		     +"prefix dc:<http://purl.org/dc/terms/>"
		     +"prefix xsd:<http://www.w3.org/2001/XMLSchema#>"
		     +"prefix rdfs:<http://www.w3.org/2000/01/rdf-schema#>";

  
  public SparqlQuiry(Models models){
	this.models=models;
  }
  
  public String getResult(ArrayList<String> result){
	  String s="";
   //   String s="http://data.linkedmdb.org/resource/film/1";
	  String queryString =this.prefix+ " SELECT  ?k  "
                         + " WHERE { ?in_class ?s ?k."
                         + "<"+result.get(1)+"> ?t ?in_class."
                         + " ?in_class rdf:type <"+result.get(0)+">. } ";
	  Query query = QueryFactory.create(queryString);
	  QueryExecution qe = QueryExecutionFactory.create(query, models.getGlobalModel());
	  ResultSet results = qe.execSelect();
	  while(results.hasNext()){
		  QuerySolution qs=results.next();
		  try{
		  s+="\n\n"+qs.get("k").asLiteral().asLiteral().toString();
		  }catch(LiteralRequiredException e){}
	   }
	  qe.close();
	  return s;
      }
   
  }
  
  

