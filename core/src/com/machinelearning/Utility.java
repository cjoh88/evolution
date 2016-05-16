package com.machinelearning;

import java.util.Random;

import org.neuroph.core.Connection;
import org.neuroph.core.Layer;
import org.neuroph.core.Neuron;

import com.machinelearning.model.Animal;

public class Utility {
	
	private static long ID = 0;
	
	public static Random random = new Random();
	
	public static void log(String string) {
		//TODO implement logging
		System.out.println(string);
	}
	
	public static long getID() {
		long id = ID;
		ID++;
		return id;
	}
	
	public static int sumNumbers(int x) {
		int result = 0;
		for(int i=0; i<x; i++) {
			result += i;
		}
		return result;
	}
	
	/*public static void normalizeWeights(Animal[] animals) {
		for(Animal a : animals) {
			double max = 0.0;
			Layer l = a.getMLP().getLayerAt(1);
			for(Neuron n : l.getNeurons()) {
				for(Connection w : n.getInputConnections()) {
					if(w.getWeight().value > max) {
						max = w.getWeight().value;
					}
				}
			}
			System.out.println("Max: " + max);
			for(Neuron n : l.getNeurons()) {
				for (Connection c : n.getInputConnections()) {
					c.getWeight().value /= max;
				}
			}
			max = 0.0;
			for(Neuron n : l.getNeurons()) {
				for(Connection w : n.getOutConnections()) {
					if(w.getWeight().value > max) {
						max = w.getWeight().value;
					}
				}
			}
			for(Neuron n : l.getNeurons()) {
				for (Connection c : n.getOutConnections()) {
					c.getWeight().value /= max;
				}
			}
		}
	}*/
	
	public static void normalizeWeights(Animal[] pop) {
		for(Animal a : pop) {
			Layer l = a.getMLP().getLayerAt(1);
			//for(Neuron n : l.getNeurons()) {
			for(int i=0; i<l.getNeuronsCount()-1; i++) {
				Neuron n = l.getNeuronAt(i);
				normalizeNeuron(n);
			}
		}
	}
	
	private static void normalizeNeuron(Neuron n) {
		double max = 0.0;
		for(Connection c : n.getInputConnections()) {
			if(c.getWeight().value > Math.abs(max)) {
				max = Math.abs(c.getWeight().value);
			}
		}
		for(Connection c : n.getInputConnections()) {
			if(max > 0.0) {
				c.getWeight().value /= max;
			}
		}
		/*max = 0.0;
		for(Connection c : n.getOutConnections()) {
			if(c.getWeight().value > Math.abs(max)) {
				max = Math.abs(c.getWeight().value);
			}
		}
		for(Connection c : n.getOutConnections()) {
			c.getWeight().value /= max;
		}*/
	}
	
	public static void sortNodes(Animal[] animals) {
		Layer layer = animals[0].getMLP().getLayerAt(1);
		for(int a=1; a<animals.length; a++) {
			Layer otherLayer = animals[a].getMLP().getLayerAt(1);
			for(int n=0; n<layer.getNeuronsCount()-1; n++) {
				Neuron neuron = layer.getNeuronAt(n);
				int closestIndex = n;
				Neuron otherNeuron = otherLayer.getNeuronAt(closestIndex);
				double dst = getDistance(neuron, otherNeuron);
				for (int i=n+1; i<otherLayer.getNeuronsCount()-1; i++) {
					Neuron x = otherLayer.getNeuronAt(i);
					double d = getDistance(neuron, x);
					if(d < dst) {
						closestIndex = i;
						otherNeuron = x;
						dst = d;
					}
				}
				swapNeurons(otherLayer, closestIndex, n);
			}
		}
	}
	
	public static void sortNodes2(Animal[] animals) {
		Layer layer = animals[0].getMLP().getLayerAt(1);
		for(int a=1; a<animals.length; a++) {
			Layer otherLayer = animals[a].getMLP().getLayerAt(1);
			for(int n=0; n<layer.getNeuronsCount()-1; n++) {
				Neuron neuron = layer.getNeuronAt(n);
				int closestIndex = n;
				Neuron otherNeuron = otherLayer.getNeuronAt(closestIndex);
				double dst = getDistance2(neuron, otherNeuron);
				for (int i=n+1; i<otherLayer.getNeuronsCount()-1; i++) {
					Neuron x = otherLayer.getNeuronAt(i);
					double d = getDistance2(neuron, x);
					if(d < dst) {
						closestIndex = i;
						otherNeuron = x;
						dst = d;
					}
				}
				swapNeurons(otherLayer, closestIndex, n);
			}
		}
	}
	
	public static void sortNodes3(Animal[] animals) {
		Layer layer = animals[0].getMLP().getLayerAt(1);
		for(int a=1; a<animals.length; a++) {
			Layer otherLayer = animals[a].getMLP().getLayerAt(1);
			for(int n=0; n<layer.getNeuronsCount()-1; n++) {
				Neuron neuron = layer.getNeuronAt(n);
				int closestIndex = n;
				Neuron otherNeuron = otherLayer.getNeuronAt(closestIndex);
				double dst = getDistance(neuron, otherNeuron) + getDistance2(neuron, otherNeuron);
				for (int i=n+1; i<otherLayer.getNeuronsCount()-1; i++) {
					Neuron x = otherLayer.getNeuronAt(i);
					double d = getDistance(neuron, otherNeuron) + getDistance2(neuron, x);
					if(d < dst) {
						closestIndex = i;
						otherNeuron = x;
						dst = d;
					}
				}
				swapNeurons(otherLayer, closestIndex, n);
			}
		}
	}
	
	private static void swapNeurons(Layer layer, int newPos, int oldPos) {
		Neuron no = layer.getNeuronAt(oldPos);
		Neuron nn = layer.getNeuronAt(newPos);
		layer.setNeuron(newPos, no);
		layer.setNeuron(oldPos, nn);
	}
	
	private static double getDistance(Neuron a, Neuron b) {
		Connection[] aConn = a.getInputConnections();
		Connection[] bConn = b.getInputConnections();
		double sum = 0.0;
		for(int i=0; i<aConn.length; i++) {
			//System.out.println("a: " + aConn.length + "\nb: " + bConn.length);
			//System.out.println(i);
			double wa = aConn[i].getWeight().value;
			double wb = bConn[i].getWeight().value;
			sum += (wb - wa) * (wb - wa);
		}
		return sum;
	}
	
	private static double getDistance2(Neuron a, Neuron b) {
		Connection[] aConn = a.getOutConnections();
		Connection[] bConn = b.getOutConnections();
		double sum = 0.0;
		for(int i=0; i<aConn.length; i++) {
			//System.out.println("a: " + aConn.length + "\nb: " + bConn.length);
			//System.out.println(i);
			double wa = aConn[i].getWeight().value;
			double wb = bConn[i].getWeight().value;
			sum += (wb - wa) * (wb - wa);
		}
		return sum;
	}

}





