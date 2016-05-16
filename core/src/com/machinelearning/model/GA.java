package com.machinelearning.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import com.machinelearning.model.crossover.Crossover2;
import com.machinelearning.model.mutation.Mutation;
import com.machinelearning.model.selection.Selection2;

//import main.Chromosome;

public class GA {


	Crossover2 crossover;
	Selection2 selection, endSelect;
	Mutation mutation;
	int generation;

	public Animal highestFitness = null;
	
	public Animal hallOfFame = null;
	
	private static Comparator<Animal> fitnessComparator = new Comparator<Animal>() {
		@Override
		public int compare(Animal a1, Animal a2) {
			return Float.compare(a2.fitness(), a1.fitness());
		}
	};

	public GA(Crossover2 c, Mutation m, Selection2 s, Selection2 es) {
		crossover = c;
		selection = s;
		mutation = m;
		endSelect = es;
	}

	public void compute(Animal[] pop) {
		int numCrossovers;
		int numElitism;
		int numIndividuals = pop.length;
		
		
		for (Animal animal : pop) {
			animal.alive = true;
			animal.starvation = false;
			animal.killed = false;		
			animal.energy = Config.MAX_ENERGY;
		}
		
		if (pop[0].getFood()[0] instanceof Plant) {
			numCrossovers = Config.NUM_CROSSOVER_CHILDREN_PREY;
			numElitism = Config.NUM_ELITISM_PREY;
		} else {
			numCrossovers = Config.NUM_CROSSOVER_CHILDREN_PRED;
			numElitism = Config.NUM_ELITISM_PRED;
		}

		if (highestFitness != null) {
			highestFitness.resetColor();
		}

		Arrays.sort(pop, fitnessComparator);
		System.out.println(pop[0].fitness());

		highestFitness = pop[0];
		
		if(highestFitness == null || hallOfFame == null || hallOfFame.fitness<highestFitness.fitness)
			hallOfFame = highestFitness.copy();
		
		highestFitness.setAsHighestFitness();

		ArrayList<Animal> newPop = new ArrayList<Animal>();

		for (int i = 0; i < numElitism; i++) {
			newPop.add(pop[i]);
			newPop.get(i).setFitness(0);
		}

		for (int i = 0; i < numCrossovers / crossover.numChildren(); i++) {
			Animal[] animals = crossover.cross(selection.select(pop, crossover.numParents()));
			for (Animal a : animals) {
				newPop.add(a);
			}
			// newPop.addAll(crossover.cross(selection.select(pop,
			// crossover.numParents())));
		}

		Animal[] rest = endSelect.select(pop, pop.length - newPop.size());
		for (Animal a : rest) {
			newPop.add(a.copy());
		}

		for (Animal a : newPop.subList(numElitism, numIndividuals - 1)) {
			mutation.mutate(a);
		}

		
		
		// newPop.addAll(endSelect.select((ArrayList<Animal>)pop.subList(Config.NUM_ELITISM,
		// pop.size()),pop.size()-newPop.size()));

		// TODO pop and newPop must be same length
		for (int i = 0; i < pop.length; i++) {
			pop[i] = newPop.get(i);
			pop[i].alive = true;
		}

		if(Config.HALL_OF_FAME){
			pop[pop.length-1] = hallOfFame.copy();
			pop[pop.length-1].setFitness(0);
			pop[pop.length-1].alive = true;
		}
	}

}



