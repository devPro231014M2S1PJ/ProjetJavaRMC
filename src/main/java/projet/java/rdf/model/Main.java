package projet.java.rdf.model;

import projet.java.rdf.view.ControlListner;
import projet.java.rdf.view.RdfWindow;

public class Main {


	public static void main(String[] args) {

		ModelCollection modelCollection=new  ModelCollection(); // cr�ation de la liste des mod�le
		modelCollection.addRDFsFile("C:/rdf/jeux_de_donnees"); // cr�ation d'un mod�le pour tous les fichiers RDF d'un dossier 
		RdfWindow window=new RdfWindow();  
		new ControlListner(window,modelCollection);
		window.Display();

	}
}
