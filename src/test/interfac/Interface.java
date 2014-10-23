package test.interfac;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import test.model.Domain;

public class Interface extends JFrame implements ActionListener{
	float isrequest=0;
	public DefaultTableModel model;
	JPanel sidebar,logo,header,content,footer,footerRight,body,grid,gridTop,gridTopRight;
    JButton parcourir=new JButton();
    JButton next=new JButton();
    JButton prev=new JButton();
	JLabel jlbMC=new JLabel("Mot-Clé"),
		   labtitApp = new JLabel(new ImageIcon("./src/app-tit.png"));
	JTextField jTxt=new JTextField("Tapez un mot clé");
	JTable tableau;
	Domain d;
	public Object[][] data ={};
	  public Interface(Domain d)
	    {  
		   this.sidebar=new JPanel(new GridLayout());
		   this.logo=new JPanel();  
		   this.header=new JPanel(new GridLayout());
		   this.content=new JPanel(new GridLayout());
		   this.footer=new JPanel(new GridLayout());
		   this.footerRight=new JPanel();
	       this.body=new JPanel(new GridLayout());
	       this.grid=new JPanel(new BorderLayout());
	       this.gridTop=new JPanel();
	       this.gridTopRight=new JPanel();
	       String  title[] = {"Sujet", "Predicat", "Ressource"};
	       model = new DefaultTableModel(data, title);
	       this.d=d;}
           
