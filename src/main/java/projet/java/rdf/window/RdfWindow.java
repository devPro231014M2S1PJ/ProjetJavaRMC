package projet.java.rdf.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import projet.java.rdf.loadModel.Models;
import projet.java.rdf.search.RdfGraph;
import projet.java.rdf.search.SearchInIndex;

@SuppressWarnings("serial")
public class RdfWindow  extends JFrame {
/**********************************************declaration**************************************************/
	 private SearchInIndex searchInIndex; 
	 private Models models;
	 private RdfGraph rdfGraph;
	 private JTextField zoneSearch;                                     
	 private JButtonFactory buttSearch,buttSuivant, buttPrecedent,buttQuitter;                   
	 private DefaultTableModel modelSearch, modelParcourir;  
	 private JTable tableauSearch, tableauParcourir;                     
	 private Object[][] dataSearch,dataParcourir;                              
	 private String[] dataModSearch = {"Ressouce"},dataModConfig = {"Sujet", "Predicat", "Objet"};

/*****************************************************************************************************/
	 public RdfWindow(SearchInIndex searchInIndex, Models models,RdfGraph rdfGraph) {
	   
	   this.searchInIndex=searchInIndex;
	   this.models=models;
	   this.rdfGraph=rdfGraph;
	   
	   this.zoneSearch=new JTextField(); 
	   this.zoneSearch.setBorder(new EmptyBorder(2,2,2,2));
	   this.zoneSearch.setSize(190, 20);
	   
	   this.modelSearch = new DefaultTableModel(dataSearch,dataModSearch); 
	   this.modelParcourir = new DefaultTableModel(dataParcourir,dataModConfig);
 	   
	   this.tableauSearch = new JTable(modelSearch);
	   this.tableauParcourir = new JTable( modelParcourir);
       
	   this.tableauParcourir.setPreferredScrollableViewportSize(new Dimension(600, 400));
	   this.tableauSearch.setPreferredScrollableViewportSize(new Dimension(600, 650));
 	   
 	   this.buttSearch = new JButtonFactory("Rechercher",new Color(255, 255, 255), new Color(254, 153, 0),200,36); 
 	   this.buttPrecedent =new JButtonFactory("Précedent",new Color(255, 255, 255), new Color(47, 204, 113),80,36);
       this.buttSuivant   =new JButtonFactory("Suivant",new Color(255, 255, 255), new Color(47, 204, 113),80,36);
       this.buttQuitter   =new JButtonFactory("Quitter",new Color(255, 255, 255), new Color(226, 73, 57),60,50); 
       
       // this.buttParcourir =new ButtonFactory("Parcourir...",new Color(255, 255, 255), new Color(47, 204, 113),100,36);
       this.Display();
	}
	 
/**********************************************************************************************************************/
     private final JPanFactory createHeader(){
    
	    
    	JPanFactory panHeader = new JPanFactory(new Color(40,170,226),950,70); 
	    JLabel labLogo = new JLabel(new ImageIcon(getClass().getResource("/logo.png")));          
	    JLabel labTitle = new JLabel(new ImageIcon(getClass().getResource("/app-tit.png")));      
	    JPanFactory panLogo= new JPanFactory(new Color(16,24,31), 200, 70); 
	    	 
	    panLogo.add(labLogo);                                                  
	    panHeader.add(panLogo, BorderLayout.WEST);                    
	    panHeader.add(labTitle,BorderLayout.CENTER);
	         
	    return panHeader;                                                     
	 }
	  
    
/*********************************************************************************************************************/
	 private final JPanFactory createSideBar(){
	    
		JPanFactory panSideBar= new JPanFactory(new Color(25,36,46),200, 630); 
	    JPanFactory panForm = new JPanFactory(new Color(25,36,46), 200, 110);  
	    panForm.setLayout(new GridLayout(3,1,0,4));
					
		JLabel labKeyWord = new JLabel("Mot-clés"); 
		
		labKeyWord.setForeground(new Color(255,255,255));
		panForm.add(labKeyWord);
		panForm.add(zoneSearch);
		panForm.add(buttSearch);
		
		panSideBar.setBorder(new EmptyBorder(15, 15, 15, 15));
	 	panSideBar.add(panForm,BorderLayout.NORTH);
	 	
	 	return panSideBar; 	
	 }
	 
/*****************************************************************************************************************/   
	private final JPanFactory createContent(){
	   
	   JPanFactory panContent = new JPanFactory(new Color(255,255,255), 950, 630);  
	   JPanFactory panFooter = new JPanFactory(new Color(255,255,255), 1000, 50); 
	 		
	   panFooter.setBorder(new EmptyBorder(10,0,5,0));
	   panFooter.add(buttQuitter, BorderLayout.EAST);
	   
	   panContent.setBorder(new EmptyBorder(15, 15, 15, 15));
	   panContent.add(this.createSwitchTable());
	        
	   panContent.add(panFooter,BorderLayout.SOUTH);
	   
	   return panContent;
	    	 
	 }

/**************************************************************************************************************************/

