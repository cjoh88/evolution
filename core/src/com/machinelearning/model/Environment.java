package com.machinelearning.model;

import java.util.Arrays;
import java.util.Comparator;

import com.badlogic.gdx.math.Vector2;
import com.machinelearning.Utility;
import com.machinelearning.diversity.DiversityPlot;
import com.machinelearning.model.action.Action;
import com.machinelearning.model.sensor.Sensor;
import com.machinelearning.statistics.DiversityData;
import com.machinelearning.statistics.StatChart;
import com.machinelearning.statistics.Statistics;

public class Environment {

	// Number of individuals in the population
	// public static final int NUM_INDIVIDUALS = 10;
	// public static final int NUM_FOOD = 8;

	// Width of the environment
	// public static final int WIDTH = 80;

	// Height of the environment
	// public static final int HEIGHT = 45;

	// public static final int STEPS_PER_GENERATION = 6000;
	private int generation = 1;
	private double time = 0;
	// private DiversityPlot DP = new DiversityPlot();

	private StatChart statPlot = new StatChart();
	Statistics preyPlot, predPlot;
	DiversityData diversityPlot;

	// private statistics statPlotPrey = new statistics("PREY:
	// Fitness/Generation");
	// private statistics statPlotPredator = new statistics("PREDATOR:
	// Fitness/Generation");

	/*
	 * Add sensors and actions to respective array
	 * 
	 * Example: private final Sensor[] sensors = { new Sensor1(), new Sensor2(),
	 * new Sensor3(), };
	 */

	private Animal prey[] = new Animal[Config.NUM_INDIVIDUALS_PREY];
	private Animal pred[] = new Animal[Config.NUM_INDIVIDUALS_PRED];
	private Plant food[] = new Plant[Config.NUM_PLANT];

	public GA gaPrey;
	public GA gaPred;

	public Environment() {
		// Initialize Sensors
		for (Sensor s : Config.sensors) {
			s.setEnvironment(this);
		}

		// Create Food
		for (int i = 0; i < food.length; i++) {
			food[i] = new Plant();
		}

		// Create Animals
		for (int i = 0; i < Config.NUM_INDIVIDUALS_PREY; i++) {
			prey[i] = new Animal(this, Config.sensors, Config.actions, food);
		}
		for (int i = 0; i < Config.NUM_INDIVIDUALS_PRED; i++) {
			pred[i] = new Animal(this, Config.sensors, Config.actions, prey);
		}

		
		if(Config.INIT_SET_GENOME_PREY)
			for (int i = 0; i < prey.length; i++)
				prey[i].setGenome(Config.INIT_GENOME_PREY);

		if(Config.INIT_SET_GENOME_PRED)
			for (int i = 0; i < pred.length; i++)
				prey[i].setGenome(Config.INIT_GENOME_PRED);
		
		
		if (Config.PLOT_STATS) {
			preyPlot = statPlot.statAdd("PREY fitness/generation", "Generation", "Fitness");
			predPlot = statPlot.statAdd("Predator fitness/generation", "Generation", "Fitness");
			if (Config.PLOT_DIVERSITY)
				diversityPlot = statPlot.addPlot("Population diversity", "Generation", "Diversity");
		}

		gaPrey = new GA(Config.CROSSOVER, Config.MUTATION, Config.SELECTION, Config.END_SELECTION);
		gaPred = new GA(Config.CROSSOVER, Config.MUTATION, Config.SELECTION, Config.END_SELECTION);
	}

