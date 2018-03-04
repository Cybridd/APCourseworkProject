package model;

public class TrafficGenerator
{
	private static VehicleFactory factory;
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
		factory.createVehicle(grid, size, delay, lane, direction).start();
	}
}
