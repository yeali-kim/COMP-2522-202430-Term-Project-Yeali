package TrashPanda;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;

public class LevelManager {
    private Mode currentMode;
    private int currentLevelIndex = 0;

    private final Mode[] levelProgression = {
            Mode.easy(new NormalMode("Easy")),     // Easy Normal
            Mode.hard(new NormalMode("Hard")),     // Hard Normal
            Mode.easy(new NightMode("Easy")),      // Easy Night
            Mode.hard(new NightMode("Hard")),      // Hard Night
            Mode.easy(new AngryNeighborMode("Easy")), // Easy Angry Neighbor
            Mode.hard(new AngryNeighborMode("Hard")), // Hard Angry Neighbor
            Mode.easy(new CompositeMode("Easy")),  // Easy Composite
            Mode.hard(new CompositeMode("Hard")),  // Hard Composite
            null                                 // Game Won
    };

    public LevelManager() {
        currentMode = levelProgression[0];
    }

    Maze getCurrentMaze() {
        return currentMode != null ? currentMode.createMaze() : null;
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
        if (currentLevelIndex < levelProgression.length - 1) {
            Mode nextMode = levelProgression[currentLevelIndex + 1];
            String currentModeName = currentMode != null ? currentMode.getName() : "Game won";
            String nextModeName = nextMode != null ? nextMode.getName() : "Congratulations! You arrived home!";

            // Format the message
            message = currentModeName + " cleared! Now prepare for " + nextModeName + "!";
        } else {
            // Final congratulatory message
            message = "Congratulations! You completed all levels! You arrived home!";
        }

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
        return true;
    }

}