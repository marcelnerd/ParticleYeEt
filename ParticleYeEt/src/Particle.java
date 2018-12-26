import java.awt.Color;
import java.util.ArrayList;

import classes.Plot;

public class Particle {

	
	final double G = 39.660;
	static double time;
	double tempD, tempX, tempY, tempTheta, tempATotal;
	double posX, posY, velX, velY, accelX, accelY, deltaTime, particleMass, distance, ymid, vymid, aymid, xmid, vxmid, axmid;
	double prevX, prevY;
	int id;
	String name;
	Color color;
	OrbitPlot systemPlot;
	//double x, y, vx, vy, ax, ay, G, dt, t, particleMass, distance, orbitalVelocity, CelestialValues.SUN_MASS, forceOfG, ymid, vymid, aymid, xmid, vxmid, axmid;
	//particleMass = 0.000003003;
	//CelestialValues.SUN_MASS = 1;


	public Particle(int ID, double mass, double x, double y, double speedX, double speedY, Color c, String n, OrbitPlot plot) {
		id = ID;
		name = n;
		color = c;
		deltaTime = 0.001;
		time = 0;
		particleMass = mass;
		accelX = 0;
		accelY = 0;
		posY = y;
		posX = x;
		systemPlot = plot;
		prevX = x;
		prevY = y;
		
		if(posY > 0) {
			velX = -speedX;
		}
		else {
			velX = speedX;
		}
		
		if(posX > 0) {
			velY = speedY;
		}
		else {
			velY = -speedY;
		}
	}
	
	public void yeet(ArrayList<Particle> nearbyParticles) {
		
		distance = Math.sqrt(Math.pow((posX - nearbyParticles.get(0).getPosX()), 2) + Math.pow((posY - nearbyParticles.get(0).getPosY()), 2)); //distance to sun	
		
		//accelX = (-1 * G * CelestialValues.SUN_MASS * posX) / Math.pow(distance, 3); //acceleration from sun
		//accelY = (-1 * G * CelestialValues.SUN_MASS * posY) / Math.pow(distance, 3); //acceleration from sun
		accelX = 0;
		accelY = 0;
		
		for(int i = 0; i < nearbyParticles.size(); i++) { // Acceleration from other Particles
			if(nearbyParticles.get(i).getId() != id) {
				tempD = Math.sqrt(Math.pow((posX - nearbyParticles.get(i).getPosX()), 2) + Math.pow((posY - nearbyParticles.get(i).getPosY()), 2));
				//tempATotal = (-1 * G * nearbyParticles.get(i).getMass()) / Math.pow(tempD, 2);
				//tempTheta = getAngle(nearbyParticles.get(0), nearbyParticles.get(i));
				accelX += (-1 * CelestialValues.NATURAL_G * nearbyParticles.get(i).getMass() * (posX - nearbyParticles.get(i).getPosX())) / Math.pow(tempD, 3); // a = -(G*M*x)/(r^3); This is correct, including the multiplication by x-displacement and the r-to-the-third-power. Check notebook or read Feynman Lectures V1 Ch9.7
				accelY += (-1 * CelestialValues.NATURAL_G * nearbyParticles.get(i).getMass() * (posY - nearbyParticles.get(i).getPosY())) / Math.pow(tempD, 3);
			}

		}
		
		/* ymid = posY + velY * .5 * deltaTime;
		xmid =  posX  + velX * .5 * deltaTime;
		vymid = velY + accelY * 0.5 * deltaTime;
		vxmid = velX + accelX * 0.5 * deltaTime;
		aymid = (-1 * G * CelestialValues.SUN_MASS * posY) / Math.pow(distance, 2);
		axmid = (-1 * G * CelestialValues.SUN_MASS * posX) / Math.pow(distance, 2);*/
		velX += accelX * deltaTime;
		velY += accelY * deltaTime;
		posX += velX * deltaTime;
		posY += velY * deltaTime;
		
		time = time + deltaTime;
		System.out.println("Particle: " + id + "; Time: " + time + "; Distance From Sun: " + distance + "; X Position: " + posX + "; Y Position: " + posY + "; Velocity in the X direction: " + velX + "; Velocity in the Y direction: " + velY + "; Acceleration in the X direction: " + accelX + "; Acceleration in the Y direction: " + accelY);
	
		
		if(id != 0) { //If the particle is not the sun
			systemPlot.setColor(color);
			systemPlot.addPoint(prevX, prevY);
			systemPlot.setColor(Color.GREEN);
			systemPlot.addPoint(posX,posY);
		}
		else {
			systemPlot.setPointSize(10);
			systemPlot.setColor(color);
			systemPlot.addPoint(posX, posY);
			systemPlot.setPointSize(3);
			
		}
		
		prevX = posX;
		prevY = posY;
	
		
	}
	
	private double getAngle(Particle center, Particle point) {
		double dist = Math.sqrt(Math.pow((point.getPosX() - center.getPosX()), 2) + Math.pow((point.getPosY() - center.getPosY()), 2));
		//System.out.println("ffdfdfd" + "     " + ((point.getPosX() - center.getPosX()) / dist));
		System.out.println(center.getPosY());
		return Math.acos((point.getPosX() - center.getPosX()) / dist);
		
		//return Math.atan((point.getPosY() - center.getPosY()) / (point.getPosX() - center.getPosX()));
	}
	
	public double getPosX() {
		return posX;
	}
	
	public double getPosY() {
		return posY;
	}
	
	public double getMass() {
		return particleMass;
	}
	
	public int getId() {
		return id;
	}
	
	public void setMass(double mass) {
		particleMass = mass;
	}
	
	public void setPosX(double pos) {
		posX = pos;
	}
	
	public void setPosY(double pos) {
		posY = pos;
	}
	
	public void setXSpeed(double sp) {
		velX = sp;
	}
	
	public void setPos(double x, double y) {
		posX = x;
		posY = y;
	}
	
	public void setYSpeed(double s) {
		velY = s;
	}
	
}
