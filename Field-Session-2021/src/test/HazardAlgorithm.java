package test;

import java.util.ArrayList;

public class HazardAlgorithm {
	/*-----------------------------------------------------------------------------
	 * To future users of this code. The original version of this code should be
	 * included. It is written in MatLab by and was quite rough. This is the reason
	 * for so many arrays, also pieces of the old algorithm may not yet be
	 * implemented. This means that all the tests given to verify the old code are
	 * no longer valid. The new version of the algorithm will need to be tested
	 * thoroughly. A hazard plotting algorithm that gives false information, is not
	 * just useless but also dangerous. Unfortunately the current team will not have
	 * the time necessary to do said testing. The original code also included manual
	 * data collection which is no longer valid, reference the old code included
	 * with the final report to implement if the app version of this code is ever
	 * fully developed.
	 * -----------------------------------------------------------------------------
	 */

	// These arrays are used throughout the algorithm code.
	private ArrayList<Double> Lat = new ArrayList<Double>();
	private ArrayList<Double> Long = new ArrayList<Double>();
	// Array of latitude, longitude read in from Strava data
	// Originally was LatLong, but was immediately separated in old code

	private ArrayList<Integer> time = new ArrayList<Integer>();
	// Time array; Seems to just go up by 1 and match LatLong in length, unit of
	// time not determined

	private ArrayList<Double> distance = new ArrayList<Double>();

	private ArrayList<Double> altitude = new ArrayList<Double>();
	// Array of altitudes corresponding to each Latitude, Longitude
	// TODO read in from Strava data

	ArrayList<Double> hazardLevel = new ArrayList<Double>();

	static DataObject returned_data = null;

	private double[] GRADE_MULTI = { 1.1, 1.2, 1.3, 1.4, 1.5, 1.5, 1.6, 1.6, 1.7, 1.7, 1.8, 1.8, 1.8, 1.9, 1.9, 2.0 };
	// multipliers used for grade and curve calculations (Combined from original)

	private double[] VELOCITY_MULTI = { 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2.0 };
	// multipliers used for velocity and acceleration calculations (Combined from
	// original)
	// TODO Where do these come from? From what I can tell just randomly generated,
	// perhaps these are meant to be adjusted to tweak algorithm effectiveness.

	private ArrayList<Double> velocity = new ArrayList<Double>();

	private ArrayList<Double> acceleration = new ArrayList<Double>();

	private ArrayList<Double> grade = new ArrayList<Double>();

	private ArrayList<Double> runAvgVel = new ArrayList<Double>();

	private ArrayList<Double> runAvgGrade = new ArrayList<Double>();

