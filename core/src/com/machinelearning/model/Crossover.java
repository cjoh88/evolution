package com.machinelearning.model;

import java.util.Random;

public class Crossover {
	
	private static Random random = new Random();
	
	public static double[][] uniform(Double[] a1, Double[] a2) {
		double[][] result = new double[2][a1.length];
		for(int i=0; i<a1.length; i++) {
			if(random.nextBoolean()) {
				result[0][i] = a1[i];
				result[1][i] = a2[i];
			}
			else {
				result[0][i] = a2[i];
				result[1][i] = a1[i];
			}
		}
		return result;
	}

}
