package com.machinelearning.model.mutation;

import com.machinelearning.model.Animal;

public class RandomMutation implements Mutation {

	@Override
	public Animal mutate(Animal input) {
		return input;
	}
	

}
