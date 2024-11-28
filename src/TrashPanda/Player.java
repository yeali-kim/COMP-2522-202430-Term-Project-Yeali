package TrashPanda;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a player (trash panda) that moves around the maze.
 * Image is updated based on direction and movement.
 */
class Player {
    //delay in milliseconds between image updates for animation
    private static final long IMAGE_UPDATE_DELAY = 100;
    private double x;
    private double y;
    private final Map<String, Integer> imageIndices = new HashMap<>();
    private Image currentImage;
    private String lastDirection = "S";
    private long lastUpdateTime = 0;

    /**
     * Constructs a new player at the specified starting position on maze.
     *
     * @param startX double representing the x-coordinate of the player
     * @param startY double representing the y-coordinate of the player
     */
    Player(final double startX, final double startY) {
        this.x = startX;
        this.y = startY;
        this.currentImage = ImageLoader.FRONT_IMAGES[0];
        imageIndices.put("W", 0);
        imageIndices.put("A", 0);
        imageIndices.put("S", 0);
        imageIndices.put("D", 0);
    }

    /**
     * Gets the current x-coordinate of the player.
     *
     * @return double that represents the x-coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the current y-coordinate of the player.
     *
     * @return double that represents the y-coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the last direction of the player.
     *
     * @return String that represents the last direction of the player
     */
    public String getLastDirection() {
        return lastDirection;
    }

    /**
     * Moves the player if movement is valid within the maze.
     *
     * @param dx   double that is the change in x-coordinate
     * @param dy   double that is the change in y-coordinate
     * @param maze Maze that is used to validate the move
     */
    public void move(final double dx, final double dy, final Maze maze) {
        double newX = x + dx;
        double newY = y + dy;

        if (maze.isValidMove(newX, y)) {
            x = newX;
        }
        if (maze.isValidMove(x, newY)) {
            y = newY;
        }
    }

    /**
     * Updates the current image of the player based on movement and direction.
     * Animates the player if moving, and resets to idle if stationary.
     *
     * @param direction String that represents the player is facing
     *                  (W: Up, A: Left, S: Down, D: Right)
     * @param moving    boolean that represents whether the player is moving
     */
    public void updateImage(final String direction, final boolean moving) {
        final int arraySize = 4;
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdateTime >= IMAGE_UPDATE_DELAY) {
            if (moving) {
                int index = (imageIndices.get(direction) + 1) % arraySize;
                imageIndices.put(direction, index);
                switch (direction) {
                    case "W":
                        currentImage = ImageLoader.BACK_IMAGES[index];
                        break;
                    case "A":
                        currentImage = ImageLoader.LEFT_IMAGES[index];
                        break;
                    case "S":
                        currentImage = ImageLoader.FRONT_IMAGES[index];
                        break;
                    case "D":
                        currentImage = ImageLoader.RIGHT_IMAGES[index];
                        break;
                    default:
                        // Handle unexpected direction by defaulting to front image
                        currentImage = ImageLoader.FRONT_IMAGES[0];
                        break;
                }
            } else {
                // When player is idle, the image is the first frame based on the last direction.
                switch (lastDirection) {
                    case "W":
                        currentImage = ImageLoader.BACK_IMAGES[0];
                        break;
                    case "A":
                        currentImage = ImageLoader.LEFT_IMAGES[0];
                        break;
                    case "S":
                        currentImage = ImageLoader.FRONT_IMAGES[0];
                        break;
                    case "D":
                        currentImage = ImageLoader.RIGHT_IMAGES[0];
                        break;
                    default:
                        // Handle unexpected direction by defaulting to front image
                        currentImage = ImageLoader.FRONT_IMAGES[0];
                        break;
                }
            }
            lastUpdateTime = currentTime;
        }
        lastDirection = direction;
    }

    /**
     * Draws the player on the specified graphics context.
     *
     * @param gc GraphicsContext on which to draw the player.
     */
    public void draw(final GraphicsContext gc) {
        final double scaleFactor = 0.6;
        final double cellSize = TrashPandaGame.CELL_SIZE;

        gc.drawImage(currentImage, x * cellSize, y * cellSize,
                cellSize * scaleFactor, cellSize * scaleFactor);
    }
}
