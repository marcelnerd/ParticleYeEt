import java.awt.Color;
import java.util.ArrayList;

import classes.Plot;

public class Particle {

	
	final double G = 39.660;
	double posX, posY, velX, velY, accelX, accelY, deltaTime, time, particleMass, distance, ymid, vymid, aymid, xmid, vxmid, axmid;
	double prevX, prevY;
	int id;
	OrbitPlot systemPlot;
	//double x, y, vx, vy, ax, ay, G, dt, t, particleMass, distance, orbitalVelocity, CelestialValues.SUN_MASS, forceOfG, ymid, vymid, aymid, xmid, vxmid, axmid;
	//particleMass = 0.000003003;
	//CelestialValues.SUN_MASS = 1;


	public Particle(int ID, double mass, double x, double y, double speedX, double speedY, OrbitPlot plot) {
		id = ID;
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
	
	public void stepOrbit(ArrayList<Particle> nearbyParticles) {
		double tempD, tempX, tempY, tempTheta, tempATotal;
		
		distance = Math.sqrt(Math.pow((posX - nearbyParticles.get(0).getPosX()), 2) + Math.pow((posY - nearbyParticles.get(0).getPosY()), 2)); //distance to sun	
		
		//accelX = (-1 * G * CelestialValues.SUN_MASS * posX) / Math.pow(distance+0.00000000001, 2); //acceleration from sun
		//accelY = (-1 * G * CelestialValues.SUN_MASS * posY) / Math.pow(distance+0.00000000001, 2); //acceleration from sun
		
		for(int i = 0; i < nearbyParticles.size(); i++) { // Acceleration from other Particles
			if(nearbyParticles.get(i).getId() != id) {
				tempD = Math.sqrt(Math.pow((posX - nearbyParticles.get(i).getPosX()), 2) + Math.pow((posY - nearbyParticles.get(i).getPosY()), 2));
				tempATotal = (-1 * G * nearbyParticles.get(i).getMass()) / Math.pow(tempD, 2);
				tempTheta = getAngle(nearbyParticles.get(0), nearbyParticles.get(i));
				accelX += tempATotal * Math.cos(tempTheta);
				accelY += tempATotal * Math.sin(tempTheta);
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
		posY +=velY * deltaTime;
		
		time = time + deltaTime;
		System.out.println("Particle: " + id + "; Time: " + time + "; Distance From Sun: " + distance + "; X Position: " + posX + "; Y Position: " + posY + "; Velocity in the X direction: " + velX + "; Velocity in the Y direction: " + velY + "; Acceleration in the X direction: " + accelX + "; Acceleration in the Y direction: " + accelY);
		
		systemPlot.setColor(Color.RED);
		systemPlot.addPoint(prevX, prevY);
		
		systemPlot.setColor(Color.BLUE);
		systemPlot.addPoint(posX,posY);
		
		prevX = posX;
		prevY = posY;
	
		
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
	
	private double getAngle(Particle center, Particle point) {
		double dist = Math.sqrt(Math.pow((point.getPosX() - center.getPosX()), 2) + Math.pow((point.getPosY() - center.getPosY()), 2));
		return Math.acos(dist * (point.getPosX() - center.getPosX()));
	}
	
}
