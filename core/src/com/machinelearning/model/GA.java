package com.machinelearning.model;

import java.util.Arrays;
import java.util.Comparator;

public class GA {
	
	// Number of individuals to preserve unchanged for the next generation
	private static final int ELITISM = 5;
	
	// Number of pairs selected for crossover
	private static final int CROSSOVER_PAIRS = 10;
	
	private static final float MUTATION_RATE = 0.05f;
	
	private static Comparator<Animal> fitnessComparator = new Comparator<Animal>() {
		@Override
		public int compare(Animal a1, Animal a2) {
			return Float.compare(a2.fitness(), a1.fitness());
		}
	};
	
	//Arrays.sort(animals, fitnessComparator);
	
	public static Animal[] createNewPopulation(Animal[] animals) {
		Animal[] result = new Animal[animals.length];
		
		return result;
	}

}
