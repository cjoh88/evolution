package com.machinelearning.model;

import java.util.Random;

public class Crossover {
	
	private static Random random = new Random();
	
	public static double[][] uniform(Double[] a1, Double[] a2) {
		double[][] result = new double[2][a1.length];
		for(int i=0; i<a1.length; i++) {
			if(random.nextBoolean()) {
				result[0][i] = a1[i];
				result[1][i] = a2[i];
			}
			else {
				result[0][i] = a2[i];
				result[1][i] = a1[i];
			}
		}
		return result;
	}
	
	public  static double[] weigthedAvg(Animal [] animals, int weigthFlag){
			int numberofParents = animals.length;
		    double[] weigthsChild = new double[animals[1].getGenome().length];	
		    
			double totalFitness = 0.0;
		    
			for(int i =0; i< numberofParents; i++){ //Get total fitness
				totalFitness += animals[i].fitness();
			 }
				
		    
			if(weigthFlag == 1 && totalFitness > 0){  //Weigthed average based on fitness.
				double[] WeigthProbPerParent = new double[numberofParents];
			    
				for(int i =0; i<numberofParents; i++){  //Calculate amount of impact each parent has
	               WeigthProbPerParent[i] = animals[i].fitness()/totalFitness;			
				}
				
				for(int i =0; i<weigthsChild.length; i++){ //Calculate weigths for the child
					for(int d = 0; d< numberofParents; d++){
						weigthsChild[i] += WeigthProbPerParent[d] * (animals[d].getGenome())[i].doubleValue();
					}
				}
					
		    }
			else{ //Just average over all parents
					
					for(int i =0; i<weigthsChild.length; i++){ //Calculate weigths for the child
						for(int d = 0; d < numberofParents; d++){
							weigthsChild[i] +=  (animals[d].getGenome())[i].doubleValue();
						}
						weigthsChild[i] = weigthsChild[i]/numberofParents;
					}
					
					
				}
				
			//Return entire animal or just weigths?..
			if(weigthsChild.length== 0){
			System.out.print("Hej");
			}
			return weigthsChild;
			
		}
		

}
