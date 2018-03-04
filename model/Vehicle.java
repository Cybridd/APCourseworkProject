package model;

import java.util.concurrent.TimeUnit;

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
		last = new GridSquare();
		
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
			case "SOUTH" : yPos = 0;
			   			   xPos = lane;
			   			   next = grid.getSquare(yPos, xPos);
			   			   carShape = "*";  break;	
			case "WEST" :  yPos = lane;
			   			   xPos = grid.n-1;
			   			   next = grid.getSquare(yPos, xPos);
			   			   carShape = "o";  break;	
		}
		
		
	}
	
	/**Run method for the Vehicle object. Locks
	 * the current gridsquare, sleeps for a given
	 * delay and checks whether next square is
	 * available
	 */
	public void run()
	{
		while(!this.isInterrupted())
		{
			try
			{
				synchronized(position)
				{
				//position.occupiedLock.lock();
				sleep(delay);
				if(!next.isOccupied())
				{
					//position.occupiedCondition.signalAll();
					//next.occupiedCondition.await();
					move();
					
				}
				
				//position.occupiedCondition.signalAll();
				//System.out.println("moved");
				}
				//if(holdsLock(last))
				//{
				//	System.out.println("Still holding lock");
				//}
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

	}

	public void move()
	{
		try
		{
			//position.occupiedLock.unlock();
			last = position;
			position = next;
			last.setOccupied(false);
			//System.out.println("" + position.isOccupied());
			//last.occupiedCondition.signalAll();
			//position.occupiedLock.lock();
			position.setOccupied(true);
			position.setCarShape(carShape);
			switch (direction)
			{
			case "NORTH" :	yPos--; break;
			case "EAST" :	xPos++;	break;
			case "SOUTH" :	yPos++; break;
			case "WEST" :	xPos--;	break;
			}
			//System.out.println("" + yPos + " " + xPos);
			if(yPos == grid.m || xPos == grid.n || yPos == -1 || xPos == -1)
			{
				System.out.println("" + yPos + " " + xPos);
				position.setOccupied(false);
				this.interrupt();
			}
			else
			{
				next = grid.getSquare(yPos, xPos);
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
