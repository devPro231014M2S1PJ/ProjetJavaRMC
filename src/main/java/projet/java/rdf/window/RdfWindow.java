package projet.java.rdf.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import projet.java.rdf.main.Main;

@SuppressWarnings("serial")
public class RdfWindow  extends JFrame {
/**********************************************declaration**************************************************/
	 private JScrollPane jScrollPane;
	 private JTextArea jTextArea;
     private boolean firstUse=true;
	 private Main main;
	 private JTabbedPane jTab;
	 private JTextField zoneSearch,dataSetDirectory=new JTextField();
	 private JButtonFactory buttSearch;
     private JPanel initialePan;                              
	 private JButtonFactory buttQuitter;   
	 private DefaultTableModel modelSearch, modelParcourir;  
	 private JTable tableauSearch, tableauParcourir;                     
	 private Object[][] dataSearch,dataParcourir;                              
	 private String[] dataModSearch = {"Ressouce"},dataModConfig = {"Sujet", "Predicat", "Objet"};

/*****************************************************************************************************/
	
	 public RdfWindow() {
	     
	   this.modelSearch = new DefaultTableModel(dataSearch,dataModSearch); 
	   this.modelParcourir = new DefaultTableModel(dataParcourir,dataModConfig);
	   this.tableauSearch = new JTable(modelSearch);
	   this.tableauParcourir = new JTable( modelParcourir);
	   this.tableauParcourir.setPreferredScrollableViewportSize(new Dimension(600, 400));
	   this.tableauSearch.setPreferredScrollableViewportSize(new Dimension(600, 650));
	   this.jTab=new JTabbedPane();
	   this.initialePan=this.intialContent();
	   this.jTab.add("test",this.initialePan); 
       this.buttQuitter   =new JButtonFactory("Quitter",new Color(255, 255, 255), new Color(226, 73, 57),100,50); 
       
       this.Display();
	}
	 
/**********************************************************************************************************************/
	 //fenetre initiale qui contient le menu pour choisir la configuration
	 private JPanel intialContent(){
		  
	   JPanel jPan=new JPanel(),jPan2=new JPanel();
	   JButtonFactory browseButt=new JButtonFactory("browse DataSet", Color.red, Color.red, 250, 30);
	
	   jPan.setLayout(new BorderLayout());
	   jPan2.setLayout(new BorderLayout());
	   jPan2.add(browseButt,BorderLayout.EAST);
	   
	   dataSetDirectory.setEditable(false);
	   jPan2.add(dataSetDirectory,BorderLayout.CENTER);
	   jPan.add(jPan2,BorderLayout.NORTH);
		  /*******************************************************************************************/
	    // bouton pour choisir le chemin qui contient l'emplacement de jeux de donner et du dossier de l'index
	   browseButt.addActionListener(new ActionListener() {
	      	/***************************************************************************/
		         @SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent arg0) {
				   try {
				        
				         JFileChooser jFileChooser=new JFileChooser();
				         jFileChooser.showOpenDialog(null);
				         File file=jFileChooser.getSelectedFile();
					     @SuppressWarnings("resource")
						 DataInputStream in=new DataInputStream(new FileInputStream(file));
					     
					     String[] s=new String[2]; 
					     s[0]=in.readLine();
					     s[1]=in.readLine();
				         main=new Main(s[0],s[1]); 
				         dataSetDirectory.setText(s[0]);
				         
				         if(firstUse) 
				        	 firstUse=false;
				             else deleteLastUse();
				         jTextArea=new JTextArea();
						 jScrollPane=new JScrollPane(jTextArea);				       
					     createSwitchTable();
					     new SearchListener(buttSearch, zoneSearch,jTextArea, main, modelSearch);
								
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		         }
	        });
	   return jPan;
	   
	} 
	
/***********************************************************************************************************************/ 
/*********************************************************************************************************************/
	 // creation de l'entete 
     private final JPanFactory createHeader(){
    
       JPanFactory panHeader = new JPanFactory(new Color(40,170,226),950,400); 
	   JLabel labLogo = new JLabel(new ImageIcon(getClass().getResource("/logo.png")));          
	   JLabel labTitle = new JLabel(new ImageIcon(getClass().getResource("/app-tit.png")));      
	   JPanFactory panLogo= new JPanFactory(new Color(25,36,46), 400, 100); 
	    	 
	   panLogo.add(labLogo);                                                  
	   panHeader.add(panLogo,BorderLayout.WEST);                    
	   panHeader.add(labTitle,BorderLayout.CENTER);
	         
	   return panHeader;                                                     
	 }
	  
    
/*********************************************************************************************************************/
	 
