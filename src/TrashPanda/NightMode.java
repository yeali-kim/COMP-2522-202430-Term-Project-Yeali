package TrashPanda;

import javafx.scene.canvas.GraphicsContext;

class NightMode implements Mode {
    private final int mazeSize;
    private final String difficulty;

    // Constructor for NormalMode
    public NightMode(String difficulty) {
        if (difficulty.equals("Easy")) {
            this.mazeSize = Mode.easy(this).getMazeSize();
        } else if (difficulty.equals("Hard")) {
            this.mazeSize = Mode.hard(this).getMazeSize();
        } else {
            throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
        this.difficulty = difficulty;
    }

    @Override
    public int getMazeSize() {
        return mazeSize;
    }

    @Override
    public Maze createMaze() {
        return new Maze(mazeSize);
    }

    @Override
    public void applyEffects(GraphicsContext gc) {
        // No special effects for normal mode
    }

    @Override
    public String getName() {
        return difficulty + " Night Mode";
    }
}
