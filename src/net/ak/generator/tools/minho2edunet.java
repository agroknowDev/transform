

package net.ak.generator.tools;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class minho2edunet {
    
    private Set ontologTerms;
    
    public Set getOntologTerms()
    {
        return ontologTerms;
    }

    public minho2edunet()
    {
        ontologTerms = new HashSet();
    }

    public void submitKeyword(String keyword)        
    {
        java.util.List lrts = TOOLS.getInstance().getOntologyTerm(keyword);
        if(lrts != null){
            System.out.println("check!"+lrts.toString());
            
            ontologTerms.addAll(lrts);}
    }
    
  /*  public static void main(String[] args) throws IOException
    {
         minho2edunet test = new minho2edunet();
         test.submitKeyword("Biotechnology");
    
    }*/

    
    
    
}

