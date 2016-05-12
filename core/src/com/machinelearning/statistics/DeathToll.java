package com.machinelearning.statistics;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DeathToll {
	public XYSeriesCollection dataset = new XYSeriesCollection();
	public XYSeries series1 = new XYSeries("Starvation Death toll");
	public XYSeries series2 = new XYSeries("Eaten Death toll");

	public DeathToll() {
		dataset.addSeries(series1);
		dataset.addSeries(series2);
	}

	public void addDead(int naturalCauses, int killed, int generation) {
		series1.add(generation, naturalCauses);
		series2.add(generation, killed);
	}

}
