package model;

import java.util.ArrayList;

public class TrafficGenerator
{
	private static VehicleFactory factory;
	private ArrayList<Vehicle> generatedVehicles;
	private int size, lane, delay;
	private Grid grid;
	private String direction;
	
	public TrafficGenerator(Grid grid, int s, int d, int l, String dir)
	{
		this.lane = lane;
		this.grid = grid;
		size = s;
		delay = d;
		lane = l;
		direction = dir;
		generatedVehicles = new ArrayList<Vehicle>();
	}
	
	public static void setVehicleFactory(String type)
	{
		if(type.equals("Car"))
		{
			factory = new CarFactory();
		}
	}
	
	public void generateVehicle()
	{
		generatedVehicles.add(factory.createVehicle(grid, size, delay, lane, direction));
		startVehicle();
		//factory.createVehicle(grid, size, delay, lane, direction).start();
	}
	
	public void startVehicle()
	{
		generatedVehicles.get(getGeneratedVehicles().size()-1).start();
	}

	public ArrayList<Vehicle> getGeneratedVehicles() {
		return generatedVehicles;
	}
	
	public int numVehiclesGenerated()
	{
		return generatedVehicles.size();
	}
}
