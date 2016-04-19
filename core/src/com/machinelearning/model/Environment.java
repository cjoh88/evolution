package com.machinelearning.model;

import com.machinelearning.model.sensor.PositionSensor;
import com.machinelearning.model.action.Action;
import com.machinelearning.model.action.TurnAction;
import com.machinelearning.model.sensor.FoodSensor;
import com.machinelearning.model.sensor.Sensor;

public class Environment {
	
	// Number of individuals in the population
	private static final int NUM_INDIVIDUALS = 50;
	
	// Width of the environment
	public static final int WIDTH = 80;
	
	// Height of the environment
	public static final int HEIGHT = 45;
	
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
			new TurnAction('l'),
			new TurnAction('r')
	};
	
	private Animal animals[] = new Animal[NUM_INDIVIDUALS];
	
	public Environment() {
		// Initialize Sensors
		for(Sensor s : sensors) {
			s.setEnvironment(this);
		}
		// Create Animals
		for(int i=0; i<animals.length; i++) {
			animals[i] = new Animal(sensors, actions);
		}
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
		}
	}
	
	public Animal[] getAnimals() {
		return animals;
	}

}
