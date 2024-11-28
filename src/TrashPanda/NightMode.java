package TrashPanda;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

class NightMode extends Mode {
    public NightMode(String difficulty) {
        super(difficulty);
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
    public void applyEffects(GraphicsContext gc, Player player, Canvas canvas, Maze maze) {
        final double radius = 200;
        double playerX = player.getX() * player.getCellSize();
        double playerY = player.getY() * player.getCellSize();

        double offsetX = (canvas.getWidth() - maze.getMazeSize() * player.getCellSize()) / 2;
        double offsetY = (canvas.getHeight() - maze.getMazeSize() * player.getCellSize()) / 2;

        double centerX = playerX + offsetX + (player.getCellSize() * 0.3);
        double centerY = playerY + offsetY + (player.getCellSize() * 0.3);

        gc.save();

        // Draw gradient effect
        RadialGradient gradient = new RadialGradient(
                0, 0,
                centerX, centerY,
                radius / 2 + 20,
                false, CycleMethod.NO_CYCLE,
                new Stop(0.6, Color.TRANSPARENT),
                new Stop(1.0, Color.BLACK)
        );

        gc.setFill(gradient);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.restore();
    }
}
