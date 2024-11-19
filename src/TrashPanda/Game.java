package TrashPanda;
import java.util.*;

class Game {
    private final Player player;
    private final Maze maze;

    public Game(Player player, Maze maze) {
        this.player = player;
        this.maze = maze;
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

        player.move(dx, dy, maze);
        player.updateImage(direction, moving);
    }
}
