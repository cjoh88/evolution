package com.machinelearning.model.crossover;

import com.machinelearning.Utility;
import com.machinelearning.model.Animal;
import com.machinelearning.model.Config;

public class UniformCrossover implements Crossover2 {

	@Override
	public Animal[] cross(Animal[] input) {
		double[] genome1 = input[0].getGenome();
		double[] genome2 = input[1].getGenome();
		double[] ng1 = new double[genome1.length];
		double[] ng2 = new double[genome1.length];
		for(int i=0; i<genome1.length; i++) {
			if(Utility.random.nextBoolean()) {
				ng1[i] = genome1[i];
				ng2[i] = genome2[i];
			}
			else {
				ng1[i] = genome2[i];
				ng2[i] = genome1[i];
			}
		}
		Animal[] result = {
				new Animal(input[0].getEnvironment(), Config.sensors, Config.actions, input[0].getFood()),
				new Animal(input[0].getEnvironment(), Config.sensors, Config.actions, input[0].getFood())
		};
		result[0].setGenome(ng1);
		result[1].setGenome(ng2);
		return result;
	}

	@Override
	public int numParents() {
		return 2;
	}

	@Override
	public int numChildren() {
		return 2;
	}

}