     // creation de la bare latéral
     private final JPanFactory createSideBar(){
	    
	   JPanFactory panSideBar= new JPanFactory(new Color(25,36,46),0, 0); 
	   JPanFactory panForm = new JPanFactory(new Color(25,36,46), 0,0); 
	    
	   panForm.setLayout(new GridLayout(3,1,0,4));
	   buttSearch = new JButtonFactory("Rechercher",new Color(255, 255, 255), new Color(254, 153, 0),145,40);   
	   zoneSearch=new JTextField(); 
	   zoneSearch.setBorder(new EmptyBorder(2,2,2,2));
	   zoneSearch.setSize(190, 20);
	  			
	   JLabel labKeyWord = new JLabel("Mot-cles"); 
	   labKeyWord.setForeground(new Color(255,255,255));
	   panForm.add(labKeyWord);
	   panForm.add(zoneSearch);
	   panForm.add(buttSearch);
	   panSideBar.setBorder(new EmptyBorder(15, 15, 15, 15));
	   panSideBar.add(panForm,BorderLayout.NORTH); 
	 	
	   return panSideBar; 	
	 }
	 
/*****************************************************************************************************************/   
	 
     // creation du conteneur central
     private final JPanFactory createContent(){
	   
	   JPanFactory panContent = new JPanFactory(new Color(255,255,255), 950, 630);  
	   JPanFactory panFooter = new JPanFactory(new Color(255,255,255), 1000, 50); 
	 		
	   panFooter.setBorder(new EmptyBorder(10,0,5,0));
	   panFooter.add(buttQuitter, BorderLayout.EAST);
	   panContent.setBorder(new EmptyBorder(15, 15, 15, 15));
	   panContent.add(this.jTab); 
	   panContent.add(panFooter,BorderLayout.SOUTH);
	   
	   return panContent;
	    	 
	 }

/**************************************************************************************************************************/
     // creation du conteneur pour parcourir les fichier rdf
     private final JPanFactory createSurfSide(){
       
       JPanFactory panSurfMod = new JPanFactory(new Color(247,247,247),200, 36);
	   JPanFactory panControl = new JPanFactory(new Color(220,220,220),164, 40);
	   JButtonFactory buttSuivant, buttPrecedent;
	 	  
  	   buttPrecedent =new JButtonFactory("Precedent",new Color(255, 255, 255), new Color(49, 224, 213),80,36);
       buttSuivant   =new JButtonFactory("Suivant",new Color(255, 255, 255), new Color(47, 204, 113),80,36);
	   panControl.add(buttPrecedent,BorderLayout.WEST);
	   panControl.add(buttSuivant,BorderLayout.EAST);
	   panSurfMod.add(panControl,BorderLayout.WEST);
	   
	   new SurfModelsListener(buttPrecedent, buttSuivant, this.main.models, this.modelParcourir); 
	   
	   return panSurfMod;	 
	}
     


/********************************************************************************************************************/
    
    // creation du switch table pour visualiser le graph et le resultat de la recherche 
    public final void createSwitchTable(){
	
	 	JPanFactory panGestionFichier = new JPanFactory(new Color(255,255,255), 950, 630); 
	 		 	 
	    panGestionFichier.setBorder(new EmptyBorder(10,10,10,10));
	    panGestionFichier.add(this.createSurfSide(),BorderLayout.NORTH);
	 	panGestionFichier.add(new JScrollPane(tableauParcourir),BorderLayout.SOUTH);
	 	
	 	this.initialePan.add( panGestionFichier,BorderLayout.CENTER);
	 	this.jTab.add("Graphe",this.main.rdfGraph.getGraphView(false));
	 	this.jTab.add("sous Graphe",this.main.rdfGraph.getGraphView(true));
	    this.jTab.add("resultat de la recherche",jScrollPane);

    }
/**********************************************************************************************************************/
    
    public final void deleteLastUse(){
    	for(int i=1;i<4;i++) this.jTab.remove(1);
    } 
	 
/********************************************************************************************************************/ 
/*****************************************************la fonction d'affichage appeler par le constructeur***********/   
    private final  void Display() {
			   	
	   this.setTitle("Projet Java - Master ACSIS - 2014/2015 Recherche par mots-clés dans"
					      + "des données RDF (Groupe 4 : Rami - Amine - Ameur - Zakaria - Ania)");
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	   Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
	   
	   this.setSize(new Dimension((int)(screenRes.getWidth()*0.98),(int)(screenRes.getHeight()*0.9)));
	   this.setLocation(0,0);  
	   this.setResizable(false);	   
	   this.getContentPane().add(this.createHeader(), BorderLayout.NORTH);
	   this.getContentPane().add(this.createSideBar(), BorderLayout.WEST);
	   this.getContentPane().add(this.createContent(), BorderLayout.CENTER);
	   this.setVisible(true);
		
    }
/****************************************************************************************************************/

    public static void main(String[] args) {
		// TODO Auto-generated method stub
           // Main main=new Main(null, null);
            new RdfWindow();
       	}
    
}
