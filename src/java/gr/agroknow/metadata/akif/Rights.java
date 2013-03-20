package gr.agroknow.metadata.akif;

import org.json.simple.JSONObject;

public class Rights 
{

	JSONObject rights ;
	
	public Rights()
	{
		rights = new JSONObject() ;
	}
	
	@SuppressWarnings("unchecked")
	public void setUrl( String url )
	{
		if ( (url != null) && !url.isEmpty()  )
		{
			rights.put( "url" , url ) ;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setDescription( String language, String description )
	{
		JSONObject obj ;
		if ( rights.containsKey( "description" ) )
		{
			obj = (JSONObject)rights.get( language ) ;
		}
		else
		{
			obj = new JSONObject() ;
		}
		obj.put( language , description ) ;
		rights.put( "description" , obj ) ;
	}
	
	public JSONObject toJSONObject()
	{
		return rights ;
	}
	
	public String toJSONString()
	{
		return rights.toJSONString() ;
	}
}
