package projet.java.rdf.graph;

import java.util.ArrayList;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
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
		       for(Node n:this.localezedNodes){
		    	   for (Edge node : dijkstra.getPathEdges(n)){
		    		   node.addAttribute("ui.style", "fill-color:red;");
		    		   
		    	   }   
		       }    
		       
		 }catch(IndexOutOfBoundsException e){Main.prints("pas de noeud trouvé",true);}
	}
	
	

}
