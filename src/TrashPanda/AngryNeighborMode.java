package TrashPanda;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

class AngryNeighborMode extends Mode {
    private Neighbor neighbor;

    public AngryNeighborMode(String difficulty) {
        super(difficulty);
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
        double mazeStartX = (1000 - mazeSizeInPixels) / 2;

        double neighborX = mazeStartX + (mazeSizeInPixels / 2) - 20; // Center neighbor
        double neighborY = (1000 - mazeSizeInPixels) / 2 - 40; // Place 40 pixels above the maze

        neighbor = new Neighbor(neighborX, neighborY);
        return new Maze(mazeSize);
    }

    @Override
    public void applyEffects(GraphicsContext gc, Player player, Canvas canvas, Maze maze) {
        if (neighbor != null) {
            neighbor.update();
            if (neighbor.checkCollision(player, maze)) {
                LevelManager.endGame();
            }
            neighbor.draw(gc, player.getCellSize());
        }
    }
}
