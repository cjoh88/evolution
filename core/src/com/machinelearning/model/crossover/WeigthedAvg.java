package com.machinelearning.model.crossover;

import com.machinelearning.model.Animal;
import com.machinelearning.model.Config;

public class WeigthedAvg implements Crossover2 {

	@Override
	public Animal[] cross(Animal[] input) {
		int numberofParents = Config.WEIGTHEDAVG_PARENTS;
		int weigthFlag = Config.WEIGTHEDAVG_FLAG;
		
	    double[] weigthsChild = new double[input[0].getGenome().length];	
	    
		double totalFitness = 0.0;
	    
		for(int i =0; i< numberofParents; i++){ //Get total fitness
			totalFitness += input[i].fitness();
		 }
			
	    
		if(weigthFlag == 1 && totalFitness > 0){  //Weigthed average based on fitness.
			double[] WeigthProbPerParent = new double[numberofParents];
		    
			for(int i =0; i<numberofParents; i++){  //Calculate amount of impact each parent has
               WeigthProbPerParent[i] = input[i].fitness()/totalFitness;			
			}
			
			for(int i =0; i<weigthsChild.length; i++){ //Calculate weigths for the child
				for(int d = 0; d< numberofParents; d++){
					weigthsChild[i] += WeigthProbPerParent[d] * (input[d].getGenome())[i].doubleValue();
				}
			}
				
	    }
		else{ //Just average over all parents
				
				for(int i =0; i<weigthsChild.length; i++){ //Calculate weigths for the child
					for(int d = 0; d < numberofParents; d++){
						weigthsChild[i] +=  (input[d].getGenome())[i].doubleValue();
					}
					weigthsChild[i] = weigthsChild[i]/numberofParents;
				}
				
				
			}
			
		//Return entire animal or just weigths?..
		if(weigthsChild.length== 0){
		System.out.print("Hej");
		}
		
		Animal[] child = {new Animal(input[0].getEnvironment(), input[0].getSensors(), input[0].getActions())};
		child[0].setGenome(weigthsChild);
		return child;
		
	}


	@Override
	public int numParents() {
		return Config.WEIGTHEDAVG_PARENTS;
	}

	@Override
	public int numChildren() {
		return 1;
	}

}
