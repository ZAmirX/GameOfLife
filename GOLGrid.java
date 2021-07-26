import java.util.Random;

public class GOLGrid {
	//Choose the number of cells horizontally and diagonally the square grid will have here
	public static int numCells = 30;
	//This is the main grid that stores the current grid information
	public static boolean[][] grid = new boolean[numCells][numCells];
	//This is a temporary grid that store temporary grid information for the next iteration
	public static boolean[][] nextGrid = new boolean[numCells][numCells];
	
	//This method creates a new random grid
	public static void newRandom()
	{
		Random rnd = new Random();
		//Iterate through the entire grid array and place a random boolean value into each array item
		for (int i=0;i<grid.length;i++){
			for (int j=0;j<grid[i].length;j++){
				grid[i][j] = rnd.nextBoolean();
			}
		}
		//Copy the grid array into the nextGrid array
		for (int i=0;i<grid.length;i++){
			for (int j=0;j<grid[i].length;j++){
				nextGrid[i][j] = grid[i][j];
			}
		}
	}
	
	public static void nextIter()
	{
		//Iterate through the entire grid array
		for (int i=0;i<grid.length;i++){
			for (int j=0;j<grid[i].length;j++){
				//Use this variable to count how many alive neighbours are around the current cell iteration
				int Ncount = 0;
				
				//Initially, the North, West, East, and South neighbours are one cell apart...
				int N = i-1, W = j-1, E = j+1, S = i+1;
				
				//...Unless the current cell iteration is the first or last one
				//For the first iteration North would be the bottom row
				if (i == 0){
					N = grid.length - 1;
				}
				//For the last iteration South would be the top row
				if (i == grid.length - 1){
					S = 0;
				}
				//It is similar for West(Right-most column) and East(Left-most column)
				if (j == 0){
					W = grid[i].length - 1;
				}
				if (j == grid[i].length - 1){
					E = 0;
				}
				
				//Add up the neighbours to the current cell
				//grid[N][W] represents North-West, grid[N][j] represents North, similarly grid[i][W] represents West
				if (grid[N][W]){
					Ncount +=1;
				}
				if (grid[N][j]){
					Ncount +=1;
				}
				if (grid[N][E]){
					Ncount +=1;
				}
				
				if (grid[i][W]){
					Ncount +=1;
				}
				if (grid[i][E]){
					Ncount +=1;
				}
				
				if (grid[S][W]){
					Ncount +=1;
				}
				if (grid[S][j]){
					Ncount +=1;
				}
				if (grid[S][E]){
					Ncount +=1;
				}
				
				//If current cell is alive and number of neighbours is less than 2 or greater than 3...
				if (grid[i][j]){
					if (Ncount < 2 || Ncount > 3){
						//...The current cell will be dead in the next iteration 
						nextGrid[i][j] = false;
					}
				}
				//Otherwise if the cell has 3 neighbours, it will be alive in the next iteration
				else {
					if (Ncount == 3){
						nextGrid[i][j] = true;
					}
				}
			}
		}
		
		//Copy the next iteration's grid into the main grid
		for (int i=0;i<grid.length;i++){
			for (int j=0;j<grid[i].length;j++){
				grid[i][j] = nextGrid[i][j];
			}
		}
	}
}
