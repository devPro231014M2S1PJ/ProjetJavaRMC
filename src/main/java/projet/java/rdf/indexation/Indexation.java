package projet.java.rdf.indexation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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


public class Indexation {
	
	public ArrayList<LocalTree> listLocalTree=new ArrayList<LocalTree>();
	
	public Indexation(ArrayList<LocalTree> listLocalTree){
		this.listLocalTree=listLocalTree;
	}
	
	
	public void testAndIndex(boolean isFirtCall){
	    try {
			IndexWriter iWriter=creatOrCallIndexDirect(isFirtCall);
			for(LocalTree t:listLocalTree){
				if(!t.isIndexed)iWriter.addDocument(t.indexArbuste());
			}
			iWriter.close();
			
		}catch (IOException e) {e.printStackTrace();}	
	}
	
	
	private static void sup(File f){
    	File[] file=f.listFiles();
	    for(File fi:file)fi.delete();	    
    }

 
    private static IndexWriter creatOrCallIndexDirect(boolean isNewIndex) throws IOException{
		 File f=new File("C:/projetIndexation/agregatIndexation");
		 if(isNewIndex)sup(f);
		    Directory directory = FSDirectory.open(f);
		    IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, new StandardAnalyzer());
		 return(new IndexWriter(directory,config));
    }
	
	
}
