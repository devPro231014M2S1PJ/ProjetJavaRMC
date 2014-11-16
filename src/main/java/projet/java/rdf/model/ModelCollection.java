package projet.java.rdf.model;
import projet.java.rdf.indexation.Indexation;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.table.DefaultTableModel;

public class ModelCollection {

	public LinkedList<ModelRDF> listModel=new LinkedList<ModelRDF>(); // liste des mod�les
	private int nowModel=0;
	
	public void addRDFsFile(String path,boolean addWithWindow){
		File file=new File(path);
		if(file.isDirectory()){                          
			File[] f= file.listFiles();                     // on recup�re la liste des fichiers + dossier
			for(File fil:f){                                // pour chaque  dossier ou fichier
				boolean bol=true; 
				for(char c:fil.getPath().toCharArray()) if(c=='~')bol=false;     //test pour eviter les fichiers cach�es
				if(bol)  this.addRDFsFile(fil.getPath(),false);                        // on appelle la fonction avec le chemin du dossier ou 
				//fichier contenue par ce  dossier
			}	 
			
		}else{
			ModelRDF modelRdf=new ModelRDF(path);
			this.listModel.add(new ModelRDF(path));  // si c'est un fichier on cr�e un mod�le RDF et on l'ajout a la liste
			if(addWithWindow)addToIndex(modelRdf);
		}
	}
	
	
	public void addToIndex(ModelRDF model){
		try {
			Indexation.addtoAgregatindex(model);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// metre le premier mod�le RDF de la liste sur le mod�le des donn�es affich� par le JTable dans RdfWindow
	public void putFirst(DefaultTableModel model){  
		if(listModel.size()>0)this.listModel.get(0).putTriplet(model);
	}
	
	// naviguer vers le mod�le suivant
	public void next(DefaultTableModel model){
		if(this.nowModel<this.listModel.size()-1){
			this.nowModel++;
			this.listModel.get(this.nowModel).putTriplet(model);
		}
	}
	
	// naviguer vers le mod�le pr�cedent
	public void previous(DefaultTableModel model){
		if(this.nowModel>0){
			this.nowModel--;
			this.listModel.get(this.nowModel).putTriplet(model);
		}
	}
}
