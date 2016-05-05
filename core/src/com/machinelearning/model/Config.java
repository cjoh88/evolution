package com.machinelearning.model;

import com.machinelearning.model.action.Action;
import com.machinelearning.model.action.SpeedAction;
import com.machinelearning.model.action.TurnAction;
import com.machinelearning.model.crossover.Crossover2;
import com.machinelearning.model.crossover.OnePoint;
import com.machinelearning.model.mutation.Mutation;
import com.machinelearning.model.mutation.UniformMutation;
import com.machinelearning.model.selection.RandomSelection;
import com.machinelearning.model.selection.Selection2;
import com.machinelearning.model.selection.TournamentSelection;
import com.machinelearning.model.sensor.AnimalDistanceSensor;
import com.machinelearning.model.sensor.DirectionSensor;
import com.machinelearning.model.sensor.PlantDirectionSensor;
import com.machinelearning.model.sensor.PredatorDirectionSensor;
import com.machinelearning.model.sensor.PreyDirectionSensor;
import com.machinelearning.model.sensor.Sensor;

public class Config {
	
	// Number of individuals to survive per generation
	public final static int NUM_ELITISM_PREY = 4;
	
	// Number of new children produced by crossover (Might be rounded down depending on crossover operator)
	public final static int NUM_CROSSOVER_CHILDREN_PREY = 30;
	
	// Number of individuals in the population
	public static final int NUM_INDIVIDUALS_PREY = 35;
	
	// Number of individuals to survive per generation
	public final static int NUM_ELITISM_PRED = 5;
		
	// Number of new children produced by crossover (Might be rounded down depending on crossover operator)
	public final static int NUM_CROSSOVER_CHILDREN_PRED = 16;
	
	// Number of individuals in the population
	public static final int NUM_INDIVIDUALS_PRED = 22;
	
	// Number of food pieces in the environment
	public static final int NUM_PLANT = 22;
	
	// Width of the environment
	public static final int WIDTH = 120;
		
	// Height of the environment
	public static final int HEIGHT = 90;
		
	// Number of time in seconds per generation
	public static final double TIME_PER_GENERATION = 20.0;
	
	// Penalty for being eaten
	public static final double EATEN_PENALTY = 500;
	
	// Reward for eating
	public static final double FOOD_REWARD = 300;
	
	// Plot fitness information in a graph
	public static final boolean PLOT_STATS = true;
		
		
	// Selection function for crossover (performance penalty)
	public static final Selection2 SELECTION = new TournamentSelection();
	
	// Crossover function
	public static final Crossover2 CROSSOVER = new OnePoint();
	
	// Mutation function
	public static final Mutation MUTATION = new UniformMutation();
	
	public static final float MUTATION_RATE = 1f / (NUM_INDIVIDUALS_PREY*10);
	public static final double MUTATION_RANGE = 0.25f;
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
			new PlantDirectionSensor('x'),	// Sense closest foods direction	x
			new PlantDirectionSensor('y'),	// Sense closest foods direction	y
			new PreyDirectionSensor('x'),
			new PreyDirectionSensor('y'),
			new PredatorDirectionSensor('x'),
			new PredatorDirectionSensor('y'),
			new AnimalDistanceSensor('p'),
			new AnimalDistanceSensor('d')
	};
	
	
	
	
	
}
