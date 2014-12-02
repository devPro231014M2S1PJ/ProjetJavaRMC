package projet.java.rdf.indexation;


import org.graphstream.graph.Edge;

import com.hp.hpl.jena.rdf.model.Resource;

public class CoupleResource {
	public Edge e;
	public Resource predicat;
	public LocalTree ArbusteDeLaResource;
	public CoupleResource(Resource predicat,LocalTree localTree) {
		this.predicat=predicat;
		this.ArbusteDeLaResource=localTree;
	}
}
