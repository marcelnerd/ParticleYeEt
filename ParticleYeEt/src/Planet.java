import java.util.ArrayList;

import classes.Plot;

public class Planet {

	
	final double G = 39.660;
	double posX, posY, velX, velY, accelX, accelY, deltaTime, time, planetMass, distance, orbitalVelocity, massOfSun, forceOfG, ymid, vymid, aymid, xmid, vxmid, axmid;
	int id;
	OrbitPlot systemPlot;
	//double x, y, vx, vy, ax, ay, G, dt, t, planetMass, distance, orbitalVelocity, massOfSun, forceOfG, ymid, vymid, aymid, xmid, vxmid, axmid;
	//planetMass = 0.000003003;
	//massOfSun = 1;


	public Planet(int ID, double mass, double x, double y, double speedX, double speedY, OrbitPlot plot) {
		id = ID;
		deltaTime = 0.001;
		time = 0;
		massOfSun = CelestialValues.SUN_MASS;
		planetMass = mass;
		accelX = 0;
		accelY = 0;
		posY = y;
		posX = x;
		systemPlot = plot;
		
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
	
	public void stepOrbit() {
		double tempDist;
		
		distance = Math.sqrt(Math.abs((Math.pow(posX, 2) + Math.pow(posY, 2)))); //distance to sun	
		
		accelX = (-1 * G * massOfSun * posX) / Math.pow(distance, 2); //acceleration from sun
		accelY = (-1 * G * massOfSun * posY) / Math.pow(distance, 2); //acceleration from sun
		
		for(int i = 0; i < systemPlot.getArray().size(); i++) { // X acceleration from other planets
			if(systemPlot.getArray().get(i).getId() != id) {
				tempDist = Math.sqrt(Math.pow((posX - systemPlot.getArray().get(i).getPosX()), 2) + Math.pow((posY - systemPlot.getArray().get(i).getPosY()), 2));
				accelX += (-1 * G * systemPlot.getArray().get(i).getMass() * (posX - systemPlot.getArray().get(i).getPosX())) / Math.pow(tempDist, 2);
			}
		}
		
		for(int i = 0; i < systemPlot.getArray().size(); i++) { // Y acceleration from other planets
			if(systemPlot.getArray().get(i).getId() != id) {
				tempDist = Math.sqrt(Math.pow((posX - systemPlot.getArray().get(i).getPosX()), 2) + Math.pow((posY - systemPlot.getArray().get(i).getPosY()), 2));
				accelY += (-1 * G * systemPlot.getArray().get(i).getMass() * (posY - systemPlot.getArray().get(i).getPosY())) / Math.pow(tempDist, 2);
			}

		}
		
		/* ymid = posY + velY * .5 * deltaTime;
		xmid =  posX  + velX * .5 * deltaTime;
		vymid = velY + accelY * 0.5 * deltaTime;
		vxmid = velX + accelX * 0.5 * deltaTime;
		aymid = (-1 * G * massOfSun * posY) / Math.pow(distance, 2);
		axmid = (-1 * G * massOfSun * posX) / Math.pow(distance, 2);*/
		velX = velX + accelX * deltaTime;
		velY = velY + accelY * deltaTime;
		posX = posX + (velX * deltaTime);
		posY = posY + (velY * deltaTime);
		
		time = time + deltaTime;
		System.out.println("Planet: " + id + "; Time: " + time + "; Distance From Sun: " + distance + "; X Position: " + posX + "; Y Position: " + posY + "; Velocity in the X direction: " + velX + "; Velocity in the Y direction: " + velY + "; Acceleration in the X direction: " + accelX + "; Acceleration in the Y direction: " + accelY);
		systemPlot.addPoint(posX,posY);
	}
	
	public double getPosX() {
		return posX;
	}
	
	public double getPosY() {
		return posY;
	}
	
	public double getMass() {
		return planetMass;
	}
	
	public int getId() {
		return id;
	}
	
	public void setMass(double mass) {
		planetMass = mass;
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
