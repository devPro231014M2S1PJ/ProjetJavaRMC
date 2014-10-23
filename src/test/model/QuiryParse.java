package test.model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuiryParse {
	 private ArrayList<String> parsedString;
	 private String toParse;
	 private static Pattern pattern;
	 private static Matcher matcher;
	 
	public QuiryParse (String toParse)
	 { this.toParse=toParse;
	   parsedString=new ArrayList<String>();
	   parsing();
     }
	
    private final void parsing()
     { pattern = Pattern.compile("[[a-z][A-Z][0-9]]+");
       matcher = pattern.matcher(toParse);
       while(matcher.find()) parsedString.add(matcher.group().toLowerCase());
       
      
      }
    
    public ArrayList<String> getResult() {return this.parsedString;}
}

