package TrashPanda;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Manages the progression of levels in the game.
 *
 * @author Yeali Kim
 * @version 2024
 */
public class LevelManager implements Serializable {
    /**
     * Indicates if the game is over.
     */
    static boolean isGameOver = false;
    /**
     * Indicates whether the player can proceed to the next level.
     */
    static boolean proceedToNext = false;
    private int currentLevelIndex = 0;
    private transient Mode currentMode;
    private transient Maze currentMaze;
    private final transient Mode[] levelProgression = {
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

    /**
     * Constructs a LevelManager instance, initializing the first game mode.
     */
    public LevelManager() {
        currentMode = levelProgression[currentLevelIndex];
    }

    /**
     * Ends the game and displays an alert.
     */
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

    /**
     * Retrieves the current game mode.
     *
     * @return Mode that is the current game mode
     */
    Mode getCurrentMode() {
        return currentMode;
    }

    /**
     * Retrieves the current maze.
     * If it does not exist, create one based on the current mode.
     *
     * @return Maze that represents the current maze instance
     */
    Maze getCurrentMaze() {
        if (currentMaze == null) {
            currentMaze = currentMode.createMaze();
        }
        return currentMaze;
    }

    /**
     * Completes the current level, saves the progression, and displays the appropriate alert.
     * If user does not click on alert, does not proceed to next or exit.
     */
    public void completeLevel() {
        //If game won, display game won alert, remove save file, and exit program.
        if (levelProgression[currentLevelIndex + 1] == null) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Game Won!");
                alert.setHeaderText(null);
                alert.setContentText("Congratulations! You have arrived home safely!");
                alert.showAndWait();
                removeSaveFile();
                Platform.exit();
            });
            return;
        }

        Mode nextMode = levelProgression[currentLevelIndex + 1];
        saveGame();
        String message = currentMode.getName()
                + " cleared! Now prepare for " + nextMode.getName() + "!";
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Level Cleared!");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
            proceedToNext = true;
        });
    }


    /**
     * Advances to the next level and updates the current mode and maze.
     */
    public void advanceLevel() {
        proceedToNext = false;
        if (currentLevelIndex + 1 >= levelProgression.length) {
            return;
        }
        currentLevelIndex++;
        currentMode = levelProgression[currentLevelIndex];
        currentMaze = currentMode.createMaze();
    }

    /**
     * Applies the drawing effects of the current game mode to the given graphics context.
     *
     * @param gc     GraphicsContext used for drawing
     * @param player Player instance
     * @param canvas Canvas on which to draw
     */
    void applyModeDrawingEffects(final GraphicsContext gc,
                                 final Player player, final Canvas canvas) {
        currentMode.applyEffects(gc, player, canvas, currentMaze);
    }

    /**
     * Saves the current level + 1 to a file named "saveFile.ser".
     */
    void saveGame() {
        try (FileOutputStream fileOut = new FileOutputStream("saveFile.ser");
             ObjectOutputStream outStream = new ObjectOutputStream(fileOut)) {
            outStream.writeObject(currentLevelIndex + 1);
            System.out.println("Game state saved in saveFile.ser");
        } catch (IOException e) {
            System.out.println("Failed to save game state.");
        }
    }

    /**
     * Loads the saved level from the "saveFile.ser" file if it exists.
     */
    void loadGame() {
        File saveFile = new File("saveFile.ser");
        if (!saveFile.exists()) {
            System.out.println("No saved game found.");
            return;
        }
        try (FileInputStream fileIn = new FileInputStream(saveFile);
             ObjectInputStream inStream = new ObjectInputStream(fileIn)) {
            currentLevelIndex = (int) inStream.readObject();
            currentMode = levelProgression[currentLevelIndex];
            currentMaze = currentMode.createMaze();
            System.out.println("Game state loaded from saveFile.ser");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load game state.");
        }
    }

    /**
     * Removes the save file "saveFile.ser" if it exists.
     */
    private void removeSaveFile() {
        File saveFile = new File("saveFile.ser");
        if (saveFile.exists() && saveFile.delete()) {
            System.out.println("Save file deleted.");
        } else {
            System.out.println("Failed to delete save file or no save file exists.");
        }
    }
}
