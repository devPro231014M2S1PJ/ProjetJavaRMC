package projet.java.rdf.indexation;

import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;

public class Arbuste {
	public Resource sujet;
	public ArrayList<Couple> couple=new ArrayList<Couple>();
	
    public Arbuste(Resource sujet,Resource predicat,Literal literal){
		this.sujet=sujet;
		couple.add(new Couple(predicat, literal));
	}
    
    public void addCouple(Resource predicat,Literal literal){
		couple.add(new Couple(predicat, literal));
	}

    public Document indexArbuste(){
	   String concatPredicat="";
	   String concatLiteral="";
	   for(Couple c:couple){
		   concatPredicat+=" "+c.predicat.getLocalName();
		   concatLiteral+=" "+c.literal.toString();
	   }
		
	   Document document = new Document();
		document.add(new Field("sujet",this.sujet.toString() , TextField.TYPE_STORED));
		document.add(new Field("literal",concatLiteral,TextField.TYPE_STORED));
		document.add(new Field("predicat" ,concatPredicat, TextField.TYPE_STORED));
		return document;
   }

}
