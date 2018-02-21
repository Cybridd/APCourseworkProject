import java.util.Scanner;

import model.*;

public class APSpec1 {
	private static VehicleFactory factory;
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String vehicleChoice = "";
		System.out.print("Welcome to the future! What type of autonomous vehicle would you like to simulate? ");
		while(!vehicleChoice.equals("Car"))
		{
			vehicleChoice = scanner.nextLine();
			if(!vehicleChoice.equals("Car"))
			{ 
				System.out.print("I'm sorry, our system doesn't support that kind of dope ride yet. Try another: ");
			}
		}

		setVehicleFactory(vehicleChoice);
		factory.createVehicle(1, 1000, 1, "NORTH");
		
		Grid grid = new Grid(4,4);
		

	}
	
	public static void setVehicleFactory(String type)
	{
		if(type.equals("Car"))
		{
			factory = new CarFactory();
		}
	}

}
