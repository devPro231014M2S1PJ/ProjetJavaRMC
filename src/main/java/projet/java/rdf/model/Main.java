package projet.java.rdf.model;

import projet.java.rdf.view.ControlListner;
import projet.java.rdf.view.RdfWindow;

public class Main {


	public static void main(String[] args) {

		ModelCollection modelCollection=new  ModelCollection(); // création de la liste des modèle
		modelCollection.addRDFsFile("C:/rdf/jeux_de_donnees"); // création d'un modèle pour tous les fichiers RDF d'un dossier 
		RdfWindow window=new RdfWindow();  
		new ControlListner(window,modelCollection);
		window.Display();

	}
}
