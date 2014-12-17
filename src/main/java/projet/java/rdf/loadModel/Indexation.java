package projet.java.rdf.loadModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import projet.java.rdf.main.Main;


public class Indexation {
	
/*********************************************************************************************/
	private ArrayList<InfoToIndex> infos_toIndex=new ArrayList<InfoToIndex>();
	private String pathOfIndexDerectory;
/**********************************************************************************************/
	public Indexation(Models models,String pathOfIndexDerectory){
		
		
		ArrayList<ArrayList<String[]>> sModels=models.getAllStingModels(false);
		
		this.pathOfIndexDerectory=pathOfIndexDerectory;
		this.putInInfoToIndex(sModels);
		this.Index();
		
	
	}
/***********************************************************************************************/
	
	private void putInInfoToIndex(ArrayList<ArrayList<String[]>> sModels){
		
		int position;
		
		for(ArrayList<String[]> sModel:sModels){
			
			for(String[] sTriplet:sModel){
	
				position=this.getIndexOfResource(sTriplet[0]);
				
				if(position==-1){
					
					InfoToIndex iti=new InfoToIndex(sTriplet[0]);
				
					this.infos_toIndex.add(iti);
					
					iti.addInInfoToindex(sTriplet[1], sTriplet[2]);
					
				}else
					
					this.infos_toIndex.get(position).addInInfoToindex(sTriplet[1], sTriplet[2]);
			}
		}
	}

/***********************************************************************************************************/	
	private int getIndexOfResource(String resource){
	    int i=0;
	    
		for(InfoToIndex iti:this.infos_toIndex){
			if(Main.compare(iti.getResource(),resource)){
				return i;
			}
			i++;
		}
		return -1;
	}
/***************************************************************************************************************/
	
    private void Index(){
	    try {
			IndexWriter iWriter=createIndex();
			for(InfoToIndex iti:this.infos_toIndex){
				iWriter.addDocument(iti.indexInfo());
			}
			iWriter.close();
			
		}catch (IOException e) {e.printStackTrace();}	
	}
	
	
/*******************************************************************************************************/
	
    private final IndexWriter createIndex() throws IOException{
    	
			 File f=new File(this.pathOfIndexDerectory);
			 Indexation.sup(f);
			 Directory directory = FSDirectory.open(f);
			 IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, new StandardAnalyzer());
			 
			 return(new IndexWriter(directory,config));
	}
    
/******************************************************************************************************/	
	
    private static final void sup(File f){
	    	
    	    File[] file=f.listFiles();
		    for(File fi:file)fi.delete();	    
    }
    

}
