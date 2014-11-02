package projet.java.rdf.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import projet.java.rdf.model.ModelCollection;

public class ControlListner implements ActionListener{
	RdfWindow Window;
	ModelCollection modelCollection;
    public ControlListner(RdfWindow window,ModelCollection modelCollection) {
       this.Window=window;                               // affecter la fenêtre dont les événement seront traité
       this.modelCollection=modelCollection;             // affecter la collection des fichiers
       modelCollection.putFirst(window.modelParcourir);  // mettre le premier modèle RDF a affiché
       // mettre sous écoute les composantes qui nous intéresse
       window.buttParcourir.addActionListener(this);     
	   window.buttSuivant.addActionListener(this);
	   window.buttPrecedent.addActionListener(this);
	   window.buttQuitter.addActionListener(this);
	 }
	// réaction a un évènement
	public void actionPerformed(ActionEvent event) {
		Object sourceOfEvent=event.getSource();
		if(sourceOfEvent.equals(this.Window.buttSuivant)) this.reactionSuivant();// appel a la methode réaction au click sur suivant
              else if(sourceOfEvent.equals(this.Window.buttPrecedent)) this.reactionPrecedant(); //appel a la méthode réaction au click sur précendent
                  else if(sourceOfEvent.equals(this.Window.buttParcourir))this.reactionParcourir();// appel a la méthode réaction au click sur parcourir
		                 else if(sourceOfEvent.equals(this.Window.buttQuitter)) this.reactionQuitter();//appel a la méthode  réaction au click sur quitter
		       
	}
	
	// reaction au click sur le bouton parcourir
	private final void reactionParcourir(){ 
	  JFileChooser chooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter("RDF", "rdf");
      chooser.setFileFilter(filter);
      chooser.showOpenDialog(null);
      modelCollection.addRDFsFile(chooser.getSelectedFile().toString());
    }
	
	// réaction au clic sur le bouton quitter
	private final void reactionQuitter(){
		int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter l'application?.",
				                                  "Message de confirmation", 
				                                   JOptionPane.YES_NO_OPTION);
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

}


