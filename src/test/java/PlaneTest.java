import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlaneTest {

    @Test
    void testGetters() {
        Plane plane = new Plane(
                "Boeing", "737", 101, 10000, 0, true
        );

        assertEquals("Boeing", plane.getMake());
        assertEquals("737", plane.getModel());
        assertEquals(101, plane.getNumber());
        assertEquals(10000, plane.getMaxAltitude());
        assertEquals(0, plane.getCurrentAltitude());
        assertTrue(plane.getLandingGearEnabled());
    }

    @Test
    void testInvalidConfigurations() {
        assertThrows(IllegalArgumentException.class,
                () -> new Plane("A", "B", 1, 50, 0, true));

        assertThrows(IllegalArgumentException.class,
                () -> new Plane("A", "B", 1, 1000, -1, true));

        assertThrows(IllegalArgumentException.class,
                () -> new Plane("A", "B", 1, 1000, 1001, false));

        assertThrows(IllegalArgumentException.class,
                () -> new Plane("A", "B", 1, 1000, 150, true));

        assertThrows(IllegalArgumentException.class,
                () -> new Plane("A", "B", 1, 1000, 50, false));
    }

    @Test
    void testEnableLandingGear() {
        Plane plane = new Plane(
                "Boeing", "737", 1, 1000, 200, false
        );

        assertFalse(plane.enableLandingGear());
        assertFalse(plane.getLandingGearEnabled());

        assertTrue(plane.changeAltitude(-100));
        assertTrue(plane.enableLandingGear());
        assertTrue(plane.getLandingGearEnabled());
    }

    @Test
    void testDisableLandingGear() {
        Plane plane = new Plane(
                "Boeing", "737", 1, 1000, 0, true
        );

        assertFalse(plane.disableLandingGear());
        assertTrue(plane.getLandingGearEnabled());

        assertTrue(plane.changeAltitude(100));
        assertTrue(plane.disableLandingGear());
        assertFalse(plane.getLandingGearEnabled());
    }

    @Test
    void testAltitudeWithGearEnabled() {
        Plane plane = new Plane(
                "Boeing", "737", 1, 1000, 0, true
        );

        assertTrue(plane.changeAltitude(50));
        assertEquals(50, plane.getCurrentAltitude());

        assertFalse(plane.changeAltitude(100));
        assertEquals(100, plane.getCurrentAltitude());

        assertTrue(plane.changeAltitude(-50));
        assertEquals(50, plane.getCurrentAltitude());

        assertFalse(plane.changeAltitude(-100));
        assertEquals(0, plane.getCurrentAltitude());
    }

    @Test
    void testAltitudeWithGearDisabled() {
        Plane plane = new Plane(
                "Boeing", "737", 1, 1000, 100, false
        );

        assertTrue(plane.changeAltitude(400));
        assertEquals(500, plane.getCurrentAltitude());

        assertFalse(plane.changeAltitude(600));
        assertEquals(1000, plane.getCurrentAltitude());

        assertTrue(plane.changeAltitude(-500));
        assertEquals(500, plane.getCurrentAltitude());

        assertFalse(plane.changeAltitude(-600));
        assertEquals(100, plane.getCurrentAltitude());
    }

    @Test
    void testInvalidAltitudeChange() {
        Plane plane = new Plane(
                "Boeing", "737", 1, 1000, 0, true
        );

        assertFalse(plane.changeAltitude(Double.NaN));
        assertFalse(plane.changeAltitude(
                Double.POSITIVE_INFINITY
        ));
        assertEquals(0, plane.getCurrentAltitude());
    }

    @Test
    void testLandingGearAt100Meters() {
        Plane plane = new Plane(
                "Boeing", "737", 1, 1000, 100, true
        );

        assertTrue(plane.disableLandingGear());
        assertTrue(plane.enableLandingGear());
        assertEquals(100, plane.getCurrentAltitude());
    }
}




