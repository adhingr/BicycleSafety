package test;

import java.util.ArrayList;

public class DataObject {

	ArrayList<Double> latitude = new ArrayList<Double>();
	ArrayList<Double> longitude = new ArrayList<Double>();
	ArrayList<Double> distance = new ArrayList<Double>();
	ArrayList<Double> altitude = new ArrayList<Double>();
	
	public ArrayList<Double> getLatitude() {
		return latitude;
	}
	
	public void setLatitude(ArrayList<Double> latitude) {
		this.latitude = latitude;
	}
	
	public ArrayList<Double> getLongitude() {
		return longitude;
	}
	
	public void setLongitude(ArrayList<Double> longitude) {
		this.longitude = longitude;
	}
	
	public ArrayList<Double> getDistance() {
		return distance;
	}
	
	public void setDistance(ArrayList<Double> distance) {
		this.distance = distance;
	}
	
	public ArrayList<Double> getAltitude() {
		return altitude;
	}
	
	public void setAltitude(ArrayList<Double> altitude) {
		this.altitude = altitude;
	}
	
	public void setLatitudeIndex(double value, int index) {
		this.latitude.set(index, value);
	}
	
	public void setLongitudeIndex(double value, int index) {
		this.longitude.set(index, value);
	}
	
	public void setDistanceIndex(double value, int index) {
		this.distance.set(index, value);
	}
	
	public void setAltitudeIndex(double value, int index) {
		this.altitude.set(index, value);
	}
	
	public void addLatitude(double value) {
		this.latitude.add(value);
	}
	
	public void addLongitude(double value) {
		this.longitude.add(value);
	}
	
	public void addDistance(double value) {
		this.distance.add(value);
	}
	
	public void addAltitude(double value) {
		this.altitude.add(value);
	}

	@Override
	public String toString() {
		return "DataObject [\nlatitude=" + latitude + "\nlongitude=" + longitude + "\ndistance=" + distance
				+ "\naltitude=" + altitude + "\n]";
	}

	
	
}
