import java.util.ArrayList;

import classes.Plot;

public class OrbitPlot extends Plot {

	int ParticleNum;
	static ArrayList<Particle> particleArray;
	
	public OrbitPlot(String title, double x1, double x2, double x3, double y1, double y2, double y3, int ParticleNumber, double speed) {
		super(title, x1, x2, x3, y1, y2, y3);
		ParticleNum = ParticleNumber+1;
		particleArray = new ArrayList<Particle>();
		
		particleArray.add(new Particle(0, CelestialValues.SUN_MASS, 0, 0, 0, 0, this)); //Add sun at center of system
		
		for(int i = 1; i < ParticleNum; i++) {
			particleArray.add(new Particle(i, CelestialValues.EARTH_MASS, 0, i+2, speed, 0, this));
		}
	
		
		/*
		// Attraction test
		particleArray.get(1).setMass(5);
		particleArray.get(1).setPosY(-4);
		particleArray.get(1).setPosX(-4);
		particleArray.get(1).setXSpeed(0);
		
		particleArray.get(2).setPosY(-7);
		*/
		
		/*
		// Binary orbit
		particleArray.get(1).setPos(0, 2);
		particleArray.get(1).setXSpeed(-1);
		particleArray.get(1).setMass(0.5);
		particleArray.get(2).setPos(-1, -2);
		particleArray.get(2).setXSpeed(1);
		particleArray.get(2).setMass(0.5);
		*/
		
		/*
		// Moon Orbiting Earth NOT WORKING YET //TODO Figure out proper speed of moon
		particleArray.get(2).setPos(particleArray.get(0).getPosX() + CelestialValues.MOON_DISTANCE, particleArray.get(0).getPosY() + CelestialValues.MOON_DISTANCE);
		particleArray.get(2).setMass(CelestialValues.MOON_MASS);
		//particleArray.get(2).setXSpeed(0);
		//particleArray.get(2).setYSpeed(0);
		*/
	}
	
	
	public void stepOrbit() {
		for(Particle p: particleArray) {
			p.stepOrbit(particleArray);
		}
	}
	
	public void yeet() {
		while(true) {
			for(Particle p: particleArray) {
				p.stepOrbit(particleArray);
			}
			//this.setLocation((int) particleArray.get(1).getPosX()-10, (int) particleArray.get(1).getPosY()+10);
		}
	}
	
	public ArrayList<Particle> getparticleArray() {
		return particleArray;
	}

}
