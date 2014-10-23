package test.model;

import test.interfac.Interface;



public class Main {
	public static void main(String[] args) 
	{  
	  Domain d=new Domain();
	  d.addRDF("C:/rdf/jeux_de_donnees");
	  System.out.println(d.listModelCreators.get(0).getLiteralTriple().get(0).getLiteral());
	  Interface inter = new Interface(d);
      d.browseAndDisplay(inter.model,0);
      inter.display();
    
     }
	}


