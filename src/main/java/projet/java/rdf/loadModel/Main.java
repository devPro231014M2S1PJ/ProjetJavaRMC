package projet.java.rdf.loadModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import projet.java.rdf.window.ControlListner;
import projet.java.rdf.window.RdfWindow;

public class Main {
	
	
	public static String configDirectoySource ;
	public static String configDirectoryIndex ;	
	// Fonction de cr�ation de la configuration
	public static void config() throws IOException{
		try {
			String ligne;
			BufferedReader fichier = new BufferedReader(new FileReader("./src/main/java/config.txt"));
			int i=0;
			while ((ligne = fichier.readLine()) != null) {
				switch(i){
				case 1: configDirectoySource=ligne; break;
				case 3: configDirectoryIndex=ligne;  break;
				} i++;
			}
			fichier.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void prints(String s,boolean ln){
		if(ln)System.out.println(s);else System.out.print(s);
	}
	

	public static void main(String[] args) throws IOException {
        Main.config();
		//ModelCollection modelCollection=new  ModelCollection();
		// création de la liste des modéle
		//modelCollection.addRDFsFile(configDirectoySource); 
		// création d'un modéle pour tous les fichiers RDF d'un dossier 
		//Indexation index=new Indexation(modelCollection.listelocalTree);
	    //index.testAndIndex(true);
	    //GlobalGraph g=new GlobalGraph(modelCollection.listelocalTree);
	    //g.display();
	    
		/*RdfWindow window=new RdfWindow(g.v);  
		new ControlListner(window,modelCollection,index,g);
		window.Display();*/
	}
}
