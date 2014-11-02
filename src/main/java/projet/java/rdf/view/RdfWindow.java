package projet.java.rdf.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class RdfWindow extends JFrame {

	/**
	 * Declaration des variables globale (les variables qui doivent être accecibles depuis les autres classes 
	 */

	public JTextField zoneSearch; // champs Text
	public JButton buttParcourir,buttSearch,buttSuivant, buttPrecedent,buttQuitter; // les boutton du mode configuration 
	public DefaultTableModel modelSearch, modelParcourir;// le model d'affichage des données dans le mode 
	private JTable tableauSearch, tableauParcourir;// les tables qui l'afficherons
	private Object[][] dataSearch,dataParcourir; // les tableaux qui contiennent les données
	private String[] dataModSearch = {"Path"},dataModConfig = {"Sujet", "Predicat", "Ressource"}; // les deux mode d'affichage

	/**
	 * à la construction
	 */

	public RdfWindow() {
		zoneSearch=new JTextField(); // zone de teste qui est une variable global
		zoneSearch.setBorder(new EmptyBorder(2,2,2,2));
		zoneSearch.setSize(190, 20);
		buttSearch = buttFactory("Rechercher",new Color(255, 255, 255), new Color(254, 153, 0),200,36);
		modelSearch = new DefaultTableModel(dataSearch,dataModSearch); 
		modelParcourir = new DefaultTableModel(dataParcourir,dataModConfig); 
		// definition des deux mode d'affiche des JTable 
		tableauSearch = new JTable(modelSearch);
		tableauSearch.setPreferredScrollableViewportSize(new Dimension(600, 650));
		// creation des deux JTable
		tableauParcourir = new JTable( modelParcourir);
		tableauParcourir.setPreferredScrollableViewportSize(new Dimension(600, 400));

		this.buttPrecedent =buttFactory("Précedent",new Color(255, 255, 255), new Color(47, 204, 113),80,36);
		this.buttSuivant =buttFactory("suivant",new Color(255, 255, 255), new Color(47, 204, 113),80,36);
		this.buttParcourir =buttFactory("Parcourir",new Color(255, 255, 255), new Color(47, 204, 113),100,36);
		this.buttQuitter= this.buttFactory("Quitter",new Color(255, 255, 255), new Color(226, 73, 57),60,50);  
	}

	/**
	 * creation de l'entête de la fenêtre
	 */	 
	private final JPanFactory createHeader(){
		JPanFactory panHeader = new JPanFactory(new Color(40,170,226),950,70); // creation du conteneur globale de l'entête
		JLabel labLogo = new JLabel(new ImageIcon("./src/logo.png"));          // creation du logo
		JLabel labTitle = new JLabel(new ImageIcon("./src/app-tit.png"));      // creation du titre
		JPanFactory panLogo= new JPanFactory(new Color(16,24,31), 200, 70);    //le conteneur du logo 
		panLogo.add(labLogo);                                                  
		panHeader.add(panLogo, BorderLayout.WEST);                    
		panHeader.add(labTitle,BorderLayout.CENTER);
		return panHeader;                                                      // retourné le conteneur globale de l entÃ©te
	}

	/**
	 * creation de la zone Sidebar
	 */
	private final JPanFactory createSideBar(){
		JPanFactory panSideBar= new JPanFactory(new Color(25,36,46),200, 630); // conteneur global
		JPanFactory panForm = new JPanFactory(new Color(25,36,46), 200, 110);  // sous conteneur qui sera positionné en haut et contient tout les composante
		panForm.setLayout(new GridLayout(3,1,0,4));


		// boutton qui lance la recherche		
		JLabel labKeyWord = new JLabel("Mot-clé");   //title
		labKeyWord.setForeground(new Color(255,255,255));
		panForm.add(labKeyWord);
		panForm.add(zoneSearch);
		panForm.add(buttSearch);
		//ajout des composantes au sous conteneur
		panSideBar.setBorder(new EmptyBorder(15, 15, 15, 15));
		panSideBar.add(panForm,BorderLayout.NORTH);
		return panSideBar; // retourner le conteneur global

	}

	/**
	 * creation du centre de la fenetre avec ces deux mode: --config  et -- recherche
	 */
	private final JPanFactory createContent(){
		JPanFactory panContent = new JPanFactory(new Color(255,255,255), 950, 630); //conteneur globale 
		JPanFactory panFooter = new JPanFactory(new Color(255,255,255), 1000, 50); // pied du conteneur global
		// le boutton quitter
		panFooter.setBorder(new EmptyBorder(10,0,5,0));
		panFooter.add(buttQuitter, BorderLayout.EAST);
		panContent.setBorder(new EmptyBorder(15, 15, 15, 15));
		panContent.add(this.createSwitchTable());
		//appel a la fonction qui construit le reste des composantes du centre de la fenetre --->this.createSwitchTable()
		panContent.add(panFooter,BorderLayout.SOUTH);
		return panContent;

	}

	/**
	 * creation du tableau qui permet la navigation entre les différentes modes
	 */

	private final JTabbedPane createSwitchTable(){
		JTabbedPane jTab = new JTabbedPane();

		JPanFactory panGestionFichier = new JPanFactory(new Color(255,255,255), 950, 630); 
		// creation du conteneur pour le mode gestion de fichier qui contient les boutton pacourir suivant et perecedent 
		panGestionFichier.setBorder(new EmptyBorder(10,10,10,10));
		panGestionFichier.add(this.createConfigMod(),BorderLayout.NORTH);
		// appel a la fonction --->this.createConfigMod() qui construire et renvoi le conteneur en mode config
		panGestionFichier.add(new JScrollPane(tableauParcourir),BorderLayout.SOUTH);
		jTab.addTab("Recherche dans un fichier", new JScrollPane(tableauSearch)); 
		jTab.addTab("Parcourir des fichiers", panGestionFichier);
		jTab.setSelectedIndex(0); // positionné le premier affichage a l'execution en mode recherche
		return jTab;
	}

	/**
	 * creation du conteneur pour le mode config
	 */

	private final JPanFactory createConfigMod(){

		// creation du conteneur qui contient les boutton suivant et precedent
		JPanFactory panControl = new JPanFactory(new Color(220,220,220),164, 40); 
		panControl.add(buttPrecedent,BorderLayout.WEST);
		panControl.add(buttSuivant,BorderLayout.EAST);
		// construction du conteneur Globale
		JPanFactory panConfigMod = new JPanFactory(new Color(247,247,247),200, 36);
		panConfigMod.add(panControl,BorderLayout.EAST);
		panConfigMod.add(buttParcourir,BorderLayout.WEST);
		return panConfigMod;	 
	}

	/**
	 * la fonction d'affichage appeler par le constructeur
	 */ 

	public final  void Display() {
		this.setTitle("Projet Java - Master ACSIS - 2014/2015 Recherche par mots-clés dans"
				+ "des données RDF (Groupe 4 : Rami - Amine - Ameur - Zakaria - Ania - Nadia)");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1100, 700));
		this.setLocation(0,0);  
		this.setResizable(false);	

		this.getContentPane().add(this.createHeader(), BorderLayout.NORTH);
		this.getContentPane().add(this.createSideBar(), BorderLayout.WEST);
		this.getContentPane().add(this.createContent(), BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}

	/**
	 * le constructeur de boutton personaliser
	 */

	private final JButton buttFactory(String buttonName, Color fontCouleur, Color backgroundCouleur, int width, int height){
		JButton button = new JButton();
		JLabel  buttLabel = new JLabel(buttonName);
		buttLabel.setForeground(fontCouleur);
		button.setBackground(backgroundCouleur);
		button.setPreferredSize(new Dimension(width, height)); 
		button.setBorder(new EmptyBorder(5,10,5,10));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.add(buttLabel);
		return button;
	}


}