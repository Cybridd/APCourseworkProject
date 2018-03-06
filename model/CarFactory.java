package model;
/** The CarFactory class implements the VehicleFactory interface to generate Car objects
 * as required by the caller and return them.
 * @author Connor Fulton
 */
public class CarFactory implements VehicleFactory {
	
	/**Method for generating new Car objects
	 * @param s size of vehicle in gridsquares (fixed in this case)
	 * @param d delay of vehicle movement (speed)
	 * @param l lane of vehicle movement
	 * @param dir direction of travel
	 * @return new Vehicle object
	 */
	public Vehicle createVehicle(Grid grid, int s, int d, int l, String dir)
	{
		return new Car(grid, d, l, dir);
	}

}
