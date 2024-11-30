package TrashPanda;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

import java.util.Objects;

/**
 * Represents the night mode that has a black overlay except for a halo following the player.
 */
class NightMode extends Mode {
    /**
     * Constructs a new night mode with the specified difficulty level.
     *
     * @param difficulty String that represents the difficulty level
     */
    NightMode(final String difficulty) {
        super(difficulty);
    }

    /**
     * Retrieves the difficulty and name of the mode.
     *
     * @return String that represents the difficulty and name of the mode
     */
    @Override
    public String getName() {
        return difficulty + " Night Mode";
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
     * Applies visual effects for night mode.
     *
     * @param gc     GraphicsContext used for rendering on the canvas
     * @param player Player representing current player
     * @param canvas Canvas on which to draw
     * @param maze   Maze that is current maze
     */
    @Override
    public void applyEffects(final GraphicsContext gc, final Player player,
                             final Canvas canvas, final Maze maze) {
        final double cellSize = TrashPandaGame.CELL_SIZE;
        final double scaleFactor = 0.6;
        final double radius = 150;
        final int radiusOffset = 20;

        double playerX = player.getX() * cellSize;
        double playerY = player.getY() * cellSize;

        double offsetX = (canvas.getWidth() - maze.getMazeSize() * cellSize) / 2;
        double offsetY = (canvas.getHeight() - maze.getMazeSize() * cellSize) / 2;

        double centerX = playerX + offsetX + (cellSize * scaleFactor / 2);
        double centerY = playerY + offsetY + (cellSize * scaleFactor / 2);

        gc.save();

        // Draw gradient effect
        RadialGradient gradient = new RadialGradient(
                0, 0,
                centerX, centerY,
                radius / 2 + radiusOffset,
                false, CycleMethod.NO_CYCLE,
                new Stop(scaleFactor, Color.TRANSPARENT),
                new Stop(1.0, Color.BLACK)
        );
        gc.setFill(gradient);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.restore();
    }

    @Override
    public String toString() {
        return "NightMode{" + ", mazeSize="
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
