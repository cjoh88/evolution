package com.machinelearning.model.crossover;
import java.util.Random;

import com.machinelearning.model.Animal;
import com.machinelearning.model.Config;

public class OnePoint implements Crossover2 {
	private Random ran = new Random();

	@Override
	public Animal[] cross(Animal[] input) {
		
	    double[] w1 = input[0].getGenome();	
	    double[] w2 = input[1].getGenome();	
	    double[] wChild = input[0].getGenome();	  
	    
	    
	    int crossPoint = ran.nextInt(w1.length - 1) + 1;
	    
		for(int i = crossPoint; i<wChild.length; i++)
				wChild[i] = w2[i];
				
		Animal[] child = {new Animal(input[0].getEnvironment(), input[0].getSensors(), input[0].getActions())};
		child[0].setGenome(wChild);
		
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
