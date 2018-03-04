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
		while(true)
		{
			for(int j = 0; j < maxGens; j ++)
			{
				generators[j].generateVehicle();
			}
			try
			{
				Thread.sleep(2200);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	
	}

}
