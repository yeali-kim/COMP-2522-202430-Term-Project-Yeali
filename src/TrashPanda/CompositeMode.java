package TrashPanda;

import javafx.scene.canvas.GraphicsContext;

class CompositeMode implements Mode {
    private final int mazeSize;
    private final String difficulty;

    // Constructor for NormalMode
    public CompositeMode(String difficulty) {
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
    public Maze createMaze() {
        return new Maze(mazeSize);
    }

    @Override
    public void applyEffect(Player player) {
        //Reduce the light radius
        player.setLightRadius(200);

    }

    @Override
    public String getName() {
        return difficulty + " Composite Mode";
    }
}
