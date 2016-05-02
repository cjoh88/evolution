package com.machinelearning.statistics;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.machinelearning.model.Config;


public class statistics {

	XYSeriesCollection dataset = new XYSeriesCollection();
	XYSeries series1 = new XYSeries("Average generation fitness");
	XYSeries series2 = new XYSeries("Best individual fitness");

	public statistics(String namn) {
		if(Config.PLOT_STATS){
			new XYLineChartExample(dataset, namn).setVisible(true);
			dataset.addSeries(series1);
			dataset.addSeries(series2);
		}
	}

	
	public void addFitness(double fitAVG, double fitBEST, int generation){
			series1.add(generation, fitAVG);
			series2.add(generation, fitBEST);
		
	}
	
	public class XYLineChartExample extends JFrame {

		public XYLineChartExample(XYDataset dataset, String namn) {
			super("XY Line Chart Example with JFreechart");

			JPanel chartPanel = createChartPanel(dataset, namn);
			add(chartPanel, BorderLayout.CENTER);

			setSize(640, 480);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
		}

		private JPanel createChartPanel(XYDataset dataset, String chartTitle) {
			//String chartTitle = "Fitness/Generation-chart";
			String xAxisLabel = "Average generation fitness";
			String yAxisLabel = "Best individual fitness";
			JFreeChart chart = ChartFactory.createScatterPlot(chartTitle, xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL, true, false, false);
					//(chartTitle, xAxisLabel, yAxisLabel, dataset)
			return new ChartPanel(chart);
		}

	}
}