package model;
/**The GridDrawer class is responsible for taking the current state
 * of the grid (junction) and printing it to console. In our simulation
 * it does this every 20ms, but this could be changed as needed.
 * @author Connor Fulton
 *
 */
public class GridDrawer extends Thread {
	
	/* Instance Variables */
	private Grid grid;
	private int frames, currentFrame;
	private boolean isDone;
	
	/**Constructor for the GridDrawer object. Initializes
	 * instance variables and sets current frames to 0 and
	 * done status to false
	 * @param grid Grid object to draw
	 * @param frames number of frames to draw
	 */
	public GridDrawer(Grid grid, int frames)
	{
		this.grid = grid;
		this.frames = frames;
		currentFrame = 0;
		isDone = false;
	}
	
	/**Run method for the GridDrawer object. Calls the DrawGrid()
	 * method while frame limit hasn't been reached, waiting
	 * an interval of time between calls.
	 */
	public void run()
	{
		while(currentFrame < frames)
		{
			DrawGrid();
			currentFrame++;
			try
			{
				sleep(20);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		isDone = true;
	}
	
	/**Method for drawing the grid to the console. Draws 
	 * an outline, the lane lines and the characters 
	 * representing vehicles if a square is occupied.
	 */
	private void DrawGrid()
	{
		for(int p = 0; p < grid.n; p++)
		{
			System.out.print("--");
		}
		System.out.println();
		for(int i = 0; i < grid.m; i++)
		{
			for(int j = 0; j < grid.n; j++)
			{
				System.out.print("|");
				if (grid.getSquare(i,j).isOccupied())
				{
					System.out.print(grid.getSquare(i, j).getCarShape());
				}
				else System.out.print(" ");
			}
			System.out.println("|");
		}
		for(int p = 0; p < grid.n; p++)
		{
			System.out.print("--");
		}
		System.out.println();
	}
	
	/**Return done status of drawer
	 * @return isDone boolean done status
	 */
	public boolean isDone()
	{
		return isDone;
	}
}
