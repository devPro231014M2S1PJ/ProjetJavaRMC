package projet.java.rdf.graph;

import java.util.ArrayList;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.Viewer.ThreadingModel;

import projet.java.rdf.indexation.CoupleResource;
import projet.java.rdf.indexation.LocalTree;
import projet.java.rdf.model.ModelCollection;


// Cette classe permet de pr�senter le graph general
public class GlobalGraph {
      ArrayList<LocalTree> localTrees;
      ArrayList<Node> lasteSearch=new ArrayList<Node>();
      Graph g=new MultiGraph("test");
      SteinerGraph steinerGraph=null;
      public Viewer v=new Viewer(g, ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
     
      
      
      public GlobalGraph(ArrayList<LocalTree> localTrees){
    	  this.localTrees=localTrees;
    	 
    	  
      }
      /*fonction qui permet d'afficher les noeuds*/
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
    		     cr.e=this.g.addEdge(cr.predicat.toString()+i, l.resource.toString(), cr.ArbusteDeLaResource.resource.toString(), true); 
    	         cr.e.addAttribute("ui.label", cr.predicat.getLocalName());
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
    	  this.steinerGraph=new SteinerGraph(g, lasteSearch);
    	  this.steinerGraph.creatSousGraph();
    	  this.steinerGraph.sousGraph.display();
    	  
      }
      
}
