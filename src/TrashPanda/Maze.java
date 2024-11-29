package TrashPanda;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a maze using recursive backtracking with walls and paths.
 */
class Maze {
    private final double cellSize = TrashPandaGame.CELL_SIZE;
    private final double canvasSize = TrashPandaGame.CANVAS_SIZE;
    private final boolean[][] maze;
    private final int size;

    /**
     * Constructs a Maze with the specified maze size.
     *
     * @param mazeSize int that represents logical size of the maze (number of cells)
     */
    Maze(final int mazeSize) {
        this.size = mazeSize * 2 + 1;
        this.maze = new boolean[size][size];
        generateMaze();
    }

    /**
     * Retrieves the grid size of the maze.
     *
     * @return int that represents the maze grid size
     */
    public int getMazeSize() {
        return size;
    }

    /**
     * Checks if a move to specific coordinates is valid within the maze.
     *
     * @param x double that represents the x-coordinate of the move
     * @param y double that represents the y-coordinate of the move
     * @return true if move is valid and within the maze and false otherwise
     */
    boolean isValidMove(final double x, final double y) {
        final double downsizeFactor = 0.5;
        int gridX = (int) Math.floor(x);
        int gridY = (int) Math.floor(y);

        int nextX = (int) Math.floor(x + 1 - downsizeFactor);
        int nextY = (int) Math.floor(y + 1 - downsizeFactor);

        return gridX >= 0 && gridX < maze.length
                && gridY >= 0 && gridY < maze.length
                && maze[gridY][gridX] && maze[gridY][nextX]
                && maze[nextY][gridX] && maze[nextY][nextX];
    }

    /**
     * Recursively generates the maze paths by visiting cells in a random order
     * and creating walls between them.
     *
     * @param x       int that represents the x-coordinate of the current cell
     * @param y       int that represents the y-coordinate of the current cell
     * @param visited boolean that represents whether the cell is visited
     */
    private void recursiveBacktracking(final int x, final int y, final boolean[][] visited) {
        visited[y / 2][x / 2] = true;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        final int totalDirections = directions.length;
        List<Integer> dirs = new ArrayList<>();
        for (int i = 0; i < totalDirections; i++) {
            dirs.add(i);
        }
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

    /**
     * Generates a mze using recursive backtracking algorithm.
     */
    private void generateMaze() {
        boolean[][] visited = new boolean[size / 2 + 1][size / 2 + 1];
        maze[1][1] = true;
        maze[size - 2][size - 2] = true;
        recursiveBacktracking(1, 1, visited);
    }

    /**
     * Draws the maze on the specified graphics context.
     *
     * @param gc GraphicsContext on which to draw the maze
     */
    public void draw(final GraphicsContext gc) {
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

    public void drawGoal(final GraphicsContext gc) {
        final double startingPos = (canvasSize - size * cellSize) / 2;
        gc.drawImage(ImageLoader.endLevel, startingPos + (size - 2) * cellSize,
                startingPos + (size - 2) * cellSize, cellSize, cellSize);
    }

    public void drawDestination(final GraphicsContext gc) {
        final double startingPos = (canvasSize - size * cellSize) / 2;
        gc.drawImage(ImageLoader.destination, startingPos + (size - 2) * cellSize,
                startingPos + (size - 2) * cellSize, cellSize, cellSize);
    }
}
