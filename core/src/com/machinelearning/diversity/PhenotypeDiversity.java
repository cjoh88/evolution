package com.machinelearning.diversity;

import java.util.Random;

import org.neuroph.nnet.MultiLayerPerceptron;

import com.machinelearning.Utility;
import com.machinelearning.model.Animal;
import com.machinelearning.model.Config;

public class PhenotypeDiversity implements Diversity {

	double[][] testInputs; // Cannot change during evolution (Maybe make fixed
							// for several runs too?)
	private static final int AMOUNT_OF_INPUTS = Config.sensors.length;
	private static final int AMOUNT_OF_TESTS = 10;

	public PhenotypeDiversity() {
		testInputs = new double[AMOUNT_OF_TESTS][AMOUNT_OF_INPUTS];
		createInputs();

	}

	private void createInputs() {
		// Generates values based on sensor order being x as odd and y as even

		for (int i = 0; i < AMOUNT_OF_TESTS; i++) {
			for (int d = 0; d < AMOUNT_OF_INPUTS/2; d=d+2) {
				
				testInputs[i][d] = Utility.random.nextDouble();    //X
				testInputs[i][d+1] = Math.sqrt(1-Math.pow(testInputs[i][d], 2)); //Y
			}

		}
	}

	

	@Override
	public double[] calculateDiversity(Animal[] population) {

		double diversity = 0.0;
		double[][] generatedOutputs = new double[population.length][Config.actions.length];
		for (int d = 0; d < AMOUNT_OF_TESTS; d++) {
			for (int i = 0; i < population.length; i++) {
				MultiLayerPerceptron ann = population[i].getMLP();
				ann.setInput(testInputs[d]);
				ann.calculate();
				generatedOutputs[i] = ann.getOutput();
			}

			diversity += diversityHamming(generatedOutputs);
		}

		diversity = diversity / AMOUNT_OF_TESTS / population.length;

		double[] result = { diversity };

		return result;
	}

	private double diversityHamming(double[][] coordinates) {
		double diversity = 0.0;
		/*
		 * coordinates[0][0] - coordinates[i][0] etc etc etc etc
		 */
		if (coordinates.length > 0)
			for (int i = 0; i < coordinates[0].length; i++) {
				for (int d = 0; d < coordinates.length; d++) {
					for (int a = d + 1; a < coordinates.length; a++) {
						diversity += Math.abs(coordinates[d][i] - coordinates[a][i]);
					}
				}
			}

		return diversity;
	}

}
