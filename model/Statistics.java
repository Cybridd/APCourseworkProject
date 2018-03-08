package model;

import java.util.ArrayList;
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
	/**Return the maximum value found in the
	 * list of doubles
	 * @return max maximum value found
	 */
	private double getMax()
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
	
	/**Return the minimum value found in the
	 * list of doubles
	 * @return min minimum value found
	 */
	private double getMin()
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
	
	/**Return the mean value for the
	 * list of doubles
	 * @return mean mean value found
	 */
	private double getMean()
	{
		return getSum() / times.size();
	}
	
	/**Return the variance for the
	 * list of doubles
	 * @return var variance value found
	 */
	private double getVariance()
	{
		double mean = getMean();
		double temp = 0;
		for(double t : times)
		{
			temp += (t-mean) * (t-mean);
		}
		return temp / (times.size()-1);
	}
	
	/**Return the sum of the
	 * list of doubles
	 * @return sum sum of values
	 */
	private double getSum()
	{
		sum = 0;
		for(double t : times)
		{
			sum += t;
		}
		return sum;
	}
	
	/**Return report of calculations
	 * for printing to console
	 * @return report String object for printing
	 */
	public String printReport()
	{
		String report = String.format("Mean time: %.3f%n" +
										"Min time: %.3f%n" +
										"Max time: %.3f%n" +
										"Variance: %.3f%n",
										getMean(),getMin(),getMax(),getVariance());
		return report;
	}
	
}
