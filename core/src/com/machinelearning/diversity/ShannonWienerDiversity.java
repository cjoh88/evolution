package com.machinelearning.diversity;

import com.machinelearning.model.Animal;

public class ShannonWienerDiversity implements Diversity {

	public ShannonWienerDiversity(){
		
	}
	
	
	@Override
	public double[] calculateDiversity(Animal[] population) {
		
		
		int genotypeLength = population[0].getGenome().length;
		double[] div_eve = {0.0,0.0};   //Diversity and eveness
		double sum = 0;
		double diversity = 0;
		double eveness = 0;
		double[] genes = new double[population.length];
		
		for(int i = 0; i<genotypeLength; i++){
			
			for (int d = 0; d<population.length; d++){
				double [] de = population[d].getGenome();
				genes[d] = de[i];
				
				sum += genes[d];
				
			}
			diversity += calculateSW(sum, genes);
			eveness += diversity / Math.log(population.length*genotypeLength);
			
		
		}
		//Normalize diversity?
		diversity = diversity / (genotypeLength*population.length);
		
		//Normalize Eveness (REQUIRED for it to be [0,1])
		//eveness = eveness / genotypeLength;
		
		System.out.println("Eveness " + eveness);
		
		div_eve[0] = diversity;
		div_eve[1] = eveness;
		
		return div_eve;
	}
	
	
	
	private double calculateSW(double sum, double[] genes ){  // W/Tot * ln(W/Tot)
		double diversitySum = 0;
		for(int i = 0; i<genes.length; i++){
			genes[i] = genes[i] / sum;
			
			
			
			
			genes[i] = genes[i] * Math.log(Math.abs(genes[i])); //Using ABS is not really good
			
		
			diversitySum += genes[i];
		}
	
		
		return Math.abs(diversitySum);
	}
	
}
