package TrashPanda;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;

public class LevelManager {
    private Mode currentMode;
    private int currentLevelIndex = 0;
    private Maze currentMaze;
    static boolean isGameOver = false;

    private final Mode[] levelProgression = {
            new NormalMode("Easy"),     // Easy Normal
            new NormalMode("Hard"),     // Hard Normal
            new NightMode("Easy"),      // Easy Night
            new NightMode("Hard"),      // Hard Night
            new AngryNeighborMode("Easy"), // Easy Angry Neighbor
            new AngryNeighborMode("Hard"), // Hard Angry Neighbor
            new CompositeMode("Easy"),  // Easy Composite
            new CompositeMode("Hard"),  // Hard Composite
            null                                 // Game Won
    };

    public LevelManager() {
        currentMode = levelProgression[4];
    }

    Maze getCurrentMaze() {
        if (currentMaze == null) {
            currentMaze = currentMode.createMaze();
        }
        return currentMaze;
    }

    public Mode getCurrentMode() {
        return currentMode;
    }

    public void advanceLevel() {
        String message;
        Mode nextMode = levelProgression[currentLevelIndex + 1];
        String currentModeName = currentMode.getName();
        String nextModeName = nextMode.getName();
        message = currentModeName + " cleared!" + (nextMode != null ? "Now prepare for " + nextModeName + "!" : "You arrived home safely!");

        //pop up
        javafx.application.Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Level Cleared!");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });

        currentLevelIndex++;

        if (currentLevelIndex >= levelProgression.length) {
            currentMode = null;
            return;
        }

        currentMode = levelProgression[currentLevelIndex];
        currentMaze = currentMode.createMaze();
    }

    public static void endGame() {
        if (isGameOver) {
            return;
        }
        isGameOver = true;

        javafx.application.Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(null);
            alert.setContentText("You have been caught by the Angry Neighbor! Game Over.");
            alert.showAndWait();
            System.exit(0);
        });
    }

    public void applyModeDrawingEffects(GraphicsContext gc, Player player, Canvas canvas) {
        if (currentMode != null) {
            currentMode.applyEffects(gc, player, canvas, currentMaze);
            if (currentMode instanceof AngryNeighborMode) {
                if (((AngryNeighborMode) currentMode).isGameOver()) {
                    endGame();
                }
            }
        }
    }
}