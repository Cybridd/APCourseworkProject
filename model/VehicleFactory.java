package model;
/**The VehicleFactory class is a Factory interface implemented by vehicle-specific
 * factories such as CarFactory. This class could be implemented by other factories
 * for new types of vehicles in the future.
 * @author Connor Fulton
 */
public interface VehicleFactory {
	
	/**Method for generating new Vehicle 
	 * objects to be implemented
	 * @param s size of vehicle in gridsquares
	 * @param d delay of vehicle movement (speed)
	 * @param l lane of vehicle movement
	 * @param dir direction of travel
	 * @return new Vehicle object
	 */
	public Vehicle createVehicle(Grid grid, int s, int d, int l, String dir);
}
