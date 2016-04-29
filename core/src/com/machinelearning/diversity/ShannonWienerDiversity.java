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
				//System.out.println("Genes " + genes[d]);
				sum += genes[d];
				
			}
			diversity += calculateSW(sum, genes);
			System.out.println("Diversity HEJ" + diversity);
			eveness += diversity / Math.log(population.length);
			
		}
		
		//Normalize diversity?
		//diversity = diversity / genotypeLength;
		
		//Normalize Eveness (REQUIRED for it to be [0,1])
		eveness = eveness / genotypeLength;
		
		div_eve[0] = diversity;
		div_eve[1] = eveness;
		
		return div_eve;
	}
	
	public double[] calculateDiversity2(double[] population) {
		
		//int genotypeLength = population[0].length;
		double[] div_eve = {0.0,0.0};   //Diversity and eveness
		double sum = 0;
		double diversity = 0;
		double eveness = 0;
		double[] genes = new double[population.length];
		
		//for(int i = 0; i<genotypeLength; i++){
			
			for (int d = 0; d<population.length; d++){
				genes[d] = population[d];
				sum += genes[d];
			}
			diversity += calculateSW(sum, genes);
			eveness += diversity / Math.log(population.length);
			
	//	}
		
		//Normalize diversity?
		//diversity = diversity / genotypeLength;
		
		//Normalize Eveness (REQUIRED for it to be [0,1])
		//eveness = eveness / genotypeLength;
		
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
