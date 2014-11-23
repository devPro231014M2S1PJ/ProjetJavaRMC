package projet.java.rdf.indexation;


import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.graphstream.graph.Node;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;

public class LocalTree { 
	public Node n;
	public Resource resource;
	public ArrayList<CoupleLiteral> couple=new ArrayList<CoupleLiteral>();
	public ArrayList<CoupleResource> coupleResc=new ArrayList<CoupleResource>();
	public boolean isIndexed=false;
	public boolean parcoured=false;
	public boolean pointed=false;
	
	public LocalTree(Resource resource){
		this.resource=resource;
		//System.out.println(resource);
	}
    
    public void addCouple(Resource predicat,Literal literal){
		couple.add(new CoupleLiteral(predicat, literal));
	}
    
    public void addCoupleResc(Resource predicat,LocalTree localtree){
    	CoupleResource cr=new CoupleResource(predicat, localtree);
		coupleResc.add(cr);
	}
	
    public Document indexArbuste(){
	   String concatPredicat="";
	   String concatLiteral="";
	   for(CoupleLiteral c:couple){
		   concatPredicat+=" "+c.predicat.getLocalName();
		   concatLiteral+=" "+c.literal.toString(); 
		 }
	   for(CoupleResource cr:coupleResc){
		   concatPredicat+=" "+cr.predicat.getLocalName();
	   }
		Document document = new Document();
		document.add(new Field("sujet",this.resource.toString() , TextField.TYPE_STORED));
		document.add(new Field("literal",concatLiteral,TextField.TYPE_STORED));
		document.add(new Field("predicat" ,concatPredicat, TextField.TYPE_STORED));
		return document;
   }
}
