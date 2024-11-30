package TrashPanda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void TestIsLevelCompleted() {
        LevelManager levelManager = new LevelManager();
        Player player = new Player(1, 1);
        Game game = new Game(player, levelManager);
        assertFalse(game.isLevelCompleted());
    }

    @Test
    void TestIsLevelCompletedYes() {
        LevelManager levelManager = new LevelManager();
        Player player = new Player(9, 9);
        Game game = new Game(player, levelManager);
        assertTrue(game.isLevelCompleted());
    }
}