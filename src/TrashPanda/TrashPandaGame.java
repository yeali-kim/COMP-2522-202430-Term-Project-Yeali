package TrashPanda;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class TrashPandaGame extends Application {
    private static final int CELL_SIZE = 40;
    private final int canvasSize = 1000;
    private Maze maze;
    private Player player;
    private Canvas canvas;
    private final Set<String> activeKeys = new HashSet<>();
    private Game game;
    private LevelManager levelManager;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        ImageLoader.loadImages();
        levelManager = new LevelManager();
        initializeLevel();
        canvas = new Canvas(canvasSize, canvasSize);
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(e -> activeKeys.add(e.getCode().toString()));
        scene.setOnKeyReleased(e -> activeKeys.remove(e.getCode().toString()));

        updateStageTitle();
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.update(activeKeys);
                drawGame();

                if (game.isLevelCompleted()) {
                    levelManager.advanceLevel();
                    activeKeys.clear();
                    initializeLevel();
                }
            }
        };
        timer.start();
    }


    private void initializeLevel() {
        maze = levelManager.getCurrentMaze();
        player = new Player(1, 1, CELL_SIZE);
        game = new Game(player, levelManager);
        updateStageTitle();
    }

    private void updateStageTitle() {
        Mode currentMode = levelManager.getCurrentMode();
        String difficulty = currentMode.getName();
        primaryStage.setTitle("Trash Panda - " + difficulty);
    }


    private void drawGame() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Apply current mode effects
        levelManager.applyCurrentModeEffects(gc);

        int mazePixelSize = maze.getMazeSize() * CELL_SIZE;
        double offsetX = (canvas.getWidth() - mazePixelSize) / 2;
        double offsetY = (canvas.getHeight() - mazePixelSize) / 2;

        gc.save();
        gc.translate(offsetX, offsetY);
        maze.draw(gc, CELL_SIZE);
        player.draw(gc);
        gc.restore();
    }


    public static void main(String[] args) {
        launch(args);
    }
}