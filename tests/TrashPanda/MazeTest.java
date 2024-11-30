package TrashPanda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
    @Test
    void TestNegativeMaze() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Maze(-1));
        assertEquals("Maze size must be a positive integer.", exception.getMessage());
    }

    @Test
    void TestZeroMaze() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Maze(0));
        assertEquals("Maze size must be a positive integer.", exception.getMessage());
    }

    @Test
    void TestPositiveMaze() {
        assertDoesNotThrow(() -> new Maze(1));
    }

    @Test
    void TestGetMazeSize() {
        Maze maze = new Maze(5);
        assertEquals(11, maze.getMazeSize());
    }
}