	public void algorithm() {
			
			/* ---------------------------------------------------
			 * Generate rough velocity array
			 * ---------------------------------------------------
			 */
			//TODO is time[i]-time[i-1] always just 1??
			for(int i = 1; i < Lat.size(); i++) {
				velocity.add((distance.get(i) - distance.get(i-1))/(time.get(i) - time.get(i-1)));
				/* ---------------------------------------------------
				 * (Distance [i] - Distance [i-1])/ time[i]-time[i-1]
				 * ---------------------------------------------------
				 */
			}
			
			
			/* -------------------------------------------------------------------------------------------------
			 * Generate average velocity array, used for acceleration calculations. Unsure why data is averaged.
			 * Then generate acceleration array
			 * -------------------------------------------------------------------------------------------------
			 */
			//Define first value of average run velocity and acceleration arrays
			runAvgVel.add(velocity.get(0));
			acceleration.add(0.0);
			
			//Again, is time[i]-time[i-1] always just 1??
			for(int i = 1; i < velocity.size()-1; i++) {
				runAvgVel.add((velocity.get(i-1) + velocity.get(i) + velocity.get(i+1))/3);
				/*
				 * (velocity[i-1] + velocity[i] + velocity[i+1])/3
				 */
				acceleration.add((runAvgVel.get(i)-runAvgVel.get(i-1))/(time.get(i) - time.get(i-1)));
				/*
				 * Local_Average_Velocity[i] - Local_Average_Velocity[i - 1] / time[i]-time[i-1] = acceleration[i]
				 */
			}
			
			
			//Define last value of average run velocity and acceleration arrays
			runAvgVel.add(velocity.get(velocity.size()-1));
			acceleration.add(0.0);
			
			
			/*-------------------------------
			 * Create grade array
			 * ------------------------------
			 */
			grade.add(0.0);
			for(int i = 1; i < distance.size()-1; i++) {
				grade.add((altitude.get(i+1) - altitude.get(i-1))/(distance.get(i+1) - distance.get(i-1)));
				/*
				 * altitude[i+1] - altitude [i-1] / distance[i+1] - distance[i-1] = grade[i]
				 */
			}
			
			//No idea why last value being assigned as such
			grade.add(grade.get(velocity.size()-2));
			
			
			/*-------------------------------
			 * Create average grade array, unsure why it is averaged
			 * ------------------------------
			 */
			
			runAvgGrade.add(0, grade.get(0));
			
			for(int i = 1; i < velocity.size()-2; i++) {
				runAvgGrade.add((grade.get(i-1) + grade.get(i) + grade.get(i+1))/3);
			}
			
			//This seems wrong, in other code is end+1????? TODO What in the world is this
			runAvgGrade.add(runAvgGrade.get(runAvgGrade.size()-1));
			
			
			/* ----------------------------
			 * Unfinished : Turn radius calculations, determine if this should be cut or not
			 * ----------------------------
			 */
			
			/*
			 * To get the radius of turns use this formula:
			 * R = (L1_2 * L2_3 * L1_3)/(4*A)
			 * where LX_Y is the distance between point x and y
			 * A is the area of the triangle created by the 3 sides
			 */
			double side1;
			double side2;
			double side3;
			double s; // Semi perimeter, aka perimeter/2
			double area; //Area of triangle formed by all 3 points
			
			ArrayList<Double> radius = new ArrayList<Double>();
			radius.add(null); //Skip first value, make same as second value later
			for(int i = 1; i < Lat.size()-1; i++) {
				side1 = this.haversine(Lat.get(i-1), Long.get(i-1), Lat.get(i), Long.get(i));
				side2 = this.haversine(Lat.get(i), Long.get(i), Lat.get(i+1), Long.get(i+1));
				side3 = this.haversine(Lat.get(i-1), Long.get(i-1), Lat.get(i+1), Long.get(i+1));
				s = (side1 + side2+ side3) / 2; //Half of perimeter of triangle, used in area calculation
				/*
				 * Area = sqrt(s*(s*side1)*(s*side2)*(s*side3))
				 */
				area = Math.sqrt(s*(s-side1)*(s-side2)*(s-side3));
				
				radius.add((side1*side2*side3)/(4*area));
			}
			radius.set(0, radius.get(1));
			radius.add(radius.get(radius.size()-1)); //To ensure an equal length in radius array
			
			
			/* -----------------------------------------------------------------------------------------------
			 * These arrays were all hard coded in old code. They are used to generate the hazard level array.
			 * -----------------------------------------------------------------------------------------------
			 */
			
			ArrayList<Integer>GradeMultiX = new ArrayList<Integer>(); //Grade hazard multipliers
			
			for(int i = -1; i >= -16; i--) {
				GradeMultiX.add(i);
			}
			
			
			ArrayList<Integer>CurveMultiX = new ArrayList<Integer>(); //Curve hazard Multipliers, may be cut
			for(int i = 150; i >= 10; i=i-10) {
				CurveMultiX.add(i);
			}
			CurveMultiX.set(14, 2);
			
			
			ArrayList<Integer>VelocityMultiX = new ArrayList<Integer>(); //Velocity hazard multipliers
			for(int i = 0; i <= 45; i=i+5) {
				VelocityMultiX.add(i);
			}
			
			
			double [] AccMultiX = {0.01, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5}; //Hardcoded acceleration hazard multipliers
			
			
			
			/*  --------------------------------------------------------
			 * This is the start of the hazard level array calculations.
			 * ---------------------------------------------------------
			 */ 
			
			double [] GML = new double [velocity.size()]; //Using the size of the velocity array because if done correctly all the data arrays should be the same length
			double [] RML = new double [velocity.size()];
			double [] VML = new double [velocity.size()];
			double [] AML = new double [velocity.size()];
			
			//Grade
			//Don't full understand how this loop works why are there two different GRADEMULTI's
			for(int i = 0; i < GML.length-1; i++) {
				for(int j = 0; j < GradeMultiX.size(); j++) {
					if(runAvgGrade.get(i) > GradeMultiX.get(0)) {
						GML[i] = 1;
					}
					if(runAvgGrade.get(i) <= GradeMultiX.get(GradeMultiX.size()-1)) {
						GML[i] = GRADE_MULTI[GRADE_MULTI.length -1];
					}
					if(runAvgGrade.get(i) > GradeMultiX.get(j)) {
						GML[i] = GRADE_MULTI[j];
						break;
					}
				}
			
			
				//Curve 
				//GRADE_MULTI AND CURVE_MULTI were combined
				for(int j = 0; j < CurveMultiX.size(); j++) {
					if(radius.get(i) >= CurveMultiX.get(0)) {
						RML[i] = GRADE_MULTI[0];
					}
					if(radius.get(i) <= CurveMultiX.get(CurveMultiX.size()-1)) {
						RML[i] = GRADE_MULTI[GRADE_MULTI.length-1];
					}
					if(radius.get(i) > CurveMultiX.get(j)) {
						RML[i] = GRADE_MULTI[j];
						break;
					}
				}
			
			
				// Velocity
				for(int j = 0; j < VelocityMultiX.size(); j++) {
					if(runAvgVel.get(i) <= VelocityMultiX.get(0)) {
						VML[i] = VELOCITY_MULTI[0];
					}
					if(runAvgVel.get(i) >= VelocityMultiX.get(VelocityMultiX.size()-1)) {
						VML[i] = VELOCITY_MULTI[VELOCITY_MULTI.length - 1];
					}
					if(runAvgVel.get(i) >= VelocityMultiX.get(j)) {
						VML[i] = VELOCITY_MULTI[j];
						break;
					}
				}
				
				//These looks are quite likely to break with out of bounds errors
				//Acceleration
				//VELOCITY_MULTI and ACCELERATION_MULTI were combined
				for(int j = 0; j < AccMultiX.length; j++) {
					if(acceleration.get(i) <= AccMultiX[0]) {
						AML[i] = VELOCITY_MULTI[0];
					}
					if(acceleration.get(i) >= AccMultiX[AccMultiX.length - 1]) {
						AML[i] = VELOCITY_MULTI[VELOCITY_MULTI.length-1];
					}
					if(acceleration.get(i) < AccMultiX[j]) {
						AML[i] = VELOCITY_MULTI[j];
					}
				}
			}
			
			for(int i = 0; i < GML.length; i++) {
				hazardLevel.add(GML[i] * VML[i] * AML[i] * RML[i]);
			}
			
			for(int i = 0; i < hazardLevel.size(); i++) {
				if(hazardLevel.get(i) > 5) {
					hazardLevel.set(i, 5.0);
				}
				if(hazardLevel.get(i) < 1){
					hazardLevel.set(i, 1.0);
				}
			}
			
			//Skipping Manual hazard section
			
			double [] bigFactor = new double [hazardLevel.size()];
			for(int i = 0; i < hazardLevel.size(); i++) {
				if(hazardLevel.get(i) > 4.75) {
					if(AML[i] > RML[i]) {
						if(AML[i] > GML[i]){
							if(AML[i] > VML[i]) {
								bigFactor[i] = AML[i];
							}
						}
						else if(RML[i] > GML[i]) {
							if(RML[i] > VML[i]) {
								bigFactor[i] = RML[i];
							}
							else if(GML[i] > VML[i]) {
								bigFactor[i] = GML[i];
							}
							else {
								bigFactor[i] = VML[i];
							}
						}
					}
				}
			}
			
			//TODO Graph and plotting code here
		}

