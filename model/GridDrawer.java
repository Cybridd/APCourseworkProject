package model;

public class GridDrawer extends Thread {
	private Grid grid;
	private int frames, currentFrame;
	private boolean isDone;
	
	public GridDrawer(Grid grid, int frames)
	{
		this.grid = grid;
		this.frames = frames;
		currentFrame = 0;
		isDone = false;
	}
	
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
			System.out.println("|");
		}
		for(int p = 0; p < grid.n; p++)
		{
			System.out.print("--");
		}
		System.out.println();
	}
	
	public boolean isDone()
	{
		return isDone;
	}
}
