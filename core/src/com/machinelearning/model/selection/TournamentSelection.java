package com.machinelearning.model.selection;

import java.util.Arrays;
import java.util.Comparator;

import com.machinelearning.model.Animal;
import com.machinelearning.model.Config;

public class TournamentSelection implements Selection2 {

	/* ALL OF THESE SHOULD BE ADDED TO THE CONFIG FILE */
	int tournamentPoolSize = 5; 
	double propabilityToPickHighest = 1.0;
	boolean returnSameParentMoreThanOnce = false; 
	/*-----------------------------------------------*/
	
	private static Comparator<Animal> fitnessComparator = new Comparator<Animal>() {
		@Override
		public int compare(Animal a1, Animal a2) {
			return Float.compare(a2.fitness(), a1.fitness());
		}
	};
	
	@Override
	public Animal[] select(Animal[] input, int returnCount) {
		Animal[] parents = new Animal[returnCount];
		for(int i = 0; i<returnCount; i++){
			Animal newParent = tournamentSelect(input);
			if(!returnSameParentMoreThanOnce && alreadyPicked(parents,newParent)){
				i--;
			}
			else{
				parents[i] = newParent;
			}		
		}
		
		return parents;
	}

	private Animal tournamentSelect(Animal[] input){
		Animal[] tournamentPool = new Animal[tournamentPoolSize];
		
		
		/*This to avoid getting stuck in a infinite loop incase individuals are lower than the tourneypool */
		int alreadyPickedTries = 0;
		int maxTries = 10; 
		
		for(int i =0; i<tournamentPoolSize; i++){
			Animal newAnimal =  input[(int)(Math.random() * input.length)];
			
			if(alreadyPickedTries < maxTries && alreadyPicked(tournamentPool,newAnimal)){ 
				i--;
				alreadyPickedTries++;
			}
			
			else{
				tournamentPool[i] = newAnimal;
			}
		}
		
		Arrays.sort(tournamentPool, fitnessComparator);
		
		/*Currently deterministic */
		return tournamentPool[0];
		
	}
	
	private boolean alreadyPicked(Animal [] picked, Animal individual){
		for(int i =0; i<picked.length; i++){
			if(picked[i] != null && picked[i].id() == individual.id() ){
				return true;
			}
		}
		
		
		return false;
	}
	
}
