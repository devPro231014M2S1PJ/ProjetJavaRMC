package projet.java.rdf.search_in_index;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

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

import projet.java.rdf.model.Main;

public class SearchInIndex {

	private String query;
	private ArrayList<String> parsedQuery=new ArrayList<String>();
	
	public ArrayList<String>search(String query,DefaultTableModel table) throws IOException, ParseException {
		
		ArrayList<String> a = searchIn("literal",false, query, table);
        ArrayList<String> b = searchIn("predicat",true,query, table);
        a.addAll(b);
        return a;  	
	}

	private ArrayList<String> searchIn(String field, boolean istoAdd, String query,DefaultTableModel table) throws IOException, ParseException {
		ArrayList<String> s=new ArrayList<String>();
		this.query=query;
		
		if(!istoAdd){
		 this.parseQuery();
		 int v=table.getRowCount();
		 for(int i=0;i<v;i++)table.removeRow(0);
		}
		
		Directory directory = FSDirectory.open(new File(Main.configDirectoryIndex));
		@SuppressWarnings("deprecation")
		
		IndexReader indexReader = IndexReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		QueryParser queryParser = new QueryParser(field,new StandardAnalyzer());
		Query queryL = queryParser.parse(this.query);
		
		TopScoreDocCollector collector = TopScoreDocCollector.create(20, true);
		indexSearcher.search(queryL, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;

		System.out.println("Found " + hits.length + " hits.");
        String ls;
		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			Document d = indexSearcher.doc(docId);
			ls=d.get("sujet");
			table.addRow(new Object[]{ls});
			s.add(ls);
		}
		return s;
	}

	
	public void parseQuery() {
		Pattern pattern = Pattern.compile("[[a-z][A-Z][0-9]]+");
		Matcher matcher = pattern.matcher(this.query);
		while(matcher.find()){
		    //System.out.println(matcher.group());
			this.parsedQuery.add(matcher.group());
		}
	}

}
