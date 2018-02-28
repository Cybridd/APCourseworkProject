import java.util.Random;
import java.util.Scanner;

import model.*;

public class APSpec1 {
	private static VehicleFactory factory;
	
	public static void main(String[] args) {

		/*Scanner scanner = new Scanner(System.in);
		String vehicleChoice = "";
		System.out.print("Welcome to the future! What type of autonomous vehicle would you like to simulate? ");
		while(!vehicleChoice.equals("Car"))
		{
			vehicleChoice = scanner.nextLine();
			if(!vehicleChoice.equals("Car"))
			{ 
				System.out.print("I'm sorry, our system doesn't support that kind of dope ride yet. Try another: ");
			}
		}*/

		setVehicleFactory("Car");
		Grid grid = new Grid(10,20);
		GridDrawer drawer = new GridDrawer(grid, 5000);
		Random rand = new Random();
		int max = 20;
		int vehicles = 0;
		drawer.start();
		while(vehicles < max)
		{
			factory.createVehicle(grid, 1, 2000, rand.nextInt(20), "NORTH").start();
			factory.createVehicle(grid, 1, 2000, rand.nextInt(10), "EAST").start();
			vehicles++;
			try
			{
				Thread.sleep(200);
			}
			catch (InterruptedException e)
			{
				
			}
		}
	}
	
	public static void setVehicleFactory(String type)
	{
		if(type.equals("Car"))
		{
			factory = new CarFactory();
		}
	}

}
