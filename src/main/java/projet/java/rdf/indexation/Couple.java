package projet.java.rdf.indexation;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Resource;

public class Couple {
	public Resource predicat;
	public Literal literal;
	public Couple(Resource predicat, Literal literal) {
		this.predicat=predicat;
		this.literal=literal;
	}

}
