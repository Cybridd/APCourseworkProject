package model;

import java.util.concurrent.locks.*;

/** The GridSquare class represents a single square on the grid
 * representing a junction. It is aware of whether it is occupied
 * or unoccupied but does not care which vehicle occupies it.
 * @author Connor Fulton
 */

public class GridSquare {
	private boolean occupied;
	protected Lock occupiedLock;
	private String carShape;
	
	/**Constructor for GridSquare object
	 */
	public GridSquare()
	{
		occupied = false;
		occupiedLock = new ReentrantLock();
	}
	
	public boolean isOccupied()
	{
		return occupied;
	}
	
	public void setOccupied(boolean occupied)
	{
		this.occupied = occupied;
	}
	
	public void setCarShape(String cs)
	{
		carShape = cs;
	}
	
	public String getCarShape()
	{
		return carShape;
	}

}
