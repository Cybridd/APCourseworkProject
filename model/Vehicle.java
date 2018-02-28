package model;
/**The Vehicle class is an abstract class from which different types of vehicles
 * can be created. It has instance variables that MAY CHANGE.
 * @author Connor Fulton
 *
 */
public abstract class Vehicle extends Thread{
	private int size, delay, lane, xPos, yPos;
	private Grid grid;
	private GridSquare position, next, last;
	private String direction;
	private String carShape;
	
	/**Constructor for the Vehicle object
	 * @param s size of vehicle in gridsquares
	 * @param d delay of vehicle movement (speed)
	 * @param l lane of vehicle movement
	 * @param dir direction of travel
	 */
	public Vehicle(Grid grid, int s, int d, int l, String dir)
	{
		this.grid = grid;
		size = s;
		delay = d;
		lane = l;
		direction = dir;
		position = new GridSquare();
		
		switch (direction)
		{
			
			case "NORTH" : yPos = grid.m-1; 
						   xPos = lane;
				           next = grid.getSquare(yPos, xPos); 
				           carShape = "*"; break;
			case "EAST" :  yPos = lane;
			   			   xPos = 0;
			   			   next = grid.getSquare(yPos, xPos);
			   			   carShape = "o";  break;	
		}
		//System.out.println("Constructing vehicle");
		
	}
	
	public void run()
	{
		while(next != null)
		{
			position.occupiedLock.lock();
			try
			{
				sleep(delay);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			if(next.occupiedLock.tryLock())
			{
				move();
			}
			//System.out.println(Thread.currentThread());
		}

	}

	public void move()
	{
		try
		{
			last = position;
			position = next;
			last.setOccupied(false);
			position.occupiedLock.lock();
			position.setOccupied(true);
			position.setCarShape(carShape);
		}
		finally
		{
			//System.out.println("unlocked");
			last.occupiedLock.unlock();
			if(holdsLock(last))
			{
				System.out.println("Still holding lock");
			}
		}
		try
		{
			switch (direction)
			{
			case "NORTH" : next = grid.getSquare(--yPos, lane);	break;
			case "EAST" : next = grid.getSquare(lane, ++xPos);	break;
			}
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			position.setOccupied(false);
			position.occupiedLock.unlock();
			next = null;
		}
	}
}
