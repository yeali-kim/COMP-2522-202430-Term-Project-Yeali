package TrashPanda;

import javafx.scene.canvas.GraphicsContext;

import java.util.*;

class Maze {
    private final boolean[][] maze;
    private final int size;

    public Maze(int mazeSize) {
        this.size = mazeSize * 2 + 1;
        this.maze = new boolean[size][size];
        generateMaze();
    }

    public int getMazeSize() {
        return size;
    }

    boolean isValidMove(double x, double y) {
        int gridX = (int) Math.floor(x);
        int gridY = (int) Math.floor(y);

        int nextX = (int) Math.floor(x + 1 - 0.5);
        int nextY = (int) Math.floor(y + 1 - 0.5);

        return gridX >= 0 && gridX < maze.length &&
                gridY >= 0 && gridY < maze.length &&
                maze[gridY][gridX] && maze[gridY][nextX] && maze[nextY][gridX] && maze[nextY][nextX];
    }

    private void recursiveBacktracking(int x, int y, boolean[][] visited) {
        visited[y / 2][x / 2] = true;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        List<Integer> dirs = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        Collections.shuffle(dirs);

        for (int dir : dirs) {
            int newX = x + directions[dir][0] * 2;
            int newY = y + directions[dir][1] * 2;

            if (newX >= 0 && newX < maze.length && newY >= 0 && newY < maze.length
                    && !visited[newY / 2][newX / 2]) {
                maze[y + directions[dir][1]][x + directions[dir][0]] = true;
                maze[newY][newX] = true;
                recursiveBacktracking(newX, newY, visited);
            }
        }
    }

    private void generateMaze() {
        boolean[][] visited = new boolean[size / 2 + 1][size / 2 + 1];
        maze[1][1] = true;
        maze[size - 2][size - 2] = true;
        recursiveBacktracking(1, 1, visited);
    }

    public void draw(GraphicsContext gc, int cellSize) {
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                if (maze[y][x]) {
                    gc.drawImage(ImageLoader.floor, x * cellSize, y * cellSize, cellSize, cellSize);
                } else {
                    gc.drawImage(ImageLoader.wall, x * cellSize, y * cellSize, cellSize, cellSize);
                }

            }
        }
    }
}