package test;

import java.awt.Color;

import eu.jacquet80.minigeo.MapWindow;
import eu.jacquet80.minigeo.Point;
import eu.jacquet80.minigeo.Segment;

import java.util.ArrayList;

public class Plotter {
	//stores the specific instance of the window
	MapWindow window = new MapWindow();
	
	//stores the three arrayLists needed
	ArrayList<Double> lat = new ArrayList<Double>();
	ArrayList<Double> lon = new ArrayList<Double>();
	ArrayList<Double> haz = new ArrayList<Double>();
	
	//example constructor
	public Plotter(ArrayList<Double> la, ArrayList<Double> lo, ArrayList<Double> h) {
		this.lat = la;
		this.lon = lo;
		this.haz = h;
	}
	
	public void createPlot() {
		for(int i = 0; i < (haz.size()-1); i++) {
			if (haz.get(i) <= 2) {
				window.addSegment( new Segment( new Point(lat.get(i), lon.get(i)), new Point(lat.get(i+1), lon.get(i+1)), Color.GREEN));
			} else if (haz.get(i) <= 3) {
				window.addSegment( new Segment( new Point(lat.get(i), lon.get(i)), new Point(lat.get(i+1), lon.get(i+1)), Color.YELLOW));
			} else if (haz.get(i) <= 4) {
				window.addSegment( new Segment( new Point(lat.get(i), lon.get(i)), new Point(lat.get(i+1), lon.get(i+1)), Color.ORANGE));
			} else {
				window.addSegment( new Segment( new Point(lat.get(i), lon.get(i)), new Point(lat.get(i+1), lon.get(i+1)), Color.RED));
			}
		}
		
		window.setVisible(true);
	}

}

