package test.model;

import com.hp.hpl.jena.rdf.model.Resource;

public class Tripler {
	private Resource subject,predicat; 
	private float score;
	public Tripler(Resource subject, Resource predicat)
	 {this.subject=subject;
	  this.predicat= predicat;
	  score=0;}
	public Resource getSubject(){ return this.subject;}
	public Resource getPredicat(){ return this.predicat;}
	public float getScore(){return this.score;}
	public void setScore(float newScore) {this.score=newScore;}
	
}
