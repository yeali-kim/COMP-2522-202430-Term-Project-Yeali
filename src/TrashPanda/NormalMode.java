package TrashPanda;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

class NormalMode extends Mode {
    public NormalMode(String difficulty) {
        super(difficulty); // Reuses the constructor logic from the abstract Mode class
    }

    @Override
    public String getName() {
        return difficulty + " Normal Mode";
    }

    @Override
    public Maze createMaze() {
        return new Maze(mazeSize); // Uses mazeSize field from the abstract class
    }

    @Override
    public void applyEffects(GraphicsContext gc, Player player, Canvas canvas, Maze maze) {
        // No effects for Normal Mode, leave this empty
    }
}
