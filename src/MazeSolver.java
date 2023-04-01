/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;


// David Mhatre Friday, March 31
public class MazeSolver {
    private Maze maze;

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        // TODO: Get the solution from the maze
        // Should be from start to end cells
        // Declares an arraylist and a stack to keep track of cells
        ArrayList<MazeCell> solution = new ArrayList<>();
        Stack<MazeCell> path = new Stack<>();
        // Sets cur cell and pushes it to path
        MazeCell cur = maze.getEndCell();
        path.push(cur);
        // Tracks back through the maze and keeps track
        while (path.peek() != maze.getStartCell()){
            cur = cur.getParent();
            path.push(cur);
        }
        // After reverses the stack and returns it
        while (!path.isEmpty()){
            solution.add(path.pop());
        }
        return solution;
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        // TODO: Use DFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        // Declares the current one and to be explored mazecells this time a stack
        MazeCell cur = maze.getStartCell();
        Stack<MazeCell> tbe = new Stack<>();

        while (cur != maze.getEndCell()){
            // Gets the current row and col
            int row = cur.getRow();
            int col = cur.getCol();
            // In the order of north, east, south, and west set cell for the Stack tbe
                setSCell(row -1, col, cur, tbe);
                setSCell(row, col +1, cur, tbe);
                setSCell(row +1, col, cur, tbe);
                setSCell(row, col -1, cur, tbe);
            // Pops the current one off the stack
            cur = tbe.pop();
        }
        // Gets the solution
        return getSolution();
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeBFS() {
        // TODO: Use BFS to solve the maze
        // Explore the cells in the order: NORTH, EAST, SOUTH, WEST
        // Declares the current one and to be explored mazecells this time a Queue
        MazeCell cur = maze.getStartCell();
        Queue<MazeCell> tbe = new LinkedList<>();

        while (cur != maze.getEndCell()){
            // Gets the current row and col
            int row = cur.getRow();
            int col = cur.getCol();

            // Again and in the order of north, east, south, and west calls set cell for Queue named tbe
                setQCell(row -1, col, cur, tbe);
                setQCell(row, col +1, cur, tbe);
                setQCell(row +1, col, cur, tbe);
                setQCell(row, col -1, cur, tbe);
            // Polls the first one of the Queue
            cur = tbe.poll();
        }
        // Returns get solution
        return getSolution();
    }

    // Two methods that make the code more efficient
    public void setSCell(int row, int col, MazeCell cur, Stack tbe){
        // Checks if valid then sets the parent and explored then adds it
        if (maze.isValidCell(row, col) && !tbe.contains(maze.getCell(row, col))){
            maze.getCell(row, col).setParent(cur);
            maze.getCell(row, col).setExplored(true);
            tbe.push(maze.getCell(row , col));
        }
    }
    public void setQCell(int row, int col, MazeCell cur, Queue tbe){
        // Checks if valid then sets the parent and explored then adds it
        if (maze.isValidCell(row, col) && !tbe.contains(maze.getCell(row, col))){
            maze.getCell(row, col).setParent(cur);
            maze.getCell(row, col).setExplored(true);
            tbe.add(maze.getCell(row , col));
        }
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
