package TrashPanda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NeighborTest {
    @Test
    void TestNeighborInvalidX() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Neighbor(-1, 0));
        assertEquals("Coordinate cannot be a negative number.", exception.getMessage());
    }

    @Test
    void TestNeighborInvalidY() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Neighbor(0, -1));
        assertEquals("Coordinate cannot be a negative number.", exception.getMessage());
    }

    @Test
    void TestNeighborCoord() {
        assertDoesNotThrow(() -> new Neighbor(1, 1));
    }

    @Test
    void TestToString() {
        Neighbor neighbor = new Neighbor(1, 1);
        assertEquals("Neighbor{x-coordinate = 1.0, y-coordinate = 1.0}", neighbor.toString());
    }
}