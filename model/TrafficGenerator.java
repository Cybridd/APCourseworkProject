package model;

import java.util.ArrayList;
/**The TrafficGenerator class manages the creation of vehicles. It 
 * has a set vehicle factory which it calls upon to generate new
 * vehicles of the class designated by the user.
 * @author Connor Fulton
 *
 */
public class TrafficGenerator
{	
	/* Instance variables */
	private static VehicleFactory factory;
	private ArrayList<Vehicle> generatedVehicles;
	private int size, lane, delay;
	private Grid grid;
	private String direction;
	
	/**Constructor for TrafficGenerator object. Initializes
	 * variables passed from instantiation, as well as the
	 * generatedVehicles ArrayList.
	 * @param grid Grid object (junction)
	 * @param s size of vehicle
	 * @param d delay of movement (speed)
	 * @param l lane of requested traffic generation
	 * @param dir direction of travel
	 */
	public TrafficGenerator(Grid grid, int s, int d, int l, String dir)
	{
		this.grid = grid;
		size = s;
		delay = d;
		lane = l;
		direction = dir;
		generatedVehicles = new ArrayList<Vehicle>();
	}
	
	/**Method for setting the type of vehicle factory
	 * that will be used. Simplified in this case as we
	 * are only using one type of vehicle, but could easily
	 * be extended to respond to other vehicle names (strings),
	 * or even decide based on vehicle size.
	 * @param type type of vehicle to be generated
	 */
	public void setVehicleFactory(String type)
	{
		if(type.equals("Car"))
		{
			factory = new CarFactory();
		}
	}
	
	/**Request new vehicle from factory, and start 
	 * the vehicle thread
	 */
	public void generateVehicle()
	{
		generatedVehicles.add(factory.createVehicle(grid, size, delay, lane, direction));
		startVehicle();
	}
	
	/**Call start() of the last created vehicle
	 */
	public void startVehicle()
	{
		generatedVehicles.get(getGeneratedVehicles().size()-1).start();
	}

	/**Get list of vehicles generated during 
	 * simulation
	 * @return generatedVehicles ArrayList of vehicles created
	 */
	public ArrayList<Vehicle> getGeneratedVehicles() {
		return generatedVehicles;
	}
	
	/**Get number of vehicles created
	 * during simulation
	 * @return size of ArrayList (number of vehicles)
	 */
	public int numVehiclesGenerated()
	{
		return generatedVehicles.size();
	}
}
