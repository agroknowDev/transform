package gr.agroknow.metadata.akif;

import java.util.Collection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TokenBlock 
{
	private JSONObject tblock ;
	
	public TokenBlock()
	{
		tblock = new JSONObject() ;
	}
	
	@SuppressWarnings("unchecked")
	public void setAgeRange( String ageRange )
	{
		tblock.put( "ageRange", ageRange ) ;
	}

	@SuppressWarnings("unchecked")
	public void setAgeRange( String minAge, String maxAge )
	{
		tblock.put( "ageRange", minAge + "-" + maxAge ) ;
	}
	
	@SuppressWarnings("unchecked")
	public void setEndUSerRoles( Collection<String> roles )
	{
		if ( tblock.containsKey( "endUserRoles" ) )
		{
			JSONArray tmp = (JSONArray)tblock.get( "endUserRoles" ) ;
			tmp.addAll( roles ) ;
			tblock.put( "endUserRoles" , tmp ) ;
		}
		else
		{
			tblock.put( "endUserRoles" , roles ) ;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setEndUSerRole( String role )
	{
		JSONArray roles = new JSONArray() ;
		roles.add( role ) ;
		setEndUSerRoles( roles ) ;
	}
	
	
	@SuppressWarnings("unchecked")
	public void setContexts( Collection<String> contexts )
	{
		if ( tblock.containsKey( "contexts" ) )
		{
			JSONArray tmp = (JSONArray)tblock.get( "contexts" ) ;
			tmp.addAll( contexts ) ;
			tblock.put( "contexts" , tmp ) ;
		}
		else
		{
			tblock.put( "contexts" , contexts ) ;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setContext( String context )
	{
		JSONArray contexts = new JSONArray() ;
		contexts.add( context ) ;
		setLearningResourceTypes( contexts ) ;
	}
	
	
	@SuppressWarnings("unchecked")
	public void setLearningResourceTypes( Collection<String> lrts )
	{
		if ( tblock.containsKey( "learningResourceTypes" ) )
		{
			JSONArray tmp = (JSONArray)tblock.get( "learningResourceTypes" ) ;
			tmp.addAll( lrts ) ;
			tblock.put( "learningResourceTypes" , tmp ) ;
		}
		else
		{
			tblock.put( "learningResourceTypes" , lrts ) ;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void setLearningResourceType( String lrt )
	{
		JSONArray lrts = new JSONArray() ;
		lrts.add( lrt ) ;
		setLearningResourceTypes( lrts ) ;
	}
	
	public JSONObject toJSONObject()
	{
		return tblock ;
	}
	
	public String toJSONString()
	{
		return tblock.toJSONString() ;
	}
	
}
