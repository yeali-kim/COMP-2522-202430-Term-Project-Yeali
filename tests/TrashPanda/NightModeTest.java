package TrashPanda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NightModeTest {
    @Test
    void TestInstantiateInvalidDifficulty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new NightMode("easy"));
        assertEquals("\"easy\" is an invalid entry. Only \"Easy\" and \"Hard\" are acceptable.",
                exception.getMessage());
    }

    @Test
    void TestInstantiateEasyLevelMazeSize() {
        NightMode nightMode = new NightMode("Easy");
        assertEquals(5, nightMode.mazeSize);
    }

    @Test
    void TestInstantiateEasyLevelDifficulty() {
        NightMode nightMode = new NightMode("Easy");
        assertEquals("Easy", nightMode.difficulty);
    }

    @Test
    void TestInstantiateHardLevelMazeSize() {
        NightMode nightMode = new NightMode("Hard");
        assertEquals(10, nightMode.mazeSize);
    }

    @Test
    void TestInstantiateInvalidLevelDifficulty() {
        NightMode nightMode = new NightMode("Hard");
        assertEquals("Hard", nightMode.difficulty);
    }

    @Test
    void TestGetEasyName() {
        NightMode nightMode = new NightMode("Easy");
        assertEquals("Easy Night Mode", nightMode.getName());
    }

    @Test
    void TestGetHardName() {
        NightMode nightMode = new NightMode("Hard");
        assertEquals("Hard Night Mode", nightMode.getName());
    }

    @Test
    void TestToStringEasy() {
        NightMode nightMode = new NightMode("Easy");
        assertEquals("NightMode{mazeSize=5, difficulty='Easy'}", nightMode.toString());
    }

    @Test
    void TestToStringHard() {
        NightMode nightMode = new NightMode("Hard");
        assertEquals("NightMode{mazeSize=10, difficulty='Hard'}", nightMode.toString());
    }
}