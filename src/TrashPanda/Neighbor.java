package TrashPanda;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Neighbor {
    private double x;
    private double y;
    private final double width = 40;
    private final double height = 20;
    private final List<Projectile> projectiles;
    private long lastShotTime;
    private final Random random;

    public Neighbor(double x, double y) {
        this.x = x;
        this.y = y;
        this.projectiles = new ArrayList<>();
        this.random = new Random();
        this.lastShotTime = System.currentTimeMillis();
    }

    public void update(Player player) {
        long SHOT_DELAY = random.nextLong(1, 10) * 100;
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShotTime >= SHOT_DELAY) {
            shoot();
            lastShotTime = currentTime;
        }

        projectiles.removeIf(projectile -> {
            projectile.update();
            return projectile.isOutOfBounds();
        });
    }

    private void shoot() {
        // Random angle between 0 and 180 degrees
        double angle = random.nextDouble() * 180;
        // Random speed between 3 and 6
        double speed = random.nextDouble() * 3 + 3;
        // Convert angle to radians
        double radians = Math.toRadians(angle);
        // Calculate velocity components
        double vx = speed * Math.cos(radians);
        double vy = speed * Math.sin(radians);

        Projectile projectile = new Projectile(x + width / 2, y + height, vx, vy);
        projectiles.add(projectile);
    }

    public void draw(GraphicsContext gc, int cellSize) {
        // Draw neighbor
        gc.drawImage(ImageLoader.neighbor, x - 20, y - 40, cellSize * 2, cellSize * 2);
        // Draw all projectiles
        for (Projectile projectile : projectiles) {
            projectile.draw(gc);
        }
    }

    public boolean checkCollision(Player player) {
        double playerX = player.getX() * player.getCellSize();
        double playerY = player.getY() * player.getCellSize();
        double playerWidth = player.getCellSize() * 0.6;
        double playerHeight = player.getCellSize() * 0.6;

        for (Projectile projectile : projectiles) {
            if (projectile.intersects(playerX, playerY, playerWidth, playerHeight)) {
                return true;
            }
        }
        return false;
    }
}