package projet.java.rdf.graph;

import java.util.ArrayList;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.Viewer.ThreadingModel;



import projet.java.rdf.indexation.CoupleResource;
import projet.java.rdf.indexation.LocalTree;
import projet.java.rdf.model.ModelCollection;

public class GlobalGraph {
      ArrayList<LocalTree> localTrees;
      ArrayList<Node> lasteSearch=new ArrayList<Node>();
      Graph g=new MultiGraph("test");
      public Viewer v=new Viewer(g, ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
      Dijkstra d=new Dijkstra(null, "", null);
      
      
      public GlobalGraph(ArrayList<LocalTree> localTrees){
    	  this.localTrees=localTrees;
    	 
    	  
      }
      
      public void display(){
    	  int i=0;
          g.setStrict(false);
          g.setAutoCreate(true);
    	  for(LocalTree l:localTrees){
    		  Node n=this.g.addNode(l.resource.toString());
    		  l.n=n;
    	      n.addAttribute("ui.label", l.resource.toString());
    	      n.addAttribute("ui.style", "fill-color: rgb(0,100,255);");
    	   
    	  }
    	  for(LocalTree l:localTrees){
    	      for(CoupleResource cr:l.coupleResc){
    	         System.out.println(l.resource.toString()+i+"-->" +cr.predicat.toString()+"--->"+cr.ArbusteDeLaResource.resource.toString()); 
    		     Edge d=this.g.addEdge(cr.predicat.toString()+i, l.resource.toString(), cr.ArbusteDeLaResource.resource.toString(), true); 
    	         d.addAttribute("ui.label", cr.predicat.getLocalName());
    		     i++;
    	       }
          }

      }
      
      public void detectResource(ArrayList<String> resultResource){
    	  for(Node n:lasteSearch) {n.removeAttribute("ui.style");
    	        n.addAttribute("ui.style", "fill-color: blue; size:0.005%;text-size:10%;");  
    	        }
    	        lasteSearch.clear();
    	  for(String s: resultResource){
    		  for(LocalTree l:localTrees){
    			  if(ModelCollection.isEquales(s, l.resource.toString())){
    				  lasteSearch.add(l.n);
    				  l.n.removeAttribute("ui.style");
    				  l.n.addAttribute("ui.style", "fill-color: rgb(255,100,000);"
    				  		+ "size:0.02%;text-size:20%; text-color:red;");  
    			  }
    		  }
    	  }
      }


}
