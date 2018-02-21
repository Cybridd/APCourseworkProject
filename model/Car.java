package model;
/**The Car class is an extension of the Vehicle class representing
 * your normal everyday automobile. It has the same instance variables
 * as the Vehicle class but with a fixed size of 1.
 * @author Connor Fulton
 *
 */
public class Car extends Vehicle {

	/**Constructor for the Car object
	 * @param d delay of vehicle movement (speed)
	 * @param l lane of vehicle movement
	 * @param dir direction of travel
	 */
	public Car(int d, int l, String dir)
	{
		super(1, d, l, dir);
		System.out.println("Beep!Beep!");
	}
}
