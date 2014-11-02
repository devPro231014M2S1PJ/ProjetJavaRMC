package projet.java.rdf.model;

import javax.swing.table.DefaultTableModel;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.LiteralRequiredException;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;

public class ModelRDF {
	public Model model;  // le modèle RDF d'un fichier
	public String path;  // le chemin dans le disque du fichier RDF
	public ModelRDF(String path){
		//FileManager.get().addLocatorClassLoader(ModelRDF.class.getClassLoader()); 
		this.model= FileManager.get().loadModel(path);// création du modèle à partir d'un fichier 
		this.path=path; // sauvegarde du chemin
	}
	
	/**
	 * mettre les triplée du model en cours dans un tableau de donner qui va être afficher dans le JTable 
	 */
	
	public void putTriplet(DefaultTableModel model){ 
		Statement stmt; // le triple
		Resource  subject,pridicat,resource; 
		Literal literal;
		StmtIterator stmtIter=this.model.listStatements(); //conversion du modèle de graphe a iterator pour le parcourir
		int v=model.getRowCount(); 
		for(int i=0;i<v;i++)model.removeRow(0); //supression des colonnes du modèle précédent
		
		while(stmtIter.hasNext()){
			stmt=stmtIter.next();	
			subject=stmt.getSubject();
			pridicat=stmt.getPredicate();
			try{ // si le modèle contient un littérale
				literal=stmt.getLiteral();
				model.addRow(new Object[]{subject.toString(),pridicat.toString(),literal.toString()});
				
			}catch(LiteralRequiredException e){ // si le modèle contient une ressource
				resource=stmt.getResource();
				model.addRow(new Object[]{subject.toString(),pridicat.toString(),resource.toString()});
			}
		}


	}
}
