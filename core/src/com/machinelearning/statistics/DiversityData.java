package com.machinelearning.statistics;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.machinelearning.diversity.Diversity;
import com.machinelearning.diversity.PhenotypeDiversity;
import com.machinelearning.model.Animal;

public class DiversityData {
	public XYSeriesCollection dataset = new XYSeriesCollection();
	public XYSeries series1 = new XYSeries("Prey");
	public XYSeries series2 = new XYSeries("Predator");
	public static final Diversity DIVERSITY = new PhenotypeDiversity();

	
	public DiversityData() {
		dataset.addSeries(series1);		
		dataset.addSeries(series2);
	}
	
	public void addFitness(Animal[] animals, Animal[] predator, int generation) {
		double[] div_eve = DIVERSITY.calculateDiversity(animals);
		double[] div = DIVERSITY.calculateDiversity(predator);
		series1.addOrUpdate(generation, div_eve[0]);
		series2.addOrUpdate(generation, div[0]);
	}
}
