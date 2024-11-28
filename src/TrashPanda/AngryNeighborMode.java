package TrashPanda;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Represents the Angry Neighbor Mode where an Angry Neighbor shoots projectiles in random direction
 * and ends the game if the projectile collides with the player.
 */
class AngryNeighborMode extends Mode {
    private Neighbor neighbor;

    /**
     * Constructs an instance of Angry Neighbor Mode with the specified difficulty level.
     *
     * @param difficulty String that represents the easy or hard level
     */
    AngryNeighborMode(final String difficulty) {
        super(difficulty);
    }

    /**
     * Gets the difficulty and name of the mode.
     *
     * @return String that represents the difficulty and name of the mode
     */
    @Override
    public String getName() {
        return difficulty + " Angry Neighbor Mode";
    }

    /**
     * Creates a maze for Angry Neighbor Mode.
     *
     * @return Maze with a Neighbor object on the top middle of the maze
     */
    @Override
    public Maze createMaze() {
        final double cellSize = 40;
        final double canvasSize = TrashPandaGame.CANVAS_SIZE;
        int actualMazeSize = mazeSize * 2 + 1;
        double mazeSizeInPixels = actualMazeSize * cellSize;
        double mazeStartX = (canvasSize - mazeSizeInPixels) / 2;

        double neighborX = mazeStartX + (mazeSizeInPixels / 2) - cellSize / 2;
        double neighborY = (canvasSize - mazeSizeInPixels) / 2 -  cellSize / 2;

        neighbor = new Neighbor(neighborX, neighborY);
        return new Maze(mazeSize);
    }

    /**
     * Apply the game effect for this mode and draw neighbor.
     *
     * @param gc GraphicsContext used for rendering the game
     * @param player Player representing the player
     * @param canvas Canvas for rendering the maze
     * @param maze Maze that is the current maze
     */
    @Override
    public void applyEffects(final GraphicsContext gc, final Player player,
                             final Canvas canvas, final Maze maze) {
        if (neighbor != null) {
            neighbor.update();
            if (neighbor.checkCollision(player, maze)) {
                LevelManager.endGame();
            }
            neighbor.draw(gc);
        }
    }
}
