package TrashPanda;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class TrashPandaGame extends Application {
    private static final int CELL_SIZE = 40;
    private Maze maze;
    private Player player;
    private Canvas canvas;
    private final Set<String> activeKeys = new HashSet<>();
    private Game game;

    @Override
    public void start(Stage primaryStage) {
        ImageLoader.loadImages();
        maze = new Maze(10);
        player = new Player(1, 1, CELL_SIZE);
        game = new Game(player, maze);

        canvas = new Canvas(maze.getMazeSize() * CELL_SIZE * 1.2, maze.getMazeSize() * CELL_SIZE * 1.2);
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(e -> activeKeys.add(e.getCode().toString()));
        scene.setOnKeyReleased(e -> activeKeys.remove(e.getCode().toString()));

        primaryStage.setTitle("Trash Panda - Normal Mode (Easy)");
        primaryStage.setScene(scene);
        primaryStage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.update(activeKeys);
                drawGame();

                if (playerReachedGoal()) {
                    showCongratulationsPopup();
                    stop();
                }
            }
        }.start();
    }

    private boolean playerReachedGoal() {
        int goalX = maze.getMazeSize() - 2;
        int goalY = maze.getMazeSize() - 2;

        int playerX = (int) Math.round(player.getX());
        int playerY = (int) Math.round(player.getY());

        return playerX == goalX && playerY == goalY;
    }

    private void showCongratulationsPopup() {
        javafx.application.Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Finished!");
            alert.setHeaderText(null);
            alert.setContentText("You arrived at home :)");
            alert.initOwner(canvas.getScene().getWindow());
            alert.show();
        });
    }

    private void drawGame() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int mazePixelSize = maze.getMazeSize() * CELL_SIZE;
        double offsetX = (canvas.getWidth() - mazePixelSize) / 2;
        double offsetY = (canvas.getHeight() - mazePixelSize) / 2;
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
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
