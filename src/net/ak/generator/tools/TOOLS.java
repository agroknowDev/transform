package net.ak.generator.tools;

import java.io.*;
import java.util.*;
import org.apache.commons.io.IOUtils;
import org.json.simple.*;


public class TOOLS {

    private JSONObject minho2organic;
    private static TOOLS instance = null;
     
    protected TOOLS()     
    {
    
        minho2organic = new JSONObject();
        InputStream is;
        is = getClass().getResourceAsStream("/net/ak/generator/tools/data/minho2orgedu.json");
       
        try
        {
            minho2organic = (JSONObject)JSONValue.parse(IOUtils.toString(is, "UTF-8"));
        }
        catch(IOException ioe)
        {
            System.out.println("There was a problem while loading");
        }
    
    }
    
    
    
    public List getOntologyTerm(String minhoKey)
    {
        ArrayList result = new ArrayList();
        JSONArray terms = (JSONArray)minho2organic.get(minhoKey);
        if(terms == null)
        {
            System.err.println("No key equivalent to:"+ minhoKey.toString());
            return null;
        }
        Object obj;
        for(Iterator iterator = terms.iterator(); iterator.hasNext(); result.add((String)obj))
            obj = iterator.next();

        return result;
    }
   
    public static TOOLS getInstance()   
    {
            if(instance == null) {
                    instance = new TOOLS();
                }  
            
        return instance;
    }
                
}
