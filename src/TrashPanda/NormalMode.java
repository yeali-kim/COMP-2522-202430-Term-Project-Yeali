package TrashPanda;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

class NormalMode implements Mode {
    private final int mazeSize;
    private final String difficulty;

    public NormalMode(String difficulty) {
        if (difficulty.equals("Easy")) {
            this.mazeSize = 5;
        } else if (difficulty.equals("Hard")) {
            this.mazeSize = 10;
        } else {
            throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
        this.difficulty = difficulty;
    }

    @Override
    public String getName() {
        return difficulty + " Normal Mode";
    }

    @Override
    public Maze createMaze() {
        return new Maze(mazeSize);
    }

    @Override
    public void applyEffects(GraphicsContext gc, Player player, Canvas canvas, Maze maze) {
        //No effect for normal mode
    }
}
