package TrashPanda;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

/**
 * Main class for the Trash Panda game application.
 * This extends the JavaFX Application class and handles the initialization, setup,
 * and rendering of the game.
 *
 * @author Yeali Kim
 * @version 2024
 */
public class TrashPandaGame extends Application {
    /**
     * The size of each cell in the maze in pixels.
     */
    static final double CELL_SIZE = 40;
    /**
     * The size of the canvas in pixels.
     */
    static final int CANVAS_SIZE = 1000;
    private final Set<String> activeKeys = new HashSet<>();
    private Maze maze;
    private Player player;
    private Canvas canvas;
    private Game game;
    private LevelManager levelManager;
    private Stage stage;

    /**
     * Initializes the game, sets up the UI components, and starts the main game.
     *
     * @param primaryStage Stage that is the primary stage for this JavaFX application
     */
    @Override
    public void start(final Stage primaryStage) {
        this.stage = primaryStage;
        initializeGameComponents();
        startAnimationTimer();
    }

    /**
     * Initializes the main components of the game including loading images,
     * setting up canvas, creating scene, and displaying the stage.
     */
    private void initializeGameComponents() {
        ImageLoader.loadImages();
        levelManager = new LevelManager();
        levelManager.loadGame();
        initializeLevel();
        canvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE);
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(e -> activeKeys.add(e.getCode().toString()));
        scene.setOnKeyReleased(e -> activeKeys.remove(e.getCode().toString()));

        updateStageTitle();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Starts the animation timer for game loop that updates and draws the game state on each frame.
     * Handles level completion and transition.
     */
    private void startAnimationTimer() {
        AnimationTimer timer = new AnimationTimer() {
            private static boolean run = true;

            /**
             * Handles the update and drawing logic on each frame and based on level completion.
             *
             * @param now long that represents current time in nanoseconds
             */
            @Override
            public void handle(final long now) {
                if (LevelManager.isGameOver) {
                    stop();
                    return;
                }
                if (run) {
                    game.update(activeKeys);
                    drawGame();
                }
                if (game.isLevelCompleted()) {
                    run = false;
                    levelManager.completeLevel();
                    activeKeys.clear();
                    initializeLevel();
                }
                if (LevelManager.proceedToNext) {
                    levelManager.advanceLevel();
                    initializeLevel();
                    run = true;
                }
            }
        };
        timer.start();
    }

    /**
     * Initializes the current level by creating a new maze, player, and game instance.
     */
    private void initializeLevel() {
        maze = levelManager.getCurrentMaze();
        player = new Player(1, 1);
        game = new Game(player, levelManager);
        updateStageTitle();
    }

    /**
     * Updates the title of the application window based on the current level and mode.
     */
    private void updateStageTitle() {
        Mode currentMode = levelManager.getCurrentMode();
        String difficulty = currentMode.getName();
        stage.setTitle("Trash Panda - " + difficulty);
    }

    /**
     * Draws the game state onto the canvas.
     */
    private void drawGame() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double mazePixelSize = maze.getMazeSize() * CELL_SIZE;
        double offsetX = (canvas.getWidth() - mazePixelSize) / 2;
        double offsetY = (canvas.getHeight() - mazePixelSize) / 2;

        gc.save();
        gc.translate(offsetX, offsetY);
        maze.draw(gc);
        player.draw(gc);
        gc.restore();
        levelManager.applyModeDrawingEffects(gc, player, canvas);
        if (levelManager.getCurrentMode().getName().equals("Hard Composite Mode")) {
            maze.drawDestination(gc);
        } else {
            maze.drawGoal(gc);
        }
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
