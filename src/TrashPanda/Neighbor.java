package TrashPanda;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Represents an angry neighbor that periodically shoots projectiles in random directions
 * and ends the game if it collides with the player.
 */
class Neighbor {
    private final double cellSize = TrashPandaGame.CELL_SIZE;
    private final double x;
    private final double y;
    private final List<Projectile> projectiles;
    private long lastShotTime;
    private final Random random;

    /**
     * Constructs a neighbor at the specified coordinates.
     *
     * @param x double that represents the x-coordinate of the neighbor
     * @param y double that represents the y-coordinate of the neighbor
     */
    Neighbor(final double x, final double y) {
        this.x = x;
        this.y = y;
        this.projectiles = new ArrayList<>();
        this.random = new Random();
        this.lastShotTime = System.currentTimeMillis();
    }

    /**
     * Updates the neighbor's state by checking if it should shoot a projectile
     * and update all active projectiles.
     */
    public void update() {
        final int delayBound = 10;
        final int timeMultiple = 100;
        long shotDelay = random.nextLong(1, delayBound) * timeMultiple;
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShotTime >= shotDelay) {
            shoot();
            lastShotTime = currentTime;
        }

        projectiles.removeIf(projectile -> {
            projectile.update();
            return projectile.isOutOfBounds();
        });
    }

    /**
     * Fires a projectile in a random direction with a random speed.
     */
    private void shoot() {
        final int degree = 180;
        // Random angle between 0 and 180 degrees
        double angle = random.nextDouble() * degree;
        // Random speed between 1.0 and 2.0
        double speed = random.nextDouble() + 1;
        // Convert angle to radians
        double radians = Math.toRadians(angle);
        // Calculate velocity components
        double vx = speed * Math.cos(radians);
        double vy = speed * Math.sin(radians);

        Projectile projectile = new Projectile(x + cellSize / 2, y + cellSize / 2, vx, vy);
        projectiles.add(projectile);
    }

    /**
     * Draws the neighbor and its projectiles on the canvas.
     *
     * @param gc GraphicsContext used to draw on the canvas
     */
    public void draw(final GraphicsContext gc) {
        // Draw neighbor
        gc.drawImage(ImageLoader.neighbor, x - cellSize / 2,
                y - cellSize, cellSize * 2, cellSize * 2);
        // Draw all projectiles
        for (Projectile projectile : projectiles) {
            projectile.draw(gc);
        }
    }

    /**
     * Checks if any of the Neighbor's projectiles have collided with the player.
     *
     * @param player      Player with which to check for collision
     * @param currentMaze Maze used to determine player positioning
     * @return true if collision is made, false otherwise
     */
    public boolean checkCollision(final Player player, final Maze currentMaze) {
        for (Projectile projectile : projectiles) {
            if (projectile.intersects(player, currentMaze)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Neighbor{" + "x=" + x + ", y=" + y + '}';
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Neighbor neighbor)) {
            return false;
        }
        return Double.compare(x, neighbor.x) == 0
                && Double.compare(y, neighbor.y) == 0
                && lastShotTime == neighbor.lastShotTime
                && Objects.equals(projectiles, neighbor.projectiles)
                && Objects.equals(random, neighbor.random);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, projectiles, lastShotTime, random);
    }
}
