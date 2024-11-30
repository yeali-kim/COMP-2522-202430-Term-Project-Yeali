package TrashPanda;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Represents an abstract class of game mode in TrashPanda game.
 * Subclasses define different types of game modes with specific behaviors, maze sizes, and effects.
 */
abstract class Mode {
    /**
     * Size of the maze for this mode.
     */
    protected final int mazeSize;
    /**
     * Difficulty level of this game mode.
     */
    protected final String difficulty;

    /**
     * Constructs a new Mode instance with the specified difficulty.
     * Maze size is set based on the difficulty level provided.
     *
     * @param difficulty String that represents the level of the mode ("Easy" or "Hard")
     * @throws IllegalArgumentException If an invalid difficulty is provided
     */
    Mode(final String difficulty) {
        final int easyMaze = 5;
        final int hardMaze = 10;
        if (difficulty.equals("Easy")) {
            this.mazeSize = easyMaze;
        } else if (difficulty.equals("Hard")) {
            this.mazeSize = hardMaze;
        } else {
            throw new IllegalArgumentException("\"" + difficulty + "\" is an invalid entry. "
                    + "Only \"Easy\" and \"Hard\" are acceptable.");
        }
        this.difficulty = difficulty;
    }

    /**
     * Gets the name of the current mode.
     *
     * @return String that represents the name of the mode
     */
    public abstract String getName();

    /**
     * Creates a new maze instance based on the current mode.
     *
     * @return Maze instance created based on the current mode.
     */
    abstract Maze createMaze();

    /**
     * Applies effects based on the current mode.
     *
     * @param gc     The GraphicsContext used for drawing on the canvas.
     * @param player The current player instance.
     * @param canvas The canvas where the effects are drawn.
     * @param maze   The current maze instance.
     */
    abstract void applyEffects(GraphicsContext gc, Player player, Canvas canvas, Maze maze);
}
