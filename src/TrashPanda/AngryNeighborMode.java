package TrashPanda;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

class AngryNeighborMode implements Mode {
    private final int mazeSize;
    private final String difficulty;
    private Neighbor neighbor;
    private boolean gameOver = false;

    public AngryNeighborMode(String difficulty) {
        if (difficulty.equals("Easy")) {
            this.mazeSize = 5;
        } else if (difficulty.equals("Hard")) {
            this.mazeSize = 10;
        } else {
            throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
        this.difficulty = difficulty;
    }

    @Override
    public String getName() {
        return difficulty + " Angry Neighbor Mode";
    }

    @Override
    public Maze createMaze() {
        int cellSize = 40;
        int actualMazeSize = mazeSize * 2 + 1; // Convert logical size to grid size
        double mazeSizeInPixels = actualMazeSize * cellSize;
        double mazeStartX = (1000 - mazeSizeInPixels) / 2; // Center maze horizontally

        // Position neighbor
        double neighborX = mazeStartX + (mazeSizeInPixels / 2) - 20; // Center neighbor
        double neighborY = (1000 - mazeSizeInPixels) / 2 - 40; // Place 40 pixels above the maze

        neighbor = new Neighbor(neighborX, neighborY);
        return new Maze(mazeSize);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public void applyEffects(GraphicsContext gc, Player player, Canvas canvas, Maze maze) {
        if (neighbor != null) {
            neighbor.update(player);
            if (neighbor.checkCollision(player)) {
                LevelManager.endGame();
            }
            neighbor.draw(gc, player.getCellSize());
        }
    }
}
