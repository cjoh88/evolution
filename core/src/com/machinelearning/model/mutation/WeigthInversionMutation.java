package com.machinelearning.model.mutation;

import java.util.Random;

import com.machinelearning.model.Animal;
import com.machinelearning.model.Config;

/*Currently based on the probability mutates the entire genome by inverting it (positive weigth = negative weigth)
 * Could potentially add a probability for specific genes too.
 */


public class WeigthInversionMutation implements Mutation {

	@Override
	public Animal mutate(Animal input) {
	
		double p = Config.MUTATION_RATE;
		Random generator = new Random();
		double value = generator.nextDouble();
		
		if(value <= p){
			double [] newMutatedWeigths = new double[input.getGenome().length];
		
			for(int i =0; i<newMutatedWeigths.length; i++){
				newMutatedWeigths[i] = -1 * (input.getGenome())[i];
			}
			input.setGenome(newMutatedWeigths);
		}
		
		return input;
	}

}
