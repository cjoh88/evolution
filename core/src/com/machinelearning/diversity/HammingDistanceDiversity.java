package com.machinelearning.diversity;

import com.machinelearning.model.Animal;

public class HammingDistanceDiversity implements Diversity {

	@Override
	public double[] calculateDiversity(Animal[] population) {
		double diversity = 0;
		
		for(int i = 0; i<population.length; i++){
			double [] firstAnimal = population[i].getGenome();
			for(int d = i+1; d<population.length; d++){
				double [] secondAnimal = population[d].getGenome();
				diversity += weigthDiversity(firstAnimal, secondAnimal);
				
			}
			
			
		}
		
		//Avg diversity?
		 diversity = diversity /(population.length * population[0].getGenome().length);
		double[] result = {diversity};
		return result;
	}
	
	private double weigthDiversity(double[] firstAnimalW, double[] secondAnimalW){
		
		if(firstAnimalW.length != secondAnimalW.length){
			System.out.println("Animals have different amount of genes");
			return 0.0;
		}
		double diversity = 0.0;
		for(int i = 0; i<firstAnimalW.length; i++){
			diversity += Math.abs(firstAnimalW[i] - secondAnimalW[i]);		
		}
		
		
		return diversity;
	}

}
