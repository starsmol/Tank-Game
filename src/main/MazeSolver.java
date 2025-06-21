package main;

import java.util.List;

public class MazeSolver {

    public boolean solveMaze(int[][] maze, int startY, int startX, int goalY, int goalX,
                             boolean[][] visited, List<int[]> path) {
        int rows = maze.length;
        int cols = maze[0].length;

        // Out of bounds or invalid
        if (startY < 0 || startX < 0 || startY >= rows || startX >= cols ||
                maze[startY][startX] == 0 || visited[startY][startX]) {
            return false;
        }
        visited[startY][startX] = true;
        path.add(new int[]{startY, startX});

        System.out.println("x = " + startX + " y = " + startY);


        // Goal reached
        if (startY == goalY && startX == goalX) {
            return true;
        }

        // Explore 4 directions
        if (solveMaze(maze, startY + 1, startX, goalY, goalX, visited, path) ||
                solveMaze(maze, startY - 1, startX, goalY, goalX, visited, path) ||
                solveMaze(maze, startY, startX + 1, goalY, goalX, visited, path) ||
                solveMaze(maze, startY, startX - 1, goalY, goalX, visited, path)) {
            return true;
        }

        // Backtrack
        path.remove(path.size() - 1);
        return false;
    }
}