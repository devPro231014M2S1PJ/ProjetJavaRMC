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
	/* * @throws IOException 
	 * 
	 * @throws ParseException *******************************************************************************************************/
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
	    
	 public void  searchManager(String Query){
		 String[] subWordQuery=Main.quiryParse(Query, " ");
		 
	 }
	 
	 
	 private String getIfClass(String subWord){
		 this.searchInIndex(subWord, "class", "sujet", 1);
		 return"";
	 }

}
