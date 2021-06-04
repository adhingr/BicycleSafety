package main;

import java.util.ArrayList;

public class HazardAlgorithm {
	//TODO Assess which variables are nessicary and useful, there are way too many arrays
	
	private ArrayList<Double> Lat = new ArrayList<Double>(); 
	private ArrayList<Double> Long = new ArrayList<Double>();
	//Array of latitude, longitude read in from Strava data
	//Originally was LatLong, but is just broken down anyway
	//TODO integrate automated Strava data
	
	private ArrayList<Integer> time = new ArrayList<Integer>();
	//Time array; Seems to just go up by 1 and match LatLong in length
	
	private ArrayList<Double>distance = new ArrayList<Double>();
	//to be read in
	//TODO determine where this comes from
	
	private ArrayList<Double> altitude = new ArrayList<Double>();
	//Array of altitudes corresponding to each LatLong
	//TODO read in from Strava data
	
	private ArrayList<Double> manLat = new ArrayList<Double>();
	private ArrayList<Double> manLong = new ArrayList<Double>();
	//Array of Lat, Long of manual hazards, originally one array then were immediately separated
	//TODO rework
	
	private double [] GRADE_MULTI = {1.1, 1.2, 1.3, 1.4, 1.5, 1.5, 1.6, 1.6,
			1.7, 1.7, 1.8, 1.8, 1.8, 1.9, 1.9, 2.0};
	//multiplier used for grade and curve calculations (Combined from original)
	//Seems arbitrarily made TODO figure out where these come from
	
	private double [] VELOCITY_MULTI =  {1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2.0};
	//combined array of velocity and accel multipliers 
	//TODO Where do these come from
	
	private double [] bNeck = {1.3, 1.6, 2.0, 1.5};
	//25%, 50%, 75%+ traffic, 1.5 else
	//Might be unused due to lack of manual data TODO rework
	
	private double [] pHole = {1.2, 1.4, 1.6, 2.0};
	//Small, moderate, huge, unavoidable potholes
	//Again might be unused TODO rework
	
	private double [] blindTurn = {1, 1.2, 1.5, 1.8, 2.0};
	//Fully visible, small vis disruption, some vis, ???
	//TODO rework
	
	private double [] camber = {0.8, 1.2};
	//leaning into turn, out of turn
	//TODO rework
	
	private double [] traffic = {1.0, 1.2};
	//No traffic, minimal traffic
	//TODO rework
	
	private double [] numRiders = {0.8, 1.0, 1.2, 1.4, 1.6};
	//Alone, low, moderate, high, race TODO rework, maybe totally unnessicary
	
	private double [] weather = {1.2, 1.4, 1.6};
	//Damp, moderate, very wet TODO rework, unnessicary
	
	private double [] roadMaterial = {1.2, 1.5, 1.6, 1.7};
	//Some gravel, gravel on pave, lots of dirt, ????
	//TODO rework
	
	private int [] manMulti = {1,2,3,4,5,6,7,8,9,10};
	//TODO What is this?
	
	private ArrayList<Double> velocity = new ArrayList<Double>();
	//to be calculated
	
	private ArrayList<Double> acceleration = new ArrayList<Double>();
	//to be calculated
	
	private ArrayList<Double> runAvgVel = new ArrayList<Double>();
	//TODO determine purpose
	
	private ArrayList<Double> runAvgGrade = new ArrayList<Double>();
	//TODO determine purpose and where it comes from
	
	private ArrayList<Double> grade = new ArrayList<Double>();
	//to be calculated
	
