package projet.java.rdf.indexation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


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

 /**************************/
    private static IndexWriter creatOrCallIndexDirect(boolean isNewIndex) throws IOException{
		 File f=new File("/media/amine/6E5B-167C/Index/Dowload/indexLucen");
		 if(isNewIndex)sup(f);
		    Directory directory = FSDirectory.open(f);
		    IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, new StandardAnalyzer());
		 return(new IndexWriter(directory,config));
    }
	
	
}
