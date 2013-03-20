package gr.agroknow.metadata.transformer.mods2akif;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import gr.agroknow.metadata.akif.*;

import net.zettadata.generator.tools.Toolbox;
import net.zettadata.generator.tools.ToolboxException;

%%
%class MODS2AKIF
%standalone
%unicode

%{
	private JSONObject akif ;
	private JSONArray contributors ;
	private Contributor contributor ;
	private LanguageBlocks lblocks ;
	private Expressions expressions ;
	private TokenBlock tblock ;
	private Rights rights ;
	
	// set default language to English	
	String language = "en" ;
	String text ;
	
    
    public void generate()
    {    
        generateTokenBlock() ;
        generateRights() ;
        generateContributors() ;
        generateLanguageBlocks() ;
    	generateExpressions() ;
    }

     
    @SuppressWarnings("unchecked")
    private void generateTokenBlock()
    {
		tblock = new TokenBlock() ;    
    	tblock.setAgeRange( "18-U" ) ;
    	akif.put("tokenBlock", tblock.toJSONObject() ) ;
    }
    
    @SuppressWarnings("unchecked")
    private void generateRights()
    {
    	akif.put( "rights", rights.toJSONObject() ) ;
    }
          	
    @SuppressWarnings("unchecked")
    private void generateContributors()
    {
    	akif.put("contributors", contributors ) ;
    }

    @SuppressWarnings("unchecked")
    private void generateLanguageBlocks()
    {
    	akif.put("languageBlocks", lblocks.toJSONObject() ) ;
    }

	@SuppressWarnings("unchecked")
    private void generateExpressions()
    {
    	akif.put("expressions", expressions.toJSONArray() ) ;
    }

    public String toString() 
    {
      return akif.toJSONString() ;
    }
    
	public JSONObject getAkif() {
		return akif;
	}

	@SuppressWarnings("unchecked")
	public void setSet(String set) {
		akif.put("set", set) ;
	}
	
	@SuppressWarnings("unchecked")
	public void setId(int id)
	{
		akif.put("identifier", new Integer( id ) ) ;
	}
		
	
	@SuppressWarnings("unchecked")
	public void init()
	{
		akif = new JSONObject() ;
		akif.put( "status", "published" ) ;
		akif.put( "generateThumbnail", new Boolean( true ) ) ;
		akif.put( "creationDate", utcNow() ) ;
		akif.put( "lastUpdateDate", utcNow() ) ;

	}
	
	private String utcNow() 
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
		return sdf.format(cal.getTime());
	}
	
	private String extract( String element )
	{	
		return element.substring(element.indexOf(">") + 1 , element.indexOf("</") );
	}
	
%}

%state MODS
%state CONTRIBUTOR

%%

<YYINITIAL>
{	
	"<mets xmlns="
	{
		yybegin( MODS ) ;
		contributors = new JSONArray() ;
		lblocks = new LanguageBlocks() ;
		expressions = new Expressions() ;
		rights = new Rights() ;
	}
}

<MODS>
{
	"</mets>"
	{
		yybegin( YYINITIAL ) ;
		generate() ;
	}
	
	"<mods:name>"
	{
		contributor = new Contributor() ;
		yybegin( CONTRIBUTOR ) ;
	}
	
	"<mods:abstract>".+"</mods:abstract>"
	{
		text = extract( yytext() ).trim() ;
		try
		{
			language = Toolbox.getInstance().detectLanguage( text ) ;
		}
		catch( ToolboxException tbe)
		{
			language = "en" ;
		}
		lblocks.setDescription( language, text ) ;
	}
	
	"<mods:topic>".+"</mods:topic>"
	{
		text = extract( yytext() ).trim() ;
		try
		{
			language = Toolbox.getInstance().detectLanguage( text ) ;
		}
		catch( ToolboxException tbe)
		{
			language = "en" ;
		}
		lblocks.setKeyword( language, text ) ;
	}
	
	"<mods:title>".+"</mods:title>"
	{
		text = extract( yytext() ).trim() ;
		try
		{
			language = Toolbox.getInstance().detectLanguage( text ) ;
		}
		catch( ToolboxException tbe)
		{
			language = "en" ;
		}
		lblocks.setTitle( language, text ) ;
	}
	
	"<premis:formatName>".+"</premis:formatName>"
	{
		expressions.setMime( extract( yytext() ).trim() ) ;
	}
	
	"<mods:languageTerm authority=\"rfc3066\">".+"</mods:languageTerm>"
	{
		text = extract( yytext() ).trim() ;
		try
		{
			language = Toolbox.getInstance().toISO6391( text ) ;
		}
		catch( ToolboxException tbe)
		{
			language = "en" ;
		}
		expressions.setLanguage( language ) ;
	}
	
	"<mods:identifier type=\"uri\">".+"</mods:identifier>"
	{
		expressions.setUrl( extract( yytext() ).trim() ) ;
	}
	
	"<mods:accessCondition type=\"useAndReproduction\">".+"</mods:accessCondition>"
	{
		text = extract( yytext() ).trim() ;
		try
		{
			language = Toolbox.getInstance().detectLanguage( text ) ;
		}
		catch( ToolboxException tbe)
		{
			language = "en" ;
		}
		if ( "open access".equals( text ) )
		{
			rights.setDescription( "en", text ) ;
		}
		else
		{
			rights.setDescription( language, text ) ;
		}
	}
	
}

<CONTRIBUTOR>
{
	"</mods:name>"
	{
		contributors.add( contributor.toJSONObject() ) ;
		yybegin( MODS ) ;
	}
	
	"<mods:roleTerm type=\"text\">".+"</mods:roleTerm>"
	{
		contributor.setRole( extract( yytext() ).trim() ) ;
	}
	
	"<mods:namePart>".+"</mods:namePart>"
	{
		contributor.setName( extract( yytext() ).trim() ) ;
	}
	
}


/* error fallback */
.|\n 
{
	//throw new Error("Illegal character <"+ yytext()+">") ;
}