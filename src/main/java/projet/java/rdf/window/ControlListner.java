package projet.java.rdf.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.lucene.queryparser.classic.ParseException;

import projet.java.rdf.graph.GlobalGraph;
import projet.java.rdf.indexation.Indexation;
import projet.java.rdf.model.ModelCollection;
import projet.java.rdf.search_in_index.SearchInIndex;

public class ControlListner implements ActionListener{
	
	RdfWindow Window;
	ModelCollection modelCollection;
	SearchInIndex recherche=new SearchInIndex();
	Indexation indexation;
	double viewPersent=1, viewCenterX=0, viewCenterY=0;
	ArrayList<String> resourceResult=new ArrayList<String>();
	GlobalGraph graph;

	public ControlListner(RdfWindow window,ModelCollection modelCollection, Indexation index, GlobalGraph graph) {

		this.Window=window;                               // affecter la fen�tre dont les �v�nement seront trait�
		this.modelCollection=modelCollection;             // affecter la collection des fichiers
		this.indexation=index;
		modelCollection.putFirst(window.modelParcourir);  // mettre le premier mod�le RDF a affich�
		// mettre sous �coute les composantes qui nous int�resse
		this.graph=graph;
		window.buttParcourir.addActionListener(this);     
		window.buttSuivant.addActionListener(this);
		window.buttPrecedent.addActionListener(this);
		window.buttQuitter.addActionListener(this);
		window.buttSearch.addActionListener(this);


	}

	// r�action a un �v�nement
	public void actionPerformed(ActionEvent event) {
		Object sourceOfEvent=event.getSource();
		if(sourceOfEvent.equals(this.Window.buttSuivant)) this.reactionSuivant();// appel a la methode r�action au click sur suivant

		else if(sourceOfEvent.equals(this.Window.buttPrecedent)) this.reactionPrecedant(); //appel a la m�thode r�action au click sur pr�cendent

		else if(sourceOfEvent.equals(this.Window.buttParcourir))this.reactionParcourir();// appel a la m�thode r�action au click sur parcourir

		else if(sourceOfEvent.equals(this.Window.buttQuitter)) this.reactionQuitter();//appel a la m�thode r�action au click sur quitter

		else if(sourceOfEvent.equals(this.Window.buttSearch))this.reactionSearch(); //appel � la m�thode r�action au click sur chercher
        
        //else if(sourceOfEvent.equals(this.Window.buttZoom))this.reactionZoom();
		
        //else if(sourceOfEvent.equals(this.Window.buttDeZoom))this.reactionDZoom();
                
        //else if(sourceOfEvent.equals(this.Window.buttNorth))this.reactionNorth();
                      
        //else if(sourceOfEvent.equals(this.Window.buttSouth))this.reactionSouth();
                          
        //else if(sourceOfEvent.equals(this.Window.buttEst))this.reactionEst();
                               
        //else if(sourceOfEvent.equals(this.Window.buttWest))this.reactionWest();
	}

	// reaction au click sur le bouton parcourir
	private final void reactionParcourir(){ 
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("RDF", "rdf");
		chooser.setFileFilter(filter);
		chooser.showOpenDialog(null);
		try{ 
			modelCollection.addRDFsFile(chooser.getSelectedFile().toString());
		}  catch(NullPointerException e){ System.out.println("aucun fichier selectionner" );}
	}

	
	// r�action au clic sur le bouton quitter
	private final void reactionQuitter(){
		int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter l'application?.","Message de confirmation",JOptionPane.YES_NO_OPTION);
		if(option == 0) System.exit(0);
		else JOptionPane.getRootFrame().dispose();
	}

	// r�action au clic sur le bouton suivant
	private final void reactionSuivant(){
		this.modelCollection.next(this.Window.modelParcourir);
	}

	private final void reactionPrecedant(){
		this.modelCollection.previous(this.Window.modelParcourir);
	}

	private final void reactionSearch() {
		if(this.Window.zoneSearch.getText().length()==0){
			int v=this.Window.modelSearch.getRowCount();
			for(int i=0;i<v;i++)this.Window.modelSearch.removeRow(0);
		} else{
			try {
				this.graph.detectResource(this.recherche.search(this.Window.zoneSearch.getText(),this.Window.modelSearch));
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		}
	}
		    

}