	public double haversine(double lat1, double lon1, double lat2, double lon2) {
		/*
		 * This code and equation being used from here
		 * https://www.geeksforgeeks.org/haversine-formula-to-find-distance-between-two-
		 * points-on-a-sphere/
		 */

		// distance between latitudes and longitudes
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);

		// convert to radians
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		// apply formula
		double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
		double rad = 6371;
		double c = 2 * Math.asin(Math.sqrt(a));
		return Math.abs(rad * c) * 1000;
	}

	public ArrayList<Double> getHazardLevel() {
		return hazardLevel;
	}

	private void getData() {
		Lat = returned_data.getLatitude();
		Long = returned_data.getLongitude();
		altitude = returned_data.getAltitude();
		distance = returned_data.getDistance();
		for (int i = 0; i < Lat.size(); i++) {
			time.add(i);
		}
	}

	public static void main(String[] args) {
		System.out.println("Test of using account's refresh token to grab the current access token.");
		TokenGetter tokener = new TokenGetter();
		String access_token = tokener.get();

		System.out.println("");
		System.out.println("Test of using the access token to segment explore search on Strava.");
		Searcher finder = new Searcher();
		ArrayList<Segment> search_results = new ArrayList<Segment>();
		search_results = finder.search(access_token, 39.6010, -105.5149, 40.0010, -105.1149, 0, 5);

		/*
		 * Scanner input_scan = new Scanner(System.in);
		 * System.out.println("Enter Route Id:"); input_id = input_scan.nextInt();
		 */

		System.out.println("");
		System.out.println(
				"Test of using the access token to then grab the data from Lookout Mountain segment of Strava.");
		DataReader reader = new DataReader();
		returned_data = reader.read(access_token, search_results.get(0).getId());

		HazardAlgorithm alg = new HazardAlgorithm();
		alg.getData();
		alg.algorithm();
		System.out.println(alg.getHazardLevel());

	}

}
