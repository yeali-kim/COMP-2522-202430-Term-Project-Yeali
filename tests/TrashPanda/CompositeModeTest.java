package TrashPanda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompositeModeTest {
    @Test
    void TestInstantiateInvalidDifficulty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new CompositeMode("easy"));
        assertEquals("\"easy\" is an invalid entry. Only \"Easy\" and \"Hard\" are acceptable.",
                exception.getMessage());
    }

    @Test
    void TestInstantiateEasyLevelMazeSize() {
        CompositeMode compositeMode = new CompositeMode("Easy");
        assertEquals(5, compositeMode.mazeSize);
    }

    @Test
    void TestInstantiateEasyLevelDifficulty() {
        CompositeMode compositeMode = new CompositeMode("Easy");
        assertEquals("Easy", compositeMode.difficulty);
    }

    @Test
    void TestInstantiateHardLevelMazeSize() {
        CompositeMode compositeMode = new CompositeMode("Hard");
        assertEquals(10, compositeMode.mazeSize);
    }

    @Test
    void TestInstantiateInvalidLevelDifficulty() {
        CompositeMode compositeMode = new CompositeMode("Hard");
        assertEquals("Hard", compositeMode.difficulty);
    }

    @Test
    void TestGetEasyName() {
        CompositeMode compositeMode = new CompositeMode("Easy");
        assertEquals("Easy Composite Mode", compositeMode.getName());
    }

    @Test
    void TestGetHardName() {
        CompositeMode compositeMode = new CompositeMode("Hard");
        assertEquals("Hard Composite Mode", compositeMode.getName());
    }

    @Test
    void TestToStringEasy() {
        CompositeMode compositeMode = new CompositeMode("Easy");
        assertEquals("CompositeMode{mazeSize=5, difficulty='Easy'}", compositeMode.toString());
    }

    @Test
    void TestToStringHard() {
        CompositeMode compositeMode = new CompositeMode("Hard");
        assertEquals("CompositeMode{mazeSize=10, difficulty='Hard'}", compositeMode.toString());
    }
}