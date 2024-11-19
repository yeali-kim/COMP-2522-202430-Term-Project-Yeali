package TrashPanda;
import javafx.scene.image.Image;
import java.util.*;

class ImageLoader {
    public static final Image[] frontImages = new Image[4];
    public static final Image[] leftImages = new Image[4];
    public static final Image[] rightImages = new Image[4];
    public static final Image[] backImages = new Image[4];

    public static void loadImages() {
        for (int i = 0; i < 4; i++) {
            frontImages[i] = new Image(Objects.requireNonNull(ImageLoader.class.getResourceAsStream("/images/front" + i + ".png")));
            leftImages[i] = new Image(Objects.requireNonNull(ImageLoader.class.getResourceAsStream("/images/left" + i + ".png")));
            rightImages[i] = new Image(Objects.requireNonNull(ImageLoader.class.getResourceAsStream("/images/right" + i + ".png")));
            backImages[i] = new Image(Objects.requireNonNull(ImageLoader.class.getResourceAsStream("/images/back" + i + ".png")));
        }
    }
}