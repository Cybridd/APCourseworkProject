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
	
	public void move()
	{
		try
		{
			//position.occupiedLock.unlock();
			setLast(getPosition());
			setPosition(getNext());
			getLast().setOccupied(false);
			//System.out.println("" + position.isOccupied());
			//last.occupiedCondition.signalAll();
			//position.occupiedLock.lock();
			getPosition().setOccupied(true);
			getPosition().setCarShape(getCarShape());
			switch (getDirection())
			{
			case "NORTH" :	setyPos(getyPos() - 1); break;
			case "EAST" :	setxPos(getxPos() + 1);	break;
			case "SOUTH" :	setyPos(getyPos() + 1); break;
			case "WEST" :	setxPos(getxPos() - 1);	break;
			}
			if(getyPos() == getGrid().m || getxPos() == getGrid().n || getyPos() == -1 || getxPos() == -1)
			{
				try
				{
					Thread.sleep(getDelay());
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				getPosition().setOccupied(false);
				this.interrupt();
			}
			else
			{
				setNext(getGrid().getSquare(getyPos(), getxPos()));
			/*try
			{
			
				switch (direction)
				{
				case "NORTH" : next = grid.getSquare(--yPos, xPos);	break;
				case "EAST" : next = grid.getSquare(yPos, ++xPos);	break;
				case "SOUTH" : next = grid.getSquare(++yPos, xPos);	break;
				case "WEST" : next = grid.getSquare(yPos, --xPos);	break;
				}
			//}
			//catch (ArrayIndexOutOfBoundsException e)
			//{
			//	System.out.println("" + yPos + " " + xPos);
			//	position.setOccupied(false);
			//	//position.occupiedLock.unlock();
			//	this.interrupt();*/
			}
		}
		finally
		{
			//last.occupiedLock.unlock();
			//if(holdsLock(last))
			//{
			//	System.out.println("Still holding lock");
			//}
		}

	}
}
