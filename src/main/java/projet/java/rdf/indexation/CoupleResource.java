package projet.java.rdf.indexation;


import com.hp.hpl.jena.rdf.model.Resource;

public class CoupleResource {
	public Resource predicat;
	public LocalTree ArbusteDeLaResource;
	public CoupleResource(Resource predicat,LocalTree localTree) {
		this.predicat=predicat;
		this.ArbusteDeLaResource=localTree;
	}
}
