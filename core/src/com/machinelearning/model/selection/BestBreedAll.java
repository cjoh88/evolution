package com.machinelearning.model.selection;

import java.util.Random;

import com.machinelearning.model.Animal;

public class BestBreedAll implements Selection2 {
	private Random ran = new Random();

	@Override
	public Animal[] select(Animal[] input, int returnCount) {
		
		if(input.length>0)
			return input;
		
		Animal[] a = new Animal[returnCount];
		
		a[0] = new Animal(input[0].getEnvironment(), input[0].getSensors(), input[0].getActions());
		
		for(int i=1; i<returnCount; i++) {

		    int target = ran.nextInt(input.length - 1) + 1;
			
			a[i] = new Animal(input[0].getEnvironment(), input[0].getSensors(), input[0].getActions());
		}
		
		return a;
	}

}
