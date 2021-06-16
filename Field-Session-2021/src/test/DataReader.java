package test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataReader {

	public DataObject read(String access_token, int segment_id) {
		String inline = "";
		
		String url_fragment = "https://www.strava.com/api/v3/segments/" + segment_id + "/streams?access_token=" + access_token;
		
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
								
				//Close the scanner.
				sc.close();
			}
			
			con.disconnect();
			
			//Fix the JSON string because Strava is unhelpful and annoying.
			String json_str = inline;
			
			json_str = "{\"pulled_data\":" + json_str;
			json_str = json_str + "}";
			
			//System.out.println(json_str);
			
			//Create a parser object to read the data into a usable format.
			JSONParser parse = new JSONParser();
			
			//Type caste parsed json data in json object.
			JSONObject jobj = (JSONObject)parse.parse(json_str);
			
			DataObject data = new DataObject();
			
			for(Iterator iterator = jobj.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				
				if(key.equals("pulled_data")) {
					JSONArray all_data = (JSONArray) jobj.get(key);
					
					JSONObject lat_long_obj = (JSONObject) all_data.get(0);
					JSONArray lat_long_arr = (JSONArray) lat_long_obj.get("data");
					
					int max_size = Math.toIntExact((long) lat_long_obj.get("original_size"));
					
					for(int i = 0; i < max_size; i++) {
						data.addLatitude((Double) ((JSONArray) lat_long_arr.get(i)).get(0));
						data.addLongitude((Double) ((JSONArray) lat_long_arr.get(i)).get(1));
					}

					JSONObject dist_obj = (JSONObject) all_data.get(1);
					JSONArray dist_arr = (JSONArray) dist_obj.get("data");
					
					for(int i = 0; i < max_size; i++) {
						data.addDistance((Double) dist_arr.get(i));
					}
					
					JSONObject alt_obj = (JSONObject) all_data.get(2);
					JSONArray alt_arr = (JSONArray) alt_obj.get("data");
					
					for(int i = 0; i < max_size; i++) {
						data.addAltitude((Double) alt_arr.get(i));
					}
					
					//System.out.println(data);
				}
			}
			
			return data;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
