package com.machinelearning.model.mutation;

import com.machinelearning.Utility;
import com.machinelearning.model.Animal;
import com.machinelearning.model.Config;

public class UniformMutation implements Mutation {

	public Animal mutate(Animal input) {
		double[] genome = convert(input.getGenome());
		
		double p = 1.0 / genome.length;
		for(int i=0; i<genome.length; i++) {
			if(Utility.random.nextDouble() <= p * 10) {
				genome[i] += Utility.random.nextDouble() * Config.MUTATION_RANGE * 2 - Config.MUTATION_RANGE;
			}
		}
		input.setGenome(genome);
		return input;
	}
	
	private double[] convert(Double[] a){
		double[] genome = new double[a.length];
		for(int i =0; i<genome.length; i++){
			genome[i] = a[i];
			
		}
		
		return genome;
		
	}
	
}