      public void display(){
    	  
    	  this.setTitle("Projet Java - Master ACSIS - 2014/2015 Recherche par mots-clés dans des données RDF (Groupe 4 : Rami - Amine - Ameur - Zakaria - Ania - Nadia)");
          this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          this.setPreferredSize(new Dimension(1200, 800));
          this.setLocation(10,10);  
          this.setResizable(false);
          
          sidebar.setPreferredSize(new Dimension(220, 800));
          sidebar.setBackground(new Color(25,36,46));
          sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(6,12,18)));
         
          jlbMC.setForeground(new Color(255,255,255));

          logo.setPreferredSize(new Dimension(220, 80));
          logo.setBackground(new Color(16,24,31));
          logo.setBorder(new EmptyBorder(10, 0, 0,0) );
          
          content.setPreferredSize(new Dimension(980, 800));
          content.setBackground(new Color(255,255,255));
          content.setBorder(BorderFactory.createEmptyBorder());
                    
          header.setPreferredSize(new Dimension(980, 80));
          header.setBackground(new Color(40,170,226));
          header.setBorder(new EmptyBorder(0, 40, 10, 0));
          header.setLayout(new BorderLayout());
          header.add(labtitApp,BorderLayout.WEST);

          footer.setPreferredSize(new Dimension(980, 60));
          footer.setBackground(new Color(255,255,255));
          footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(212,212,212)));
           
          footerRight.setPreferredSize(new Dimension(90, 50));
          footerRight.setBackground(new Color(255,255,255));
          footerRight.setBorder(new EmptyBorder(5, 5, 5, 5));
          
          footer.setLayout(new BorderLayout());
          footer.add(footerRight,BorderLayout.EAST);
          
          body.setPreferredSize(new Dimension(980, 720));
          body.setBackground(new Color(255,255,255));
                     
          grid.setPreferredSize(new Dimension(980,660));
          grid.setBackground(new Color(255,255,255));
          grid.setBorder(new EmptyBorder(60, 40, 40, 40));
           
          gridTop.setPreferredSize(new Dimension(200,20));
          gridTop.setBackground(new Color(255,255,255));
          grid.setBorder(new EmptyBorder(60, 40, 40, 40));
          
          gridTopRight.setPreferredSize(new Dimension(200,20));
          gridTopRight.setBackground(new Color(255,255,255));

          
          parcourir.setBackground(new Color(62, 196, 141));
          parcourir.setFont(new Font("arial", 1, 13));
          parcourir.setForeground(new Color(255, 255, 255));
          parcourir.setText("Parcourir un fichier");
          parcourir.setPreferredSize(new Dimension(190, 40)); 
          parcourir.setBorder(new EmptyBorder(0, 6, 0, 6) );
          
          next.setBackground(new Color(69, 89, 98));
          next.setFont(new Font("arial", 1, 13));
          next.setForeground(new Color(255, 255, 255));
          next.setText("Suivant");
          next.setPreferredSize(new Dimension(80, 40)); 
          next.setBorder(new EmptyBorder(0, 6, 0, 6) );
        
          prev.setBackground(new Color(69, 89, 98));
          prev.setFont(new Font("arial", 1, 13));
          prev.setForeground(new Color(255, 255, 255));
          prev.setText("Précedent");
          prev.setPreferredSize(new Dimension(100, 40)); 
          prev.setBorder(new EmptyBorder(0, 6, 0, 6) );
          
          parcourir.addActionListener(new ActionListener() 
              {public void actionPerformed(ActionEvent evt) 
                    { JFileChooser chooser = new JFileChooser();
    		          FileNameExtensionFilter filter = new FileNameExtensionFilter("RDF", "rdf");
    		          chooser.setFileFilter(filter);
    		          int returnVal = chooser.showOpenDialog(null);
    		          d.addRDF(chooser.getSelectedFile().toString());
    		         }
               });
          
          gridTop.add(parcourir);
          gridTopRight.add(prev);
          gridTopRight.add(next);
          tableau = new JTable( model );
          tableau.setPreferredScrollableViewportSize(new Dimension(600, 470));
          
          grid.setLayout(new BorderLayout());
          grid.add(gridTop,BorderLayout.LINE_START);
          grid.add(gridTopRight,BorderLayout.EAST);
          grid.add(new JScrollPane(tableau),BorderLayout.SOUTH);
         
          body.setLayout(new BorderLayout());
          body.add(grid,BorderLayout.NORTH);
          body.add(footer,BorderLayout.SOUTH);

          JButton btn=new JButton();
          btn.setBackground(new Color(254, 153, 0));
          btn.setFont(new Font("arial", 1, 13));
          btn.setForeground(new Color(255, 255, 255));
          btn.setText("Rechercher");
          btn.setPreferredSize(new Dimension(190, 40)); 
          btn.setBorder(new EmptyBorder(0, 6, 0, 6) );
          
         
          JLabel lab = new JLabel(new ImageIcon("./src/logo.png")); 
          logo.setLayout(new BorderLayout());
          logo.add(lab,BorderLayout.NORTH);
          
          JPanel znSearch = new JPanel(new FlowLayout(FlowLayout.LEFT));
          znSearch.setPreferredSize(new Dimension(220, 150));
          znSearch.setBorder(new EmptyBorder(20, 10, 10, 10) );
          znSearch.setBackground(null);
          
         
          jTxt.setBorder(new EmptyBorder(0, 6, 0, 6) );
          jTxt.setForeground(new Color(85,85,85));
          
          jTxt.setPreferredSize(new Dimension(190, 40)); 
          jTxt.setCursor(new Cursor(TEXT_CURSOR));
          znSearch.add(jlbMC);
          znSearch.add(jTxt);
          znSearch.add(btn);
          
          JPanel copy = new JPanel(new GridLayout());
          copy.setPreferredSize(new Dimension(220, 60));
          copy.setBorder(new EmptyBorder(0, 30, 10, 10) );
          copy.setBackground(null);
          
          JLabel lbCopy= new JLabel("Copyright © 2014, Groupe 4.");
          lbCopy.setPreferredSize(new Dimension(220, 60));
          lbCopy.setForeground(new Color(255,255,255));
          lbCopy.setFont(new Font("arial", 1, 11));
          copy.add(lbCopy);
          
          JButton btnClose=new JButton();
          btnClose.setBackground(new Color(224, 67, 67));
          btnClose.setFont(new Font("arial", 1, 13));
          btnClose.setForeground(new Color(255, 255, 255));
          btnClose.setText("Quitter");
          btnClose.setPreferredSize(new Dimension(90, 40)); 
          btnClose.setBorder(new EmptyBorder(0, 6, 0, 6) );
          btnClose.addActionListener(this);
          
          
          footerRight.setLayout(new BorderLayout());
          footerRight.add(btnClose,BorderLayout.EAST);
          
          this.getContentPane().setLayout(new BorderLayout());
          
          this.getContentPane().add(sidebar,BorderLayout.WEST);
          this.getContentPane().add(content,BorderLayout.EAST);
          
          sidebar.setLayout(new BorderLayout());
          sidebar.add(logo,BorderLayout.NORTH);
          sidebar.add(znSearch,BorderLayout.CENTER);
          sidebar.add(copy,BorderLayout.SOUTH);
        
          content.setLayout(new BorderLayout());
          content.add(header,BorderLayout.NORTH);
          content.add(body,BorderLayout.SOUTH);
          btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			  String quiry=jTxt.getText();
			  if(quiry.length()>3){
			   d.setQuiry(quiry);
			   d.quiryLiteral();
			   for(int k=0;k<5;k++)
			   {for(int j=0;j<model.getRowCount();j++)model.removeRow(j);}
			   tableau.repaint();
			   isrequest=1;
			   d.browseAndDisplay(model, isrequest);}else{isrequest=0;}
			   tableau.repaint();
				// TODO Auto-generated method stub
			
			}});
          
          next.addActionListener(new ActionListener() {
  			@Override
  			public void actionPerformed(ActionEvent e) 
  			{ if(d.i<d.lenght)
  			    {  for(int k=0;k<5;k++)
 			   {for(int j=0;j<model.getRowCount();j++)model.removeRow(j);}
  				  d.i++; d.browseAndDisplay(model,isrequest);
  			      tableau.repaint();}	
  			}
  		     });
          prev.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(d.i>0)
  			    {  for(int k=0;k<5;k++)
 			       {for(int j=0;j<model.getRowCount();j++)model.removeRow(j);}
				  d.i--; d.browseAndDisplay(model,isrequest);
  			      tableau.repaint();}
				// TODO Auto-generated method stub
				
			     }
		         });

          this.pack();
          this.setVisible(true);
    }	
	
      
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str.equals("Quitter")){
			int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter l'application?.", "Message de confirmation", JOptionPane.YES_NO_OPTION);
			if(option == 0) System.exit(0);
			else JOptionPane.getRootFrame().dispose();
			
		}
	}

}
