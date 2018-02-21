package model;

import java.util.concurrent.locks.*;

/** The GridSquare class represents a single square on the grid
 * representing a junction. It is aware of whether it is occupied
 * or unoccupied but does not care what type of vehicle occupies it.
 * @author Connor Fulton
 */

public class GridSquare {
	private boolean occupied;
	protected Lock occupiedLock;
	
	/**Constructor for GridSquare object
	 */
	public GridSquare()
	{
		occupied = false;
		occupiedLock = new ReentrantLock();
	}

}
