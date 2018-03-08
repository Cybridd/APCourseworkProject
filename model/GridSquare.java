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
	private final ReentrantLock occupiedLock = new ReentrantLock();
	private String carShape;
	
	/**Constructor for GridSquare object
	 */
	public GridSquare()
	{
		occupied = false;
	}
	
	/**Return occupied status of gridsquare
	 * @return occupied boolean occupied status
	 */
	protected boolean isOccupied()
	{
		return occupied;
	}
	
	/**Set occupied status of gridsquare
	 * @param occupied boolean occupied status
	 */
	protected void setOccupied(boolean occupied)
	{
		this.occupied = occupied;
	}
	
	/**Set character representing car
	 * currently in square
	 * @param cs String car shape e.g. '*'
	 */
	protected void setCarShape(String cs)
	{
		carShape = cs;
	}
	
	/**Return car shape character of
	 * car currently in square
	 * @return carShape String car shape
	 */
	protected String getCarShape()
	{
		return carShape;
	}

}
