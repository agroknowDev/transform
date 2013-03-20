package gr.agroknow.metadata.akif;

import java.util.Collection;

import net.zettadata.generator.tools.Toolbox;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LanguageBlocks 
{

	JSONObject blocks ;
	
	public LanguageBlocks()
	{
		blocks = new JSONObject() ;
	}
	
	@SuppressWarnings("unchecked")
	public void setTitle( String language, String title )
	{
		JSONObject block ;
		if ( blocks.containsKey( language ) )
		{
			block = (JSONObject)blocks.get( language ) ;
		}
		else
		{
			block = new JSONObject() ;
		}
		block.put( "title" , title ) ;
		blocks.put( language , block ) ;
	}

	@SuppressWarnings("unchecked")
	public void setDescription( String language, String description )
	{
		JSONObject block ;
		if ( blocks.containsKey( language ) )
		{
			block = (JSONObject)blocks.get( language ) ;
		}
		else
		{
			block = new JSONObject() ;
		}
		block.put( "description" , description ) ;
		blocks.put( language , block ) ;
	}

	@SuppressWarnings("unchecked")
	public void setCoverage( String language, String coverage )
	{
		JSONObject block ;
		if ( blocks.containsKey( language ) )
		{
			block = (JSONObject)blocks.get( language ) ;
		}
		else
		{
			block = new JSONObject() ;
		}
		block.put( "coverage" , coverage ) ;
		blocks.put( language , block ) ;
	}

	@SuppressWarnings("unchecked")
	public void setKeywords( String language, Collection<String> keywords )
	{
		JSONObject block ;
		if ( blocks.containsKey( language ) )
		{
			block = (JSONObject)blocks.get( language ) ;
		}
		else
		{
			block = new JSONObject() ;
		}
		
		if ( block.containsKey( "keywords" ) )
		{
			JSONArray tmp = (JSONArray)block.get( "keywords" ) ;
			tmp.addAll( keywords ) ;
			block.put( "keywords" , tmp ) ;
		}
		else
		{
			block.put( "keywords" , keywords ) ;
		}
		blocks.put( language , block ) ;
	}
	
	@SuppressWarnings("unchecked")
	public void setKeyword( String language, String keyword )
	{
		JSONArray keywords = new JSONArray() ;
		keywords.add( keyword ) ;
		setKeywords( language, keywords ) ;
	}
	
	public JSONObject toJSONObject()
	{
		return blocks ;
	}
	
	public String toJSONString()
	{
		return blocks.toJSONString() ;
	}
}
