import java.util.ArrayList;

import classes.Plot;

public class OrbitPlot extends Plot {

	int planetNum;
	static ArrayList<Planet> planetArray;
	
	public OrbitPlot(String title, double x1, double x2, double x3, double y1, double y2, double y3, int planetNumber, double speed) {
		super(title, x1, x2, x3, y1, y2, y3);
		planetNum = planetNumber;
		planetArray = new ArrayList<Planet>();
		for(int i = 0; i < planetNum; i++) {
			planetArray.add(new Planet(i, CelestialValues.EARTH_MASS, 0, i+2, speed, 0, this));
		}
	
		
		/*
		// Attraction test
		planetArray.get(0).setMass(5);
		planetArray.get(0).setPosY(-4);
		planetArray.get(0).setPosX(-4);
		planetArray.get(0).setXSpeed(0);
		
		planetArray.get(1).setPosY(-7);
		*/
		
		/*
		// Binary orbit
		planetArray.get(0).setPos(0, 2);
		planetArray.get(0).setXSpeed(-1);
		planetArray.get(0).setMass(0.5);
		planetArray.get(1).setPos(-1, -2);
		planetArray.get(1).setXSpeed(1);
		planetArray.get(1).setMass(0.5);
		*/
		
		/*
		// Moon Orbiting Earth NOT WORKING YET //TODO Figure out proper speed of moon
		planetArray.get(1).setPos(planetArray.get(0).getPosX() + CelestialValues.MOON_DISTANCE, planetArray.get(0).getPosY() + CelestialValues.MOON_DISTANCE);
		planetArray.get(1).setMass(CelestialValues.MOON_MASS);
		//planetArray.get(1).setXSpeed(0);
		//planetArray.get(1).setYSpeed(0);
		*/
	}
	
	
	public void stepOrbit() {
		for(int i = 0; i < planetArray.size(); i++) {
			planetArray.get(i).stepOrbit();
		}
	}
	
	public ArrayList<Planet> getArray() {
		return planetArray;
	}

}
