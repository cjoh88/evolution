package com.machinelearning.model;

import com.machinelearning.model.action.Action;
import com.machinelearning.model.action.SpeedAction;
import com.machinelearning.model.action.TurnAction;
import com.machinelearning.model.crossover.Crossover2;
import com.machinelearning.model.crossover.OnePoint;
import com.machinelearning.model.crossover.UniformNeuronCrossover;
import com.machinelearning.model.mutation.Mutation;
import com.machinelearning.model.mutation.UniformMutation;
import com.machinelearning.model.selection.RandomSelection;
import com.machinelearning.model.selection.RankSelection;
import com.machinelearning.model.selection.Selection2;
import com.machinelearning.model.sensor.AnimalDistanceSensor;
import com.machinelearning.model.sensor.DirectionSensor;
import com.machinelearning.model.sensor.PlantDirectionSensor;
import com.machinelearning.model.sensor.PredatorDirectionSensor;
import com.machinelearning.model.sensor.PreyDirectionSensor;
import com.machinelearning.model.sensor.Sensor;

public class Config {

	/*
	 * PREY configuration
	 */

	// Number of individuals to survive per generation
	public final static int NUM_ELITISM_PREY = 10;

	// Number of new children produced by crossover (Might be rounded down
	// depending on crossover operator)
	public final static int NUM_CROSSOVER_CHILDREN_PREY = 40;

	// Number of individuals in the population
	public static final int NUM_INDIVIDUALS_PREY = 50;

	// Penalty for being eaten
	public static final double EATEN_PENALTY = 100;

	// Penalty for starvibng - not eating, out of energy
	public static final double STARVATION_PENALTY = 100;

	// Reward for eating Fitness
	public static final double FOOD_FITNESS_REWARD = 100;

	// Controls re-spawn/permanent death on being eaten
	public static final boolean KILL_ON_EATEN = true;

	// consume energy by existing. W/o energy the animal dies. Encourages
	// eating.
	public static final boolean INFINITE_ENERGY = false;

	// Starting Energy
	public static final int MAX_ENERGY = 500;

	// Reward for eating Energy
	public static final int FOOD_ENERGY_REWARD = 500;

	// Max speed for individuals
	public static final float MAX_SPEED = 10.0f;

	// The weights are initiated with a specific value (INIT_GENOME below) if
	// INIT_SET_GENOME is true
	public static final boolean INIT_SET_GENOME_PREY = false;

	// The weights used to initiate the population if INIT_SET_GENOME is true
	// !EVERY individual in the population will be set to this!
	public static final double[] INIT_GENOME_PREY = { };

	/*
	 * PREDATOR configuration
	 */

	// Number of individuals to survive per generation
	public final static int NUM_ELITISM_PRED = 5;

	// Number of new children produced by crossover (Might be rounded down
	// depending on crossover operator)
	public final static int NUM_CROSSOVER_CHILDREN_PRED = 20;

	// Number of individuals in the population
	public static final int NUM_INDIVIDUALS_PRED = 25;

	// The weights are initiated with a specific value (INIT_GENOME below) if
	// INIT_SET_GENOME is true
	public static final boolean INIT_SET_GENOME_PRED = false;

	// The weights used to initiate the population if INIT_SET_GENOME is true
	// !EVERY individual in the population will be set to this!
	public static final double[] INIT_GENOME_PRED = {
			
			
			
	};

	/*
	 * MAP general configuration
	 */

	// Number of food pieces in the environment
	public static final int NUM_PLANT = 60;

	// Width of the environment
	public static final int WIDTH = 140;

	// Height of the environment
	public static final int HEIGHT = 90;

	// Number of time in seconds per generation
	public static final double TIME_PER_GENERATION = 20.0;

	// Oparque walls - or portal behavior
	public static final boolean WALL_PORTAL = true;

	// Plot fitness information in a graph
	public static final boolean PLOT_STATS = true;

	// Use hall-of-fame - add the best Animal throughout all generations
	// (fitness wise) to each new generation.
	public static final boolean HALL_OF_FAME = true;

	// Plot diversity information in a graph (sever performance penalty (50%) )
	public static final boolean PLOT_DIVERSITY = true;
	
	// allow predator to move
	public static final boolean PRED_MOVEMENT = true;

	// Selection function for crossover (performance penalty)
	public static final Selection2 SELECTION = new RankSelection();

	/// Crossover function
	public static final Crossover2 CROSSOVER = new UniformNeuronCrossover();

	// Mutation function
	public static final Mutation MUTATION = new UniformMutation();

	public static final float MUTATION_RATE = 0.015f;
													// NUM_INDIVIDUALS_PREY;
	public static final double MUTATION_RANGE = 0.05f;
	/*----------------------------------------------------------------*/

	// Selection function for the remaining individuals
	public static final Selection2 END_SELECTION = new RandomSelection();

	/* Specific settings for WeightedAvg crossover */
	public static final int WEIGTHEDAVG_PARENTS = 2;
	public static final int WEIGTHEDAVG_FLAG = 1;
	/*------------------------------------------- */

	// The Animals actions
	public final static Action[] actions = { new TurnAction('l'), // Turn left
			new TurnAction('r'), // Turn right
			new SpeedAction() };

	// The Animals sensors
	public final static Sensor[] sensors = { new DirectionSensor('x'), // Sense
																		// the
																		// animals
																		// direction
																		// x
			new DirectionSensor('y'), // Sense the animals direction y
			new PlantDirectionSensor('x'), // Sense closest foods direction x
			new PlantDirectionSensor('y'), // Sense closest foods direction y
			new PreyDirectionSensor('x'), new PreyDirectionSensor('y'), new PredatorDirectionSensor('x'),
			new PredatorDirectionSensor('y'), new AnimalDistanceSensor('p'), new AnimalDistanceSensor('d') };

}
