package TrashPanda;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public abstract class Mode {
    protected final int mazeSize;
    protected final String difficulty;

    public Mode(String difficulty) {
        if (difficulty.equals("Easy")) {
            this.mazeSize = 5;
        } else if (difficulty.equals("Hard")) {
            this.mazeSize = 10;
        } else {
            throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
        this.difficulty = difficulty;
    }

    public abstract String getName();

    public abstract Maze createMaze();

    public abstract void applyEffects(GraphicsContext gc, Player player, Canvas canvas, Maze maze);
}
