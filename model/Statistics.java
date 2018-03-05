package model;

import java.util.ArrayList;
import java.util.Collections;
/**The Statistics class is a simple class responsible for calculating the
 * statistics from a junction simulation. It's not aware of the junction or
 * the origin of the numbers. 
 * @author Connor Fulton
 *
 */
public class Statistics {
	private double sum, max, min, mean, variance;
	private ArrayList<Double> times;
	
	/**Constructor for the statistics class.
	 * @param times list of times in seconds
	 */
	public Statistics(ArrayList<Double> times)
	{
		this.times = times;
	}
	
	public double getMax()
	{
		max = 0;
		for(double t : times)
		{
			if(t > max)
			{
				max = t;
			}
		}
		return max;
	}
	
	public double getMin()
	{
		min = 1000;
		for(double t : times)
		{
			if(t < min)
			{
				min = t;
			}
		}
		return min;
	}
	
	public double getMean()
	{
		return getSum() / times.size();
	}
	
	public double getVariance()
	{
		double mean = getMean();
		double temp = 0;
		for(double t : times)
		{
			temp += (t-mean) * (t-mean);
		}
		return temp / (times.size()-1);
	}
	
	public double getSum()
	{
		sum = 0;
		for(double t : times)
		{
			sum += t;
		}
		return sum;
	}
	
	public String printReport()
	{
		String report = String.format("Simulation statistics:%n" + 
										"Mean time: %.3f%n" +
										"Min time: %.3f%n" +
										"Max time: %.3f%n" +
										"Variance: %.3f%n",
										getMean(),getMin(),getMax(),getVariance());
		return report;
	}
	
}
