package com.machinelearning.model;

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
	private final Sensor[] sensors = {};
	private final Action[] actions = {};
	
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
