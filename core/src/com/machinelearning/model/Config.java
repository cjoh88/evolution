package com.machinelearning.model;

import com.machinelearning.model.crossover.Crossover2;
import com.machinelearning.model.crossover.RandomCrossover;
import com.machinelearning.model.mutation.Mutation;
import com.machinelearning.model.mutation.RandomMutation;
import com.machinelearning.model.selection.RandomSelection;
import com.machinelearning.model.selection.Selection2;

public class Config {
	
	public final static int NUM_ELITISM = 2;
	public final static int NUM_CROSSOVER_CHILDREN = 10;
	
	// Number of individuals in the population
		public static final int NUM_INDIVIDUALS = 40;
		public static final int NUM_FOOD = 8;
		
		// Width of the environment
		public static final int WIDTH = 80;
		
		// Height of the environment
		public static final int HEIGHT = 45;
		
		public static final int STEPS_PER_GENERATION = 6000;
		
		
		//TODO Add selection, mutation etc
		public static final Selection2 SELECTION = new RandomSelection();
		public static final Crossover2 CROSSOVER = new RandomCrossover();
		public static final Mutation MUTATION = new RandomMutation();
		public static final Selection2 END_SELECTION = new RandomSelection();
}
