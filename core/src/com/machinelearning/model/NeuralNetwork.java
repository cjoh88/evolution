package com.machinelearning.model;

import java.util.Random;

public class NeuralNetwork {
	
	private float weights[][];
	private static Random random = new Random();
	
	public NeuralNetwork(int numInputs, int numHidden, int numOutputs) {
		weights = new float[numInputs][numOutputs];
		for(int i=0; i<numInputs; i++) {
			for(int j=0; j<numOutputs; j++) {
				//TODO set proper interval
				weights[i][j] = random.nextFloat() * 2.0f - 1.0f;
			}
		}
	}

}
