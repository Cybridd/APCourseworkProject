import java.util.ArrayList;
import java.util.Random;

import model.*;

public class APSpec2 {

	public static void main(String[] args) {
		
		int rows = 10;
		int cols = 20;
		Grid grid = new Grid(rows,cols);
		GridDrawer drawer = new GridDrawer(grid, 2000);
		Random rand = new Random();
		ArrayList<Double> elapsedTimes = new ArrayList<Double>();
		String[] directions = {"NORTH","SOUTH","EAST","WEST"};
		int maxGens = rows + cols;
		TrafficGenerator[] generators = new TrafficGenerator[maxGens];
		
		
		for(int i = 0; i < (rows/2); i++)
		{
			int delay = rand.nextInt(1900) + 100;
			generators[i] = new TrafficGenerator(grid, 1, delay, i, "EAST");
			generators[i + (rows/2)] = new TrafficGenerator(grid, 1, delay, i + (rows/2), "WEST");
		}
		for(int i = rows; i < cols; i++)
		{
			int delay = rand.nextInt(1900) + 100;
			generators[i] = new TrafficGenerator(grid, 1, delay, i - rows, "NORTH");
			generators[i + (cols/2)] = new TrafficGenerator(grid, 1, delay, i + (cols/2) - rows, "SOUTH");
		}
		
		for(TrafficGenerator tg : generators)
		{
			tg.setVehicleFactory("Car");
		}
		drawer.start();
		while(!drawer.isDone())
		{
			for(int j = 0; j < maxGens; j++)
			{
				generators[j].generateVehicle();
			}
			try
			{
				Thread.sleep(6500);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		System.out.println("Calculating vehicle journey times, please wait...");
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
				elapsedTimes.add(v.getElapsedTime());
			}
		}
		
		Statistics statistics = new Statistics(elapsedTimes);
		System.out.println(statistics.printReport());

	}

}
