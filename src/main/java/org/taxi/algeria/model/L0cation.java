package org.taxi.algeria.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class L0cation {

	private int locationID;
	private String name;
	private float lat;
	private float lng;

	public L0cation() {
	}

	public L0cation(int locationID, String name,float lat, float lng) {
		this.locationID = locationID;
		this.name = name;
		this.lat=lat;
		this.lng=lng;
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

}
