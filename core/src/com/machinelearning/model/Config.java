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
	 * PREY configuration
	 */

	// Number of individuals to survive per generation
	public final static int NUM_ELITISM_PREY = 5;

	// Number of new children produced by crossover (Might be rounded down
	// depending on crossover operator)
	public final static int NUM_CROSSOVER_CHILDREN_PREY = 45;

	// Number of individuals in the population
	public static final int NUM_INDIVIDUALS_PREY = 50;

	// Penalty for being eaten
	public static final double EATEN_PENALTY = 1000;

	// Penalty for starvibng - not eating, out of energy
	public static final double STARVATION_PENALTY = 500;

	// Reward for eating Fitness
	public static final double FOOD_FITNESS_REWARD = 250;

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
	public static final double[] INIT_GENOME_PREY = { -18.410998527105, 3.5331378341102275, 12.385788698403788,
			17.07130258006267, 20.521855690152133, -1.0508291301520967, 3.0068510423239316, 0.5123741175681952,
			-5.453302136778231, -7.898440418435566, 33.37696197947441, -29.45759321514591, 31.06733462051801,
			-1.8928273513258291, -32.478940288745214, 21.59089030859444, 16.192476597638354, 8.846325800590204,
			6.408682946316512, 35.31532321004453, -8.58512557190844, -28.051939409676894, -23.490840305072535,
			-30.939944308656898, -7.257143330501489, 37.089041801387516, -0.7845385373751246, 14.223363673139795,
			1.3586180406301949, -1.4358165140907706, 1.4284445994391652, -16.616963779747778, 17.812463118969553,
			32.02956708316071, 3.5082423439457666, -20.73163590600536, -21.354716417647325, -44.56599771393496,
			-2.4083500024929267, 4.635355502340518, 4.683995375564414, 20.101132774408715, -22.500086712144565,
			20.95743056580147, 5.979171516240767, 0.7744263249823673, 24.243721654467492, 10.688169571289553,
			9.794278472190284, -13.544799802661503, -4.72381699239704, -4.95356385231, -15.937080493758675,
			1.6741238610298896, 20.086131660531315, -8.960030769143255, -26.751324447365874, -17.44291695192883,
			-18.973889873275375, -4.787243497910597, 16.610370955188863, 5.11638738398432, 12.208537432142682,
			4.096769342585933, 14.918438185348698, 10.369485297812682, -14.920785762615237, 20.791365094168764,
			-22.523851581066232, 0.7880812659989582, 20.51769375770869, -6.4886523816193575, 21.068963698149297 };

	/*
	 * PREDATOR configuration
	 */

	// Number of individuals to survive per generation
	public final static int NUM_ELITISM_PRED = 5;

	// Number of new children produced by crossover (Might be rounded down
	// depending on crossover operator)
	public final static int NUM_CROSSOVER_CHILDREN_PRED = 45;

	// Number of individuals in the population
	public static final int NUM_INDIVIDUALS_PRED = 50;

	// The weights are initiated with a specific value (INIT_GENOME below) if
	// INIT_SET_GENOME is true
	public static final boolean INIT_SET_GENOME_PRED = false;

	// The weights used to initiate the population if INIT_SET_GENOME is true
	// !EVERY individual in the population will be set to this!
	public static final double[] INIT_GENOME_PRED = { -0.10694315423546008, 0.6852377996136099, 0.747179131731869,
			2.8824820820781625, 5.739249016406459, 3.1115371466385557, 2.5016558827520674, -0.6319978158316097,
			-0.6857695654444291, 5.156622395851075, -2.303111947459527, -6.230406922800987, 3.780322845196147,
			-1.3552682850583877, 3.036560708221684, 2.2766890278680947, -0.7466584933285884, -7.006755994415253,
			-1.9009233169283728, 2.312693772750758, 0.6562832547229516, -2.972394918700456, 0.13491911419783842,
			1.0488593639400654, -0.6915351102306648, -4.322035566063878, 1.2587708450137614, 4.867536439842438,
			1.1337369875702428, 4.327311689425706, -1.5842542015334868, 1.0360346181429185, 2.267699580692912,
			-1.859821810846635, -3.1729734342556926, 0.7022154709067643, 1.8350227026122472, -2.0528304414621226,
			4.85406723159312, 0.09213697766800788, 3.965355544225885, 4.289539588724252, 2.8395190914124764,
			2.835835945335985, 2.302836345355998, 2.5539190761277673, -3.171730370987271, 0.47869987901710376,
			-1.7987106935490114, -0.4374899865230938, -2.470833812653272, 2.3870433331753724, -4.265821546431958,
			-0.5554951227807681, 1.8164975133256311, 2.2819743293791976, -0.9204483518549963, 3.379905236617978,
			0.00414467728408191, 2.445307375684931, 5.977342455967469, -0.4227416599885112, -4.994949913522301,
			1.225759010989802, 5.263244554811879, -5.26408136364275, 3.0781390615568482, -1.8552029502826364,
			-0.3424265894608877, 3.5950063149331903, -3.5083103179944146, 0.6286056963218021, -0.07942603408770887,
			2.00353331941901, -3.702891027989335, -0.7096042301631359, -3.617738456374297, 4.0167606771193745,
			2.8358855484669956, 1.7805892811804558, -0.6548523214268337, 1.1663563964289028, 1.5299890785663472,
			-1.1998758879372797, -0.8903770146533652, -0.7178496447107763, -0.4526170865669551, -4.244528716690468,
			-1.9525940609439512, 1.9695376707841246, -0.36240445529275844, -0.8529014408171488, 3.477149287897,
			-2.6721030079652595, 0.5121054180365896, 1.3655366792228267, 1.165457565940387, 1.2647004508404462,
			1.6551097031152164, -3.1833233787479718, -1.6839541869279304, 0.8499944362630493, -1.0054376137049752,
			0.8454546549756491, 0.3602550502554968, -1.9126133859114378, 0.45296384724653616, 2.6720276647933017,
			-1.6996818444293151, -2.620766458311926, 0.9092534265657958, 1.6059904782786942, -1.065653829371982,
			-2.68074384411198, -5.860309188722292, 0.2732294916415387, -4.080542335268104, 1.2301305169508496,
			-2.4457921118462425, 3.0702332784226867, -4.74713587545322, -0.3563771207653042, 1.5159877217192748,
			1.444176138402114, 0.8229003423274257, -3.3628680562250017, 0.23656909385707098, -1.8200277550539452,
			0.6737860680892305, 1.7389368221296815, -0.665184940150366, -3.6898981317670394, -1.0776785462058864,
			-1.2355808605272087, 1.5574515575947798, 0.9771377912743238, -3.6290881958486176, -4.612502716982235,
			-0.5966655140233756, -0.3362143161278532, 8.054828866407005, 0.02175356337940637, -2.199763673285507,
			-0.1575231589791548, 5.9284279269720574, -0.8474501980746432, -4.764374439486312, -0.5131602031776622,
			1.6815522464751065, -0.908286624168958, 2.0039948910058527, 4.101299455603944, -3.9650909593128625,
			1.7206217181827894, -2.3619451087528267, -0.7224643239190454, 1.0116396396349716, 2.3220149831156665,
			-0.37505551340753307, 3.163392199803616, 0.5743777110087854, 1.5276573329804564, 3.748921497938003,
			-0.8321086547543826, 0.979683387441714, 0.4481811249433635, 0.1623950997140623, -0.7809740071741476,
			-4.361925461272186, -1.6901020755931015, -2.841867712059147, 4.084199689427503, 0.37846765688409706,
			0.5422704278967426, 2.2412533662385896, 4.027369114384816, -1.483707909623533, -2.456261340628415,
			-0.6659830721783518, -1.262564751484146, 1.0573530327703677, -0.18121834430451972, 1.9856687717782067,
			-0.7670267102580595, -0.8507978442091242, -0.8660857001178951, 2.772328056090208, -1.5761255408520456,
			-0.24221052834221118, 2.338520972357931, 2.727072168823062, -3.737656596064819, -1.3730778982591576,
			-0.7143880721599201, 1.4788986877860892, 1.3002154836687745, 3.5618418193978862, -1.3328123971696735,
			2.24514920603235, 6.049729777221611, -5.728921149597158, -0.15412392210929005, -0.4150286584828869,
			-4.708884345023161, -2.1822651558359807, 4.529473834128991, -2.2196948912331953, 0.8355255663377283,
			-3.328234655470515, 1.2269003168195796, 1.2614057782631378 };

	/*
	 * MAP general configuration
	 */

	// Number of food pieces in the environment
	public static final int NUM_PLANT = 45;

	// Width of the environment
	public static final int WIDTH = 140;

	// Height of the environment
	public static final int HEIGHT = 90;

	// Number of time in seconds per generation
	public static final double TIME_PER_GENERATION = 35.0;

	// Oparque walls - or portal behavior
	public static final boolean WALL_PORTAL = false;

	// Plot fitness information in a graph
	public static final boolean PLOT_STATS = true;

	// Use hall-of-fame - add the best Animal throughout all generations
	// (fitness wise) to each new generation.
	public static final boolean HALL_OF_FAME = true;

	// Plot diversity information in a graph (sever performance penalty (50%) )
	public static final boolean PLOT_DIVERSITY = false;

	// Selection function for crossover (performance penalty)
	public static final Selection2 SELECTION = new RankSelection();

	/// Crossover function
	public static final Crossover2 CROSSOVER = new TwoPoint();

	// Mutation function
	public static final Mutation MUTATION = new UniformMutation();

	public static final float MUTATION_RATE = 0.05f;// 1f /
													// NUM_INDIVIDUALS_PREY;
	public static final double MUTATION_RANGE = 0.25f;
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
