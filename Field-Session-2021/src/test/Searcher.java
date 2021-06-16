package test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Searcher {

	public ArrayList<Segment> search(String access_token, double min_lat, double min_long, double max_lat, double max_long, int min_cat, int max_cat) {
		String inline = "";
		
		String url_fragment = "https://www.strava.com/api/v3/segments/explore?bounds=" + min_lat + "," + min_long + "," + max_lat + "," + max_long + "&activity_type=riding&min_cat=" + min_cat + "&max_cat=" + max_cat + "&access_token=" + access_token;
		
		try {
			URL url = new URL(url_fragment);
			
			//Parse URL into HttpURLConnection
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			//Set the request method to "GET"
			con.setRequestMethod("GET");
			//Create the connection bridge.
			con.connect();
			
			//Get the response code.
			int response_code = con.getResponseCode();
			//System.out.println("Response Code: " + response_code);
			
			if(response_code != 200) {
				throw new RuntimeException("HttpResponseCode: " + response_code);
			} else {
				//Use scanner to read the data from the stream.
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext()) {
					inline+=sc.nextLine();
				}
				
				//Test this worked by printing the data.
				//System.out.println("\nJSON response in String format:");
				//System.out.println(inline);
				
				//Close the scanner.
				sc.close();
			}
			
			con.disconnect();
			
			ArrayList<Segment> results = new ArrayList<Segment>();
			
			JSONParser parse = new JSONParser();

			JSONObject jobj = (JSONObject)parse.parse(inline);
			
			for(Iterator iterator = jobj.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				
				if(key.equals("segments")) {
					JSONArray all_data = (JSONArray) jobj.get(key);
					
					//System.out.println(all_data);
					
					int size = all_data.size();
					
					for(int i = 0; i < size; i++) {
						
						JSONObject segment_data = (JSONObject) all_data.get(i);
						
						Segment segment_to_add = new Segment();
						
						segment_to_add.setDistance((double) segment_data.get("distance"));
						segment_to_add.setId(Math.toIntExact((long) segment_data.get("id")));
						segment_to_add.setName((String) segment_data.get("name"));
						segment_to_add.setClimb_category(Math.toIntExact((long) segment_data.get("climb_category")));
						segment_to_add.setAvg_grade((double) segment_data.get("avg_grade"));
						segment_to_add.setElev_diff((double) segment_data.get("elev_difference"));
						segment_to_add.setClimb_category_desc((String) segment_data.get("climb_category_desc"));

						segment_to_add.setStart_lat((Double) ((JSONArray) segment_data.get("start_latlng")).get(0));
						segment_to_add.setStart_long((Double) ((JSONArray) segment_data.get("start_latlng")).get(1));

						segment_to_add.setEnd_lat((Double) ((JSONArray) segment_data.get("end_latlng")).get(0));
						segment_to_add.setEnd_long((Double) ((JSONArray) segment_data.get("end_latlng")).get(1));
						
						results.add(segment_to_add);
					}
					
					/* for(int i = 0; i < results.size(); i++) {
						System.out.println(results.get(i));
						}
					*/
					
					return results;
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
