package TrashPanda;

import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a projectile that is shot from neighbor.
 * A projectile moves at a random velocity in random angle and may intersect with player.
 */
class Projectile {
    private double x;
    private double y;
    private final double vx;
    private final double vy;
    private final double projectileSize = 10;
    private final double canvasSize = TrashPandaGame.CANVAS_SIZE;

    /**
     * Constructs a new projectile with specified position and velocity.
     *
     * @param x  double representing the x-coordinate of the projectile
     * @param y  double representing the y-coordinate of the projectile
     * @param vx double representing the x-component of the projectile's velocity
     * @param vy double representing the y-component of the projectile's velocity
     */
    Projectile(final double x, final double y, final double vx, final double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    /**
     * Updates the position of the projectile based on its velocity.
     */
    public void update() {
        x += vx;
        y += vy;
    }

    /**
     * Draws the projectile on the specified graphics context.
     *
     * @param gc GraphicsContext on which to draw the projectile
     */
    public void draw(final GraphicsContext gc) {
        gc.setFill(javafx.scene.paint.Color.RED);
        gc.fillOval(x - projectileSize / 2, y - projectileSize / 2, projectileSize, projectileSize);
    }

    /**
     * Checks if the projectile is out of bounds of the game canvas.
     *
     * @return true if the projectile is out of bounds and false otherwise
     */
    public boolean isOutOfBounds() {
        return x + projectileSize / 2 < 0
                || x - projectileSize / 2 > canvasSize
                || y + projectileSize / 2 < 0
                || y - projectileSize / 2 > canvasSize;
    }

    /**
     * Checks if the projectiles intersects with the player.
     *
     * @param player Player to check for intersection
     * @param maze   Maze to calculate player positioning
     * @return true if projectile intersects with player, and false otherwise
     */
    public boolean intersects(final Player player, final Maze maze) {
        final double cellSize = TrashPandaGame.CELL_SIZE;
        final double scaleFactor = 0.6;
        double mazeSize = maze.getMazeSize();

        double playerX = (canvasSize - mazeSize * cellSize) / 2
                + player.getX() * cellSize;
        double playerY = (canvasSize - mazeSize * cellSize) / 2
                + player.getY() * cellSize;

        double playerWidth = cellSize * scaleFactor;
        double playerHeight = cellSize * scaleFactor;

        //Projectile bounds
        double projectileLeft = x - projectileSize / 2;
        double projectileRight = x + projectileSize / 2;
        double projectileTop = y - projectileSize / 2;
        double projectileBottom = y + projectileSize / 2;

        // Player bounds
        double playerRight = playerX + playerWidth;
        double playerBottom = playerY + playerHeight;

        return !(projectileRight < playerX
                || projectileLeft > playerRight
                || projectileBottom < playerY
                || projectileTop > playerBottom);
    }
}
