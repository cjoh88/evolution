package com.machinelearning.model;

public class Environment {
	
	private static final int NUM_INDIVIDUALS = 50;
	
	private final Sensor[] sensors = {};
	private final Action[] actions = {};
	
	private Animal animals[] = new Animal[NUM_INDIVIDUALS];
	
	private Environment() {
		// Initialize Sensors
		for(Sensor s : sensors) {
			s.setEnvironment(this);
		}
		// Create Animals
		for(int i=0; i<animals.length; i++) {
			animals[i] = new Animal(sensors, actions);
		}
	}

}
