package projet.java.rdf.loadModel;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

// agregation des information qui conserne la méme ressource

public class InfoToIndex {
	
/***********************************************************************************************************/
	
	private String sResource;  // la ressource
	private String literalConcatination; // concatenation des litteral 
	private String predicatConcatination; // concatenationdes des predicat
	private String classes; // nom de la class 
	

	
/***********************************************************************************************************/
	
	public InfoToIndex(String sResource){
		this.sResource=sResource;
		this.literalConcatination="";
		this.predicatConcatination="";
		this.classes="";
	
		
	}

/************************************************************************************************************/
	
	public void addInInfoToindex(String sPredicat,String sLiteral){
		   if(sPredicat.equals("//**")){
			   this.classes=" "+sLiteral;
		   }else{
		    this.predicatConcatination+=" "+sPredicat;
		    this.literalConcatination+=" "+sLiteral+""+sPredicat;
		    }
	}
	
/************************************************************************************************************/
	// indexation des info d'une meme ressource dans le méme document lucen
	public Document indexInfo(){
		
		Document document = new Document();
		document.add(new Field("sujet",this.sResource ,TextField.TYPE_STORED));
		document.add(new Field("predicats",this.predicatConcatination,TextField.TYPE_STORED));
		document.add(new Field("literals",this.literalConcatination,TextField.TYPE_STORED));
	    
		if(classes!="")
	    	document.add(new Field("class", this.classes, TextField.TYPE_STORED));
		
		return document;
		
	}

/**************************************************************************************************************/
	
	public final String getResource(){
	
	   return this.sResource;
	}
	

}
