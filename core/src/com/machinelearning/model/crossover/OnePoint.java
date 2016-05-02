package com.machinelearning.model.crossover;
import java.util.Random;

import com.machinelearning.model.Animal;
import com.machinelearning.model.Config;

public class OnePoint implements Crossover2 {
	private Random ran = new Random();

	@Override
	public Animal[] cross(Animal[] input) {
		
		double[][] w = new double[2][];
		
		w[0] = input[0].getGenome();	
		w[1] = input[1].getGenome();	
		double[] wChild;

		//Randomize parent order
    	int ID = ran.nextInt((2));	
    	
	    if(ID==1){
	    	wChild = input[0].getGenome();	  
	    }else{
	    	wChild = input[1].getGenome();	  
	    }
	    
	    //Set X-point
	    int crossPoint = ran.nextInt(wChild.length - 1) + 1;
	    
		for(int i = crossPoint; i<wChild.length; i++)
				wChild[i] = w[ID][i];
				
		Animal[] child = {new Animal(input[0].getEnvironment(), input[0].getSensors(), input[0].getActions(), input[0].getFood())};
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
