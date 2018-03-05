import java.util.ArrayList;
import java.util.Random;

import model.*;

public class APSpec1 {
	
	public static void main(String[] args) {
		
		int rows = 10;
		int cols = 20;
		Grid grid = new Grid(rows,cols);
		GridDrawer drawer = new GridDrawer(grid, 2000);
		Random rand = new Random();
		ArrayList<Double> elapsedTimes = new ArrayList<Double>();
		ArrayList<Vehicle> allVehicles = new ArrayList<Vehicle>();
		String[] directions = {"SOUTH","EAST"};
		int maxGens = 20;
		TrafficGenerator[] generators = new TrafficGenerator[maxGens];
		
		
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
		
		drawer.start();
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
			}
		}
		
		
		
		
		
	}

}
