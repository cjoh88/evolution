package com.machinelearning.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import com.machinelearning.Utility;

public class GA {
	
	// Number of individuals to preserve unchanged for the next generation
	private static final int ELITISM = 5;
	
	// Number of pairs selected for crossover
	private static final int CROSSOVER_PAIRS = 20;
	
	//private static final float MUTATION_RATE = 1.0f / Environment.NUM_INDIVIDUALS;
	private static final double MUTATION_RANGE = 0.5f;
	
	//private static final Random random = new Random();
	
	//private static final int rankSum = Utility.sumNumbers(Environment.NUM_INDIVIDUALS);
	
	private static Comparator<Animal> fitnessComparator = new Comparator<Animal>() {
		@Override
		public int compare(Animal a1, Animal a2) {
			return Float.compare(a2.fitness(), a1.fitness());
		}
	};
	
	//Arrays.sort(animals, fitnessComparator);
	
	public static void createNewPopulation(Animal[] population) {
		Arrays.sort(population, fitnessComparator);
		Animal[] newPopulation = new Animal[population.length];
		int index = 0;
		for(int i=0; i<ELITISM; i++) {
			newPopulation[index] = population[index];
			index++;
		}
		System.out.println("ELITISM DONE!!!");
		
		for(int i=0; i<CROSSOVER_PAIRS; i++) {
			Animal p1 = Selection.rank(population);
			Animal p2 = Selection.rank(population);
			while(p1.id() == p2.id()) {
				p2 = Selection.rank(population);
			}
			
			double[][] genomes = Crossover.uniform(p1.getGenome(), p2.getGenome());
			p1 = new Animal(p1.getEnvironment(), p1.getSensors(), p1.getActions());
			mutate(genomes[0]);
			p1.setGenome(genomes[0]);
			p2 = new Animal(p2.getEnvironment(), p2.getSensors(), p2.getActions());
			mutate(genomes[1]);
			p2.setGenome(genomes[1]);
			newPopulation[index] = p1;
			newPopulation[index+1] = p2;
			
			index += 2;
		}
		
		while(index < Environment.NUM_INDIVIDUALS) {
			newPopulation[index] = new Animal(population[0].getEnvironment(), population[0].getSensors(), population[0].getActions());
			index++;
		}
		
		for(int i=0; i<population.length; i++) {
			population[i] = newPopulation[i];
		}
	}
	
	private static void mutate(double[] genome) {
		double p = 1.0 / genome.length;
		for(int i=0; i<genome.length; i++) {
			if(Utility.random.nextDouble() <= p * 10) {
				genome[i] += Utility.random.nextDouble() * MUTATION_RANGE * 2 - MUTATION_RANGE;
			}
		}
	}
	
/*	private static Animal selectByRank(Animal[] population) {
		int x = random.nextInt(rankSum);
		int y = 0;
		int i = population.length - 1;
		while(y < x) {
			y += i;
			i--;
		}
		return population[i];
	}*/

}








