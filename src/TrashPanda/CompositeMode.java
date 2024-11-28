package TrashPanda;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

class CompositeMode implements Mode {
    private final String difficulty;
    private final Mode[] modes;

    public CompositeMode(String difficulty) {
        this.difficulty = difficulty;
        modes = new Mode[]{
                new NightMode(difficulty),
                new AngryNeighborMode(difficulty)
        };
    }

    @Override
    public String getName() {
        return difficulty + "Composite Mode";
    }

    @Override
    public Maze createMaze() {
        return modes[1].createMaze();
    }

    @Override
    public void applyEffects(GraphicsContext gc, Player player, Canvas canvas, Maze currentMaze) {
        for (Mode mode : modes) {
            mode.applyEffects(gc, player, canvas, currentMaze);
        }
    }
}