	//Array X and Y are also listed, no idea what they are supposed to be
	
	
	public void algorithm() {
		double tol = 0.0001; //TODO random number, rework
		ArrayList<Double> actLat = new ArrayList<Double>();
		ArrayList<Double> actLong = new ArrayList<Double>();
		//More random Arrays with no explanation
		
		//TODO I am fairly sure this loop is nonsense
		for(int i = 0; i < Lat.size(); i++) {
			for(int j = 0; i < manLat.size(); j++) {
				if(Math.abs(Lat.get(i) - manLat.get(j)) < tol &&
						Math.abs(Long.get(i) - manLong.get(j)) < tol) {
					actLat.getIndex(j) = manLat.getIndex(j);
				}
			}
		}
		
		
		//Create velocity array, not sure what these loop parameters are
		for(int i = 1; i < Lat.size(); i++) {
			double temp = (distance.get(i) - distance.get(i-1))/(time.get(i) -time.get(i-1));
			velocity.add(i, temp);
		}
		
		runAvgVel.add(0, velocity.get(0));
		
		
		//Create runAvgVel and Acceleration arrays
		acceleration.add(0,  0.0);
		
		for(int i = 1; i < velocity.size(); i++) {
			double temp = (velocity.get(i-1) + velocity.get(i) + velocity.get(i+1))/3;
			runAvgVel.add(i, temp);
			temp = (runAvgVel.get(i)-runAvgVel.get(i-1))/(time.get(i) - time.get(i-1));
			acceleration.add(i, temp);
		}
		
		runAvgVel.add(velocity.size()-1, velocity.get(velocity.size()-1));
		acceleration.add(velocity.size()-1, 0.0);
		
		
		//Create grade array
		grade.add(0, 0.0);
		for(int i = 1; i < distance.size()-1; i++) {
			double temp = 100 * (altitude.get(i+1) - altitude.get(i-1))/
					(distance.get(i+1) - distance.get(i-1));
			grade.add(i, temp);
		}
		
		//No idea what is being done here
		grade.add(velocity.size()-1, grade.get(velocity.size()-2));
		
		runAvgGrade.add(0, grade.get(0));
		
		for(int i = 1; i < velocity.size()-2; i++) {
			double temp = (grade.get(i-1) + grade.get(i) + grade.get(i+1))/3;
			runAvgGrade.add(i, temp);
		}
		
		//This seems wrong, in other code is end+1?????
		runAvgGrade.add(runAvgGrade.size(), runAvgGrade.get(runAvgGrade.size()-1));
		
		
		//Turn radius calculations
		int R = 6371000; // No idea what this random number does
		//This is where array X and Y are used from beginning defining them here instead
		ArrayList<Double> X = new ArrayList<Double>();
		ArrayList<Double> Y = new ArrayList<Double>();
		
		X.add(0, 0.0);
		Y.add(0, 0.0);
		
		ArrayList<Double> LatR = new ArrayList<Double>();
		ArrayList<Double> LongR = new ArrayList<Double>();
		
		for(int i = 0; i < Lat.size(); i++) {
			LatR.add(Lat.get(i)*Math.PI/180);
			LongR.add(Long.get(i)*Math.PI/180);
			
			X.add(LongR(i)-LongR(0)*Math.cos((LatR.get(i) + LatR.get(i-1))/2);
			Y.add(LatR.get(i) - LatR(0) * R);
		}
		
		
		for(int i = 0; i < Lat.size()-1; i++) {
			//TODO figure out what is going on for the rest of the radius section 
			//and how to do it in java lines 147 -177 in old code
		}
		
		
		//Vel Multi Loop
		ArrayList<Integer>GradeMultiX = new ArrayList<Integer>();
		for(int i = -1; i >= -16; i--) {
			GradeMultiX.add(i);
		}
		ArrayList<Integer>CurveMultiX = new ArrayList<Integer>();
		for(int i = 150; i >= 10; i=i-10) {
			CurveMultiX.add(i);
		}
		CurveMultiX.set(15, 2)
		ArrayList<Integer>VelocityMultiX = new ArrayList<Integer>();
		for(int i = 0; i <= 45; i=i+5) {
			VelocityMultiX.add(i);
		}
		AccMultiX = {0.01, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5};
		System.out.println(AccMultiX);
	}
	public static void main(String[] args) {
		HazardAlgorithm alg = new HazardAlgorithm();
		alg.algorithm();
	}
}