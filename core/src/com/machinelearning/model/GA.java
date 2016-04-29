package com.machinelearning.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import com.machinelearning.model.crossover.Crossover2;
import com.machinelearning.model.mutation.Mutation;
import com.machinelearning.model.selection.Selection2;

//import main.Chromosome;

public class GA {
//	 EvoAlg conf = new EvoAlg();
//  	  
//  	  conf.crossover = new CrossUniform(new SelectRandom());
//  	  conf.selection = new SelectRandom();
//  	  conf.mutation = new MutateUniform(0.125, 10);  	
//  	  
//  	  conf.setElitism(25);
//  	  conf.setPopulationSize(22); 
//  	   
//  
//  	  XYSeriesCollection dataset = new XYSeriesCollection(); 
//  	  XYSeries series3 = new XYSeries("Fitness - generation");
//  	  dataset.addSeries(series3);
//  	  
//  	  for (int i = 0; i < 22; i++){   
//  		  series3.add(i, conf.getBest().fitness);
//  		  conf.evolve();
//  	  }
//  	   

	
	  Crossover2 crossover;
	  Selection2 selection, endSelect;
	  Mutation mutation; 
	  int generation; 
	  
	  private Animal highestFitness = null;
	  
	  	  
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
  	  
  	  public void compute(Animal[] pop){
  		  
  		  if(highestFitness != null) {
  			  highestFitness.resetColor();
  		  }
  		
  		  Arrays.sort(pop, fitnessComparator);
  		  
  		  highestFitness = pop[0];
  		  highestFitness.setAsHighestFitness();
  		  
  		  ArrayList<Animal> newPop = new ArrayList<Animal>();
  		  
  		  for(int i=0; i<Config.NUM_ELITISM; i++) {
  			  newPop.add(pop[i]);
  			  newPop.get(i).setFitness(0);
  		  }
  		  
  		  for(int i=0; i<Config.NUM_CROSSOVER_CHILDREN / crossover.numChildren(); i++) {
  			  Animal[] animals = crossover.cross(selection.select(pop, crossover.numParents()));
  			  for(Animal a : animals) {
  				  newPop.add(a);
  			  }
  			  //newPop.addAll(crossover.cross(selection.select(pop, crossover.numParents())));
  		  }
  		  
  		  Animal[] rest = endSelect.select(pop, pop.length - newPop.size());
  		  for(Animal a : rest) {
  			  newPop.add(a.copy());
  		  }
  		  
  		  for(Animal a : newPop.subList(Config.NUM_ELITISM, Config.NUM_INDIVIDUALS-1)) {
  			  mutation.mutate(a);
  		  }
  		  
  		  //newPop.addAll(endSelect.select((ArrayList<Animal>)pop.subList(Config.NUM_ELITISM, pop.size()),pop.size()-newPop.size()));
  		  
  		  //TODO pop and newPop must be same length
  		  for(int i=0; i<pop.length; i++) {
  			  pop[i] = newPop.get(i);
  		  }
  		  
  	  }


}


//	
//	// Number of individuals to preserve unchanged for the next generation
//	private static final int ELITISM = 3;
//	
//	// Number of pairs selected for crossover
//	private static final int CROSSOVER_PAIRS = 3;
//	
//	//private static final float MUTATION_RATE = 1.0f / Environment.NUM_INDIVIDUALS;
//	private static final double MUTATION_RANGE = 0.5f;
//	
//	//private static final Random random = new Random();
//	
//	//private static final int rankSum = Utility.sumNumbers(Environment.NUM_INDIVIDUALS);
//	
//	private static Comparator<Animal> fitnessComparator = new Comparator<Animal>() {
//		@Override
//		public int compare(Animal a1, Animal a2) {
//			return Float.compare(a2.fitness(), a1.fitness());
//		}
//	};
//	
//	//Arrays.sort(animals, fitnessComparator);
//	
//	public static void createNewPopulation(Animal[] population) {
//		Arrays.sort(population, fitnessComparator);
//		Animal[] newPopulation = new Animal[population.length];
//		int index = 0;
//		for(int i=0; i<ELITISM; i++) {
//			newPopulation[index] = population[index];
//			newPopulation[index].setFitness(0);
//			index++;
//		}
//		System.out.println("ELITISM DONE!!!");
//		
//		/*Här börjar de nya */
//		
//		for(int i = ELITISM; i < population.length; i++){
//			
//			//Animal[] numberOfParents = {population[i],population[i+1]};
//			Animal[] numberOfParents = {Selection.rank(population),Selection.rank(population)};
//			double [] genome = Crossover.weigthedAvg(numberOfParents, 1);
//			Animal p1 = new Animal(population[i].getEnvironment(), population[i].getSensors(), population[i].getActions());
//			mutate(genome);
//			p1.setGenome(genome);
//			newPopulation[index] = p1;
//			index++;
//		
//		}
//		/* Gammla */
//		/*
//		for(int i=0; i<CROSSOVER_PAIRS; i++) {
//			Animal p1 = Selection.rank(population);
//			Animal p2 = Selection.rank(population);
//			while(p1.id() == p2.id()) {
//				p2 = Selection.rank(population);
//			}
//			
//			double[][] genomes = Crossover.uniform(p1.getGenome(), p2.getGenome());
//			p1 = new Animal(p1.getEnvironment(), p1.getSensors(), p1.getActions());
//			mutate(genomes[0]);
//			p1.setGenome(genomes[0]);
//			p2 = new Animal(p2.getEnvironment(), p2.getSensors(), p2.getActions());
//			mutate(genomes[1]);
//			p2.setGenome(genomes[1]);
//			newPopulation[index] = p1;
//			newPopulation[index+1] = p2;
//			
//			index += 2;
//			
//		}*/
//		
//		while(index < Environment.NUM_INDIVIDUALS) {
//			newPopulation[index] = new Animal(population[0].getEnvironment(), population[0].getSensors(), population[0].getActions());
//			index++;
//		}
//		
//		for(int i=0; i<population.length; i++) {
//			population[i] = newPopulation[i];
//		}
//	}
//	
//	private static void mutate(double[] genome) {
//		double p = 1.0 / genome.length;
//		for(int i=0; i<genome.length; i++) {
//			if(Utility.random.nextDouble() <= p * 10) {
//				genome[i] += Utility.random.nextDouble() * MUTATION_RANGE * 2 - MUTATION_RANGE;
//			}
//		}
//	}
//	
///*	private static Animal selectByRank(Animal[] population) {
//		int x = random.nextInt(rankSum);
//		int y = 0;
//		int i = population.length - 1;
//		while(y < x) {
//			y += i;
//			i--;
//		}
//		return population[i];
//	}*/
//
//}
//
//
//
//
//
//
//
//
