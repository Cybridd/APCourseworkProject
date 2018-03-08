import java.util.ArrayList;
import java.util.Random;

import model.*;
/**APSpec2 contains the main method for part 2 of the assessed exercise.
 * It involves a junction with traffic on all lanes and direction changed
 * for each half of the rows and columns. Vehicles have randomly allocated
 * movement delays (speeds).
 * @author Connor Fulton
 *
 */
public class APSpec2 {

	public static void main(String[] args) {
		
		/* Instantiate objects and initialize variables */
		int rows = 10;
		int cols = 20;
		Grid grid = new Grid(rows,cols);
		GridDrawer drawer = new GridDrawer(grid, 2000);
		Random rand = new Random();
		ArrayList<ArrayList<Double>> elapsedTimes = new ArrayList<ArrayList<Double>>();
		String[] directions = {"NORTH","SOUTH","EAST","WEST"};
		int maxGens = rows + cols;
		TrafficGenerator[] generators = new TrafficGenerator[maxGens];
		
		/* Create traffic generators for every lane, with 
		 * direction different for each half of the rows 
		 * and columns. Vehicles have random movement speeds.
		 */
		for(int i = 0; i < (rows/2); i++)
		{
			int delay = rand.nextInt(1900) + 100;  // 100ms minimum delay so no supersonic cars
			generators[i] = new TrafficGenerator(grid, 1, delay, i, directions[2]);
			generators[i + (rows/2)] = new TrafficGenerator(grid, 1, delay, i + (rows/2), directions[3]);  // Second half of rows have opposite direction
		}
		for(int i = rows; i < cols; i++)
		{
			int delay = rand.nextInt(1900) + 100;
			generators[i] = new TrafficGenerator(grid, 1, delay, i - rows, directions[0]);
			generators[i + (cols/2)] = new TrafficGenerator(grid, 1, delay, i + (cols/2) - rows, directions[1]);  // Second half of columns have opposite direction
		}
		
		// Set vehicle factory for all generators
		for(TrafficGenerator tg : generators)
		{
			tg.setVehicleFactory("Car");
		}
		
		// Start GridDrawer thread
		drawer.start();
		
		/* Generate traffic (start vehicle threads) until drawer
		 * is done. Wait between generations so traffic jams are 
		 * not too severe.
		 */
		while(!drawer.isDone())
		{
			for(int j = 0; j < maxGens; j++)
			{
				generators[j].generateVehicle();
			}
			try
			{
				Thread.sleep(6500);  // Longer delay than APSpec1 because all lanes have traffic
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		// Message to reassure user something is still happening
		System.out.println("Calculating vehicle journey times, please wait...");
		
		/* Wait for vehicle threads to finish, calculate their
		 * journey times and display them to the user
		 */
		for(TrafficGenerator tg : generators)
		{
			elapsedTimes.add(new ArrayList<Double>());
			for(Vehicle v : tg.getGeneratedVehicles())
			{
				try
				{
					v.join();
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				System.out.println(String.format("Elapsed time: %.3f seconds", v.getElapsedTime()));
				elapsedTimes.get(elapsedTimes.size()-1).add(v.getElapsedTime());
			}
		}
		
		// Instantiate Statistics object and print report for user
		for(int i = 0; i < maxGens; i++)
		{
		Statistics statistics = new Statistics(elapsedTimes.get(i));
		System.out.println("Traffic Generator " + (i+1) + " statistics:\n" + statistics.printReport());
		}

	}

}
