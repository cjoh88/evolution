package com.machinelearning.model.crossover;

import java.util.ArrayList;

import com.machinelearning.model.Animal;

public class RandomCrossover implements Crossover2 {

	@Override
	public Animal[] cross(Animal[] input) {
		return input;
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
