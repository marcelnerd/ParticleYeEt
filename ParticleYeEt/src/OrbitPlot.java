import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.Plot;

public class OrbitPlot extends Plot {

	int ParticleNum;
	ParticleBox partBox;
	
	ArrayList<Particle> particleArray;
	
	public OrbitPlot(String title, double x1, double x2, double x3, double y1, double y2, double y3, int ParticleNumber, double speed, String situation) {
		super(title, x1, x2, x3, y1, y2, y3);
		ParticleNum = ParticleNumber+1;
		particleArray = new ArrayList<Particle>();
		particleArray.add(new Particle(0, CelestialValues.SUN_MASS, 0, 0, 0, 0, Color.YELLOW, "sun", this)); //Add sun at center of system
		partBox = new ParticleBox();
		
		for(int i = 1; i < ParticleNum; i++) {
			particleArray.add(new Particle(i, CelestialValues.EARTH_MASS, 0, i, speed, 0, getColorById(i), null, this));
		}
		
		if(situation != null) {
			setMimic(situation);
		}
	}
	
	public void yeet() {
		Particle particle;
		//Iterator<Particle> it;
		
		while(true) {
			
			for(int i = 0; i< particleArray.size(); i++) {
				particle = particleArray.get(i);
				particle.yeet(particleArray);
			}
			 /*for(it = particleArray.iterator(); it.hasNext(); ) {
				particle = it.next();
				particle.yeet(particleArray);
			} */
		}
	}
	
	public ArrayList<Particle> getParticleArray() {
		return particleArray;
	}
	
	private void setMimic(String situation) { //Set up system in a certain situation
		switch(situation) {
			case "attraction test": // Attraction test; Use 2 planets
				particleArray.get(0).setMass(0);
				particleArray.get(1).setMass(5);
				particleArray.get(1).setPosY(-4);
				particleArray.get(1).setPosX(-4);
				particleArray.get(1).setXSpeed(5);
				particleArray.get(2).setPosY(-7);
				break;
				
			case "binary orbit": //Binary star orbit
				particleArray.get(1).setPos(0, 2);
				particleArray.get(1).setXSpeed(-1);
				particleArray.get(1).setMass(0.5);
				particleArray.get(2).setPos(-1, -2);
				particleArray.get(2).setXSpeed(1);
				particleArray.get(2).setMass(0.5);
				break;
				
			case "earth and moon": //Earth Orbiting Moon ; May not be accurate
				particleArray.get(2).setPos(particleArray.get(0).getPosX() + CelestialValues.MOON_DISTANCE, particleArray.get(0).getPosY() + CelestialValues.MOON_DISTANCE);
				particleArray.get(2).setMass(CelestialValues.MOON_MASS);
				break;
		}
	}
	
	private Color getColorById(int id) {
		switch(id) {
		case 1:
			return Color.RED;
		case 2:
			return Color.BLUE;
		case 3:
			return Color.BLACK;
		default:
			return Color.ORANGE;
		}
	}
	
	public class ParticleBox {
		JFrame frame;
		JPanel panel;
		JButton particleButton;
		JTextField partMass, partX, partY, partSpeed;
		TextPrompt massPrompt, xPrompt, yPrompt, speedPrompt;
		
		public ParticleBox() {
			frame = new JFrame("Add Particle");
			panel = new JPanel();
			particleButton = new JButton("Add Particle");
			partMass = new JTextField(10);
			partX = new JTextField(10);
			partY = new JTextField(10);
			partSpeed = new JTextField(10);
			
			massPrompt = new TextPrompt("Particle Mass", partMass);
			xPrompt = new TextPrompt("Particle X-Position", partX);
			yPrompt = new TextPrompt("Particle Y-Position", partY);
			speedPrompt = new TextPrompt("Particle Speed", partSpeed);
			
			frame.add(panel);
			panel.add(partMass);
			panel.add(partX);
			panel.add(partY);
			panel.add(partSpeed);
			panel.add(particleButton);

			particleButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					particleArray.add(new Particle(particleArray.size(), CelestialValues.EARTH_MASS, Double.parseDouble(partX.getText()), Double.parseDouble(partY.getText()), Double.parseDouble(partSpeed.getText()), 0, getColorById(particleArray.size()), null, OrbitPlot.this));
				}
				
			});
			
			//OTHER STUFF
			
			frame.setPreferredSize(new Dimension(500, 100));
			frame.setSize(500, 100);
			frame.setVisible(true);
		}
		
	}

}
