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

public class DiversityPlot {
	
	  static XYSeries values = new XYSeries( "Values" );
	  ShannonWienerDiversity SWD = new ShannonWienerDiversity();
    public DiversityPlot(){
        
       
        
        XYSeriesCollection dataset = new XYSeriesCollection(values);    
        JFreeChart chart = ChartFactory.createXYLineChart(
                "" ,
                "Generation" ,
                "Diversity" ,
                dataset ,
                PlotOrientation.VERTICAL ,
                true , true , false
                );
        
        final XYPlot plot = chart.getXYPlot();
        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
       // axis.setFixedAutoRange(60000.0);

        JFrame frame = new JFrame("Diversity for population / generation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChartPanel label = new ChartPanel(chart);
        frame.getContentPane().add(label);
        //Suppose I add combo boxes and buttons here later

        frame.pack();
        frame.setVisible(true);
    }


    public void plot(Animal[] animals, int generation) {
        	
        	   double[] div_eve = SWD.calculateDiversity(animals);
        	   System.out.println("Diversity: " + div_eve[0]);
        	   values.addOrUpdate(generation, (int) div_eve[0]);
    }
       

}
    

