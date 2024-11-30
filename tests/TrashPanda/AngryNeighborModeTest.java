package TrashPanda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AngryNeighborModeTest {
    @Test
    void TestInstantiateInvalidDifficulty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AngryNeighborMode("easy"));
        assertEquals("\"easy\" is an invalid entry. Only \"Easy\" and \"Hard\" are acceptable.",
                exception.getMessage());
    }

    @Test
    void TestInstantiateEasyLevelMazeSize() {
        AngryNeighborMode angryNeighborMode = new AngryNeighborMode("Easy");
        assertEquals(5, angryNeighborMode.mazeSize);
    }

    @Test
    void TestInstantiateEasyLevelDifficulty() {
        AngryNeighborMode angryNeighborMode = new AngryNeighborMode("Easy");
        assertEquals("Easy", angryNeighborMode.difficulty);
    }

    @Test
    void TestInstantiateHardLevelMazeSize() {
        AngryNeighborMode angryNeighborMode = new AngryNeighborMode("Hard");
        assertEquals(10, angryNeighborMode.mazeSize);
    }

    @Test
    void TestInstantiateInvalidLevelDifficulty() {
        AngryNeighborMode angryNeighborMode = new AngryNeighborMode("Hard");
        assertEquals("Hard", angryNeighborMode.difficulty);
    }

    @Test
    void TestGetEasyName() {
        AngryNeighborMode angryNeighborMode = new AngryNeighborMode("Easy");
        assertEquals("Easy Angry Neighbor Mode", angryNeighborMode.getName());
    }

    @Test
    void TestGetHardName() {
        AngryNeighborMode angryNeighborMode = new AngryNeighborMode("Hard");
        assertEquals("Hard Angry Neighbor Mode", angryNeighborMode.getName());
    }

    @Test
    void TestToStringEasy() {
        AngryNeighborMode angryNeighborMode = new AngryNeighborMode("Easy");
        assertEquals("AngryNeighborMode{mazeSize=5, difficulty='Easy'}", angryNeighborMode.toString());
    }

    @Test
    void TestToStringHard() {
        AngryNeighborMode angryNeighborMode = new AngryNeighborMode("Hard");
        assertEquals("AngryNeighborMode{mazeSize=10, difficulty='Hard'}", angryNeighborMode.toString());
    }
}