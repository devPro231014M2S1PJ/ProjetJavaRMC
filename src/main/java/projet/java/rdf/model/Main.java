package projet.java.rdf.model;

import java.io.IOException;

import projet.java.rdf.indexation.Indexation;
import projet.java.rdf.view.ControlListner;
import projet.java.rdf.view.RdfWindow;

public class Main {


	public static void main(String[] args) throws IOException {

		ModelCollection modelCollection=new  ModelCollection(); // création de la liste des modèle
		modelCollection.addRDFsFile("C:/rdf/jeux_de_donnees",false); // création d'un modèle pour tous les fichiers RDF d'un dossier 
		Indexation index=new Indexation(modelCollection.listModel);
	    index.createAgregatIndex();
		RdfWindow window=new RdfWindow();  
		new ControlListner(window,modelCollection);
		window.Display();
	}
}
