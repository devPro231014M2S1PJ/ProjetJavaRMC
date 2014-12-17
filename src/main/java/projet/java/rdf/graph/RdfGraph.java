package projet.java.rdf.graph;

import java.util.ArrayList;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.IdAlreadyInUseException;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swingViewer.View;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.Viewer.ThreadingModel;


public class RdfGraph {
	
   private Graph graph;
   private Graph subGraph;
   private Dijkstra dijkstraOnGraph;
   private Dijkstra dijkstraOnSubGraph;
   private int countEdge=0;
   
/**************************************************************************************/
    
   public RdfGraph(ArrayList<ArrayList<String[]>> sModels){
    	this.graph=new MultiGraph("RdfGraph");
    	this.subGraph=new MultiGraph("sousGraph");
    	this.buildGraph(sModels);
    	this.dijkstraOnGraph=new Dijkstra(Dijkstra.Element.EDGE, null, null);
    	this.dijkstraOnGraph.init(this.graph);
    	this.dijkstraOnSubGraph=new Dijkstra(Dijkstra.Element.EDGE, null, null);
    	this.dijkstraOnSubGraph.init(this.subGraph);
    }
/****************************************************************************************/
   
    private final void buildGraph(ArrayList<ArrayList<String[]>> sModels){
       
    	for(ArrayList<String[]> sModel:sModels){
    	   
    		for(String[] triplet:sModel){
    	     
    			try{
    				
    				this.graph.addNode(triplet[0]);//.addAttribute("ui.label",triplet[0]);
    			}catch(IdAlreadyInUseException e){}
    			
    	        try {
    	        	this.graph.addNode(triplet[2]);//.addAttribute("ui.label",triplet[2]);
    	        	
    	        }catch(IdAlreadyInUseException e){}
    	        
    	        this.graph.addEdge(triplet[1]+" "+this.countEdge, triplet[0],triplet[2], true);
    	        //.addAttribute("ui.label",triplet[1]);
    	        
    	        this.countEdge++;
           }
        }
    }
/********************************************************************************************************/   
/*************************************************************************************************************/    
    
    public View getGraphView(boolean isSubGraph){
    	Graph lGraph=(isSubGraph)?this.subGraph:this.graph;
    	Viewer viewer=new Viewer(lGraph, ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.enableAutoLayout();
		return (viewer.addDefaultView(false));
    }
    
/************************************************************************************************************/ 
    
    public void searchTreatment(ArrayList<String> results) throws IndexOutOfBoundsException{
        ArrayList<Node> nodes=this.linkResources(results);
        for(Node node:nodes){
        	  node.addAttribute("ui.style","fill-color:red;");
        	 //node.addAttribute("ui.label",node.getId());
        }
        
        //nodes.get(0).addAttribute("ui.label", nodes.get(0));
        //nodes.get(1).addAttribute("ui.label", nodes.get(1));
        this.dijkstraOnGraph.setSource(nodes.get(0));
        this.dijkstraOnGraph.compute();
        try {
        	this.createSubGraph(nodes);
        	} catch (NullPointerException e){}
      
    }
/**************************************************************************************************************/    
    
    private void createSubGraph(ArrayList<Node> nodes){
  
            for(Node node:nodes){
	    	   for (Edge edje : this.dijkstraOnGraph.getPathEdges(node)){
	    		  // System.out.println(edje.getId());
	    		   //edje.setAttribute("ui.style", "fill-color:red;");
	    		   //edje.getSourceNode().addAttribute("ui.style", "fill-color:blue;");
	    		   //edje.getTargetNode().addAttribute("ui.style", "fill-color:blue;");
	    		   String sourceNode=edje.getSourceNode().getId();
	    		   String targetNode=edje.getTargetNode().getId();
	    		   
	    		   try{
	    			   this.subGraph.addNode(sourceNode);
	    			   }catch(IdAlreadyInUseException e){}
	    		   
	    		   try{
	    			   this.subGraph.addNode(targetNode);
	    			   }catch(IdAlreadyInUseException e){}
	    		   
	    		   try{
	    			 
	    			   this.subGraph.addEdge(edje.getId(),sourceNode,targetNode,true);
	    			   }catch(IdAlreadyInUseException e){}
	    		   //e0dje.getSourceNode().addAttribute("ui.style", "fill-color:red;");
	    		   //edje.getTargetNode().addAttribute("ui.style", "fill-color:red;");
	           } 
	    	    
            }
            this.createSneiderTree(nodes.get(0));
   
    	
    }
/******************************************************************************************************************/
    
    private void createSneiderTree(Node node){
    	try{
            dijkstraOnSubGraph.setSource(this.subGraph.getNode(node.getId()));
            dijkstraOnSubGraph.compute();
            for (Edge edge : dijkstraOnSubGraph.getTreeEdges())
               edge.addAttribute("ui.style", "fill-color: red;");
    	}catch(IllegalStateException e){}
    	
    }
    
/**********************************************************************************************************/    
    
    private final ArrayList<Node> linkResources(ArrayList<String> results){
    	ArrayList<Node> nodes=new ArrayList<Node>();
    	for(String result:results){
    		Node nodeResult=this.graph.getNode(result);
            nodes.add(nodeResult);
    	}
       return nodes;
    	
    }
    
/*******************************************************************************************************/
    
}
