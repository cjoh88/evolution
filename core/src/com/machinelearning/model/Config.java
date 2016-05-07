package com.machinelearning.model;

import com.machinelearning.model.action.Action;
import com.machinelearning.model.action.SpeedAction;
import com.machinelearning.model.action.TurnAction;
import com.machinelearning.model.crossover.Crossover2;
import com.machinelearning.model.crossover.TwoPoint;
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
	 *	PREY configuration
	 */
	
	// Number of individuals to survive per generation
	public final static int NUM_ELITISM_PREY = 5;
	
	// Number of new children produced by crossover (Might be rounded down depending on crossover operator)
	public final static int NUM_CROSSOVER_CHILDREN_PREY = 45;
	
	// Number of individuals in the population
	public static final int NUM_INDIVIDUALS_PREY = 50;
	
	// Penalty for being eaten
	public static final double EATEN_PENALTY = 500;
	
	// Penalty for starvibng - not eating, out of energy
	public static final double STARVATION_PENALTY = 500;
	
	// Reward for eating Fitness
	public static final double FOOD_FITNESS_REWARD = 300;
	
	// Controls re-spawn/permanent death on being eaten
	public static final boolean KILL_ON_EATEN = true;
	
	// consume energy by existing. W/o energy the animal dies. Encourages eating.
	public static final boolean INFINITE_ENERGY = false;
	
	// Starting Energy
	public static final int MAX_ENERGY = 500;
	
	// Reward for eating Energy
	public static final int	 FOOD_ENERGY_REWARD = 500;

	
	// Max speed for individuals
	public static final float MAX_SPEED = 6.0f;
	
	// The weights are initiated with a specific value (INIT_GENOME below) if INIT_SET_GENOME is true 
	public static final boolean INIT_SET_GENOME_PREY = false;
	
	// The weights used to initiate the population if INIT_SET_GENOME is true !EVERY individual in the population will be set to this!
	public static final double[] INIT_GENOME_PREY = {-18.410998527105, 3.5331378341102275, 12.385788698403788, 17.07130258006267, 20.521855690152133, -1.0508291301520967, 3.0068510423239316, 0.5123741175681952, -5.453302136778231, -7.898440418435566, 33.37696197947441, -29.45759321514591, 31.06733462051801, -1.8928273513258291, -32.478940288745214, 21.59089030859444, 16.192476597638354, 8.846325800590204, 6.408682946316512, 35.31532321004453, -8.58512557190844, -28.051939409676894, -23.490840305072535, -30.939944308656898, -7.257143330501489, 37.089041801387516, -0.7845385373751246, 14.223363673139795, 1.3586180406301949, -1.4358165140907706, 1.4284445994391652, -16.616963779747778, 17.812463118969553, 32.02956708316071, 3.5082423439457666, -20.73163590600536, -21.354716417647325, -44.56599771393496, -2.4083500024929267, 4.635355502340518, 4.683995375564414, 20.101132774408715, -22.500086712144565, 20.95743056580147, 5.979171516240767, 0.7744263249823673, 24.243721654467492, 10.688169571289553, 9.794278472190284, -13.544799802661503, -4.72381699239704, -4.95356385231, -15.937080493758675, 1.6741238610298896, 20.086131660531315, -8.960030769143255, -26.751324447365874, -17.44291695192883, -18.973889873275375, -4.787243497910597, 16.610370955188863, 5.11638738398432, 12.208537432142682, 4.096769342585933, 14.918438185348698, 10.369485297812682, -14.920785762615237, 20.791365094168764, -22.523851581066232, 0.7880812659989582, 20.51769375770869, -6.4886523816193575, 21.068963698149297};

	
	
	/*
	 *	PREDATOR configuration
	 */
	
	// Number of individuals to survive per generation
	public final static int NUM_ELITISM_PRED = 5;
		
	// Number of new children produced by crossover (Might be rounded down depending on crossover operator)
	public final static int NUM_CROSSOVER_CHILDREN_PRED = 45;
	
	// Number of individuals in the population
	public static final int NUM_INDIVIDUALS_PRED = 50;
	
	// The weights are initiated with a specific value (INIT_GENOME below) if INIT_SET_GENOME is true 
	public static final boolean INIT_SET_GENOME_PRED = false;
	
	// The weights used to initiate the population if INIT_SET_GENOME is true !EVERY individual in the population will be set to this!
	public static final double[] INIT_GENOME_PRED = {-4.3700582282335505, 0.5105948916378504, 2.3359869287513653, 2.2147350207845777, 5.250161830259521, 6.532229591559512, -3.783351898590063, -7.664104307865367, 22.334677224515993, -5.363157637153137, 3.5669383451514562, -5.066340024421597, 5.782898685291833, -3.9662540610239585, -0.7016517305909993, -3.2741782026474637, -3.2706282920549246, 13.517622118057428, -22.742911389272027, 3.232446220024775, -16.675371889158875, 28.973781336291133, -27.7812425749162, -7.242683020532839, 12.44585510493476, 5.552809129530651, 14.746550308933516, -12.050794141525753, -19.548512485335156, 11.66729462125121, -17.47061970700312, -14.911979501696706, 5.402460954873213, -6.357534374309534, -22.013675118419588, 2.6490202419556543, 12.87075626906289, 4.640721887228677, -0.37333579388552923, 14.211673124003623, -10.610065343953178, 13.398524544544555, -9.129769281274484, -3.673800200226623, 3.6220146593347486, -21.04426464891902, -7.16623156331228, 5.3596779752464805, -21.052986377836294, 14.70149092115363, -0.267356196885592, -7.1103088168180255, 2.245778907813876, -12.922952816012627, 8.77557533293656, -1.5532827427073315, -7.164023310680523, 3.8468726835982743, -14.354111674186443, -5.882576232111457, -7.05784265576126, 27.26824728836773, -28.203173783695206, -22.728353288336198, 31.05082420618938, 3.3443262219588155, 6.093990272020866, 8.37954196779329, -1.0399960333167124, 27.706214231174805, -1.1804577912985237, 0.10370862952046117, 24.70621954304447};

	
	/*
	 *	MAP general configuration
	 */
	
	
	// Number of food pieces in the environment
	public static final int NUM_PLANT = 45;
	
	// Width of the environment
	public static final int WIDTH = 140;
		
	// Height of the environment
	public static final int HEIGHT = 90;
		
	// Number of time in seconds per generation
	public static final double TIME_PER_GENERATION = 30.0;
	
	// Oparque walls - or portal behavior
	public static final boolean WALL_PORTAL = false;
	
	// Plot fitness information in a graph
	public static final boolean PLOT_STATS = true;
	
	// Plot diversity information in a graph (sever performance penalty (50%) )
	public static final boolean PLOT_DIVERSITY = false;
	
	// Selection function for crossover (performance penalty)
	public static final Selection2 SELECTION = new RankSelection();
	
	/// Crossover function
	public static final Crossover2 CROSSOVER = new TwoPoint();
	
	// Mutation function
	public static final Mutation MUTATION = new UniformMutation();
	
	public static final float MUTATION_RATE = 0.05f;//1f / NUM_INDIVIDUALS_PREY;
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