	public void update(float delta) {
		for (Animal animal : prey) {
			if (animal.alive) {
				animal.fitness++;		
				animal.readSensorData();
			}
		}
		
//		for (Animal animal : pred) //REMOVE THIS LOOP
//			animal.setMaxSpeed(0.0f);
		
		for (Animal animal : prey)
			if (animal.alive)
				animal.consumeEnergy();	
		
		for (Animal animal : pred)
			if (animal.alive)
				animal.consumeEnergy();
		
		for (Animal animal : pred) {
			if (animal.alive)
				animal.readSensorData();
		}
		for (Animal animal : prey) {
			if (animal.alive)
				animal.executeAction();
		}
		for (Animal animal : pred) {
			if (animal.alive)
				animal.executeAction();
		}
		for (Animal animal : prey) {
			if (animal.alive)
				animal.update(delta);
			// animal.update(1.0f);
		}
		for (Animal animal : pred) {
			if (animal.alive)
				animal.update(delta);
			// animal.update(1.0f);
		}
		
		boolean allDead = true;
		for (Animal animal : prey)
			if (animal.alive)
				allDead = false;
		
		if(allDead)
			time = Config.TIME_PER_GENERATION;
		
		time += delta;
		if (time >= Config.TIME_PER_GENERATION) {

			if (Config.PLOT_STATS) {
				Arrays.sort(prey, fitnessComparator);
				Arrays.sort(pred, fitnessComparator);

				int tmpFit = 0;
				for (int i = 0; i < prey.length; i++)
					tmpFit += prey[i].fitness();
				tmpFit = tmpFit / (prey.length + 1);

				if (prey.length > 0)
					preyPlot.addFitness(tmpFit, prey[0].fitness(), generation);

				tmpFit = 0;
				for (int i = 0; i < pred.length; i++)
					tmpFit += pred[i].fitness();
				tmpFit = tmpFit / (pred.length + 1);

				if (pred.length > 0)
					predPlot.addFitness(tmpFit, pred[0].fitness(), generation);

				// DP.plot(prey, pred, generation);
				if (Config.PLOT_DIVERSITY)
					diversityPlot.addFitness(prey, pred, generation);
			}

			if (prey.length > 0)
				gaPrey.compute(prey);

			if (pred.length > 0)
				gaPred.compute(pred);
			time = 0;

			generation++;
			Utility.log("Generation " + generation + " has started.");

		}

	}

	private boolean isAllPreyDead(Animal[] prey) {
		for (int i = 0; i < prey.length; i++) {
			if (prey[i].alive) {
				return false;
			}
		}
		return true;

	}

	private static Comparator<Animal> fitnessComparator = new Comparator<Animal>() {
		@Override
		public int compare(Animal a1, Animal a2) {
			return Float.compare(a2.fitness(), a1.fitness());
		}
	};

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
		for (Plant f : food) {
			float x = f.getPosition().x;
			float y = f.getPosition().y;
			//float d = position.dst2(x,y);
			float d = findDistance(position, f.getPosition());
			if (d < distance) {
				distance = d;
				result = f;
			}
		}
		return result;
	}

	public Animal getNearestAnimal(Animal animal) {
		float distance = Float.MAX_VALUE;
		Animal result = null;
		Vector2 position = animal.getPosition();
		for (Animal a : prey) {
			if (a == animal) {
				continue;
			}
			float x = a.getPosition().x;
			float y = a.getPosition().y;
			//float d = position.dst2(x,y);
			float d = findDistance(position, a.getPosition());
			if (d < distance) {
				distance = d;
				result = a;
			}
		}
		return result;
	}

	public Animal getNearestPredator(Animal animal) {
		float distance = Float.MAX_VALUE;
		Animal result = null;
		Vector2 position = animal.getPosition();
		for (Animal a : pred) {
			if(a == animal) {
				continue;
			}
			float x = a.getPosition().x;
			float y = a.getPosition().y;
			//float d = position.dst2(x,y);
			float d = findDistance(position, a.getPosition());
			if (d < distance) {
				distance = d;
				result = a;
			}
		}
		return result;
	}
	
	public float findDistance(Vector2 v1, Vector2 v2) {
		float distance = Float.MAX_VALUE;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				float d = v1.dst2((i-1) * Config.WIDTH + v2.x, (j-1) * Config.HEIGHT + v2.y);
				if(d<distance) {
					distance = d;
				}
			}
		}
		return distance;
	}

	public Vector2 findDirection(Vector2 v1, Vector2 v2) {
		float distance = Float.MAX_VALUE;
		Vector2 direction = new Vector2();
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				float d = v1.dst2((i-1) * Config.WIDTH + v2.x, (j-1) * Config.HEIGHT + v2.y);
				if(d<distance) {
					distance = d;
					direction.add(v2.cpy().sub(v1));
				}
			}
		}
		return direction.nor();
	}

	
	public Sensor[] getSensors() {
		return Config.sensors;
	}

	public Action[] getActions() {
		return Config.actions;
	}

}
