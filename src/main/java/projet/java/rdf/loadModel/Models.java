package projet.java.rdf.loadModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.openjena.riot.RiotException;

import com.hp.hpl.jena.n3.JenaURIException;
import com.hp.hpl.jena.rdf.model.LiteralRequiredException;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.FileManager;

public class Models{
	
	private LinkedList<Model> Models=new LinkedList<Model>();
	private int indexToDisplay=0;

	

/*****************************************************************************************************************/
	public Models(String path){
		this.LoadModels(path);
	}

/***************************************************************************************************************/
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
				   }catch(RiotException et){}
		 
         }catch(JenaURIException e){}
	   }
    }
	
/*************************************************************************************************************************/	
    
	public ArrayList<String[]> current(){
	  
	  ArrayList<String[]> triples=new ArrayList<String[]>();
	  
	  if(this.indexToDisplay<this.Models.size()){
		
		  return this.getModel(this.indexToDisplay,true,false);
	  } 
	  
	  return triples;
     }

/*************************************************************************************************************************/
	
    public ArrayList<String[]> next(){
		
		
		if(this.indexToDisplay<this.Models.size()-1){
			
			this.indexToDisplay++;
			return this.getModel(this.indexToDisplay,true,false);
		}
		
		return this.current();
    }

/*************************************************************************************************************************/
	
    public ArrayList<String[]> previous(){
		
		if(this.indexToDisplay!=0){
			
			this.indexToDisplay--;
			return this.getModel(this.indexToDisplay,true,false);
		}
		
		return this.current();
	}
/************************************************************************************************************************/
	
/************************************************************************************************************************/
	
	private ArrayList<String[]> getModel(int index,boolean isToDisplay,boolean isToBuildGraph){
		
	   ArrayList<String[]> triples=new ArrayList<String[]>();
	 
       if(index<=this.Models.size()){	   
            
    	    Model model=this.Models.get(index);        
        	Iterator<Statement> stmtIter=model.listStatements();
        	Statement statement;
        	String sSub,sPred,sLitOrResc;
        		
        	while(stmtIter.hasNext()){
			   
        	   statement=stmtIter.next();
			   sSub=statement.getSubject().toString();
			   sPred=statement.getPredicate().getLocalName();
			   
			   try{ 
			      
				  sLitOrResc=statement.getLiteral().toString();
			      if(isToBuildGraph) continue;
			    }
			   catch(LiteralRequiredException e){
				  
				  if(isToDisplay||isToBuildGraph)
			             
					      sLitOrResc=statement.getResource().toString();
			      else    
			    	      sLitOrResc="";
			    }
			   String[] sStatment={sSub,sPred,sLitOrResc};
			   triples.add(sStatment);	
            }
	   } 
       
       return triples;
	}
		
/***********************************************************************************************************************/
	
	public ArrayList<ArrayList<String[]>> getAllModels(boolean isToBuildGraph){
		
		int lenght=this.Models.size();
		ArrayList<ArrayList<String[]>> result=new ArrayList<ArrayList<String[]>>();
		
		for(int i=0;i<lenght;i++) {
			
			result.add(this.getModel(i, false,isToBuildGraph)); 
		}
		
		return result;
	}
	
	
	public LinkedList<Model> getModels(){
		
		return this.Models;
	}
	
/************************************************************************************************************************/
}
