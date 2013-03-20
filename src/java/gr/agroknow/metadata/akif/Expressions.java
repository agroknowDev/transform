package gr.agroknow.metadata.akif ;

import org.json.simple.JSONArray;

public class Expressions
{
	private String url ;
	private String mime ;
	private String language ;
	
	public void setMime( String mime )
	{
		this.mime = mime ;
	}
	
	public void setUrl( String url )
	{
		this.url = url ;
	}
	
	public void setLanguage( String language )
	{
		this.language = language ;
	}
	
	public String toJSONString()
	{
		return "[{\"language\":\"" + language + "\",\"manifestations\":[{\"parameter\":\"" + mime +
		"\",\"items\":[{\"broken\":false,\"url\":\"" + url + "\"}],\"name\":\"experience\"}],}]" ;
	}
	
	public JSONArray toJSONArray()
	{
		return (JSONArray) org.json.simple.JSONValue.parse( toJSONString() ) ;
	}
	
}
