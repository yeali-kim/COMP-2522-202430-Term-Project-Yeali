package TrashPanda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelManagerTest {
    LevelManager levelManager = new LevelManager();

    @Test
    void TestInstantiateLevelManager() {
        NormalMode easyNormalMode = new NormalMode("Easy");
        assertEquals(easyNormalMode, levelManager.getCurrentMode());
    }

    @Test
    void TestGetCurrentMode() {
        NormalMode easyNormalMode = new NormalMode("Easy");
        assertEquals(easyNormalMode, levelManager.getCurrentMode());
    }

    @Test
    void TestAdvanceLevel() {
        NormalMode hardNormalMode = new NormalMode("Hard");
        levelManager.advanceLevel();
        assertEquals(hardNormalMode, levelManager.getCurrentMode());
    }
}