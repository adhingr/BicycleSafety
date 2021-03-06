package test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
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
		System.out.println("Test of using the access token to segment explore search on Strava.");
		Searcher finder = new Searcher();
		ArrayList<Segment> search_results = new ArrayList<Segment>();
		search_results = finder.search(access_token, 39.6010, -105.5149, 40.0010, -105.1149, 0, 5);
		
		//2764729
		int input_id = 0;
		
		/*Scanner input_scan = new Scanner(System.in);
		System.out.println("Enter Route Id:");
		input_id = input_scan.nextInt();
		*/
		
		System.out.println("");
		System.out.println("Test of using the access token to then grab the data from Lookout Mountain segment of Strava.");
		DataReader reader = new DataReader();
		DataObject returned_data = null;
		returned_data = reader.read(access_token, search_results.get(0).getId());
		
		
		//Data arrays:
		// Distance Array
		// Latitude Array
		// Longitude Array
		// Elevation/Altitude Array
	}
	
}