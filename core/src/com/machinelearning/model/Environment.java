package com.machinelearning.model;

import com.machinelearning.model.sensor.DirectionSensor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.machinelearning.Utility;
import com.machinelearning.model.action.Action;
import com.machinelearning.model.sensor.FoodDirectionSensor;
import com.machinelearning.model.sensor.Sensor;

public class Environment {
	
	// Number of individuals in the population
	//public static final int NUM_INDIVIDUALS = 10;
	//public static final int NUM_FOOD = 8;
	
	// Width of the environment
	//public static final int WIDTH = 80;
	
	// Height of the environment
	//public static final int HEIGHT = 45;
	
	//public static final int STEPS_PER_GENERATION = 6000;
	private int generation = 1;
	private double time = 0;
	
	
	/* Add sensors and actions to respective array
	 * 
	 * Example:
	 * 	private final Sensor[] sensors = {
	 * 		new Sensor1(),
	 * 		new Sensor2(),
	 * 		new Sensor3(),
	 * 	};
	 */
	
	private Animal animals[] = new Animal[Config.NUM_INDIVIDUALS];
	private Food food[] = new Food[Config.NUM_FOOD];
	
	private GA ga;
	
	public Environment() {
		// Initialize Sensors
		for(Sensor s : Config.sensors) {
			s.setEnvironment(this);
		}
		// Create Animals
		for(int i=0; i<Config.NUM_INDIVIDUALS; i++) {
			animals[i] = new Animal(this, Config.sensors, Config.actions);
		}
		// Create Food
		for(int i=0; i<food.length; i++) {
			food[i] = new Food();
		}
		ga = new GA(Config.CROSSOVER, Config.MUTATION, Config.SELECTION, Config.END_SELECTION);
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
		if(time % 100 == 0) {
			//System.out.println("Step: " + step);
		}
		time += delta;
		if(time >= Config.TIME_PER_GENERATION) {
			//TODO Create new generation
			System.out.println(animals[0].fitness());
			
			//System.out.println(Arrays.toString(animals[0].getGenome()));
			ga.compute(animals);
			time = 0;
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
		return Config.sensors;
	}
	
	public Action[] getActions() {
		return Config.actions;
	}
	

}







