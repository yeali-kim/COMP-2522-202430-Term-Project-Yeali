package TrashPanda;

import java.util.Objects;
import java.util.Set;

/**
 * Represents the game logic, including player movement and level completion.
 * Handles player updates, movement, and checks if a level has been completed.
 */
class Game {
    private final Player player;
    private final Maze maze;

    /**
     * Constructs a new Game instance with the specified player and level.
     *
     * @param player       Player instance of the game
     * @param levelManager LevelManager that provides the current maze and mode
     */
    Game(final Player player, final LevelManager levelManager) {
        this.player = player;
        this.maze = levelManager.getCurrentMaze();
    }

    /**
     * Updates the game state based on the active keys pressed by the user.
     *
     * @param activeKeys a set of Strings representing the keys currently being pressed
     */
    public void update(final Set<String> activeKeys) {
        double dx = 0;
        double dy = 0;
        final double movement = 0.1;
        boolean moving = false;
        String direction = player.getLastDirection();

        if (activeKeys.contains("W")) {
            dy -= movement;
            direction = "W";
            moving = true;
        }
        if (activeKeys.contains("S")) {
            dy += movement;
            direction = "S";
            moving = true;
        }
        if (activeKeys.contains("A")) {
            dx -= movement;
            direction = "A";
            moving = true;
        }
        if (activeKeys.contains("D")) {
            dx += movement;
            direction = "D";
            moving = true;
        }
        player.move(dx, dy, maze);
        player.updateImage(direction, moving);
    }

    /**
     * Checks if the current level has been completed by determining
     * whether the player reached the goal position of the maze, which is the right bottom corner.
     *
     * @return true is player reached the end position, and false otherwise
     */
    public boolean isLevelCompleted() {
        int goalX = maze.getMazeSize() - 2;
        int goalY = maze.getMazeSize() - 2;

        int playerX = (int) Math.round(player.getX());
        int playerY = (int) Math.round(player.getY());

        return playerX == goalX && playerY == goalY;
    }

    @Override
    public String toString() {
        return "Game{maze=" + maze + '}';
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Game game)) {
            return false;
        }
        return Objects.equals(player, game.player)
                && Objects.equals(maze, game.maze);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, maze);
    }
}
