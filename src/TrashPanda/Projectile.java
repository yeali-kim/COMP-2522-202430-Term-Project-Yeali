package TrashPanda;

import javafx.scene.canvas.GraphicsContext;


class Projectile {
    private double x;
    private double y;
    private final double vx;
    private final double vy;
    private static final double SIZE = 10;
    private static final int CANVAS_SIZE = 1000; // Match your canvas size

    public Projectile(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    public void update() {
        x += vx;
        y += vy;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(javafx.scene.paint.Color.RED);
        gc.fillOval(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
    }

    public boolean isOutOfBounds() {
        return x + SIZE / 2 < 0 || x - SIZE / 2 > CANVAS_SIZE ||
                y + SIZE / 2 < 0 || y - SIZE / 2 > CANVAS_SIZE;
    }

    public boolean intersects(Player player, Maze maze) {
        double mazeSize = maze.getMazeSize();
        int cellSize = player.getCellSize();

        System.out.println(mazeSize);
        System.out.println(cellSize);

        double playerX = (1000 - mazeSize * cellSize) / 2 + player.getX() * cellSize  ;
        double playerY = (1000 - mazeSize * cellSize) / 2 + player.getY() * cellSize ;

        double playerWidth = cellSize * 0.6;
        double playerHeight = cellSize * 0.6;

        //Projectile bounds
        double projectileLeft = x - SIZE / 2;
        double projectileRight = x + SIZE / 2;
        double projectileTop = y - SIZE / 2;
        double projectileBottom = y + SIZE / 2;

        // Player bounds
        double playerRight = playerX + playerWidth;
        double playerBottom = playerY + playerHeight;

        System.out.printf("Projectile: [%f, %f, %f, %f] Player: [%f, %f, %f, %f]%n",
                projectileLeft, projectileRight, projectileTop, projectileBottom,
                playerX, playerRight, playerY, playerBottom);

        return !(projectileRight < playerX || projectileLeft > playerRight ||
                projectileBottom < playerY || projectileTop > playerBottom);
    }
}