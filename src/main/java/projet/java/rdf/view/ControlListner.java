package projet.java.rdf.view;

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

		this.Window=window;                               // affecter la fenêtre dont les événement seront traité
		this.modelCollection=modelCollection;             // affecter la collection des fichiers
		this.indexation=index;
		modelCollection.putFirst(window.modelParcourir);  // mettre le premier modèle RDF a affiché
		// mettre sous écoute les composantes qui nous intéresse
		this.graph=graph;
		window.buttParcourir.addActionListener(this);     
		window.buttSuivant.addActionListener(this);
		window.buttPrecedent.addActionListener(this);
		window.buttQuitter.addActionListener(this);
		window.buttSearch.addActionListener(this);
		window.buttEst.addActionListener(this);
		window.buttWest.addActionListener(this);
		window.buttNorth.addActionListener(this);
		window.buttSouth.addActionListener(this);
		window.buttZoom.addActionListener(this);
		window.buttDeZoom.addActionListener(this);

	}

	// réaction a un évènement
	public void actionPerformed(ActionEvent event) {
		Object sourceOfEvent=event.getSource();
		if(sourceOfEvent.equals(this.Window.buttSuivant)) this.reactionSuivant();// appel a la methode réaction au click sur suivant

		else if(sourceOfEvent.equals(this.Window.buttPrecedent)) this.reactionPrecedant(); //appel a la méthode réaction au click sur précendent

		else if(sourceOfEvent.equals(this.Window.buttParcourir))this.reactionParcourir();// appel a la méthode réaction au click sur parcourir

		else if(sourceOfEvent.equals(this.Window.buttQuitter)) this.reactionQuitter();//appel a la méthode réaction au click sur quitter

		else if(sourceOfEvent.equals(this.Window.buttSearch))this.reactionSearch(); //appel à la méthode réaction au click sur chercher
        
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

	
	// réaction au clic sur le bouton quitter
	private final void reactionQuitter(){
		int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter l'application?.","Message de confirmation",JOptionPane.YES_NO_OPTION);
		if(option == 0) System.exit(0);
		else JOptionPane.getRootFrame().dispose();
	}

	// réaction au clic sur le bouton suivant
	private final void reactionSuivant(){
		this.modelCollection.next(this.Window.modelParcourir);
	}

	// réaction au clic sur le bouton précedent
	private final void reactionPrecedant(){
		this.modelCollection.previous(this.Window.modelParcourir);
	}

	private final void reactionSearch() {
		if(this.Window.zoneSearch.getText().length()==0){
			int v=this.Window.modelSearch.getRowCount();
			for(int i=0;i<v;i++)this.Window.modelSearch.removeRow(0);
		} else{
			try {
				this.recherche.search(this.Window.zoneSearch.getText(),this.Window.modelSearch);
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/* 
	private final void reactionZoom(){
	   this.viewPersent/=1.2;
	   Window.vi.setViewPercent(this.viewPersent);
	}
	   
	private final void reactionDZoom(){
	   this.viewPersent*=1.2;
	   Window.vi.setViewPercent(this.viewPersent);
	}
	    
	private final void reactionNorth(){
	  this.viewCenterY+=0.1;
	  Window.vi.setViewCenter(this.viewCenterY, this.viewCenterY, 0);
	}
	    
	private final void reactionSouth(){
	  this.viewCenterY-=0.1;
	  Window.vi.setViewCenter(this.viewCenterX, this.viewCenterY, 0);
	}
	    
	private final void reactionEst(){
	  this.viewCenterX+=0.1;
	  Window.vi.setViewCenter(this.viewCenterX, this.viewCenterY, 0);
	}
	    
	private final void reactionWest(){
	  this.viewCenterX-=0.1;
	  Window.vi.setViewCenter(viewCenterX, this.viewCenterY, 0);
	}*/
	    

}


