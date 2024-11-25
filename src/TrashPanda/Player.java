package TrashPanda;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.*;

class Player {
    private double x;
    private double y;
    private final int cellSize;
    private Image currentImage;
    private boolean hasShield;
    private final Map<String, Integer> imageIndices = new HashMap<>();

    private String lastDirection = "S";
    private long lastUpdateTime = 0;
    private static final long IMAGE_UPDATE_DELAY = 100;

    public Player(double startX, double startY, int cellSize) {
        this.x = startX;
        this.y = startY;
        this.cellSize = cellSize;
        this.currentImage = ImageLoader.frontImages[0];
        imageIndices.put("W", 0);
        imageIndices.put("A", 0);
        imageIndices.put("S", 0);
        imageIndices.put("D", 0);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getLastDirection() {
        return lastDirection;
    }

    public int getCellSize() {
        return cellSize;
    }

    public void move(double dx, double dy, Maze maze) {
        double newX = x + dx;
        double newY = y + dy;

        if (maze.isValidMove(newX, y)) {
            x = newX;
        }
        if (maze.isValidMove(x, newY)) {
            y = newY;
        }
    }

    public void updateImage(String direction, boolean moving) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdateTime >= IMAGE_UPDATE_DELAY) {
            if (moving) {
                int index = (imageIndices.get(direction) + 1) % 4;
                imageIndices.put(direction, index);
                switch (direction) {
                    case "W":
                        currentImage = ImageLoader.backImages[index];
                        break;
                    case "A":
                        currentImage = ImageLoader.leftImages[index];
                        break;
                    case "S":
                        currentImage = ImageLoader.frontImages[index];
                        break;
                    case "D":
                        currentImage = ImageLoader.rightImages[index];
                        break;
                }
            } else {
                switch (lastDirection) {
                    case "W":
                        currentImage = ImageLoader.backImages[0];
                        break;
                    case "A":
                        currentImage = ImageLoader.leftImages[0];
                        break;
                    case "S":
                        currentImage = ImageLoader.frontImages[0];
                        break;
                    case "D":
                        currentImage = ImageLoader.rightImages[0];
                        break;
                }
            }
            lastUpdateTime = currentTime;
        }
        lastDirection = direction;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(currentImage, x * cellSize, y * cellSize, cellSize * 0.6, cellSize * 0.6);
    }
}