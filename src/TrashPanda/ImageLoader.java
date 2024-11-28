package TrashPanda;

import javafx.scene.image.Image;

import java.util.Objects;

/**
 * Loads and initializes image assets from resources folder to be used in game.
 *
 * @author Yeali Kim
 * @version 2024
 */
public final class ImageLoader {
    /**
     * Number of images per character direction array.
     */
    static final int ARRAY_SIZE = 4;
    /**
     * Image representing a wall tile.
     **/
    static Image wall;
    /**
     * Image representing a floor tile.
     **/
    static Image floor;
    /**
     * Image representing a neighbor.
     **/
    static Image neighbor;
    /**
     * Array of front images for character.
     **/
    static final Image[] FRONT_IMAGES = new Image[ARRAY_SIZE];
    /**
     * Array of left images for character.
     **/
    static final Image[] LEFT_IMAGES = new Image[ARRAY_SIZE];
    /**
     * Array of right images for character.
     **/
    static final Image[] RIGHT_IMAGES = new Image[ARRAY_SIZE];
    /**
     * Array of back images for character.
     **/
    static final Image[] BACK_IMAGES = new Image[ARRAY_SIZE];

    private ImageLoader() {
    }

    /**
     * Loads images for the four directional arrays for character and specific images for
     * wall, floor, and neighbor from the images folder.
     */
    static void loadImages() {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            FRONT_IMAGES[i] = new Image(Objects.requireNonNull(
                    ImageLoader.class.getResourceAsStream("/images/front" + i + ".png")));
            LEFT_IMAGES[i] = new Image(Objects.requireNonNull(
                    ImageLoader.class.getResourceAsStream("/images/left" + i + ".png")));
            RIGHT_IMAGES[i] = new Image(Objects.requireNonNull(
                    ImageLoader.class.getResourceAsStream("/images/right" + i + ".png")));
            BACK_IMAGES[i] = new Image(Objects.requireNonNull(
                    ImageLoader.class.getResourceAsStream("/images/back" + i + ".png")));
        }
        wall = new Image(Objects.requireNonNull(
                ImageLoader.class.getResourceAsStream("/images/wall.png")));
        floor = new Image(Objects.requireNonNull(
                ImageLoader.class.getResourceAsStream("/images/floor.png")));
        neighbor = new Image(Objects.requireNonNull(
                ImageLoader.class.getResourceAsStream("/images/neighbor.png")));
    }
}
