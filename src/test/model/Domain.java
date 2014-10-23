package test.model;

import java.io.File;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class Domain {
	 ArrayList<ModelCreator> listModelCreators=new ArrayList<ModelCreator>();
	 QuiryParse parseQuiry;
	 MathingAndScor mathingScor;
	 public int i=0,lenght=0;
	 
	 
	    public void setQuiry(String quiry){this.parseQuiry=new QuiryParse(quiry);}
	    
	    public void addRDF(String path)
	      {File file=new File(path);
	       if(file.isDirectory())
	         {File[] f= file.listFiles();
	    	  for(File fil:f)
	    	     { boolean bol=true; for(char c:fil.getPath().toCharArray())
	    	       {if(c=='~')bol=false;}
	    	       if(bol)this.addRDF(fil.getPath());
	    	      }
	    	        	 //System.out.print("vv");/*this.addModels(fil.getPath())*/;};
	    	   }else {ModelCreator modelCreator=new ModelCreator(path);
	                  listModelCreators.add(modelCreator);
	                  modelCreator.modelAllTripl();
	                 // mathingScor= new MathingAndScor(parseQuiry.getResult(),modelCreator.getLiteralTriple());
	                   this.lenght++;
	                 //  quiryLiteral();
	    	        }
	       }
        public void quiryLiteral()
        {for(ModelCreator m:this.listModelCreators)
              this.mathingScor= new MathingAndScor(parseQuiry.getResult(),m.getLiteralTriple());
        }
        public void browseAndDisplay(DefaultTableModel model,float requestScor)
             {ModelCreator modelCreator= listModelCreators.get(i);
        	  browsLiteral(modelCreator, model,requestScor);
        	  if(requestScor==0)browsResource(modelCreator, model);
             }
        public void browsLiteral(ModelCreator modelCreator,DefaultTableModel model,float requiredScor)
             { for(LiteralTripl l :modelCreator.getLiteralTriple())
 	                {if(l.getScore()==requiredScor)
 	                     {model.addRow(new Object[]{l.getSubject().toString(),
	                                                l.getPredicat().toString(),
	                                                l.getLiteral().toString()     });}
                     }
             }
        public void browsResource(ModelCreator modelCreator,DefaultTableModel model)
              {  
        	     for(ResrcTripl l :modelCreator.getResrcTriple())
	                 {model.addRow(new Object[]{l.getSubject().toString(),
	                                            l.getPredicat().toString(),
	                                            l.getResource().toString() });
                      }
              }
          }
