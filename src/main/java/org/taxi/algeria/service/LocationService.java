package org.taxi.algeria.service;

import java.util.ArrayList;

import org.taxi.algeria.database.FetchDatabase;
import org.taxi.algeria.model.L0cation;

public class LocationService {
	
	private FetchDatabase fetchDatabase;

	
	public LocationService()throws Exception{
		this.fetchDatabase= new FetchDatabase();
	}
	
	public ArrayList<L0cation> getLocations()throws Exception{	
		return fetchDatabase.getLocations();
	}
	
	public L0cation getLocation(int locationName) throws Exception{
		return fetchDatabase.getLocation(locationName);
	}

	public int insertLocation(L0cation location)throws Exception{
		return fetchDatabase.insertLocation(location);
	}

	
	
	
	
}
