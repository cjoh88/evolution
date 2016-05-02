package com.machinelearning.model;

import java.util.Random;

import com.machinelearning.Utility;

public class Selection {
	
	private static final Random random = new Random();
	
	private static final int rankSum = Utility.sumNumbers(Config.NUM_INDIVIDUALS_PREY);
	private static final double[] rankProbs = rankProbabilities();
	
	public static Animal rank(Animal[] population) {
		double x = random.nextDouble();
		int index = 0;
		while(x > rankProbs[index]) {
			index++;
		}
		return population[index];
	}
	
	private static double[] rankProbabilities() {
		double[] p = new double[Config.NUM_INDIVIDUALS_PREY];
		p[0] = Config.NUM_INDIVIDUALS_PREY / (double)rankSum;
		for(int i=1; i<Config.NUM_INDIVIDUALS_PREY; i++) {
			p[i] = ((Config.NUM_INDIVIDUALS_PREY - i) / (double)rankSum) + p[i-1];
		}
		return p;
	}

}
