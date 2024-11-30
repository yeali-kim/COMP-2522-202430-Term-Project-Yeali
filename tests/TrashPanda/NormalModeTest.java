package TrashPanda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormalModeTest {
    @Test
    void TestInstantiateInvalidDifficulty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new NormalMode("easy"));
        assertEquals("\"easy\" is an invalid entry. Only \"Easy\" and \"Hard\" are acceptable.",
                exception.getMessage());
    }

    @Test
    void TestInstantiateEasyLevelMazeSize() {
        NormalMode normalMode = new NormalMode("Easy");
        assertEquals(5, normalMode.mazeSize);
    }

    @Test
    void TestInstantiateEasyLevelDifficulty() {
        NormalMode normalMode = new NormalMode("Easy");
        assertEquals("Easy", normalMode.difficulty);
    }

    @Test
    void TestInstantiateHardLevelMazeSize() {
        NormalMode normalMode = new NormalMode("Hard");
        assertEquals(10, normalMode.mazeSize);
    }

    @Test
    void TestInstantiateInvalidLevelDifficulty() {
        NormalMode normalMode = new NormalMode("Hard");
        assertEquals("Hard", normalMode.difficulty);
    }

    @Test
    void TestGetEasyName() {
        NormalMode normalMode = new NormalMode("Easy");
        assertEquals("Easy Normal Mode", normalMode.getName());
    }

    @Test
    void TestGetHardName() {
        NormalMode normalMode = new NormalMode("Hard");
        assertEquals("Hard Normal Mode", normalMode.getName());
    }

    @Test
    void TestToStringEasy() {
        NormalMode normalMode = new NormalMode("Easy");
        assertEquals("NormalMode{mazeSize=5, difficulty='Easy'}", normalMode.toString());
    }

    @Test
    void TestToStringHard() {
        NormalMode normalMode = new NormalMode("Hard");
        assertEquals("NormalMode{mazeSize=10, difficulty='Hard'}", normalMode.toString());
    }
}