package com.machinelearning.statistics;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Statistics {
	public XYSeriesCollection dataset = new XYSeriesCollection();
	public XYSeries series1 = new XYSeries("Average generation fitness");
	public XYSeries series2 = new XYSeries("Best individual fitness");

	public Statistics() {
		dataset.addSeries(series1);
		dataset.addSeries(series2);
	}

	public void addFitness(double fitAVG, double fitBEST, int generation) {
		series1.add(generation, fitAVG);
		series2.add(generation, fitBEST);
	}

}
