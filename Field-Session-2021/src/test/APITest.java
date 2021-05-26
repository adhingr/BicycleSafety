package test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class APITest {

	public static void main(String [] args) {
		
		String inline = "";
		
		try {
			URL url = new URL("https://www.strava.com/api/v3/segments/2764729/streams?access_token=c4e7445618322f48b205f78881352295cdbc94c6");
			
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
