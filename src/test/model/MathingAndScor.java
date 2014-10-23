package test.model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathingAndScor {
	private ArrayList<String> quiry;
	private ArrayList<LiteralTripl> literals;
	private static Pattern pattern;
	private static Matcher matcher;
	public MathingAndScor(ArrayList<String> quiry, ArrayList<LiteralTripl> literals)
	      { this.quiry=quiry;
	        this.literals=literals;
	        this.parcourAndCombin();
	        }
private void parcourAndCombin()
	   {for(LiteralTripl literal:this.literals){ this.matching(literal);literal.setScore(0);}}


private void matching(LiteralTripl literal )
	   {for(String quiri:this.quiry) this.test(literal,quiri);}

private void test(LiteralTripl literal,String quiry)
	   {  pattern = Pattern.compile("[[^a-z][^A-Z]]?"+quiry+"[[^a-z][^A-Z]]?");
          matcher = pattern.matcher(literal.getLiteral());
          if(matcher.find())literal.setScore(literal.getScore()+1f/(float)(this.quiry.size()));                         
          }
	 }
