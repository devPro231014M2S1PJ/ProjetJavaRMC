package test.model;
import java.util.ArrayList;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.FileManager;

public class ModelCreator {
	private String filePath;
	private Model model;
    private ArrayList<LiteralTripl> litralTripl= new ArrayList<LiteralTripl>();
    private ArrayList<ResrcTripl> rscTripl=new ArrayList<ResrcTripl>();
	public ModelCreator(String filePath)
	  {FileManager.get().addLocatorClassLoader(ModelCreator.class.getClassLoader());
	   this.model= FileManager.get().loadModel(filePath);
	   this.filePath=filePath;
	   }
    
	public void modelAllTripl()
	   {QuerySolution soln;
	    Resource resrc;
	    Literal litr;
	    Resource subj;
	    Resource pred;
		String queryString= "SELECT  ?subj ?pred  ?litrOrResc"
	                       +" WHERE{ ?subj ?pred ?litrOrResc.}";
		 Query query=QueryFactory.create(queryString);
		 QueryExecution qexec=QueryExecutionFactory.create(query, model);
		 ResultSet results=qexec.execSelect();
		 while(results.hasNext())
		    { soln=results.nextSolution();
		      subj=soln.getResource("subj");
		      pred=soln.getResource("pred");
		  	  try{ litr=soln.getLiteral("litrOrResc");
		  	       this.litralTripl.add(new LiteralTripl(subj,pred,litr));
		  	     
		  	      }catch(ClassCastException e)
		  	        {resrc=soln.getResource("litrOrResc");
		  	         this.rscTripl.add(new ResrcTripl(subj,pred,resrc));
		  	        } 
		  	     }
	    }
	public String getFilePath(){return this.filePath;}
    public ArrayList<LiteralTripl> getLiteralTriple(){  return this.litralTripl;}
    public ArrayList<ResrcTripl> getResrcTriple(){      return this.rscTripl;}
}
