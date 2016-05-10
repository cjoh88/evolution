package com.machinelearning.model;

import com.machinelearning.model.action.Action;
import com.machinelearning.model.action.SpeedAction;
import com.machinelearning.model.action.TurnAction;
import com.machinelearning.model.crossover.Crossover2;
import com.machinelearning.model.crossover.OnePoint;
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
	public static final boolean INIT_SET_GENOME_PREY = true;

	// The weights used to initiate the population if INIT_SET_GENOME is true
	// !EVERY individual in the population will be set to this!
	public static final double[] INIT_GENOME_PREY = { 4.475961182082039, -1.182568837289811, 4.159827858633407,
			-4.119684564153916, 18.85151700591558, 1.1197322352482815, -1.0176080766724476, 8.03473468756664,
			-1.7219051375982006, -2.7567390494871855, -16.299903877281814, -1.2824882721792852, -14.829448954577828,
			-10.373328978622084, 15.432371390532799, -10.787398926718183, -10.224667459018015, 3.481274831255572,
			0.07609473011302531, -3.537650823547932, -16.113619745566453, 9.94476433319875, 7.983089868626987,
			4.442440118506897, -10.748095130433855, -13.024181977440128, 7.03919365620311, 0.3618405932934762,
			0.38977177246803507, 0.6812784754204605, 15.411035767434642, -0.26337097805106113, -2.553653472959981,
			-6.286421765137859, -3.5139933320063994, 4.135887125710031, 8.407704669414883, 0.0950477262585166,
			-3.226505212835275, -1.3741251297887491, -1.7045458551299841, 6.3936562038170255, -17.629344622706345,
			-8.03580668192253, 8.041097476682323, 0.610214999301973, -19.64792895086441, 5.896164908372257,
			-4.609593882654916, 2.8323412017980125, -2.0095714653951307, 0.5910475089786942, 4.97319106293824,
			-0.3586832613509452, 14.282965360006143, -5.338022612045061, 2.911694736530995, 6.462685249344528,
			8.221400730689767, 5.479837638703819, -15.42604608302545, -1.4805587757649379, -1.7672337398618858,
			-4.38483594733303, -10.554030514963095, 7.13659643514697, 10.921111460362637, -14.454041664982494,
			-10.655072795636132, 4.152567976948895, 0.15566246538107803, -15.687868249727154, 2.23359767375418,
			-0.9973719440633886, -0.07562382213903951, -4.714142423868085, -14.828547212684697, 10.643351487324558,
			-5.32096105812094, 1.2536813255721149, 14.150433418189253, 1.2370451495304147, 1.7525157173144201,
			-1.3833210310448587, -8.310302585300654, 10.9739450848808, -6.370042799117583, 16.196013110290878,
			5.226862413019168, 12.044960495438767, 24.591302884830284, 3.0726405047273233, 2.091577278175877,
			4.670894931599943, -14.756238505268387, 2.8355244609568127, 15.822856885502432, 4.102257200657498,
			-0.6727154276351401, 5.9297758863445225, 1.6513480200413944, 0.8771700166842263, 2.501054417717989,
			11.098623525810925, 6.328474393169708, 0.5720403314921891, 1.7740372696776174, 1.3889459554527759,
			-5.974785041700874, -5.282844828544806, -10.607553699638327, -4.193483773416958, -10.9533314828773,
			-11.785024973327072, 9.84222440466717, -16.265406286994118, 8.030515819270423, 12.485505822244303,
			-1.645111759932176, -8.29410386805849, 5.6282815821334875, -8.217696116136135, 2.355619905639355,
			10.198455978693191, -2.2423083307278375, -12.021280838601387, 7.458583848562432, -6.902802038140751,
			-0.5406072568747291, 4.024582363981644, 11.436808975357717, 6.6892818450302185, -2.367933038115912,
			3.610177597619432, 5.719867685004486, -1.5499806083391268, -1.3918692447240297, -4.912547468855256,
			-10.58013332027556, -1.0483486602218575, 0.4253931701483343, 7.498035152119095, 11.012494032865682,
			9.810947101133696, -5.84624701978819, 4.944346404547004, 0.2383032205423894, -4.982579785355205,
			-3.7369900379818564, 0.80105595844433, 5.6679109663561285, -16.230237996471686, 1.12575960766449,
			7.538380296319502, -2.535925553935015, 2.7318421322198336, -16.717117235943288, 3.1963481726896372,
			4.208958645181998, -0.8673835258421487, -3.898068481283672, -2.0939306275171843, -1.7021475534348849,
			-8.66474795767494, 0.945238123812346, -0.8209142542026079, -16.0467773526728, 3.849117440264564,
			-1.758576557326155, 5.877377023863415, -16.180471729258933, 6.2547465854098, 1.4088731056024664,
			9.179613608271728, 1.244061583962407, 4.502346495348464, 3.851704496608674, 5.919174616336939,
			-2.3870217359886854, -17.8974293696449, 5.687675210226032, -12.741408034995661, 2.219780359629638,
			-4.632678894062191, 1.075124895133695, -2.009553124093082, 4.742698105343363, 3.8129106951519773,
			4.3090258530177294, -6.7797882624964485, -4.343651056898352, 11.240706486343607, 1.912018673814442,
			4.45094322391715, 15.47614919955698, -0.6934771236218151, 0.4781271386284323, 4.203280787519824,
			12.660339689981097, -15.419919267548277, -6.181429503653073, 9.710492956383728, 19.75638139222407,
			-7.265910265896689, -9.769740274856014, 2.0976620761438456, 5.747937051242074, -1.0225289714006882,
			-2.3926857064414415, -5.546442142600381, 10.929507969508784, 19.880625593118623, -12.512345188842227,
			-7.597251188588913, 7.50853545962295, -4.02289545521295, -2.6564267712739946, -3.740513003801947,
			5.701562269102873, -5.13956088606632, 8.239544480324293, 3.33685640807654, -4.6278498034538575,
			3.3000415758151966, 0.333728674971433, -9.2419452656262, 5.62505933236214, -0.3385516751555649,
			0.5701118003308563, 1.3923750671261383, -4.229267244522808, 20.20443487590774, -5.220936803178969,
			-0.8161556514281203, 9.366090734406201, 10.01255175776404, 8.25194917303032, -3.304405511682936,
			-0.32028888883749534, -2.459411038200767, 6.9347106687017375, -0.09179872126251609, 4.01909838506344,
			-5.409178739412754, 1.3593548443111416, 1.9219186215274981, -2.738431747181305, 7.640391270682632,
			1.5079748645055213, 2.4375801311799328, 12.620074522618209, -3.7610470698593415, -2.1581047623042684,
			-7.09640301199047, 6.52436620719706, -14.108398906213248, 3.5301951427765395, -8.541960751061367,
			-2.962911986378522, 8.352334182555378, -15.002941394712941, -6.545124912390344, 0.013387563301007432,
			-0.7948091790935736, 20.13457615256599, -5.214322182146473, 1.5893157706059098, -0.24683234007307064,
			3.6917436422113385, -1.6822414132605445, 5.033993691383567, 4.072526743384804, 1.0825598313564702,
			1.079060754261073, 7.666675375449619, 4.365943751568172, 15.161488134257466, 2.5355373062523263,
			-9.518809265400591, -9.656203301977836, -0.6499266071221272 };

	/*
	 * PREDATOR configuration
	 */

	// Number of individuals to survive per generation
	public final static int NUM_ELITISM_PRED = 5;

	// Number of new children produced by crossover (Might be rounded down
	// depending on crossover operator)
	public final static int NUM_CROSSOVER_CHILDREN_PRED = 15;

	// Number of individuals in the population
	public static final int NUM_INDIVIDUALS_PRED = 20;

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
	public static final int NUM_PLANT = 45;

	// Width of the environment
	public static final int WIDTH = 140;

	// Height of the environment
	public static final int HEIGHT = 90;

	// Number of time in seconds per generation
	public static final double TIME_PER_GENERATION = 35.0;

	// Oparque walls - or portal behavior
	public static final boolean WALL_PORTAL = true;

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
	public static final Crossover2 CROSSOVER = new OnePoint();

	// Mutation function
	public static final Mutation MUTATION = new UniformMutation();

	public static final float MUTATION_RATE = 0.15f;// 1f /
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
