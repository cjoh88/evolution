package com.machinelearning.model;

import com.badlogic.gdx.math.Vector2;
import com.machinelearning.Utility;
import com.machinelearning.diversity.DiversityPlot;
import com.machinelearning.model.action.Action;
import com.machinelearning.model.sensor.Sensor;
import com.machinelearning.statistics.statistics;

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
	private DiversityPlot DP = new DiversityPlot();

	private statistics statPlot = new statistics();

	
	/* Add sensors and actions to respective array
	 * 
	 * Example:
	 * 	private final Sensor[] sensors = {
	 * 		new Sensor1(),
	 * 		new Sensor2(),
	 * 		new Sensor3(),
	 * 	};
	 */
	
	private Animal prey[] = new Animal[Config.NUM_INDIVIDUALS_PREY];
	private Animal pred[] = new Animal[Config.NUM_INDIVIDUALS_PRED];
	private Plant food[] = new Plant[Config.NUM_PLANT];
	
	private GA gaPrey;
	private GA gaPred;
	
	public Environment() {
		// Initialize Sensors
		for(Sensor s : Config.sensors) {
			s.setEnvironment(this);
		}
		// Create Animals
		for(int i=0; i<Config.NUM_INDIVIDUALS_PREY; i++) {
			prey[i] = new Animal(this, Config.sensors, Config.actions, food);
		}
		for(int i=0; i<Config.NUM_INDIVIDUALS_PRED; i++) {
			pred[i] = new Animal(this, Config.sensors, Config.actions, prey);
		}
		// Create Food
		for(int i=0; i<food.length; i++) {
			food[i] = new Plant();
		}
		gaPrey = new GA(Config.CROSSOVER, Config.MUTATION, Config.SELECTION, Config.END_SELECTION);
		gaPred = new GA(Config.CROSSOVER, Config.MUTATION, Config.SELECTION, Config.END_SELECTION);
	}
	
	public void update(float delta) {
		for(Animal animal : prey) {
			animal.readSensorData();
		}
		for(Animal animal : pred) {
			animal.readSensorData();
		}
		for(Animal animal : prey) {
			animal.executeAction();
		}
		for(Animal animal : pred) {
			animal.executeAction();
		}
		for(Animal animal : prey) {
			animal.update(delta);
			//animal.update(1.0f);
		}
		for(Animal animal : pred) {
			animal.update(delta);
			//animal.update(1.0f);
		}
		time += delta;
		if(time >= Config.TIME_PER_GENERATION) {
			//TODO Create new generation
		
			
			int tmpFit=0;
			for (int i = 0; i < prey.length; i++) 
				tmpFit += prey[i].fitness();
			tmpFit = tmpFit/prey.length;
			
			statPlot.addFitness(tmpFit, prey[0].fitness(), generation);
			
			
			//System.out.println(Arrays.toString(animals[0].getGenome()));
			DP.plot(prey, generation);
			gaPrey.compute(prey);
			gaPred.compute(pred);
			time = 0;
			
			
			generation++;
			Utility.log("Generation " + generation + " has started.");
			
			
		}
		
	}
	
	public Animal[] getPrey() {
		return prey;
	}
	
	public Animal[] getPred() {
		return pred;
	}
	
	public Plant[] getPlant() {
		return food;
	}
	
	public Plant getNearestPlant(Vector2 position) {
		float distance = Float.MAX_VALUE;
		Plant result = null;
		for(Plant f : food) {
			float d = position.dst2(f.position);
			if(d < distance) {
				distance = d;
				result = f;
			}
		}
		return result;
	}
	
	public Animal getNearestAnimal(Vector2 position) {
		float distance = Float.MAX_VALUE;
		Animal result = null;
		for(Animal a: prey) {
			float d = position.dst2(a.getPosition());
			if(d < distance) {
				distance = d;
				result = a;
			}
		}
		return result;
	}
	
	public Animal getNearestPredator(Vector2 position) {
		float distance = Float.MAX_VALUE;
		Animal result = null;
		for(Animal a: pred) {
			float d = position.dst2(a.getPosition());
			if(d < distance) {
				distance = d;
				result = a;
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







