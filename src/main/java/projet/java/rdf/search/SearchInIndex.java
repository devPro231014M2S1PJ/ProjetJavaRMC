package projet.java.rdf.search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import projet.java.rdf.main.Main;

public class SearchInIndex {
	
	private IndexSearcher indexSearcher;

/****************************************************************************************************/
	// recherhce dand l'index
	public SearchInIndex(String pathOfIndexDerectory){
		
		Directory directory;
		try {
			directory = FSDirectory.open(new File(pathOfIndexDerectory));
			@SuppressWarnings("deprecation")
			IndexReader indexReader = IndexReader.open(directory);
			this.indexSearcher = new IndexSearcher(indexReader);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
/*************************************************************************************************************/
	// decoupage de la requté d'enté en mot puis regroupement en fonction des class et predict trouvé
	 public ArrayList<String>  searchManager(String Query){
		  int j=0;
		  boolean lastIsClass=true;
		  ArrayList<String> responce=new ArrayList<String>();
		  String[] subWordQuery=Main.quiryParse(Query, " ");
		  
		  for(int i=0;i<subWordQuery.length;i++){
			  String result=this.getIfClass(subWordQuery[i]);
			  if(result!="*"){
				  responce.add(result); 
				  j++; 
				  lastIsClass=true;
			   }else{
				   if(lastIsClass){
					   responce.add(subWordQuery[i]); 
					   lastIsClass=false;
					  }else{
				       responce.set(j, responce.get(j)+" "+subWordQuery[i]);
					  }  
				   }
		 }
			 
         for(int i=0;i<responce.size();i++){
        	try{
        		if(!responce.get(i).substring(0,4).equals("http")) 
        			responce.set(i,this.getIfLitteral(responce.get(i)));
        		
        	}catch(StringIndexOutOfBoundsException e){
        		responce.set(i,this.getIfLitteral(responce.get(i)));
        	}    
         }
         
        return responce;
         
		 
	 }
	 // chercher si un mot correspond a une class 
	 private String getIfClass(String subWord){
		 ArrayList<String> result=this.searchInIndex(subWord, "class", "sujet", 1);
		 if(result.size()!=0) return result.get(0);else return"*";
		 
	 }
	 //chercher si un groupe de mot correspond a un litteral
	 private String getIfLitteral(String subWord){
		 ArrayList<String> result=this.searchInIndex(subWord, "literals", "sujet", 1);
		 if(result.size()!=0) return result.get(0);else return"*";
	 }
/***************************************************************************************************************/
	/* * @throws IOException 
	 * 
	 * @throws ParseException *********
	 * **********************************************************************************************/
	 
	 // fonction search dans l'index "prend en parametre des mot clé le champ dans lequel chercher et le champ a retourner"
	    public   final  ArrayList<String> searchInIndex(String quiry,String InField,String outField,int nResult) {
	    	
	    	ArrayList<String> result=new ArrayList<String>();
		
	    	try {
				
				QueryParser queryParser = new QueryParser(InField,new StandardAnalyzer());
			    Query query;
				
			    try {
			    	
					query = queryParser.parse(quiry);
					TopScoreDocCollector collector = TopScoreDocCollector.create(nResult, true);
					indexSearcher.search(query, collector);
					ScoreDoc[] hits = collector.topDocs().scoreDocs;
					System.out.println("Found " + hits.length + " hits.");
					String ls;
					for (int i = 0; i < hits.length; ++i) {
					     int docId = hits[i].doc;
					     Document d = indexSearcher.doc(docId);
					     ls=d.get(outField);
					     result.add(ls);
					}
				
			    } catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return result;
			
	    }
	    


}
