package TrashPanda;

import java.util.*;

class Game {
    private final Player player;
    private final Maze maze;
    private final LevelManager levelManager;

    public Game(Player player, LevelManager levelManager) {
        this.player = player;
        this.levelManager = levelManager;
        this.maze = levelManager.getCurrentMaze();
    }

    public void update(Set<String> activeKeys) {
        double dx = 0, dy = 0;
        boolean moving = false;
        String direction = player.getLastDirection();

        if (activeKeys.contains("W")) {
            dy -= 0.1;
            direction = "W";
            moving = true;
        }
        if (activeKeys.contains("S")) {
            dy += 0.1;
            direction = "S";
            moving = true;
        }
        if (activeKeys.contains("A")) {
            dx -= 0.1;
            direction = "A";
            moving = true;
        }
        if (activeKeys.contains("D")) {
            dx += 0.1;
            direction = "D";
            moving = true;
        }
        // Apply the mode-specific effects to the player
        Mode currentMode = levelManager.getCurrentMode();
        if (currentMode != null) {
            currentMode.applyEffect(player);
        }
        player.move(dx, dy, maze);
        player.updateImage(direction, moving);
    }

    public boolean isLevelCompleted() {
        int goalX = maze.getMazeSize() - 2;
        int goalY = maze.getMazeSize() - 2;

        int playerX = (int) Math.round(player.getX());
        int playerY = (int) Math.round(player.getY());

        return playerX == goalX && playerY == goalY;
    }
}
