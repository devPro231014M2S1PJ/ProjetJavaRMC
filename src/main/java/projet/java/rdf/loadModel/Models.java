package projet.java.rdf.loadModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import com.hp.hpl.jena.n3.JenaURIException;
import com.hp.hpl.jena.rdf.model.LiteralRequiredException;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.FileManager;

public class Models{
	// liste des models
	private LinkedList<Model> Models;
	// regroupement des model dans un seul pour l'interrogationsparql
	private Model globalModel;
	// l'index en cour d'affichge
	private int indexToDisplay=0;

/*****************************************************************************************************************/
	 public Models(String path){
		
		this.Models=new LinkedList<Model>();
		this.LoadModels(path);
		this.createGlobalModel();
	 }

/***************************************************************************************************************/
	 // chargement du données
	 private final void LoadModels(String path){
		
		File file=new File(path);
	
		
	    if(file.isDirectory()){                          
			
			File[] f= file.listFiles();                     
			
			for(File fil:f){                               
				 
			   this.LoadModels(fil.getPath());                        
			}	
			
		}else{
			
			 try{
				 
				 try{
				      
					    Model model= FileManager.get().loadModel(path);
				        this.Models.add(model);
				  
				   }catch(Exception et){}
		 
         }catch(JenaURIException e){}
	   }
     }
	
	 
/*************************************************************************************************************************/
	 
	 private void createGlobalModel(){
		 
		for(int i=0;i<Models.size();i++ ){
			if(i==0)this.globalModel=Models.get(i);
			else this.globalModel.add(Models.get(i));
		} 
		 
	 }
/*************************************************************************************************************************/	
   
	 // fonction pour recupéré le model e  cour pour l'affichage
	 public ArrayList<String[]> current(){
	  
	    ArrayList<String[]> triples=new ArrayList<String[]>();
	  
	    if(this.indexToDisplay<this.Models.size()){
		
		   return this.getModel(this.indexToDisplay,true,false);
	    } 
	  
	   return triples;
     }

/*************************************************************************************************************************/
	// chargement du model suivant
     public ArrayList<String[]> next(){
		
		
		if(this.indexToDisplay<this.Models.size()-1){
			
			this.indexToDisplay++;
			return this.getModel(this.indexToDisplay,true,false);
		}
		
		return this.current();
    }

/*************************************************************************************************************************/
	// chargement du model precedant
    public ArrayList<String[]> previous(){
		
		if(this.indexToDisplay!=0){
			
			this.indexToDisplay--;
			
			return this.getModel(this.indexToDisplay,true,false);
		}
		
		return this.current();
	 }
    
/************************************************************************************************************************/
/************************************************************************************************************************/
	// chargemnet du model qui a l'index "index"
	 private ArrayList<String[]> getModel(int index,boolean isToDisplay,boolean isToBuildGraph){
		
	    ArrayList<String[]> triples=new ArrayList<String[]>();
	 
        if(index<=this.Models.size()){	   
            
    	    Model model=this.Models.get(index);        
        	Iterator<Statement> stmtIter=model.listStatements();
        	Statement statement;
        	String sSub,sPred,sLitOrResc;
        		
        	while(stmtIter.hasNext()){
			   
        	   statement=stmtIter.next();
			   sSub=statement.getSubject().getURI();
			   sPred=statement.getPredicate().getLocalName();
			   
			   try{ 
			      
				  sLitOrResc=statement.getLiteral().getString();
			      if(isToBuildGraph) continue;
			    }
			   
			    catch(LiteralRequiredException e){
				  
				  if(isToDisplay||isToBuildGraph)
			             
					      sLitOrResc=statement.getResource().getURI();
			      else{   
			    	      if(sPred.equals("type") ){
			    	    	         
			    	         triples.add(new String[]{statement.getResource().getURI(),"//**",
			    	        	                         statement.getResource().getLocalName()});
			    	          sSub="//**"; sLitOrResc="";
			    	      }else
			    	         sLitOrResc="";
			    }
			 }
			  
			      String[] sStatment={sSub,sPred,sLitOrResc};
			      triples.add(sStatment);
			   	
            }
	    } 
       
       return triples;
	}
		
/***********************************************************************************************************************/
	// resuperé tout le model en un seul block
    public ArrayList<ArrayList<String[]>> getAllStingModels(boolean isToBuildGraph){
		
		int lenght=this.Models.size();
		
		ArrayList<ArrayList<String[]>> result=new ArrayList<ArrayList<String[]>>();
		
		for(int i=0;i<lenght;i++) {
			
			result.add(this.getModel(i, false,isToBuildGraph)); 
		}
				
		return result;
	 }
	
/******************************************************************************************************************/	
	 
     public LinkedList<Model> getModels(){
		
    	 return this.Models;
		
	 }
/********************************************************************************************************************/	 

   public Model getGlobalModel(){return this.globalModel;}
     
/************************************************************************************************************************/
}