     private final JPanFactory createConfigSide(){
	  	
	   JPanFactory panControl = new JPanFactory(new Color(220,220,220),164, 40);
	   
	   panControl.add(buttPrecedent,BorderLayout.WEST);
	   panControl.add(buttSuivant,BorderLayout.EAST);
	
	   JPanFactory panConfigMod = new JPanFactory(new Color(247,247,247),200, 36);
	   
	   panConfigMod.add(panControl,BorderLayout.EAST);
	//   panConfigMod.add(buttParcourir,BorderLayout.WEST);
	   
	   return panConfigMod;	 
	}
     
/********************************************************************************************************************/  
	 private final JTabbedPane createSwitchTable(){
	    
		JTabbedPane jTab = new JTabbedPane();
	 	JPanFactory panGestionFichier = new JPanFactory(new Color(255,255,255), 950, 630); 
	 		 	 
	    panGestionFichier.setBorder(new EmptyBorder(10,10,10,10));
	    panGestionFichier.add(this.createConfigSide(),BorderLayout.NORTH);
	 	panGestionFichier.add(new JScrollPane(tableauParcourir),BorderLayout.SOUTH);
	 		 
	 	jTab.addTab("Gestion de fichiers", panGestionFichier);
	 	jTab.addTab("Recherche dans un fichier", new JScrollPane(tableauSearch)); 
	 	jTab.add("graph viewer",this.rdfGraph.getGraphView(false));
	 	jTab.add("sousGraphViewer",this.rdfGraph.getGraphView(true));
	 		 
	 	jTab.setSelectedIndex(0); 
	 		 
	 	return jTab;
    }
	 
/********************************************************************************************************************/ 
/*****************************************************la fonction d'affichage appeler par le constructeur***********/   
    private final  void Display() {
			
	   new SurfModelsListener(this.buttPrecedent, this.buttSuivant, this.models, this.modelParcourir); 
       new SearchListener(this.buttSearch, this.zoneSearch, this.searchInIndex, this.modelSearch,this.rdfGraph);
			
	   this.setTitle("Projet Java - Master ACSIS - 2014/2015 Recherche par mots-clÃ©s dans"
					      + "des donnÃ©es RDF (Groupe 4 : Rami - Amine - Ameur - Zakaria - Ania - Nadia)");
			
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   Dimension screanRes = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	   this.setSize(new Dimension((int)(screanRes.getWidth()*0.98),(int)(screanRes.getHeight()*0.9)));
	   this.setLocation(0,0);  
	   this.setResizable(false);	
		    
	   this.getContentPane().add(this.createHeader(), BorderLayout.NORTH);
	   this.getContentPane().add(this.createSideBar(), BorderLayout.WEST);
	   this.getContentPane().add(this.createContent(), BorderLayout.CENTER);
	   //this.pack();
			
	   this.setVisible(true);
		
    }
/****************************************************************************************************************/

}
