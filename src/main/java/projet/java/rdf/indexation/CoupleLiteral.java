package projet.java.rdf.indexation;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;

public class CoupleLiteral {
	public Resource predicat;
	public Literal literal;
	public CoupleLiteral(Resource predicat, Literal literal) {
		this.predicat=predicat;
		this.literal=literal;
	}

}
