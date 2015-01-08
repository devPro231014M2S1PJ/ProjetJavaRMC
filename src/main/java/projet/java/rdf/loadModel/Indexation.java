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


public class Indexation {
	
/*********************************************************************************************/
	// inoration a indexé
	private ArrayList<InfoToIndex> infos_toIndex=new ArrayList<InfoToIndex>();
	// emplacement de l index
	private String pathOfIndexDerectory;
/**********************************************************************************************/
	public Indexation(Models models,String pathOfIndexDerectory){
		
		// recuperation des ressource sous forme de chane de carctére
		ArrayList<ArrayList<String[]>> sModels=models.getAllStingModels(false);
		
		this.pathOfIndexDerectory=pathOfIndexDerectory;
		// structuration des information, toute les info concérnat une ressource sont regroupé
		this.putInInfoToIndex(sModels);
		this.Index();
		
	
	}
/***********************************************************************************************/
	
	// organisation des information a indexé
	
	private final void putInInfoToIndex(ArrayList<ArrayList<String[]>> sModels){
		
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
	private final int getIndexOfResource(String resource){
	    int i=0;
	    
		for(InfoToIndex iti:this.infos_toIndex){
			if(iti.getResource().equals(resource)){
				return i;
			}
			i++;
		}
		return -1;
	}
/***************************************************************************************************************/
	// indexation  des information qui on été structuré ulterieurement
    private final void Index(){
	    try {
			IndexWriter iWriter=createIndex();
			for(InfoToIndex iti:this.infos_toIndex){
				iWriter.addDocument(iti.indexInfo());
			}
			iWriter.close();
			
		}catch (IOException e) {e.printStackTrace();}	
	}
	
	
/*******************************************************************************************************/
	// initialisation de l'index
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
