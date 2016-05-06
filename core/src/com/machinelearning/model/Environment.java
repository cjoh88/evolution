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

	private GA gaPrey;
	private GA gaPred;

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

		double[] gensPrey = {0.5408206750851943, -0.3716100729503559, 0.05957670226440526, 5.594779053260686, 0.48451455923667974, -0.49843573113790396, -1.598605132265502, -1.496074613247594, 0.17688420580784003, 0.5676574242226379, -0.6281348902908954, 0.8258274696969335, -0.11916980990987075, 0.06222134232433196, -0.26448525486296715, -0.34210216088546885, -0.05098328227730753, 0.6692005347756258, -0.28618343243435707, -0.6951095081485299, 0.6461587227862262, 4.200755719010533, -1.098407848176337, 1.1287775349766758, 1.9272858709812628, 0.488861585995727, 0.19763728338994535, 0.13488281895604753, -0.15800703698814622, 0.38318030640234274, -0.46108132709441685, 1.0122400832294214, 0.016598869714196218, 1.127370121547011, 1.363223596088317, -0.060896941696965357, 1.9484116976133559, 0.35432335331519044, -0.05559301518860876, -0.01950455050102118, 0.009970676264266343, -0.566192620899733, -2.7482934486026713, 0.25994777869606167, 0.7923570278410146, -0.6988926010602061, -0.29762361999151105, -0.0015359532262903541, -1.119867758047334, -0.010036075316485482, -0.8821796398530857, -0.35259904661467417, 1.703156281334391, -1.7325339312491712, -0.03167459489925406, 0.920666398645064, 3.281340822697217, -1.7513730291096594, 0.762216390008484, 0.8385913423971778, -0.5366417623671635, -2.1167518818127364, 0.8209327376434626, -1.991234974603942, 2.7128866612856934, 0.5529917819190542, 1.3207261560625343, -0.032085812284407766, 1.0231120404744054, -1.2758756584105033, 0.9455531822972982, 0.2527071239965274, 0.29458240046277534};
		for (int i = 0; i < prey.length; i++)
			prey[i].setGenome(gensPrey);
		
		double[] gensPred = {-0.3316313029758927, -0.4526832609412481, 0.13132487424255923, 0.4533194732799193, -0.47473064839482904, -0.39146028017725853, 0.38677676207655276, 0.2556600051442123, -0.2108500900781603, -0.048379691679684034, 0.3942211868927217, 0.14978441571878542, -0.452161251984041, -0.5911396402232986, 0.5315392388631108, -0.655493805412766, -0.5732605351631254, -0.04914172522968141, 0.4782697576836077, 0.1733347863918473, -0.5366334962581406, -0.575056831919973, -0.23313336951285618, 0.1072840872356644, -0.5457995200543407, -0.10482793697232606, 0.42613313960414856, 0.0204278293626744, -0.32729049876496086, -0.4347340068416388, -0.2574640064245179, -0.5962225469466442, 0.4657813083220286, 0.09936396824577787, 0.5578736209001725, -0.4357324597504905, -0.637040180033622, 0.19147503667488774, -0.26670174319655593, -0.044403671782721865, -0.6212225009358703, 0.0055121177814927425, 0.03996332155310167, 0.46637887213256635, -0.6468760337006173, -0.2359796534181638, 0.5855922280349555, 0.33853125379587135, 0.2579893289428291, -0.23816693363049563, 0.35403768547919445, 0.6050158606154503, -0.5989306505629677, -0.1738263515826467, 0.3214237672639033, 0.12103816096106923, -0.4223819829303459, 0.1763857403314748, -0.4822632451895972, -0.3835035017451135, 0.26984862249028524, 0.27790014326504386, 0.3267545560487499, -0.0800527846267205, 0.2854447625173918, 0.1542102012253116, -0.34002300937347657, 0.39555892530327585, -0.0646248691849417, 0.452550101933479, -0.598256331488067, 0.29448956444276564, 0.18413631194773317};
		for (int i = 0; i < pred.length; i++)
			pred[i].setGenome(gensPred);
		
		
		if (Config.PLOT_STATS) {
			preyPlot = statPlot.statAdd("PREY fitness/generation", "Generation", "Fitness");
			predPlot = statPlot.statAdd("Predator fitness/generation", "Generation", "Fitness");
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
		
		for (Animal animal : prey)
			if (animal.alive)
				animal.consumeEnergy();	
		
		for (Animal animal : pred)
			if (animal.alive)
				animal.consumeEnergy();
		
		for (Animal animal : pred) {
			animal.readSensorData();
		}
		for (Animal animal : prey) {
			if (animal.alive)
				animal.executeAction();
		}
		for (Animal animal : pred) {
			animal.executeAction();
		}
		for (Animal animal : prey) {
			if (animal.alive)
				animal.update(delta);
			// animal.update(1.0f);
		}
		for (Animal animal : pred) {
			animal.update(delta);
			// animal.update(1.0f);
		}
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
			float d = position.dst2(f.position);
			if (d < distance) {
				distance = d;
				result = f;
			}
		}
		return result;
	}

	public Animal getNearestAnimal(Vector2 position) {
		float distance = Float.MAX_VALUE;
		Animal result = null;
		for (Animal a : prey) {
			float d = position.dst2(a.getPosition());
			if (d < distance) {
				distance = d;
				result = a;
			}
		}
		return result;
	}

	public Animal getNearestPredator(Vector2 position) {
		float distance = Float.MAX_VALUE;
		Animal result = null;

		for (Animal a : pred) {
			float d = position.dst2(a.getPosition());
			if (d < distance) {
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
