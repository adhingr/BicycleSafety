package test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class APITest {

	public static void main(String [] args) {
		
		System.out.println("Test of using account's refresh token to grab the current access token.");
		TokenGetter tokener = new TokenGetter();
		String access_token = tokener.get();
		
		System.out.println("");
		System.out.println("Test of using the access token to then grab the data from Lookout Mountain segment of Strava.");
		DataReader reader = new DataReader();
		reader.read(access_token);
	}
	
}
