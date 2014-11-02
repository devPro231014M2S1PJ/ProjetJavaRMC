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
       this.Window=window;                               // affecter la fen�tre dont les �v�nement seront trait�
       this.modelCollection=modelCollection;             // affecter la collection des fichiers
       modelCollection.putFirst(window.modelParcourir);  // mettre le premier mod�le RDF a affich�
       // mettre sous �coute les composantes qui nous int�resse
       window.buttParcourir.addActionListener(this);     
	   window.buttSuivant.addActionListener(this);
	   window.buttPrecedent.addActionListener(this);
	   window.buttQuitter.addActionListener(this);
	 }
	// r�action a un �v�nement
	public void actionPerformed(ActionEvent event) {
		Object sourceOfEvent=event.getSource();
		if(sourceOfEvent.equals(this.Window.buttSuivant)) this.reactionSuivant();// appel a la methode r�action au click sur suivant
              else if(sourceOfEvent.equals(this.Window.buttPrecedent)) this.reactionPrecedant(); //appel a la m�thode r�action au click sur pr�cendent
                  else if(sourceOfEvent.equals(this.Window.buttParcourir))this.reactionParcourir();// appel a la m�thode r�action au click sur parcourir
		                 else if(sourceOfEvent.equals(this.Window.buttQuitter)) this.reactionQuitter();//appel a la m�thode  r�action au click sur quitter
		       
	}
	
	// reaction au click sur le bouton parcourir
	private final void reactionParcourir(){ 
	  JFileChooser chooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter("RDF", "rdf");
      chooser.setFileFilter(filter);
      chooser.showOpenDialog(null);
      modelCollection.addRDFsFile(chooser.getSelectedFile().toString());
    }
	
	// r�action au clic sur le bouton quitter
	private final void reactionQuitter(){
		int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter l'application?.",
				                                  "Message de confirmation", 
				                                   JOptionPane.YES_NO_OPTION);
		if(option == 0) System.exit(0);
		else JOptionPane.getRootFrame().dispose();
	}
	// r�action au clic sur le bouton suivant
	private final void reactionSuivant(){
		 this.modelCollection.next(this.Window.modelParcourir);
	}
	// r�action au clic sur le bouton pr�cedent
    private final void reactionPrecedant(){
    	this.modelCollection.previous(this.Window.modelParcourir);
	}

}


