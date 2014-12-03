package projet.java.rdf.graph;

import java.util.ArrayList;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.IdAlreadyInUseException;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

import projet.java.rdf.model.Main;

public class SteinerGraph {
	public Graph sousGraph;
	public Graph graph=new MultiGraph("sous graphe");
	public ArrayList<Node> localezedNodes;
	Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, null);
	public SteinerGraph(Graph graph, ArrayList<Node> localezedNodes){
		this.graph=graph;
		this.localezedNodes=localezedNodes;
		dijkstra.init(graph);
		 try{  Node sourceNode=this.localezedNodes.get(0);
			   dijkstra.setSource(sourceNode);
		       //sousGraph.addNode(sourceNode.getId());
		       dijkstra.compute();
		           
		       
		 }catch(IndexOutOfBoundsException e){Main.prints("pas de noeud trouvé",true);}
	}
	public void creatSousGraph(){
		   Node n1;
		   Node n2;
	       for(Node n:this.localezedNodes){
	    	   for (Edge edje : dijkstra.getPathEdges(n)){
	    		   edje.addAttribute("ui.style", "fill-color:red;");
	    		   n1=edje.getSourceNode();
	    		   n2=edje.getTargetNode();
	    		   try{sousGraph.addNode(n1.getId());}catch(IdAlreadyInUseException e){}
	    		   try{sousGraph.addNode(n2.getId());}catch(IdAlreadyInUseException e){}
	    		   try{sousGraph.addEdge(edje.getId(), n1, n2,true);}catch(IdAlreadyInUseException e){} 
	    		   
	    	   }   
	       }
	       }
	

}
