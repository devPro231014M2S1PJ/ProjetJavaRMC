package projet.java.rdf.model;

import java.io.IOException;

import projet.java.rdf.indexation.Indexation;
import projet.java.rdf.view.ControlListner;
import projet.java.rdf.view.RdfWindow;
import projet.java.rdf.graph.GlobalGraph;

public class Main {
	
	public static void pf(String s,boolean rl){
		if(rl)System.out.println(s);else System.out.println(s);
	}


	public static void main(String[] args) throws IOException {

		ModelCollection modelCollection=new  ModelCollection(); 
		// cr�ation de la liste des mod�le
		modelCollection.addRDFsFile("/home/amine/Bureau/jeuxDeDonner"); 
		// cr�ation d'un mod�le pour tous les fichiers RDF d'un dossier 
		
		Indexation index=new Indexation(modelCollection.listelocalTree);
	    index.testAndIndex(true);
	    
	    GlobalGraph g=new GlobalGraph(modelCollection.listelocalTree);
	    g.display();
	    
		RdfWindow window=new RdfWindow(g.v);  
		new ControlListner(window,modelCollection,index,g);
		window.Display();
	}
}
