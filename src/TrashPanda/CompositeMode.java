package TrashPanda;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.Objects;

/**
 * Represents a composite mode that combines effects from night mode and angry neighbor mode.
 */
class CompositeMode extends Mode {
    private final Mode nightMode;
    private final Mode angryNeighborMode;

    /**
     * Constructs a new composite mode instance with specified difficulty level.
     *
     * @param difficulty String that represents the difficulty level
     */
    CompositeMode(final String difficulty) {
        super(difficulty);
        this.nightMode = new NightMode(difficulty);
        this.angryNeighborMode = new AngryNeighborMode(difficulty);
    }

    /**
     * Retrieves the difficulty and name of the mode.
     *
     * @return String that represents the difficulty and name of the mode
     */
    @Override
    public String getName() {
        return difficulty + " Composite Mode";
    }

    /**
     * Creates a maze for the composite mode.
     * Uses the maze for angry neighbor mode to render neighbor on the canvas.
     *
     * @return Maze instance for the composite mode
     */
    @Override
    public Maze createMaze() {
        return angryNeighborMode.createMaze(); // You could combine or choose one
    }

    /**
     * Applies the effects of the composite mode to the specified graphics context.
     * It applies both night mode and angry neighbor mode effects.
     *
     * @param gc GraphicsContext used for drawing on the canvas
     * @param player Player instance
     * @param canvas Canvas on which to apply the effects
     * @param maze Maze instance to apply effects to
     */
    @Override
    public void applyEffects(final GraphicsContext gc, final Player player,
                             final Canvas canvas, final Maze maze) {
        nightMode.applyEffects(gc, player, canvas, maze);
        angryNeighborMode.applyEffects(gc, player, canvas, maze);
    }

    @Override
    public String toString() {
        return "CompositeMode{" + ", mazeSize="
                + mazeSize + ", difficulty='" + difficulty + '\'' + '}';
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof AngryNeighborMode that)) {
            return false;
        }
        return Objects.equals(mazeSize, that.mazeSize)
                && Objects.equals(difficulty, that.difficulty);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mazeSize);
    }
}
