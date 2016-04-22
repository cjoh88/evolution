package com.machinelearning.model;

import com.machinelearning.model.sensor.PositionSensor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.machinelearning.Utility;
import com.machinelearning.model.action.Action;
import com.machinelearning.model.action.TurnAction;
import com.machinelearning.model.sensor.FoodSensor;
import com.machinelearning.model.sensor.Sensor;

public class Environment {
	
	// Number of individuals in the population
	public static final int NUM_INDIVIDUALS = 10;
	public static final int NUM_FOOD = 8;
	
	// Width of the environment
	public static final int WIDTH = 80;
	
	// Height of the environment
	public static final int HEIGHT = 45;
	
	public static final int STEPS_PER_GENERATION = 6000;
	private int generation = 1;
	private int step = 0;
	
	
	/* Add sensors and actions to respective array
	 * 
	 * Example:
	 * 	private final Sensor[] sensors = {
	 * 		new Sensor1(),
	 * 		new Sensor2(),
	 * 		new Sensor3(),
	 * 	};
	 */
	private final Sensor[] sensors = {
			new PositionSensor('x'),		// Sense the animals x position
			new PositionSensor('y'),		// Sense the animals y position
			new FoodSensor('x'),			// Sense closest foods x position
			new FoodSensor('y')				// Sense closest foods y position
	};
	private final Action[] actions = {
			new TurnAction('l'),			// Turn left
			new TurnAction('r')				// Turn right
	};
	
	private Animal animals[] = new Animal[NUM_INDIVIDUALS];
	private Food food[] = new Food[NUM_FOOD];
	
	public Environment() {
		// Initialize Sensors
		


		for(Sensor s : sensors) {
			s.setEnvironment(this);
		}
		// Create Animals
		for(int i=0; i<animals.length; i++) {
			animals[i] = new Animal(this, sensors, actions);
		}
		// Create Food
		for(int i=0; i<food.length; i++) {
			food[i] = new Food();
		}
		//double[] g = {-0.9701863478501088, -2.2066581642525844, 4.761877502405084, -0.7315744595900948, 1.584358790261458, -0.7119908045844757, 1.7847068721061516, -2.970641087586524, -3.0279272749906316, 4.4741752121600005, -2.1255276318671528, -0.13243553593068136, -0.5517508878885039, 0.22568959618378076, 4.047178258377074, -2.3715375973467223, -0.2928493458682689, 0.05453657060496018, -1.7168743214220354, 1.110187123223965, 1.3850891306352509, 0.38676945432506415, -0.8433309360073165, 0.6087295252807482, 1.887450101586186, 1.4258570325652666, 1.8585136925417756, -0.5849470057856481, -2.3526322742202965, 0.19697700768176354, -1.0805522056363948, -1.0427899012272444, 3.561204229108534, -1.6843682386896313, -0.5268086237270319, -1.0509691379389188, -1.2879472398563867, 0.3602606157195102, 1.6577953589854721, -1.4542498023068762, -0.8684977637903027, -0.04695903218885972, 0.21658447120322333, 1.0790790592008221, -2.529796101570664, -2.717106425904696, 1.5208527282350683, 1.579882438719034, -0.3092657998268631, 3.7099973335859433, -1.6291876881961336, -0.5451061886275013, -0.6808200399024757, -1.1523306426723354, -0.6136175903215819, 0.3529015434911601, -1.1551658805938185, 1.3849512027567266};

		
		//animals[0].setGenome(g);
	}
	
	public void update(float delta) {
		for(Animal animal : animals) {
			animal.readSensorData();
		}
		for(Animal animal : animals) {
			animal.executeAction();
		}
		for(Animal animal : animals) {
			animal.update(delta);
			//animal.update(1.0f);
		}
		if(step % 100 == 0) {
			//System.out.println("Step: " + step);
		}
		step++;
		if(step >= STEPS_PER_GENERATION) {
			//TODO Create new generation
			System.out.println(animals[0].fitness());
			
			System.out.println(Arrays.toString(animals[0].getGenome()));
			GA.createNewPopulation(animals);
			step = 0;
			generation++;
			Utility.log("Generation " + generation + " has started.");
			
			
		}
		
	}
	
	public Animal[] getAnimals() {
		return animals;
	}
	
	public Food[] getFood() {
		return food;
	}
	
	public Food getNearestFood(Vector2 position) {
		float distance = Float.MAX_VALUE;
		Food result = null;
		for(Food f : food) {
			float d = position.dst2(f.position);
			if(d < distance) {
				distance = d;
				result = f;
			}
		}
		return result;
	}
	
	public Sensor[] getSensors() {
		return sensors;
	}
	
	public Action[] getActions() {
		return actions;
	}
	

}







