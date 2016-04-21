package com.machinelearning.model;

import java.util.Random;

import com.machinelearning.Utility;

public class Selection {
	
	private static final Random random = new Random();
	
	private static final int rankSum = Utility.sumNumbers(Environment.NUM_INDIVIDUALS);
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
		double[] p = new double[Environment.NUM_INDIVIDUALS];
		p[0] = Environment.NUM_INDIVIDUALS / (double)rankSum;
		for(int i=1; i<Environment.NUM_INDIVIDUALS; i++) {
			p[i] = ((Environment.NUM_INDIVIDUALS - i) / (double)rankSum) + p[i-1];
		}
		return p;
	}

}
