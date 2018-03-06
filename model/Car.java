package model;
/**The Car class is an extension of the Vehicle class representing
 * your normal everyday automobile. It has the same instance variables
 * as the Vehicle class but with a fixed size of 1.
 * @author Connor Fulton
 *
 */
public class Car extends Vehicle {
	private static final int size = 1;
	
	/**Constructor for the Car object
	 * @param Grid grid object (junction)
	 * @param d delay of vehicle movement (speed)
	 * @param l lane of vehicle movement
	 * @param dir direction of travel
	 */
	public Car(Grid grid, int d, int l, String dir)
	{
		super(grid, size, d, l, dir);
	}
	
	/**Method for moving the vehicle once the next
	 * square is available. Reassigns position variable,
	 * sets occupied boolean and car shape, and updates
	 * coordinates of next gridsquare.
	 */
	public void move()
	{
		// Make this gridsquare Last, make next gridsquare Position
		setLast(getPosition());
		setPosition(getNext());
		
		// Switch occupied booleans and set car shape of new square
		getLast().setOccupied(false);
		getPosition().setOccupied(true);
		getPosition().setCarShape(getCarShape());
		
		// Modify coordinates of next target depending on direction
		switch (getDirection())
		{
		case "NORTH" :	setyPos(getyPos() - 1); break;
		case "EAST" :	setxPos(getxPos() + 1);	break;
		case "SOUTH" :	setyPos(getyPos() + 1); break;
		case "WEST" :	setxPos(getxPos() - 1);	break;
		}
		
		/* If car has reached an edge of the grid (junction),
		 * wait for the delay constant to elapse and then interrupt thread
		 */
		if(getyPos() == getGrid().m || getxPos() == getGrid().n || getyPos() == -1 || getxPos() == -1)
		{
			try
			{
				Thread.sleep(getDelay());
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			getPosition().setOccupied(false);  // Car disappears when leaving edge of junction
			this.interrupt();
		}
		// If not at an edge, set the next gridsquare
		else
		{
			setNext(getGrid().getSquare(getyPos(), getxPos()));
		}
	}
}
