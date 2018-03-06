package model;

/**The Vehicle class is an abstract class from which different types of vehicles
 * can be created. It has instance variables that are passed from the vehicle factory.
 * @author Connor Fulton
 *
 */
public abstract class Vehicle extends Thread {
	
	/* Instance variables */
	private int size, delay, lane, xPos, yPos;
	private Grid grid;
	private GridSquare position, next, last;
	private String direction;
	private String carShape;
	private long clockStart, clockFinish;
	
	/**Constructor for the Vehicle object
	 * @param grid = grid object (junction)
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
		
		/* Set next gridsquare and character representing
		 * car depending on lane and direction
		 */
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
		clockStart = System.nanoTime();
		while(!this.isInterrupted())
		{
			try
			{
				synchronized(position)
				{
					sleep(delay);
					if(!next.isOccupied())
					{
						move();					
					}
				}
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		clockFinish = System.nanoTime();

	}
	
	/** Abstract method for making correct gridsquare movement.
	 * Implemented by subclasses because it will be different
	 * depending on type of car e.g. Car (size 1) vs Bus (size 2)
	 */
	abstract void move();
	
	/** Method for calculating vehicle journey time and returning
	 * in the form of seconds (double). Accuracy reduced by conversion
	 * but not below reasonable requirements.
	 * @return elapseTime seconds vehicle took to cross junction
	 */
	public double getElapsedTime()
	{
		return (double) (clockFinish - clockStart) * Math.pow(10, -9);
	}

	/**Getter and Setter methods for instance variables. Used 
	 * to allow subclass access while keeping variables private.
	 * @return various instance variables
	 */
	protected int getDelay() {
		return delay;
	}

	protected void setDelay(int delay) {
		this.delay = delay;
	}

	protected int getLane() {
		return lane;
	}

	protected void setLane(int lane) {
		this.lane = lane;
	}

	protected int getxPos() {
		return xPos;
	}

	protected void setxPos(int xPos) {
		this.xPos = xPos;
	}

	protected int getyPos() {
		return yPos;
	}

	protected void setyPos(int yPos) {
		this.yPos = yPos;
	}

	protected Grid getGrid() {
		return grid;
	}

	protected void setGrid(Grid grid) {
		this.grid = grid;
	}

	protected GridSquare getPosition() {
		return position;
	}

	protected void setPosition(GridSquare position) {
		this.position = position;
	}

	protected GridSquare getNext() {
		return next;
	}

	protected void setNext(GridSquare next) {
		this.next = next;
	}

	protected GridSquare getLast() {
		return last;
	}

	protected void setLast(GridSquare last) {
		this.last = last;
	}

	protected String getDirection() {
		return direction;
	}

	protected void setDirection(String direction) {
		this.direction = direction;
	}

	protected String getCarShape() {
		return carShape;
	}

	protected void setCarShape(String carShape) {
		this.carShape = carShape;
	}

	protected long getClockStart() {
		return clockStart;
	}

	protected void setClockStart(long clockStart) {
		this.clockStart = clockStart;
	}

	protected long getClockFinish() {
		return clockFinish;
	}

	protected void setClockFinish(long clockFinish) {
		this.clockFinish = clockFinish;
	}
}
