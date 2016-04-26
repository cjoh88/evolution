package com.machinelearning.model;

import com.machinelearning.model.action.Action;
import com.machinelearning.model.action.TurnAction;
import com.machinelearning.model.crossover.Crossover2;
import com.machinelearning.model.crossover.RandomCrossover;
import com.machinelearning.model.mutation.Mutation;
import com.machinelearning.model.mutation.RandomMutation;
import com.machinelearning.model.selection.RandomSelection;
import com.machinelearning.model.selection.Selection2;
import com.machinelearning.model.sensor.FoodDirectionSensor;
import com.machinelearning.model.sensor.DirectionSensor;
import com.machinelearning.model.sensor.Sensor;

public class Config {
	
	// Number of individuals to survive per generation
	public final static int NUM_ELITISM = 2;
	
	// Number of new children produced by crossover (Might be rounded down depending on crossover operator)
	public final static int NUM_CROSSOVER_CHILDREN = 10;
	
	// Number of individuals in the population
	public static final int NUM_INDIVIDUALS = 40;
	
	// Number of food pieces in the environment
	public static final int NUM_FOOD = 8;
	
	// Width of the environment
	public static final int WIDTH = 80;
		
	// Height of the environment
	public static final int HEIGHT = 45;
		
	// Number of time steps per generation
	public static final int STEPS_PER_GENERATION = 6000;
		
		
	// Selection function for crossover
	public static final Selection2 SELECTION = new RandomSelection();
	
	// Crossover function
	public static final Crossover2 CROSSOVER = new RandomCrossover();
	
	// Mutation function
	public static final Mutation MUTATION = new RandomMutation();
	
	// Selection function for the remaining individuals
	public static final Selection2 END_SELECTION = new RandomSelection();
	
	// The Animals actions
	public final static Action[] actions = {
			new TurnAction('l'),			// Turn left
			new TurnAction('r')				// Turn right
	};
	
	// The Animals sensors
	public final static Sensor[] sensors = {
			new DirectionSensor('x'),		// Sense the animals direction	x
			new DirectionSensor('y'),		// Sense the animals direction	y
			new FoodDirectionSensor('x'),	// Sense closest foods direction	x
			new FoodDirectionSensor('y')	// Sense closest foods direction	y
	};
}
