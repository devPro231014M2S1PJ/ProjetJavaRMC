package projet.java.rdf.model;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

import projet.java.rdf.indexation.LocalTree;

import com.hp.hpl.jena.n3.JenaURIException;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.LiteralRequiredException;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;

public class ModelCollection {
    
	public ArrayList<LocalTree> listelocalTree=new ArrayList<LocalTree>();
	public LinkedList<Model> listModel=new LinkedList<Model>(); // liste des mod�les
	
	private int nowModel=0;
	
	public void addRDFsFile(String path){
		System.out.println(path);
		File file=new File(path);
		if(file.isDirectory()){                          
			File[] f= file.listFiles();                     // on recup�re la liste des fichiers + dossier
			for(File fil:f){                                // pour chaque  dossier ou fichier
				
				 this.addRDFsFile(fil.getPath());                        // on appelle la fonction avec le chemin du dossier ou 

			}	 
			
		}else{
			 try{
				 
				 Model model= FileManager.get().loadModel(path);
				 this.listModel.add(model);
				 this.AddToLocalTree(model);
			       
		}catch(JenaURIException e){}
	}
}
	
	

	
	// metre le premier mod�le RDF de la liste sur le mod�le des donn�es affich� par le JTable dans RdfWindow
	public void putFirst(DefaultTableModel jTableModel){  
		if(listModel.size()>0)ModelCollection.putTriplet(listModel.get(0),jTableModel);
	}
	
	// naviguer vers le mod�le suivant
	public void next(DefaultTableModel jTableModel){
		if(this.nowModel<this.listModel.size()-1){
			this.nowModel++;
			ModelCollection.putTriplet(this.listModel.get(nowModel), jTableModel);
		}
	}
	
	// naviguer vers le mod�le pr�cedent
	public void previous(DefaultTableModel jTableModel){
		if(this.nowModel>0){
			this.nowModel--;
			ModelCollection.putTriplet(this.listModel.get(nowModel), jTableModel);
		}
	}
	

	public void AddToLocalTree(Model model){
		 Main.pf("test", false);
		 Statement stmt; 
		 // le triple
		 Resource  resource,predicat,objet; 
		 Literal literal;
		 StmtIterator stmtIter=model.listStatements(); 
		 while(stmtIter.hasNext()){
			 Main.pf("testing", false);
			   stmt=stmtIter.next();	
			   resource=stmt.getSubject();
			   predicat=stmt.getPredicate();
			   int test=positionOfLocalTree(resource,this.listelocalTree);
			   try{literal=stmt.getLiteral(); 
				   if(test==-1){
					     LocalTree newTree=new LocalTree(resource);
					     this.listelocalTree.add(newTree);
					     newTree.addCouple(predicat, literal);
				   }else this.listelocalTree.get(test).addCouple(predicat, literal);
				   
				   
			   }catch(LiteralRequiredException e){
				      objet=stmt.getResource();
				      int test2=positionOfLocalTree(objet, this.listelocalTree);
				      
				      
				      if(test==-1){
					        LocalTree MainNewTree=new LocalTree(resource);
					        this.listelocalTree.add(MainNewTree);
					        if(test2==-1){
					               LocalTree secondNewTree=new LocalTree(objet);
					               secondNewTree.pointed=true;
					               this.listelocalTree.add(secondNewTree);
					               MainNewTree.addCoupleResc(predicat, secondNewTree);
					         }else MainNewTree.addCoupleResc(predicat, listelocalTree.get(test2));
					        
					        
				      }else{
				    	  
					        LocalTree MainNewTree= this.listelocalTree.get(test); 
					        if(test2==-1){
						            LocalTree secondNewTree=new LocalTree(objet);
						            secondNewTree.pointed=true;
					                this.listelocalTree.add(secondNewTree);
					                MainNewTree.addCoupleResc(predicat, secondNewTree);
					         }else  MainNewTree.addCoupleResc(predicat, listelocalTree.get(test2));
					        
				      }  
			   }
         }
	}
	
	
	
	public static final int positionOfLocalTree(Resource resource, ArrayList<LocalTree> a){
		int i=0;
		for(LocalTree tree: a){
			if(isEquales(tree.resource.toString(),resource.toString())) return i;
			i++;
			}
		return -1;
	}
	
	
	 public static final boolean isEquales(String s1,String s2 ){
		  String rs1="";
		  String rs2="";
		  s1=s1.toLowerCase();
		  s2=s2.toLowerCase();
	      Pattern pattern = Pattern.compile("[a-zA-Z_0-9]");
	      Matcher matcher = pattern.matcher(s1);
	      while(matcher.find()) {
	         rs1+=matcher.group();
	      }
	      matcher = pattern.matcher(s2);
	      while(matcher.find()) {
	         rs2+=matcher.group();
	      }
	      boolean returne=(rs1.compareTo(rs2)==0)? true: false;
	      return returne;
	  }
	 
	 
	  public static final void putTriplet(Model model,DefaultTableModel tableLigne){
			 Statement stmt; // le triple
			 Resource  subject,pridicat,objet; 
			 Literal literal;
			 StmtIterator stmtIter=model.listStatements(); 
			 // convertion du model de graph a iterator pour le parcourir
			 int v=tableLigne.getRowCount(); 
		     for(int i=0;i<v;i++)tableLigne.removeRow(0);
		     // supression des colonnes du model precedent
			 while(stmtIter.hasNext()){
			   stmt=stmtIter.next();	
			   subject=stmt.getSubject();
			   pridicat=stmt.getPredicate();
			   try{ // si le model contient un literale
			        literal=stmt.getLiteral();
			        tableLigne.addRow(new Object[]{subject.toString(),pridicat.getLocalName(),literal.toString()});
			   }  catch(LiteralRequiredException e){ 
				   // si le model contient un ressource
				   objet=stmt.getResource();
				   tableLigne.addRow(new Object[]{subject.toString(),pridicat.getLocalName(),objet.toString()});   
			       }
		       }
		 }
}
