package test.model;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;

public class LiteralTripl extends Tripler{
	private String literal;
	public LiteralTripl(Resource subject, Resource predicat,Literal literal) {
		super(subject, predicat);
		this.literal=literal.toString().toLowerCase();
	}
	public String getLiteral(){return this.literal;}

	

}
