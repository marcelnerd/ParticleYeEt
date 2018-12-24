import classes.Plot;
import java.util.Scanner;

public class Orbit1 {

	/* public static void simulateOrbit(double speed) {
		double x, y, vx, vy, ax, ay, G, dt, t, massOfEarth, distance, orbitalVelocity, massOfSun, forceOfG, ymid, vymid, aymid, xmid, vxmid, axmid;
		dt = 0.001;
		t = 0;
		G = 39.660;
		massOfSun = 1;
		massOfEarth = 0.000003003;
		ax = 0;
		vy = speed;
		vx = 0;
		y = 0;
		x = 6;
		while(t <= 1000000000) {
			distance = Math.sqrt(Math.abs((Math.pow(x, 2) + Math.pow(y, 2))));
			//forceOfG = (G * massOfSun * massOfEarth) / Math.pow(distance, 3);
			ax = (-1 * G * massOfSun * x) / Math.pow(distance, 2);
			ay = (-1 * G * massOfSun * y) / Math.pow(distance, 2);
			ymid = y + vy * .5 * dt;
			xmid = x + vx * .5 * dt;
			vymid = vy + ay * 0.5 * dt;
			vxmid = vx + ax * 0.5 * dt;
			aymid = (-1 * G * massOfSun * y) / Math.pow(distance, 2);
			axmid = (-1 * G * massOfSun * x) / Math.pow(distance, 2);
			y = y + (vymid * dt);
			x = x + (vxmid *dt);
			vy = vy + (aymid * dt);
			vx = vx + (axmid * dt);
			t = t + dt;
			System.out.println("Time: " + t + "; Distance From Sun: " + distance + "; X Position: " + x + "; Y Position: " + y + "; Velocity in the X direction: " + vx + "; Velocity in the Y direction" + vy + "; Acceleration in the X direction: " + ax + "; Acceleration in the Y direction: " + ay);
			orbitPlot.addPoint(x,y);
		}
	} */
	public static void main(String[] args) {
		double speed;
		Scanner sc = new Scanner(System.in);
		System.out.println("What is the initial velocity of Earth?");
		speed = sc.nextDouble();
		OrbitPlot orbitPlot = new OrbitPlot("Plot of Planet Orbiting Sun", -10, 10, 1, -10, 10, 1, 2, speed);

		//simulateOrbit(speed);
		long counter = 0;
		while(true) {
		orbitPlot.stepOrbit();
		counter++;
		}
	}

}
