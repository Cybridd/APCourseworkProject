package model;
/**The Grid class represents the pattern of available spaces in 
 * something like a road junction.
 * @author Connor Fulton
 *
 */
public class Grid {
	private GridSquare[][] grid;
	
	/**Constructor for the Grid object
	 * @param m number of rows in grid
	 * @param n number of columns in grid
	 */
	public Grid(int m, int n)
	{
		grid = new GridSquare[m][n];
		// Fill new grid with GridSquare objects
		for (GridSquare[] lane : grid)
		{
			for (GridSquare g : lane)
			{
				g = new GridSquare();
			}
		}
	}
	
	/**Getter method for returning a specific
	 * GridSquare in grid
	 * @param row row position in grid
	 * @param col column position in grid
	 * @return
	 */
	public GridSquare getSquare(int row, int col)
	{
		return grid[row][col];
	}

}