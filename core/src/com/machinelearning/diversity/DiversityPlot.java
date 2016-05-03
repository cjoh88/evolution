package com.machinelearning.diversity;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.machinelearning.model.Animal;
import com.machinelearning.model.Config;
import com.machinelearning.model.crossover.Crossover2;
import com.machinelearning.model.crossover.WeigthedAvg;

public class DiversityPlot {

	static XYSeries values = new XYSeries("Prey");
	static XYSeries values2 = new XYSeries("Predator");
	public static final Diversity DIVERSITY = new HammingDistanceDiversity();

	public DiversityPlot() {

		if (Config.PLOT_STATS) {

			XYSeriesCollection dataset = new XYSeriesCollection(values);
			dataset.addSeries(values2);
			JFreeChart chart = ChartFactory.createXYLineChart("", "Generation", "Diversity", dataset,
					PlotOrientation.VERTICAL, true, true, false);

			final XYPlot plot = chart.getXYPlot();
			ValueAxis axis = plot.getDomainAxis();
			axis.setAutoRange(true);
			// axis.setFixedAutoRange(60000.0);

			JFrame frame = new JFrame("Diversity for population / generation");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ChartPanel label = new ChartPanel(chart);
			frame.getContentPane().add(label);
			// Suppose I add combo boxes and buttons here later

			frame.pack();
			frame.setVisible(true);
		}
	}

	public void plot(Animal[] animals,Animal[] predator, int generation) {

		double[] div_eve = DIVERSITY.calculateDiversity(animals);
		double[] div = DIVERSITY.calculateDiversity(predator);
		System.out.println("Diversity: " + div_eve[0]);
		values.addOrUpdate(generation, div_eve[0]);
		values2.addOrUpdate(generation, div[0]);
		
		
	}

}
