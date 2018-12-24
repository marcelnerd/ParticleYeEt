import classes.Plot;
import java.util.Scanner;

public class Orbit1 {

	public static void main(String[] args) {
		double speed;
		Scanner sc = new Scanner(System.in);
		System.out.println("What is the initial velocity of Earth?");
		speed = sc.nextDouble();
		OrbitPlot orbitPlot = new OrbitPlot("Plot of Planet Orbiting Sun", -10, 10, 1, -10, 10, 1, 1, speed);

		orbitPlot.yeet();
		
		
	}

}
