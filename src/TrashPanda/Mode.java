package TrashPanda;

import javafx.scene.canvas.GraphicsContext;

public interface Mode {
    Maze createMaze();
    void applyEffect(Player player);
    String getName();
}