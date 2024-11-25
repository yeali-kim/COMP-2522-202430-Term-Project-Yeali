package TrashPanda;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public interface Mode {
    String getName();
    Maze createMaze();
    void applyEffects(GraphicsContext gc, Player player, Canvas canvas, Maze maze);
}