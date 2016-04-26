package com.machinelearning.model.crossover;

import java.util.ArrayList;

import com.machinelearning.model.Animal;

public interface Crossover2 {
	public Animal[] cross(Animal[] input);
	
	public int numParents();
	public int numChildren();
}
