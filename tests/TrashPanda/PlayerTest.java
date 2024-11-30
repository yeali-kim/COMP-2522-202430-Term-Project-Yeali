package TrashPanda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void TestPlayerInvalidX() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Player(-1, 0));
        assertEquals("Coordinate cannot be a negative number.", exception.getMessage());
    }

    @Test
    void TestPlayerInvalidY() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Player(0, -1));
        assertEquals("Coordinate cannot be a negative number.", exception.getMessage());
    }

    @Test
    void TestPlayerValidXY() {
        assertDoesNotThrow(() -> new Player(1, 1));
    }

    @Test
    void TestGetX() {
        Player player = new Player(20, 10);
        assertEquals(20, player.getX());
    }

    @Test
    void TestGetY() {
        Player player = new Player(20, 10);
        assertEquals(10, player.getY());
    }

    @Test
    void TestToString() {
        Player player = new Player(1, 1);
        assertEquals("Player{x-coordinate = 1.0, y-coordinate = 1.0}", player.toString());
    }
}