import java.util.Random;

import model.*;
/**APSpec1 contains the main method for part 1 of the assessed exercise.
 * It involves a junction with traffic heading in the NORTH->SOUTH and
 * EAST->WEST directions. I chose to generate cars on random lanes rather
 * than filling every row to make it more interesting.
 * @author Connor Fulton
 *
 */
public class APSpec1 {
	
	public static void main(String[] args) {
		
		/* Instantiate objects and initialize variables */
		int rows = 10;
		int cols = 20;
		Grid grid = new Grid(rows,cols);
		GridDrawer drawer = new GridDrawer(grid, 2000);
		Random rand = new Random();
		String[] directions = {"SOUTH","EAST"};
		int maxGens = 20;
		TrafficGenerator[] generators = new TrafficGenerator[maxGens];
		
		/*Create traffic generators for random lanes and randomly selected
		 * SOUTH or EAST direction. More than one generator per row/col allowed
		 * to create varying traffic densities.
		 */
		for(int i = 0; i < maxGens; i++)
		{
			String dir = directions[rand.nextInt(2)];
			int delay = rand.nextInt(1900) + 100;
			if(dir.equals("SOUTH"))
			{
				generators[i] = new TrafficGenerator(grid, 1, delay, rand.nextInt(cols), dir);
			}
			else
			{
				generators[i] = new TrafficGenerator(grid, 1, delay, rand.nextInt(rows), dir);
			}
			generators[i].setVehicleFactory("Car");
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
				Thread.sleep(4500);
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
			}
		}
		
		System.out.println("Simulation complete.");
		
		
		
		
		
	}

}
