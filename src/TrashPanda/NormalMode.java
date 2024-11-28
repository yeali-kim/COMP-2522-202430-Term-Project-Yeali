package TrashPanda;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Represents the normal mode of the game without special effects.
 */
class NormalMode extends Mode {
    /**
     * Constructs a new normal mode with the specified difficulty level.
     *
     * @param difficulty String that represents the difficulty level
     */
    NormalMode(final String difficulty) {
        super(difficulty);
    }

    /**
     * Retrieves the difficulty and name of the mode.
     *
     * @return String that represents the difficulty and name of the mode
     */
    @Override
    public String getName() {
        return difficulty + " Normal Mode";
    }

    /**
     * Creates a new instance of a maze with the specified size.
     *
     * @return Maze instance created with the specified size
     */
    @Override
    public Maze createMaze() {
        return new Maze(mazeSize);
    }

    /**
     * Applies visual effects for normal mode. For normal mode, no additional effects are applied.
     *
     * @param gc GraphicsContext used for rendering on the canvas
     * @param player Player representing current player
     * @param canvas Canvas on which to draw
     * @param maze Maze that is current maze
     */
    @Override
    public void applyEffects(final GraphicsContext gc, final Player player,
                             final Canvas canvas, final Maze maze) {
        // No effects for Normal Mode
    }
}
