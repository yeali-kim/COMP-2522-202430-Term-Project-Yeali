package TrashPanda;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;

public class LevelManager {
    private Mode currentMode;
    private int currentLevelIndex = 0;
    private Maze currentMaze;

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
        currentMode = levelProgression[0];
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

    public void applyCurrentModeEffects(GraphicsContext gc) {
        if (currentMode != null) {
            currentMode.applyEffects(gc);
        }
    }

    public boolean advanceLevel() {
        String message;
        Mode nextMode = levelProgression[currentLevelIndex + 1];
        String currentModeName = currentMode.getName();
        String nextModeName = nextMode.getName();
        // Format the message
        message = currentModeName + " cleared!" + (nextMode != null ? "Now prepare for " + nextModeName + "!" : "You arrived home safely!");

        // Show the popup
        javafx.application.Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Level Cleared!");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });

        // Advance to the next level
        currentLevelIndex++;

        // Check if we've reached the end of levels
        if (currentLevelIndex >= levelProgression.length) {
            currentMode = null;
            return false;
        }

        // Set the current mode to the next level
        currentMode = levelProgression[currentLevelIndex];
        currentMaze = currentMode.createMaze();

        return true;
    }

}