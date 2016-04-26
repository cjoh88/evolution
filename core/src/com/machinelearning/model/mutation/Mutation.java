package com.machinelearning.model.mutation;

import java.util.ArrayList;

import com.machinelearning.model.Animal;

public interface Mutation {
	public Animal mutate(Animal input);
}
