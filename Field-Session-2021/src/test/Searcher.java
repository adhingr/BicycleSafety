package test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Searcher {

	public void search(String access_token, double min_lat, double min_long, double max_lat, double max_long, int min_cat, int max_cat) {
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
			System.out.println("Response Code: " + response_code);
			
			if(response_code != 200) {
				throw new RuntimeException("HttpResponseCode: " + response_code);
			} else {
				//Use scanner to read the data from the stream.
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext()) {
					inline+=sc.nextLine();
				}
				
				//Test this worked by printing the data.
				System.out.println("\nJSON response in String format:");
				System.out.println(inline);
				
				//Close the scanner.
				sc.close();
			}
			
			con.disconnect();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}