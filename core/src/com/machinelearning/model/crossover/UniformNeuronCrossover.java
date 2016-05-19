package com.machinelearning.model.crossover;

import org.neuroph.core.Layer;
import org.neuroph.core.Neuron;

import com.machinelearning.Utility;
import com.machinelearning.model.Animal;

public class UniformNeuronCrossover implements Crossover2 {

	@Override
	public Animal[] cross(Animal[] input) {
		Animal a = input[0].copy();
		Animal b = input[1].copy();
		Layer la = a.getMLP().getLayerAt(1);
		Layer lb = b.getMLP().getLayerAt(1);
		for(int i=0; i<la.getNeuronsCount()-1; i++) {
			if(Utility.random.nextBoolean()) {
				Neuron na = la.getNeuronAt(i);
				Neuron nb = lb.getNeuronAt(i);
				la.setNeuron(i, nb);
				lb.setNeuron(i, na);
			}
		}
		return new Animal[]{a, b};
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
