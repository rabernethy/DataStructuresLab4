# ***Maze Solving Lab:***
## CIS 2168 001 - Data Structures
## Written: Russell Abernethy

Here is a picture of the output of the depth first search maze generator before it has been solved:

![GeneratedMaze](https://github.com/rabernethy/DataStructuresLab4/blob/master/photos/DFSMazeUnsolved.png)

And here is a picure of the output of the maze after it has been solved:

![SolvedMaze](https://github.com/rabernethy/DataStructuresLab4/blob/master/photos/solved100x100Maze.png)

**A few notes:**
- The starting position is in the top left corner and the exit is in the bottom right corner.
- A cell colored green means that it is a part of the path to the exit.
- A cell colored red means that it is the exit.
- A cell colored grey means that the cell was visited when trying to find a path to the exit but is not part of the path to the exit.
- A cell colored white means that the cell was never visited before finding the path to the exit.
