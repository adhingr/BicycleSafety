package test;

public class Segment {
	String name;
	String climb_category_desc;
	
	int id;
	int climb_category;
	
	double avg_grade;
	
	double start_lat;
	double start_long;
	
	double end_lat;
	double end_long;
	
	double elev_diff;
	double distance;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClimb_category_desc() {
		return climb_category_desc;
	}
	public void setClimb_category_desc(String climb_category_desc) {
		this.climb_category_desc = climb_category_desc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClimb_category() {
		return climb_category;
	}
	public void setClimb_category(int climb_category) {
		this.climb_category = climb_category;
	}
	public double getAvg_grade() {
		return avg_grade;
	}
	public void setAvg_grade(double avg_grade) {
		this.avg_grade = avg_grade;
	}
	public double getStart_lat() {
		return start_lat;
	}
	public void setStart_lat(double start_lat) {
		this.start_lat = start_lat;
	}
	public double getStart_long() {
		return start_long;
	}
	public void setStart_long(double start_long) {
		this.start_long = start_long;
	}
	public double getEnd_lat() {
		return end_lat;
	}
	public void setEnd_lat(double end_lat) {
		this.end_lat = end_lat;
	}
	public double getEnd_long() {
		return end_long;
	}
	public void setEnd_long(double end_long) {
		this.end_long = end_long;
	}
	public double getElev_diff() {
		return elev_diff;
	}
	public void setElev_diff(double elev_diff) {
		this.elev_diff = elev_diff;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	
	
	@Override
	public String toString() {
		return "Segment [\nname=" + name + "\nclimb_category_desc=" + climb_category_desc + "\nid=" + id
				+ "\nclimb_category=" + climb_category + "\navg_grade=" + avg_grade + "\nstart_lat=" + start_lat
				+ "\nstart_long=" + start_long + "\nend_lat=" + end_lat + "\nend_long=" + end_long + "\nelev_diff="
				+ elev_diff + "\ndistance=" + distance + "\n]";
	}
}
