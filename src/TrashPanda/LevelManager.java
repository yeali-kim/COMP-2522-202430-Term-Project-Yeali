package TrashPanda;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

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

    public void advanceLevel() {
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
            return;
        }

        // Set the current mode to the next level
        currentMode = levelProgression[currentLevelIndex];
        currentMaze = currentMode.createMaze();
    }

    public void applyModeDrawingEffects(GraphicsContext gc, Player player, Canvas canvas) {
                Mode currentMode = getCurrentMode();
        if (currentMode != null) {
            // For NightMode or CompositeMode, apply the light radius effect
            if (currentMode instanceof NightMode || currentMode instanceof CompositeMode) {
                // Calculate the player's position adjusted by the maze's offset
                double playerX = player.getX() * player.getCellSize();
                double playerY = player.getY() * player.getCellSize();
                double offsetX = (canvas.getWidth() - currentMaze.getMazeSize() * player.getCellSize()) / 2;
                double offsetY = (canvas.getHeight() - currentMaze.getMazeSize() * player.getCellSize()) / 2;

                // Calculate center of the player sprite
                double centerX = playerX + offsetX + (player.getCellSize() * 0.3); // Adjust for player sprite center
                double centerY = playerY + offsetY + (player.getCellSize() * 0.3); // Adjust for player sprite center

                double radius = player.getLightRadius();

                // Save the current state
                gc.save();

                gc.beginPath();
                gc.rect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.arc(centerX, centerY, radius / 2, radius / 2, 0, 360);
                gc.closePath();

                gc.setFill(Color.BLACK);
                gc.fill();

                // Gradient circle
                RadialGradient gradient = new RadialGradient(
                        0, 0,
                        centerX,
                        centerY,
                        radius / 2 + 20,
                        false,
                        CycleMethod.NO_CYCLE,
                        new Stop(0.6, Color.TRANSPARENT),
                        new Stop(1.0, Color.BLACK)
                );

                gc.setFill(gradient);
                gc.fillOval(centerX - (radius / 2 + 20), centerY - (radius / 2 + 20),
                        (radius + 40), (radius + 40));

                gc.restore();
            } else if (currentMode instanceof AngryNeighborMode) {
                gc.setFill(javafx.scene.paint.Color.rgb(0, 0, 0, 0.0));
                gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
            }
        }
    }
}