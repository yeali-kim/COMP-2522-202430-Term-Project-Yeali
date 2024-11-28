package TrashPanda;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;

public class LevelManager {
    private Mode currentMode;
    private int currentLevelIndex = 0;
    private Maze currentMaze;
    static boolean isGameOver = false;
    static boolean proceedToNext = false;

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
        currentMode = levelProgression[currentLevelIndex];
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

    public void completeLevel() {
        if (levelProgression[currentLevelIndex + 1] == null) {
            // Display "Game Won" alert and stop the game
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Game Won!");
                alert.setHeaderText(null);
                alert.setContentText("Congratulations! You have arrived home safely!");
                alert.showAndWait(); // This blocks until the user clicks "OK"
                Platform.exit();
            });

            return;
        }

        // Display a "Level Cleared" message and wait for user input
        Mode nextMode = levelProgression[currentLevelIndex + 1];
        String message = currentMode.getName() + " cleared! Now prepare for " + nextMode.getName() + "!";
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Level Cleared!");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
            proceedToNext = true;
        });
    }


    public void advanceLevel() {
        proceedToNext = false;
        if (currentLevelIndex + 1 >= levelProgression.length) {
            return;
        }
        currentLevelIndex++;
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
        currentMode.applyEffects(gc, player, canvas, currentMaze);
    }
}