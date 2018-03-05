import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import model.*;

public class APSpec1 {
	private static VehicleFactory factory;
	
	public static void main(String[] args) {
		
		int rows = 10;
		int cols = 20;
		Grid grid = new Grid(rows,cols);
		GridDrawer drawer = new GridDrawer(grid, 2000);
		Random rand = new Random();
		ArrayList<Double> elapsedTimes = new ArrayList<Double>();
		ArrayList<Vehicle> allVehicles = new ArrayList<Vehicle>();
		String[] directions = {/*"NORTH",*/"SOUTH","EAST"/*,"WEST"*/};
		int maxGens = 20;
		TrafficGenerator[] generators = new TrafficGenerator[maxGens];
		
		
		for(int i = 0; i < maxGens; i++)
		{
			String dir = directions[rand.nextInt(2)];
			if(dir.equals("NORTH") || dir.equals("SOUTH"))
			{
			generators[i] = new TrafficGenerator(grid, 1, 1000, rand.nextInt(cols), dir);
			}
			else
			{
			generators[i] = new TrafficGenerator(grid, 1, 1000, rand.nextInt(rows), dir);
			}
			generators[i].setVehicleFactory("Car");
		}
		
		drawer.start();
		while(!drawer.isDone())
		{
			for(int j = 0; j < maxGens; j ++)
			{
				generators[j].generateVehicle();
			}
			try
			{
				Thread.sleep(3500);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
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
				System.out.println("Elapsed time: " + v.getElapsedTime() + " seconds");
			}
		}
		
		
		
		
		
	}

}
