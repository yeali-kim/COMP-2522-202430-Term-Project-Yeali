package TrashPanda;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

class NightMode implements Mode {
    private final int mazeSize;
    private final String difficulty;

    public NightMode(String difficulty) {
        if (difficulty.equals("Easy")) {
            this.mazeSize = 5;
        } else if (difficulty.equals("Hard")) {
            this.mazeSize = 10;
        } else {
            throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
        this.difficulty = difficulty;
    }

    @Override
    public String getName() {
        return difficulty + " Night Mode";
    }

    @Override
    public Maze createMaze() {
        return new Maze(mazeSize);
    }

    @Override
    public void applyEffect(Player player) {
        player.setLightRadius(200);
    }

    public void drawEffects(GraphicsContext gc, Player player, Canvas canvas, Maze currentMaze) {
        double playerX = player.getX() * player.getCellSize();
        double playerY = player.getY() * player.getCellSize();

        double offsetX = (canvas.getWidth() - currentMaze.getMazeSize() * player.getCellSize()) / 2;
        double offsetY = (canvas.getHeight() - currentMaze.getMazeSize() * player.getCellSize()) / 2;

        double centerX = playerX + offsetX + (player.getCellSize() * 0.3);
        double centerY = playerY + offsetY + (player.getCellSize() * 0.3);

        double radius = player.getLightRadius();

        gc.save();

        // Clip circle
        gc.beginPath();
        gc.rect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.arc(centerX, centerY, radius / 2, radius / 2, 0, 360);
        gc.closePath();

        gc.setFill(Color.BLACK);
        gc.fill();

        // Gradient effect
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
                radius + 40, radius + 40);

        gc.restore();
    }
}