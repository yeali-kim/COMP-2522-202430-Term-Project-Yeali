package TrashPanda;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

class CompositeMode extends Mode {
    private final Mode nightMode;
    private final Mode angryNeighborMode;

    public CompositeMode(String difficulty) {
        super(difficulty);
        this.nightMode = new NightMode(difficulty);
        this.angryNeighborMode = new AngryNeighborMode(difficulty);
    }

    @Override
    public String getName() {
        return difficulty + " Composite Mode";
    }

    @Override
    public Maze createMaze() {
        return angryNeighborMode.createMaze(); // You could combine or choose one
    }

    @Override
    public void applyEffects(GraphicsContext gc, Player player, Canvas canvas, Maze maze) {
        nightMode.applyEffects(gc, player, canvas, maze);
        angryNeighborMode.applyEffects(gc, player, canvas, maze);
    }
}
