package TrashPanda;

import javafx.scene.canvas.GraphicsContext;

public interface Mode {
    int getMazeSize();
    Maze createMaze();
    void applyEffects(GraphicsContext gc);
    String getName();
}