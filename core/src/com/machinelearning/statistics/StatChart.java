package com.machinelearning.statistics;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;

public class StatChart extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatChart() {
		super("Evoulutionary computing");
		setLayout(new GridLayout(2, 2));
		setSize(1000, 700);

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public Statistics statAdd(String namn, String xStr, String yStr) {
		Statistics stats = new Statistics();
		JPanel chartPanel = createChartPanel(stats.dataset, namn, xStr, yStr);
		add(chartPanel);
		setVisible(true);

		return stats;
	}

	public DiversityData addPlot(String namn, String xStr, String yStr) {

		DiversityData stats = new DiversityData();
		// stats.addFitness(animals, predator, generation);
		JPanel chartPanel = createChartPanel(stats.dataset, namn, xStr, yStr);
		add(chartPanel);
		setVisible(true);

		return stats;

	}

	private JPanel createChartPanel(XYDataset dataset, String namn, String xStr, String yStr) {
		String chartTitle = namn;// "Fitness/Generation-chart";
		String xAxisLabel = xStr;// "Average generation fitness";
		String yAxisLabel = yStr;// "Best individual fitness";
		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset,
				PlotOrientation.VERTICAL, true, false, false);
		return new ChartPanel(chart);
	}


}
