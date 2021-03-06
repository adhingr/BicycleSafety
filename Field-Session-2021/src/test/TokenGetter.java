package test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TokenGetter {

	public String get() {
		String inline = "";
		
		try {
			URL url = new URL("https://www.strava.com/api/v3/oauth/token?client_id=66572&client_secret=afc6f019f7c3eb4e5e0233471b2c8c4c0ae59a58&grant_type=refresh_token&refresh_token=1eb0126800cea2fe60e83b769ab3c9ac87259a53");
			
			//Parse URL into HttpURLConnection
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			//Set the request method to "GET"
			con.setRequestMethod("POST");
			//Create the connection bridge.
			con.connect();
			
			//Get the response code.
			int response_code = con.getResponseCode();
			//System.out.println("Response Code: " + response_code);
			
			if(response_code != 200) {
				throw new RuntimeException("HttpResponseCode: " + response_code);
			} else {
				//Use scanner to read the data from the stream.
				Scanner sc = new Scanner(con.getInputStream());
				while(sc.hasNext()) {
					inline+=sc.nextLine();
				}

				//Close the scanner.
				sc.close();
			}
			
			con.disconnect();
			
			//Create a parser object to read the data into a usable format.
			JSONParser parse = new JSONParser();
			
			//Type caste parsed json data in json object.
			JSONObject jobj = (JSONObject)parse.parse(inline);
			
			String token = null;
			
			for(Iterator iterator = jobj.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				
				if(key.equals("access_token")) {
					System.out.println("Access Token: ");
					token = (String) jobj.get(key);
					System.out.println(token);
				}
			}
			
			return token;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
