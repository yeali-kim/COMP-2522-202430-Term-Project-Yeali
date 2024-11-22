package TrashPanda;

import javafx.scene.canvas.GraphicsContext;

public interface Mode {
    int getMazeSize();
    Maze createMaze();
    void applyEffects(GraphicsContext gc);
    String getName();


    // Default factory methods for easy and hard difficulties
    static Mode easy(Mode baseMode) {
        return new Mode() {
            @Override
            public int getMazeSize() {
                return 5;
            }

            @Override
            public Maze createMaze() {
                return new Maze(5);
            }

            @Override
            public void applyEffects(GraphicsContext gc) {
                baseMode.applyEffects(gc);
            }

            @Override
            public String getName() {
                return "Easy Mode";
            }
        };
    }

    static Mode hard(Mode baseMode) {
        return new Mode() {
            @Override
            public int getMazeSize() {
                return 10;
            }

            @Override
            public Maze createMaze() {
                return new Maze(10);
            }

            @Override
            public void applyEffects(GraphicsContext gc) {
                baseMode.applyEffects(gc);
            }

            @Override
            public String getName() {
                return "Hard";
            }
        };
    }
}