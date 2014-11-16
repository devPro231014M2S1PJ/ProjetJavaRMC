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

public class SearchInIndex {

	private String query;
	private ArrayList<String> parsedQuery=new ArrayList<String>();

	public void search(String query,DefaultTableModel table) throws IOException, ParseException {
		this.query=query;
		this.parseQuery();
		int v=table.getRowCount();

		for(int i=0;i<v;i++)table.removeRow(0);

		Directory directory = FSDirectory.open(new File("C:/projetIndexation/agregatIndexation"));

		@SuppressWarnings("deprecation")
		IndexReader indexReader = IndexReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		QueryParser queryParser = new QueryParser("literal",new StandardAnalyzer());

		Query queryL = queryParser.parse(this.query);
		//TopDocs hits = indexSearcher.search(query, 1000);
		TopScoreDocCollector collector = TopScoreDocCollector.create(2, true);
		indexSearcher.search(queryL, collector);
		ScoreDoc[] hits = collector.topDocs().scoreDocs;

		System.out.println("Found " + hits.length + " hits.");

		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			Document d = indexSearcher.doc(docId);
			table.addRow(new Object[]{d.get("sujet")});
		}
	}

	public void parseQuery() {
		Pattern pattern = Pattern.compile("[[a-z][A-Z][0-9]]+");
		Matcher matcher = pattern.matcher(this.query);
		while(matcher.find()){
			System.out.println(matcher.group());
			this.parsedQuery.add(matcher.group());
		}
	}

}
