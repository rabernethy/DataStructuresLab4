import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JPanel;

public class MazeGridPanel extends JPanel{
	private int rows;
	private int cols;
	private Cell[][] maze;



/* void genDFSMaze(), written by: Russell Abernethy */

	public void genDFSMaze() {

		Stack<Cell> stack  = new Stack<Cell>();

		// Choose the initial cell, mark it as visited and push it to the stack.
		Cell start = maze[0][0];
		start.setBackground(Color.BLACK);
		stack.push(start);

		// While the stack is not empty:
		while(!stack.isEmpty()) {

			// Pop a cell from the stack and make it a current cell
			Cell currentCell = stack.pop();

			// If the current cell has any neighbours which have not been visited
			if((currentCell.col+1 < cols && !visited(currentCell.row, currentCell.col+1)) || (currentCell.col-1 >= 0 && !visited(currentCell.row, currentCell.col-1)) || (currentCell.row-1 >= 0 && !visited(currentCell.row-1, currentCell.col)) || (currentCell.row+1 < rows && !visited(currentCell.row+1, currentCell.col))) {
				
				// Push the current cell to the stack
				stack.push(currentCell);

				// Choose one of the unvisited neighbours
				boolean flag = true;
				while(flag) {

					// The domain of possible values for dir is [0,3].
					// Key: 0 = N, 1 = E, 2 = S, 3 = W.
					int dir = (int)(Math.random() * 4);

					// North:
					if(dir == 0 && currentCell.row-1 >= 0 && !visited(currentCell.row-1, currentCell.col)) {

						// Remove the wall between the current cell and the chosen cell
						currentCell.northWall = false;
						maze[currentCell.row-1][currentCell.col].southWall = false;

						// Mark the chosen cell as visited.
						maze[currentCell.row-1][currentCell.col].setBackground(Color.BLACK);

						// Push the chosen cell to the stack.
						stack.push(maze[currentCell.row-1][currentCell.col]);
						
						// Stop trying an unvisited neighbor.
						flag = false;
					}

					// East:
					if(dir == 1 && currentCell.col+1 < cols && !visited(currentCell.row, currentCell.col+1)) {
						
						// Remove the wall between the current cell and the chosen cell.
						currentCell.eastWall = false;
						maze[currentCell.row][currentCell.col+1].westWall = false;
						
						// Mark the chosen cell as visited.
						maze[currentCell.row][currentCell.col+1].setBackground(Color.BLACK);

						// Push the chosen cell to the stack.
						stack.push(maze[currentCell.row][currentCell.col+1]);

						// Stop trying an unvisited neighbor.
						flag = false;
					}

					// South:
					if(dir == 2 && currentCell.row+1 < rows && !visited(currentCell.row+1, currentCell.col)) {

						// Remove the wall between the current cell and the chosen cell.
						currentCell.southWall = false;
						maze[currentCell.row+1][currentCell.col].northWall = false;
						
						// Mark the chosen cell as visited.
						maze[currentCell.row+1][currentCell.col].setBackground(Color.BLACK);

						// Push the chosen cell to the stack.
						stack.push(maze[currentCell.row+1][currentCell.col]);

						// Stop trying an unvisited neighbor.
						flag = false;
					}

					// West:
					else if(dir == 3 && currentCell.col-1 >= 0 && !visited(currentCell.row, currentCell.col-1)) {
						
						// Remove the wall between the current cell and the chosen cell.
						currentCell.westWall = false;
						maze[currentCell.row][currentCell.col-1].eastWall = false;

						// Mark the chosen cell as visited.
						maze[currentCell.row][currentCell.col-1].setBackground(Color.BLACK);

						// Push the chosen cell to the stack.
						stack.push(maze[currentCell.row][currentCell.col-1]);

						// Stop trying an unvisited neighbor.
						flag = false;
					}

				}
				
			}
					
		}

		// Reset the tile colors.
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				maze[i][j].setBackground(Color.WHITE);
			}
		}

	}

