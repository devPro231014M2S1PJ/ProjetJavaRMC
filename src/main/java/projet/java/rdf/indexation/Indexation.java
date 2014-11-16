package projet.java.rdf.indexation;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.LiteralRequiredException;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import projet.java.rdf.model.ModelRDF;


public class Indexation {
	
	public LinkedList<ModelRDF> listModel=new LinkedList<ModelRDF>();
	
	public Indexation(LinkedList<ModelRDF>listModel){
		this.listModel=listModel;
	}
	
	public static void addtoAgregatindex(ModelRDF model) throws IOException{
		 File f=new File("C:/projetIndexation/agregatIndexation");
		    Directory directory = FSDirectory.open(f);
		    IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, new StandardAnalyzer());
		    IndexWriter iwriter=new IndexWriter(directory,config);
		    	for(Arbuste arbust: model.listArbustes){
		    		iwriter.addDocument(arbust.indexArbuste());
		    	}
		    iwriter.close();
	}
	
    public void createAgregatIndex() throws IOException{
	    File f=new File("C:/projetIndexation/agregatIndexation");
	    sup(f);
	    Directory directory = FSDirectory.open(f);
	    IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, new StandardAnalyzer());
	    IndexWriter iwriter=new IndexWriter(directory,config);
	    for(ModelRDF modelRdf:listModel){
	    
	    	for(Arbuste arbust: modelRdf.listArbustes){
	    		
	    		iwriter.addDocument(arbust.indexArbuste());
	    	}
	    }
	    iwriter.close();
    }
    
    
    public void sup(File f){
    	File[] file=f.listFiles();
	    for(File fi:file)fi.delete();
	}

    public void fullIndexation() throws IOException{
       File f=new File("C:/projetIndexation/fullIndexation");
  	    sup(f);
  	    Directory directory = FSDirectory.open(f);
  	    IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, new StandardAnalyzer());
  	    IndexWriter iwriter=new IndexWriter(directory,config);
  	    for(ModelRDF model:listModel){
  	       Statement stmt; // le triple
  	  	   Resource  subject,predicat; 
  	  	   Literal literal;
  	  	   StmtIterator stmtIter=model.model.listStatements();
  	  	   while(stmtIter.hasNext()){
  		       stmt=stmtIter.next();	
  		       subject=stmt.getSubject();
  		       predicat=stmt.getPredicate();
  		       try{ // si le model contient un literal
  		           literal=stmt.getLiteral();
  		           Document doc=new Document();
  		           doc.add(new Field("sujet", subject.toString(),TextField.TYPE_STORED));
  		           doc.add(new Field("predicat", predicat.getLocalName(),TextField.TYPE_STORED));
  		           doc.add(new Field("literal", literal.toString(),TextField.TYPE_STORED));
  		       }catch(LiteralRequiredException e){}
  		   }
  	    }
  	    iwriter.close();
   }
    
    public static void addToFullIndexation(ModelRDF model) throws IOException  {
        File f=new File("C:/projetIndexation/fullIndexatio");
   	    Directory directory = FSDirectory.open(f);
   	    IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, new StandardAnalyzer());
   	    IndexWriter iwriter=new IndexWriter(directory,config);
   	       Statement stmt; // le triple
   	  	   Resource  subject,predicat; 
   	  	   Literal literal;
   	  	   StmtIterator stmtIter=model.model.listStatements();
   	  	   while(stmtIter.hasNext()){
   		       stmt=stmtIter.next();	
   		       subject=stmt.getSubject();
   		       predicat=stmt.getPredicate();
   		       try{ // si le model contient un literal
   		           literal=stmt.getLiteral();
   		           Document doc=new Document();
   		           doc.add(new Field("sujet", subject.toString(),TextField.TYPE_STORED));
   		           doc.add(new Field("predicat", predicat.getLocalName(),TextField.TYPE_STORED));
   		           doc.add(new Field("literal", literal.toString(),TextField.TYPE_STORED));
   		          }catch(LiteralRequiredException e){}
   		   }
   	    iwriter.close();
    	
    }
}
