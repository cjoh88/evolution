package com.machinelearning.model;

import com.machinelearning.model.action.Action;
import com.machinelearning.model.action.SpeedAction;
import com.machinelearning.model.action.TurnAction;
import com.machinelearning.model.crossover.Crossover2;
import com.machinelearning.model.crossover.WeigthedAvg;
import com.machinelearning.model.mutation.Mutation;
import com.machinelearning.model.mutation.UniformMutation;
import com.machinelearning.model.selection.BestBreedAll;
import com.machinelearning.model.selection.RandomSelection;
import com.machinelearning.model.selection.RankSelection;
import com.machinelearning.model.selection.Selection2;
import com.machinelearning.model.sensor.DirectionSensor;
import com.machinelearning.model.sensor.FoodDirectionSensor;
import com.machinelearning.model.sensor.Sensor;

public class Config {
	
	// Number of individuals to survive per generation
	public final static int NUM_ELITISM = 10;
	
	// Number of new children produced by crossover (Might be rounded down depending on crossover operator)
	public final static int NUM_CROSSOVER_CHILDREN = 50;
	
	// Number of individuals in the population
	public static final int NUM_INDIVIDUALS = 60;
	
	// Number of food pieces in the environment
	public static final int NUM_FOOD = 30;
	
	// Width of the environment
	public static final int WIDTH = 80;
		
	// Height of the environment
	public static final int HEIGHT = 45;
		
	// Number of time in seconds per generation
	public static final double TIME_PER_GENERATION = 20.0;
		
		
	// Selection function for crossover
	public static final Selection2 SELECTION = new RankSelection();
	
	// Crossover function
	public static final Crossover2 CROSSOVER = new WeigthedAvg();
	
	// Mutation function
	public static final Mutation MUTATION = new UniformMutation();
	
	public static final float MUTATION_RATE = 0.0f / NUM_INDIVIDUALS;
	public static final double MUTATION_RANGE = 0.3f;
	/*----------------------------------------------------------------*/
	
	// Selection function for the remaining individuals
	public static final Selection2 END_SELECTION = new RandomSelection();
	
	
	/*Specific settings for WeightedAvg crossover */
	public static final int WEIGTHEDAVG_PARENTS = 2;
	public static final int WEIGTHEDAVG_FLAG = 1;
	/*------------------------------------------- */
	
	
	
	
	
	
	// The Animals actions
	public final static Action[] actions = {
			new TurnAction('l'),			// Turn left
			new TurnAction('r'),				// Turn right
			new SpeedAction()
	};
	
	// The Animals sensors
	public final static Sensor[] sensors = {
			new DirectionSensor('x'),		// Sense the animals direction	x
			new DirectionSensor('y'),		// Sense the animals direction	y
			new FoodDirectionSensor('x'),	// Sense closest foods direction	x
			new FoodDirectionSensor('y')	// Sense closest foods direction	y
	};
	
	
	
	
	
}
