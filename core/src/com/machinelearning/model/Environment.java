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
	public static final int NUM_INDIVIDUALS = 250;
	public static final int NUM_FOOD = 10;
	
	// Width of the environment
	public static final int WIDTH = 80;
	
	// Height of the environment
	public static final int HEIGHT = 45;
	
	public static final int STEPS_PER_GENERATION = 2000;
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
			System.out.println("Step: " + step);
		}
		step++;
		if(step >= STEPS_PER_GENERATION) {
			//TODO Create new generation
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







