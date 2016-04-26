package com.machinelearning.model.selection;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.machinelearning.model.Animal;

public class RandomSelection implements Selection2 {

	@Override
	public Animal[] select(Animal[] input, int returnCount) {
		Animal[] a = new Animal[returnCount];
		for(int i=0; i<returnCount; i++) {
			//a[i] = input[i];
			a[i] = new Animal(input[0].getEnvironment(), input[0].getSensors(), input[0].getActions());
		}
		return a;
	}

}
