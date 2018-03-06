package model;

import java.util.concurrent.locks.*;

/** The GridSquare class represents a single square on the grid
 * representing a junction. It is aware of whether it is occupied
 * or unoccupied, and does not care which specific vehicle occupies it,
 * only what direction it is travelling in for drawing purposes.
 * @author Connor Fulton
 */

public class GridSquare {
	
	/* Instance variables */
	private boolean occupied;
	protected final ReentrantLock occupiedLock = new ReentrantLock();
	protected Condition occupiedCondition;
	private String carShape;
	
	/**Constructor for GridSquare object
	 */
	public GridSquare()
	{
		occupied = false;
		occupiedCondition = occupiedLock.newCondition();
	}
	
	/**Return occupied status of gridsquare
	 * @return occupied boolean occupied status
	 */
	public boolean isOccupied()
	{
		return occupied;
	}
	
	/**Set occupied status of gridsquare
	 * @param occupied boolean occupied status
	 */
	public void setOccupied(boolean occupied)
	{
		this.occupied = occupied;
	}
	
	/**Set character representing car
	 * currently in square
	 * @param cs String car shape e.g. '*'
	 */
	public void setCarShape(String cs)
	{
		carShape = cs;
	}
	
	/**Return car shape character of
	 * car currently in square
	 * @return carShape String car shape
	 */
	public String getCarShape()
	{
		return carShape;
	}

}
