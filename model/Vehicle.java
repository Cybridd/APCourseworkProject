package model;
/**The Vehicle class is an abstract class from which different types of vehicles
 * can be created. It has instance variables that MAY CHANGE.
 * @author Connor Fulton
 *
 */
public abstract class Vehicle extends Thread{
	private int size, delay, lane;
	private GridSquare position;
	private String direction;
	
	/**Constructor for the Vehicle object
	 * @param s size of vehicle in gridsquares
	 * @param d delay of vehicle movement (speed)
	 * @param l lane of vehicle movement
	 * @param dir direction of travel
	 */
	public Vehicle(int s, int d, int l, String dir)
	{
		size = s;
		delay = d;
		lane = l;
		direction = dir;
	}
}
