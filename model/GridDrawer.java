package model;

public class GridDrawer extends Thread {
	private Grid grid;
	private int frames, currentFrame;
	
	public GridDrawer(Grid grid, int frames)
	{
		this.grid = grid;
		this.frames = frames;
		currentFrame = 0;
	}
	
	public void run()
	{
		while(currentFrame < frames)
		{
			DrawGrid();
			currentFrame++;
			try
			{
				sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void DrawGrid()
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
			System.out.println();
		}
		for(int p = 0; p < grid.n; p++)
		{
			System.out.print("--");
		}
		System.out.println();
	}
}
