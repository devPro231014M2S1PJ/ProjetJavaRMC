package test.model;

import com.hp.hpl.jena.rdf.model.Resource;

public class ResrcTripl extends Tripler{
	private Resource resource;
	public ResrcTripl(Resource subject, Resource predicat,Resource resource) {
		super(subject, predicat);
		this.resource=resource;
	}
	public Resource getResource(){return this.resource;}

	
}
