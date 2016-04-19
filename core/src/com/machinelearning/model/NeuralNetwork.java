package com.machinelearning.model;

import java.util.Random;

import com.machinelearning.Log;

public class NeuralNetwork {
	
	private float hidden[][];
	private float output[][];
	private static Random random = new Random();
	private int numInputs, numHidden, numOutputs;
	
	public NeuralNetwork(int numInputs, int numHidden, int numOutputs) {
		hidden = new float[numInputs][numHidden];
		output = new float[numHidden][numOutputs];
		this.numInputs = numInputs;
		this.numHidden = numHidden;
		this.numOutputs = numOutputs;
		for(int j=0; j<numHidden; j++) {
			for(int i=0; i<numInputs; i++) {
				//TODO set proper interval
				hidden[i][j] = random.nextFloat() * 2.0f - 1.0f;
			}
			for(int i=0; i<numOutputs; i++) {
				//TODO set proper interval
				output[j][i] = random.nextFloat() * 2.0f - 1.0f;
			}
		}
	}
	
	public float[] execute(float[] input) {
		if(input.length != numInputs) {
			Log.log("NeuralNetwork.class: Wrong number of inputs. Expected: " + numInputs + " Received: " + input.length );
		}
		float[] hiddenOutput = new float[numHidden];
		for(int i=0; i<numInputs; i++) {
			for(int h=0; h<numHidden; h++) {
				hiddenOutput[h] += input[i] * hidden[i][h];
			}
		}
		float[] result = new float[numOutputs];
		for(int h=0; h<numHidden; h++) {
			hiddenOutput[h] = (float)Math.tanh(hiddenOutput[h]);
			for(int o=0; o<numOutputs; o++) {
				result[o] += hiddenOutput[h] * output[h][o];
			}
		}
		for(int i=0; i<numOutputs; i++) {
			result[i] = (float)Math.tanh(result[i]);
		}
		return result;
	}

}






