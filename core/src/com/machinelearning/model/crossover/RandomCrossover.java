package com.machinelearning.model.crossover;

import java.util.ArrayList;
import java.util.Random;

import com.machinelearning.model.Animal;

public class RandomCrossover implements Crossover2 {

	@Override
	public Animal[] cross(Animal[] input) {
		
	    double[] weigthsChild = new double[input[0].getGenome().length];	
	    
	    for(int i = 0; i< weigthsChild.length; i++){
			Random generator = new Random();
	    	double value = generator.nextDouble();
	    	
	    	if(value < 0.5){
	    		weigthsChild[i] = (input[0].getGenome())[i];
	    	}
	    	else{
	    		weigthsChild[i] = (input[1].getGenome())[i];
	    	}
	    }
	    
		Animal[] child = {new Animal(input[0].getEnvironment(), input[0].getSensors(), input[0].getActions(), input[0].getFood())};
		child[0].setGenome(weigthsChild);
		return child;
		
	}

	@Override
	public int numParents() {
		return 2;
	}

	@Override
	public int numChildren() {
		return 2;
	}
	
	

}