/* void solveMaze(), written by: Russell Abernethy */
	public void solveMaze() {
	
		Stack<Cell> stack  = new Stack<Cell>();
		Cell start = maze[0][0];
		start.setBackground(Color.GREEN);
		Cell finish = maze[rows-1][cols-1];
		finish.setBackground(Color.RED);

		// Push start onto the stack.
		stack.push(start);

		// While the end has not been reached and the stack isn't empty:
		while(stack.peek().getBackground() != Color.RED && !stack.isEmpty()) {
			
			// Look at the current position.
			Cell currentCell = stack.peek();

			// Try to go North if it is unvisited and there isn't a wall there:
			if(!currentCell.northWall && !visited(currentCell.row -1, currentCell.col)) {

				// Push the northern cell onto the stack.
				stack.push(maze[currentCell.row-1][currentCell.col]);

				// Mark currentCell as visited.
				currentCell.setBackground(Color.GREEN); 
			}

			// Try to go South if it is unvisited and there isn't a wall there:
			else if(!currentCell.southWall && !visited(currentCell.row +1, currentCell.col)) {

				// Push the southern cell onto the stack.
				stack.push(maze[currentCell.row+1][currentCell.col]);

				// Mark currentCell as visited.
				currentCell.setBackground(Color.GREEN);
			}

			// Try to go West if it is unvisited and there isn't a wall there:
			else if(!currentCell.westWall && !visited(currentCell.row, currentCell.col-1)) {
				
				// Push the western cell onto the stack.
				stack.push(maze[currentCell.row][currentCell.col-1]);

				// Mark currentCell as visited.
				currentCell.setBackground(Color.GREEN);
			}

			// Try to go East if it is unvisited and there isn't a wall there:
			else if(!currentCell.eastWall && !visited(currentCell.row, currentCell.col+1)) {
				
				// Push the eastern cell onto the stack.
				stack.push(maze[currentCell.row][currentCell.col+1]);
				
				// Mark currentCell as visited.
				currentCell.setBackground(Color.GREEN);
			}

			// There are no unvisited cells adjacent to currentCell:
			else {

				// Mark currentCell as a dead end.
				currentCell.setBackground(Color.GRAY);

				// Remove currentCell from the stack.
				stack.pop();
			}

		}

	}


	public boolean visited(int row, int col) {
		Cell c = maze[row][col];
		Color status = c.getBackground();
		if(status.equals(Color.WHITE)  || status.equals(Color.RED)  )
			return false;
		else
			return true;
	}


	public void genNWMaze() {
		for(int row = 0; row  < rows; row++) {
			for(int col = 0; col < cols; col++) {
				if(row == 0 && col ==0) 
					continue;

				else if(row ==0) {
					maze[row][col].westWall = false;
					maze[row][col-1].eastWall = false;
				} 
				
				else if(col == 0) {
					maze[row][col].northWall = false;
					maze[row-1][col].southWall = false;
				}
				
				else {
					boolean north = Math.random()  < 0.5;
					if(north ) {
						maze[row][col].northWall = false;
						maze[row-1][col].southWall = false;
					} else {  // remove west
						maze[row][col].westWall = false;
						maze[row][col-1].eastWall = false;
					}
					maze[row][col].repaint();
				}
			}
		}
		this.repaint();
		
	}


	public MazeGridPanel(int rows, int cols) {
		this.setPreferredSize(new Dimension(800,800));
		this.rows = rows;
		this.cols = cols;
		this.setLayout(new GridLayout(rows,cols));
		this.maze =  new Cell[rows][cols];
		for(int row = 0 ; row  < rows ; row++) {
			for(int col = 0; col < cols; col++) {

				maze[row][col] = new Cell(row,col);

				this.add(maze[row][col]);
			}

		}

		this.genDFSMaze();
		this.solveMaze();
		
	}

}
