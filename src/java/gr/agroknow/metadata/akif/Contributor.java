package gr.agroknow.metadata.akif;

import org.json.simple.JSONObject;

public class Contributor 
{

	JSONObject contributor ;
	
	public Contributor()
	{
		contributor = new JSONObject() ; 
	}
	
	@SuppressWarnings("unchecked")
	public Contributor( String role, String name, String date )
	{
		contributor = new JSONObject() ;
		contributor.put( "role", role ) ;
		contributor.put( "name", name ) ;
		contributor.put( "date", date ) ;
	}

	@SuppressWarnings("unchecked")
	public void setRole( String role )
	{
		contributor.put( "role", role ) ;
	}
	
	@SuppressWarnings("unchecked")
	public void setName( String name )
	{
		contributor.put( "name", name ) ;
	}
	
	@SuppressWarnings("unchecked")
	public void setDate( String date )
	{
		contributor.put( "date", date ) ;
	}
	
	public JSONObject toJSONObject()
	{
		return contributor ;
	}
	
	public String toJSONString()
	{
		return contributor.toJSONString() ;
	}
	
}
